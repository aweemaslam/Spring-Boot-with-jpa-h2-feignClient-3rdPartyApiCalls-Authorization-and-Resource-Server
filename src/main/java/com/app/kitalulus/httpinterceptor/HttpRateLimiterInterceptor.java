package com.app.kitalulus.httpinterceptor;

import java.time.Duration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.session.TooManyActiveSessionsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@Component
public class HttpRateLimiterInterceptor implements HandlerInterceptor{

	private final Bucket bucket;
    public HttpRateLimiterInterceptor(@Value("${api.rate.limiter.rpm}") Integer requestsAllowed) {
        Bandwidth limit = Bandwidth.classic(requestsAllowed, Refill.intervally(requestsAllowed, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();
    }
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (bucket.tryConsume(1)) { 
			return true;
		}
		
		response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase());
		return false;
	}

}
