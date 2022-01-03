package com.app.kitalulus.service;

import java.util.List;

import com.app.kitalulus.dto.CountryDto;
import com.app.kitalulus.model.CurrencyAuditLog;

public interface ICountryInformationService {
	List<CountryDto> getCounrtyInformationByName(String countryName);
	
	List<CurrencyAuditLog> getAuditLog();
}
