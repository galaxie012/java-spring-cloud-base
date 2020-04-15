package hou.tidaa.news;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class WebConfig {
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter requestLoggingFilter = new CommonsRequestLoggingFilter();
        requestLoggingFilter.setIncludeClientInfo(true);
        requestLoggingFilter.setIncludeHeaders(true);
        requestLoggingFilter.setIncludeQueryString(true);
        requestLoggingFilter.setIncludePayload(true);
        return requestLoggingFilter;
    }
}
