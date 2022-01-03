### Requirements

* Java 11
* maven
* h2

### Goals Achieved
* exposed API to return country full name, population and list of official currencies including exchange rate to IDR `/country/{counrty-name}`
* exposed API to get currencies, exchange rate and user information searched by logged in user `/currencyAuditlog`
* used bucket4j  in HandlerInterceptor to rate limit APIs to 30(``api.rate.limiter.rpm``) requests per minute.
* integrated `https://restcountries.com/` and `https://fixer.io` using feign client.
* OAuth2 flow is implemented
* swagger documentation `http://localhost:8080/swagger-ui.html#/`