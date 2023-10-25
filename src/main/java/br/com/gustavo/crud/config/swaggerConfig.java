package br.com.gustavo.crud.config;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Crud Api")
                .version("v1")
                .description("Rest API for Web / Cloud developer challenge")
        ).externalDocs(new ExternalDocumentation()
                .description("Link to Github repository and readme")
                .url("https://github.com/GUSTAV0DEDEUS/Cloud_Web_Developer")
        );

    }
}
