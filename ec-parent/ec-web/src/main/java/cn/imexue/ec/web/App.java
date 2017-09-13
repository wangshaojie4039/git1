package cn.imexue.ec.web;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@SpringCloudApplication
@EnableRedisHttpSession
@ComponentScan("${base.package}")
@EnableCaching
@EnableSwagger2
public class App {

	public static void main(String[] args) {
		new SpringApplication(App.class).run(args);
	}

}
