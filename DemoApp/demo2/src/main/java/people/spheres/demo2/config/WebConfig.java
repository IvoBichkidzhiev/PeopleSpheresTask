package people.spheres.demo2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import people.spheres.demo2.constants.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(Constants.LOCALHOST_AND_PORT, Constants.LOCALHOST_IP_AND_PORT)
                .allowedMethods(HttpMethod.GET.name())
                .allowedHeaders(HttpHeaders.AUTHORIZATION);
    }

}