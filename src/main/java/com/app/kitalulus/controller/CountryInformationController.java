package com.app.kitalulus.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.kitalulus.dto.CountryDto;
import com.app.kitalulus.service.ICountryInformationService;
import com.app.kitalulus.service.impl.CountryInformationServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/country")
@Api(value = "Country Information Controller", tags = "Get Country Information")
public class CountryInformationController {

	@Autowired
	ICountryInformationService countryInformationServiceImpl;

	@GetMapping("/{counrty-name}")
	@ApiOperation(value = "Get Country Information By country Name", notes = "This method returns the full name, population, and a list of its official currencies including the current exchange rate to IDR", response = CountryDto.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = CountryDto.class),
			@ApiResponse(code = 429, message = "Too Many Requets", response = String.class) })
	public ResponseEntity<?> getUser(@Valid @NotBlank @PathVariable("counrty-name") String countryName) {

		return new ResponseEntity<>(countryInformationServiceImpl.getCounrtyInformationByName(countryName),
				HttpStatus.OK);
	}
}
