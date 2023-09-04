package com.webshop.configurer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import java.util.Collections;

/**
 * Swagger configuration class
 */
@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfigurer {

    private final ResponseMessage m200 = simpleMessage(200, "Search successful");
    private final ResponseMessage m201 = simpleMessage(201, "Resource created");
    private final ResponseMessage m204put = simpleMessage(204, "Update successful");
    private final ResponseMessage m204del = simpleMessage(204, "Deletion successful");
    private final ResponseMessage m403 = simpleMessage(403, "Not authorized");
    private final ResponseMessage m404 = simpleMessage(404, "Not found");
    private final ResponseMessage m422 = simpleMessage(422, "Validation error");
    private final ResponseMessage m500 = simpleMessage(500, "Unexpected error");

    /**
     * Docket api
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.webshop.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Web Shop API",
                "Web Shop Project.",
                "version 1.0.0",
                "",
                new Contact("Mario Ariano",
                        "",
                        "marioariano@gmail.com"),
                "",
                "",
                Collections.emptyList()
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }
}
