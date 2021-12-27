package cn.sy.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author joy joe
 * @date 2021/12/27 下午8:16
 * @DESC TODO
 */
@ImportResource(locations = {"classpath*:spring-*.xml", "classpath*:*/spring-*.xml"})
@SpringBootApplication(exclude = {SessionAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableAsync
@MapperScan(basePackages = {"cn.sy.*.dao"})
public class SyMicroServiceDbApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SyMicroServiceDbApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SyMicroServiceDbApplication.class);
    }
}
