package vn.com.saytruyen.admin_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * The type Swagger config.
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Ryo",
                        email = "buianhtai2017tq@gmail.com",
                        url = "https://github.com/buianhtai1205"
                ),
                description = "OpenApi documentation for Spring Boot",
                title = "Admin-Service",
                version = "1.0",
                license = @License(
                        name = "License name",
                        url = "https://example.com"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:0"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://buianhtai.dev"
                )
        }
//        ,
//        security = {
//                @SecurityRequirement(
//                        name = "bearerAuth"
//                )
//        }
)
//@SecurityScheme(
//        name = "bearerAuth",
//        description = "JWT auth description",
//        scheme = "bearer",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
public class SwaggerConfig {
}
