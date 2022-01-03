package com.app.kitalulus.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.kitalulus.FeignConfig;
import com.app.kitalulus.dto.CountryDto;
@FeignClient(name="restcountries", url = "https://restcountries.com/v3.1/", configuration = FeignConfig.class)
public interface CountryInformationServiceClient {
	@GetMapping( value = "name/{name}")
    List<CountryDto> getCountryByName(@PathVariable String name);
}
