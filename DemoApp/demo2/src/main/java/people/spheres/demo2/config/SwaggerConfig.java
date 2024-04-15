package people.spheres.demo2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import people.spheres.demo2.constants.Constants;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url(Constants.LOCALHOST_AND_PORT)
                        .description("Nginx-proxied API"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .addOpenApiCustomizer(openApiCustomiser())
                .build();
    }

    private OpenApiCustomizer openApiCustomiser() {
        return openApi -> openApi.addServersItem(new Server().url(Constants.LOCALHOST_AND_PORT).description("Alternate server"));
    }
}
