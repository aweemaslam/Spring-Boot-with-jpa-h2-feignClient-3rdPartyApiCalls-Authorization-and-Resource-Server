package com.app.kitalulus.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.kitalulus.dto.CountryDto;
import com.app.kitalulus.dto.CurrencyDto;
import com.app.kitalulus.feignclient.CountryInformationServiceClient;
import com.app.kitalulus.feignclient.CurrencyInformationServiceClient;
import com.app.kitalulus.model.CurrencyAuditLog;
import com.app.kitalulus.model.User;
import com.app.kitalulus.repository.RepositoryCurrencyAuditLog;
import com.app.kitalulus.repository.RepositoryUser;
import com.app.kitalulus.service.ICountryInformationService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service("countryInformationServiceImpl")
public class CountryInformationServiceImpl implements ICountryInformationService {

	private static final String BASE_CURRENCY = "IDR";
	@Value("${api.fixer-io.api-key}")
	private String FIXER_IO_API_KEY;
	@Autowired
	CountryInformationServiceClient countryInformationServiceClient;
	@Autowired
	CurrencyInformationServiceClient currencyInformationServiceClient;
	DecimalFormat df = new DecimalFormat("####0.00");
	@Autowired
	RepositoryCurrencyAuditLog repositoryCurrencyAuditLog;
	@Autowired
	RepositoryUser repositoryUser;
    @Autowired
    HttpServletRequest request;

	@Override
	public List<CountryDto> getCounrtyInformationByName(String countryName) {
		List<CountryDto> countryByNames = countryInformationServiceClient.getCountryByName(countryName);
		List<CurrencyAuditLog> currencyAuditLogs = new ArrayList<>();
		User currentLoggedInUser = repositoryUser.findByPrincipalName(request.getUserPrincipal().getName());
		countryByNames.stream().forEach(country -> {
			AtomicReference<String> symbol = new AtomicReference<>();
			country.getCurrencies().fieldNames().forEachRemaining(key -> {
				symbol.getAndSet(key);
			});
			CurrencyDto currencyBySumbol = currencyInformationServiceClient.getCurrencyBySymbol(FIXER_IO_API_KEY);
			if (currencyBySumbol.getSuccess().booleanValue()) {
				Double derivedValue = currencyBySumbol.getRates().get(symbol.get());
				Double baseValue = currencyBySumbol.getRates().get(BASE_CURRENCY);

				if (derivedValue != null && baseValue != null) {
					String convertedValue =  df.format(baseValue / derivedValue);
					((ObjectNode) country.getCurrencies()).put("ExchangeRate",convertedValue);
					currencyAuditLogs.add(CurrencyAuditLog.builder().currencyName(symbol.get())
							.exchangeRate(Double.valueOf(convertedValue)).user(currentLoggedInUser).build());
				}
			}
		});
		repositoryCurrencyAuditLog.saveAll(currencyAuditLogs);
		return countryByNames;
	}

	@Override
	public List<CurrencyAuditLog> getAuditLog() {
		return repositoryCurrencyAuditLog.findAll();
	}
	
	

}
