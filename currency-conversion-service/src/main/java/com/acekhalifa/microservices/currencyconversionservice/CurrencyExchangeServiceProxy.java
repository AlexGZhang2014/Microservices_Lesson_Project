package com.acekhalifa.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8000")
//feign client url is configured in application.properties
//@FeignClient(name="currency-exchange-service")
//instead of doing the above, we route all requests thru zuul instead
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
//	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	// The below is the correct way to do it when using zuul
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from,
			@PathVariable String to);
	//Previous feign required explicit PathVariable definitions -- @PathVariable("from"), @PathVariable("to)
}