package az.turbo.backend.configs;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${spring.contact.name}")
    private String contactName;

    @Value("${spring.contact.url}")
    private String contactUrl;

    @Value("${spring.contact.email}")
    private String contactEmail;

    @Value("${spring.project.title}")
    private String projectTitle;

    @Value("${spring.project.description}")
    private String projectDescription;

    @Value("${spring.project.version}")
    private String projectVersion;

    private final String AUTHORIZATION = "Authorization";
    private final String JWT_KEY = "JWT";
    private final String HEADER = "header";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .forCodeGeneration(true)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(jwt()))
                .globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name("lang")
                                .description("locales: [en, ru], default: en")
                                .modelRef(new ModelRef("string"))
                                .parameterType("query")
                                .required(false)
                                .build()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact(contactName, contactUrl, contactEmail);

        return new ApiInfoBuilder()
                .title(projectTitle)
                .description(projectDescription)
                .version(projectVersion)
                .contact(contact)
                .build();
    }

    private ApiKey jwt() {
        return new ApiKey(JWT_KEY, AUTHORIZATION, HEADER);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return Predicates.and(
                Predicates.not(PathSelectors.regex("/error")),
                Predicates.not(PathSelectors.regex("/users/sign-up")),
                Predicates.not(PathSelectors.regex("/users/login"))
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", null);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
    }
}