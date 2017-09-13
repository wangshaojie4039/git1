package cn.imexue.ec.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swaggerUI配置类
 * 用于去掉多余的url
 * @author Administrator
 *
 */
@Configuration
public class SwaggerUIConfig {

	@Value("${base.package}")
	private String basePackage;
	
	    @Bean
	    public Docket createRestApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage(basePackage))
	                .paths(PathSelectors.any())
	                .build();
	    }

	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("swaggerui")
	                .description("swaggerui")
	                .termsOfServiceUrl("http://blog.didispace.com/")
	                .contact((Contact) null)
	                .version("1.0")
	                .build();
	    }

}
