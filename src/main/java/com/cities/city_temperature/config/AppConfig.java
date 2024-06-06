package com.cities.city_temperature.config;

import com.cities.city_temperature.Constants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig implements Constants {


    @Bean
    RestTemplate restTemplate() { return new RestTemplate(); }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title(CITY_API)
                .version(SWAGGER_INFO_VERSION)
                .description(SWAGGER_DESCRIPTION));
    }
}
