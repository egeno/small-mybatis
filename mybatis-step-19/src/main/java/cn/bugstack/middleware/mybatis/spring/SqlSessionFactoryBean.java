package cn.bugstack.middleware.mybatis.spring;

import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.session.SqlSessionFactory;
import cn.bugstack.mybatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Reader;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话工厂对象
 * @date 2022/7/6
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return SqlSessionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
