package kr.ac.deu.computer_engineering.Absenteeism.Management.config.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${springdoc.swagger-ui.serverUrl}")
    private String serverUrl;

    @Bean
    public GroupedOpenApi basicApi() {
        return GroupedOpenApi.builder()
                .group("docs")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springOpenAPI() {
//        SecurityScheme scheme = new SecurityScheme()
//                .type(SecurityScheme.Type.HTTP)
//                .scheme("bearer")
//                .bearerFormat("JWT")
//                .in(SecurityScheme.In.HEADER)
//                .name("Authorization");
//        SecurityRequirement securityRequirement = new SecurityRequirement().addList("jwt");
        Info info = new Info().title("동의대학교 컴퓨터공학과 소프트웨어 공학 수업 8조")
                .description("api 문서")
                .version("1.0.0");

        Server server = new Server();
        server.url(serverUrl);
        server.description("Local 서버");

        List<Server> servers = new ArrayList<>();
        servers.add(server);
        return new OpenAPI()
                .servers(servers)
                .externalDocs(new ExternalDocumentation())
//                .components(new Components().addSecuritySchemes("jwt", scheme))
//                .addSecurityItem(securityRequirement)
                .info(info);
    }
}
