package com.okta.developer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }

//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder, TokenRelayGatewayFilterFactory filterFactory) {
//        return builder.routes()
//                .route("cart", r -> r.path("/cart/**")
//                        .filters(f -> f.filter(filterFactory.apply()))
//                        .uri("lb://cart"))
//                .build();
//    }

}