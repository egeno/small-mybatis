package cn.bugstack.mybatis.test;


import cn.bugstack.mybatis.test.po.Human;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestReflect {

    @Test
    public void test() throws NoSuchMethodException {
        Class clazz  = Human.class;
        final Constructor[] constructors = clazz.getDeclaredConstructors();

        for (Constructor<Human> constructor:constructors){
            System.out.println(constructor.getName()+"构造器的isAccessible()值为:"+constructor.isAccessible());
        }

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods){
            if (!Object.class.equals(method.getDeclaringClass())) {
                System.out.println(method.getName() + "方法的isAccessible()值为：" + method.isAccessible());
            }
        }
    }
}
