package spring.cloud.sample.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.cloud.sample.annotation.RequireServiceInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class ServiceInstanceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(ServiceInstanceInterceptor.class.getName());

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod obj = (HandlerMethod) handler;
            RequireServiceInstance requireServiceInstance = obj.getMethodAnnotation(RequireServiceInstance.class);
            if (requireServiceInstance != null) {
                ServiceInstance instance = discoveryClient.getLocalServiceInstance();
                logger.info(StringUtils.repeat("=", 80));
                logger.info("Host : " + instance.getHost());
                logger.info("Port : " + instance.getPort());
                logger.info("Meatdata : " + instance.getMetadata());
                logger.info("ServiceId : " + instance.getServiceId());
                logger.info("Uri : " + instance.getUri());
                logger.info(StringUtils.repeat("=", 80));
            }
        }
        return true;
    }
}
