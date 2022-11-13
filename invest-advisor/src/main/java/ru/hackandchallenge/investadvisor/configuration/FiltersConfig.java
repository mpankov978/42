package ru.hackandchallenge.investadvisor.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hackandchallenge.investadvisor.filters.AuthFilter;
import ru.hackandchallenge.investadvisor.infrastructure.ClientComponent;
import ru.hackandchallenge.investadvisor.services.CheckAuthService;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> loggingFilter(
            CheckAuthService checkAuthService,
            ClientComponent clientComponent) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthFilter(checkAuthService, clientComponent));
        registrationBean.addUrlPatterns("/operations/*", "/collectors/*", "/info/*", "/notifications/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
