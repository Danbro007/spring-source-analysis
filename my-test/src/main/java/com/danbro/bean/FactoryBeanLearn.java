package com.danbro.bean;

import com.danbro.service.FactoryBeanService;
import com.danbro.service.FactoryBeanServiceImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Classname FactoryBeanLearn
 * @Description TODO
 * @Date 2020/6/1 10:33
 * @Author Danrbo
 */
@Component
public class FactoryBeanLearn implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new FactoryBeanServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
