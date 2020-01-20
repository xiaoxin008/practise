package com.xiaoxin008.spring.ioc.resource;

import org.springframework.core.io.Resource;

/**
 * Resource收集器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ResourceCollector {

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
