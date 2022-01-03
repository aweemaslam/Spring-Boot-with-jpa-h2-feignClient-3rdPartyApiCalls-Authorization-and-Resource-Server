package com.app.kitalulus.service;

import java.util.List;

import com.app.Kitalulus.model.CurrencyAuditLog;
import com.app.kitalulus.dto.CountryDto;

public interface ICountryInformationService {
	List<CountryDto> getCounrtyInformationByName(String countryName);
	
	List<CurrencyAuditLog> getAuditLog();
}
