package cn.bugstack.mybatis.test;

import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.reflection.MetaObject;
import cn.bugstack.mybatis.reflection.SystemMetaObject;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;
import cn.bugstack.mybatis.session.SqlSessionFactoryBuilder;
import cn.bugstack.mybatis.test.dao.IUserDao;
import cn.bugstack.mybatis.test.po.User;
import com.alibaba.fastjson.JSON;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 单元测试
 * @date 2022/3/26
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ApiTest {

    private Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_SqlSessionFactory() throws IOException {
        // 1. 从SqlSessionFactory中获取SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 2. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 3. 测试验证
        User user = userDao.queryUserInfoById(1L);
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }

    @Test
    public void test_reflection() {
        //第一次读取
        Teca teca = new Teca();

        List<Teca.Stu> objects = new ArrayList<>();
        objects.add(new Teca.Stu());
        teca.setName("lili");
        teca.setStus(objects);

        MetaObject metaObject = SystemMetaObject.forObject(teca);
        System.out.println("getGetterNames：" + JSON.toJSONString(metaObject.getGetterNames()));
        System.out.println("getSetterNames：" + JSON.toJSONString(metaObject.getSetterNames()));
        System.out.println("name的get方法返回值：" + JSON.toJSONString(metaObject.getGetterType("name")));
        System.out.println("stus的set方法参数值：" + JSON.toJSONString(metaObject.getGetterType("stus")));
        System.out.println("name的hasGetter：" + metaObject.hasGetter("name"));
        // 出现：UnsupportedOperationException异常
        // System.out.println("stus.id(属性为集合)的hasGetter：" + metaObject.hasGetter("stus.id"));
        System.out.println("stu.id（属性为对象）的hasGetter：" + metaObject.hasGetter("stu.id"));
        System.out.println("获取name的属性值：" + metaObject.getValue("name"));
        // 重新设置属性值
        metaObject.setValue("name", "huahua");
        System.out.println("设置name的属性值：" + metaObject.getValue("name"));
        // 设置属性（集合）的元素值
        metaObject.setValue("stus[0].id", "001");
        System.out.println("获取stus集合的第一个元素的属性值：" + JSON.toJSONString(metaObject.getValue("stus[0].id")));
        System.out.println("对象的序列化：" + JSON.toJSONString(teca));
    }

    static class Teca {

        private String name;

        private double price;

        private List<Stu> stus;

        private Stu stu;

        public static class Stu {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<Stu> getStus() {
            return stus;
        }

        public void setStus(List<Stu> stus) {
            this.stus = stus;
        }

        public Stu getStu() {
            return stu;
        }

        public void setStu(Stu stu) {
            this.stu = stu;
        }
    }

}
