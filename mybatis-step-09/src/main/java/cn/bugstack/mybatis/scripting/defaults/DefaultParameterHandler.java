package cn.bugstack.mybatis.scripting.defaults;

import cn.bugstack.mybatis.executor.parameter.ParameterHandler;
import cn.bugstack.mybatis.mapping.BoundSql;
import cn.bugstack.mybatis.mapping.MappedStatement;
import cn.bugstack.mybatis.mapping.ParameterMapping;
import cn.bugstack.mybatis.reflection.MetaObject;
import cn.bugstack.mybatis.session.Configuration;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 小傅哥，微信：fustack
 * @description 默认参数处理器
 * @date 2022/5/16
 * @github https://github.com/fuzhengwei/CodeDesignTutorials
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class DefaultParameterHandler implements ParameterHandler {

    private final MappedStatement mappedStatement;
    private final Object parameterObject;
    private BoundSql boundSql;
    private Configuration configuration;

    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }

    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        for (Map.Entry<Integer, String> parameter : parameterMappings.entrySet()) {
//            String propertyName = parameter.getValue();
//            MetaObject metaObject = configuration.newMetaObject(parameterObject);
//            Object value = metaObject.getValue(propertyName);
//
//        }
    }

}
