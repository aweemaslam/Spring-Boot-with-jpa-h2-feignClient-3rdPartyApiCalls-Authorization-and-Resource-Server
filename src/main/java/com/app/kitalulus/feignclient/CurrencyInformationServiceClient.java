package com.app.kitalulus.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.kitalulus.FeignConfig;
import com.app.kitalulus.dto.CurrencyDto;

@FeignClient(name = "restCurrencies", url = "http://data.fixer.io/api/latest", configuration = FeignConfig.class)
public interface CurrencyInformationServiceClient {
	@GetMapping
	CurrencyDto getCurrencyBySymbol(@RequestParam(value = "access_key") String access_key);
	
}
