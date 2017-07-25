package spring.cloud.sample.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.cloud.sample.annotation.RequireServiceInstance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

public class ServiceInstanceInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = Logger.getLogger(ServiceInstanceInterceptor.class.getName());

    @Autowired
    private Registration registration;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private int port;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod obj = (HandlerMethod) handler;
            RequireServiceInstance requireServiceInstance = obj.getMethodAnnotation(RequireServiceInstance.class);
            if (requireServiceInstance != null) {
                final String serviceId = registration.getServiceId();
                List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
                for (ServiceInstance serviceInstance : serviceInstances) {
                    if (serviceInstance.getPort() == port) {
                        logger.info(StringUtils.repeat("=", 80));
                        logger.info("Host : " + serviceInstance.getHost());
                        logger.info("Port : " + serviceInstance.getPort());
                        logger.info("Meatdata : " + serviceInstance.getMetadata());
                        logger.info("ServiceId : " + serviceInstance.getServiceId());
                        logger.info("Uri : " + serviceInstance.getUri());
                        logger.info(StringUtils.repeat("=", 80));
                    }
                }
            }
        }
        return true;
    }
}
