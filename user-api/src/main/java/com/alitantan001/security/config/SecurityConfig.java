package com.alitantan001.security.config;

import com.alitantan001.security.filter.AclInterceptor;
import com.alitantan001.security.filter.AuditLogIntercepter;
import com.alitantan001.security.log.AuditLog;
import com.alitantan001.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private AuditLogIntercepter auditLogIntercepter;

    @Autowired
    private AclInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auditLogIntercepter);
        registry.addInterceptor(aclInterceptor);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> {
            ServletRequestAttributes servletRequestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            UserInfo userInfo = (UserInfo) servletRequestAttributes.getRequest().getSession().getAttribute("user");
            String username = null;
            if (userInfo != null) {
                username = userInfo.getUsername();
            }
            return Optional.ofNullable(username);
        };

    }

}
