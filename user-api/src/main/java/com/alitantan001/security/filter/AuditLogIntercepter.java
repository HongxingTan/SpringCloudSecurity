package com.alitantan001.security.filter;

import com.alitantan001.security.log.AuditLog;
import com.alitantan001.security.log.AuditLogRepository;
import com.alitantan001.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuditLogIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(3);

        AuditLog auditLog = new AuditLog();

        auditLog.setMethod(request.getMethod());

        auditLog.setPath(request.getRequestURI());

        auditLogRepository.save(auditLog);

        request.setAttribute("auditLogId", auditLog.getId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        Long auditLogId = (Long) request.getAttribute("auditLogId");

        AuditLog log = auditLogRepository.findById(auditLogId).get();

        log.setStatus(response.getStatus());

        auditLogRepository.save(log);

    }
}
