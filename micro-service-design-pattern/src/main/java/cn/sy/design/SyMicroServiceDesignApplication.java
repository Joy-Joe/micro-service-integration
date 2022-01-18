package cn.sy.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author joy joe
 * @date 2022/1/18 下午10:27
 * @DESC TODO
 */
@ImportResource(locations = {"classpath*:spring-*.xml", "classpath*:*/spring-*.xml"})
@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
@EnableAsync
public class SyMicroServiceDesignApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SyMicroServiceDesignApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SyMicroServiceDesignApplication.class);
    }
}
