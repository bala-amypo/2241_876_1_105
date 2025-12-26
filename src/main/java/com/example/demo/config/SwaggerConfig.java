// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 // You need to change the port as per your server
//                 .servers(List.of(
//                         new Server().url("https://9131.32procr.amypo.ai/")
//                 ));
//         }
// }
// // package com.example.demo.config;

// // import io.swagger.v3.oas.annotations.*;
// // import io.swagger.v3.oas.annotations.info.Info;
// // import io.swagger.v3.oas.annotations.security.*;
// // import org.springframework.context.annotation.Configuration;

// // @Configuration
// // @OpenAPIDefinition(
// //         info = @Info(title = "Digital Visitor Management API", version = "1.0"),
// //         security = @SecurityRequirement(name = "bearerAuth")
// // )
// // @SecurityScheme(
// //         name = "bearerAuth",
// //         type = SecuritySchemeType.HTTP,
// //         scheme = "bearer",
// //         bearerFormat = "JWT"
// // )
// // public class SwaggerConfig {}





// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import java.util.List;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("Supplier Diversity Tracker")
//                         .version("1.0")
//                         .description("API documentation for AmyPO test cases")
//                 )
//                 .servers(List.of(
//                         new Server().url("https://9126.32procr.amypo.ai/")
//                 ));
//     }
// }




package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Supplier Diversity Tracker")
                        .version("1.0")
                        .description("API documentation for AmyPO test cases")
                )
                .servers(List.of(
                        new Server().url("https://9126.32procr.amypo.ai/")
                ))
                // 🔐 Enable Authorize button
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(
                        new Components()
                                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}