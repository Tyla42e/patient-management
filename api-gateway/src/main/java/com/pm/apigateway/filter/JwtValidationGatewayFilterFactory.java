package com.pm.apigateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private static final Logger log = LoggerFactory.getLogger(JwtValidationGatewayFilterFactory.class);
    private final WebClient webClient;

    public JwtValidationGatewayFilterFactory(WebClient.Builder wclientBuilder, @Value("${auth.service.url}") String authServiceUrl) {

        log.info("Base URL is {}", authServiceUrl);
        this.webClient = wclientBuilder.baseUrl(authServiceUrl).build();

    }
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            log.info("Request Host: {}",exchange.getRequest().getURI() );
            log.info("Request Path: {}",exchange.getRequest().getPath() );
            log.info("Request Host: {}",exchange.getRequest().getLocalAddress() );
            
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            log.info("Token value: {}",token);
            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
            
            log.info("Valid Token. Calling Validation URL");
            return webClient.get()
                .uri("/validate")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .toBodilessEntity()
                .then(chain.filter(exchange));
        };
    }
    
}
