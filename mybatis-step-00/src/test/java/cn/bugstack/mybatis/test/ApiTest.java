package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.test.dao.IActivityDao;
import cn.bugstack.mybatis.test.dao.IUserDao;
import cn.bugstack.mybatis.test.po.Activity;
import cn.bugstack.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 单元测试，源码对照测试类
 * @date 2022/3/26
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2. 开启 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IActivityDao dao = sqlSession.getMapper(IActivityDao.class);

        // 4. 测试验证
        Activity res = dao.queryActivityById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(res));
    }

    @Test
    public void test_SqlSessionFactory_Annotation() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource-annotation.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2. 开启 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        List<User> users = userDao.queryUserInfoList();
        logger.info("测试结果：{}", JSON.toJSONString(users));
    }

}
