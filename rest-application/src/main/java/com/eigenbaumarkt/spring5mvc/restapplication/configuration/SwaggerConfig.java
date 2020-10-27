package com.eigenbaumarkt.spring5mvc.restapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

// enables OpenApi in project
// @EnableSwagger2
// replaced, see: https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
// @EnableSwagger2WebMvc
// replaced and auto activated with @EnableWebMVC, see: https://github.com/springfox/springfox (19.10.2020)
// @EnableWebMvc -- not working, switching back :-(

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    // Docket is OpenAPI-Configuration-Object
    @Bean
    public Docket openApi() {

        // effects how the JSON-Output in "/v2/api-docs" is formulated
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // .apis(RequestHandlerSelectors.basePackage("com.eigenbaumarkt.spring5mvc.restapplication.controllers"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    // improve Swagger default Metadata
    private ApiInfo metaData() {

        // springfox.documentation.service.Contact
        Contact contact = new Contact("Jochen Gebsattel", "https://www.gebsattel.rocks", "jochen@gebsattel.rocks");

                return new ApiInfo(
                        "JHipster Guru",
                        "but first - Spring framework 5: Beginner to Guru",
                        "1.0",
                        "Terms of service: just learning, just kidding!",
                        contact,
                        "Apache License Version 2.0",
                        "https://www.apache.org/license/LICENSE-2.0",
                        new ArrayList<>());
    }

    // also: https://editor.swagger.io/ is an editor - like apiary.io - to follow the API-first-pattern.
    // HINT:
    // - localhost:8080/api/v2/api-docs can be copied into https://editor.swagger.io to test and expand the own custom API
    // - platform can also be examined with command line, e.g.: "curl -X GET "https://petstore.swagger.io/v2/pet/findByStatus?status=pending" -H  "accept: application/json" " -
    //   we see, they are running an example of Petstore...

}
