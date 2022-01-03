package com.app.kitalulus.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private Double exchangeRate;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
   	private String base;
    @JsonInclude(JsonInclude.Include.NON_NULL)
	private Map<String,Double> rates; 
}
