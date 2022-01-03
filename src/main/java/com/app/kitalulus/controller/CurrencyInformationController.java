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
import com.app.kitalulus.model.CurrencyAuditLog;
import com.app.kitalulus.service.ICountryInformationService;
import com.app.kitalulus.service.impl.CountryInformationServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/currencyAuditlog")
@Api(value = "Currency Information Controller", tags = "Get Currency Information")
public class CurrencyInformationController {

	@Autowired
	ICountryInformationService countryInformationServiceImpl;

	@GetMapping
	@ApiOperation(value = "Get all currency audit log", notes = "This method returns currency, user and exchange rate searched by all users", response = CurrencyAuditLog.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = CountryDto.class),
			@ApiResponse(code = 429, message = "Too Many Requets", response = String.class) })
	public ResponseEntity<?> getcurrencyAuditlog() {

		return new ResponseEntity<>(countryInformationServiceImpl.getAuditLog(),
				HttpStatus.OK);
	}
}
