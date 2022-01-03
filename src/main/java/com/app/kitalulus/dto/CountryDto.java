package com.app.kitalulus.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private CountryNameDto name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private Long population;
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private List<CurrencyDto> officialCurrencies;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private JsonNode currencies;
}
