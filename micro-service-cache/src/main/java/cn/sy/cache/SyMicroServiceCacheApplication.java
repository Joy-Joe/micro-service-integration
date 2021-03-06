package cn.sy.cache;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@ImportResource(locations = {"classpath*:spring-*.xml", "classpath*:*/spring-*.xml"})
@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
@EnableAsync
public class SyMicroServiceCacheApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SyMicroServiceCacheApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SyMicroServiceCacheApplication.class);
    }
}
