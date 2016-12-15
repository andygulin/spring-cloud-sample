package spring.cloud.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import spring.cloud.sample.interceptor.ServiceInstanceInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(serviceInstanceInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public ServiceInstanceInterceptor serviceInstanceInterceptor() {
        return new ServiceInstanceInterceptor();
    }
}
