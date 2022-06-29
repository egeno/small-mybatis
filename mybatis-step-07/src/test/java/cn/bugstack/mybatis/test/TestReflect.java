package cn.bugstack.mybatis.test;


import cn.bugstack.mybatis.test.po.Human;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {

    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz  = Human.class;
        final Constructor[] constructors = clazz.getDeclaredConstructors();
        final Constructor constructor = clazz.getConstructor();
        Human human = (Human) constructor.newInstance();

//        for (Constructor<Human> constructor:constructors){
//            System.out.println(constructor.getName()+"构造器的isAccessible()值为:"+constructor.isAccessible());
//            human = (Human) constructor.newInstance();
//        }

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods){
            if (!Object.class.equals(method.getDeclaringClass())) {
                System.out.println(method.getName() + "方法的isAccessible()值为：" + method.isAccessible());

                    try {
                        if (method.getName().equals("getName")) {
                            System.out.println(method.invoke(human, "zhangsan"));
                        }
                        if (method.getName().equals("getAge")){
                            System.out.println(method.invoke(human,10));
                        }
                    } catch (IllegalAccessException e) {
                        System.out.println(e.getMessage());
                    }

            }
        }
    }
}
