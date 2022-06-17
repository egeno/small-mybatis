package cn.bugstack.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @author 小傅哥，微信：fustack
 * @description 映射器代理类
 * @date 2022/3/26
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private Map<String, String> sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Object提供的例如toString或者hashCode等方法是不需要被代理的，所以这里加个判断
        if (Object.class.equals(method.getDeclaringClass())) {
            //直接调用，不做处理
            return method.invoke(this, args);
        } else {
            return "你的被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }






    //测试反射相关方法，与mybatis无关
    public String test(){
        return "test";
    }


    //Object定义的方法，在反射获取对应的Class对象里的Method的时候，这个Method里关联的class是Object.class
    //反之，则Method里关联的class都是本类class
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MapperProxy mapperProxy = new MapperProxy(new HashMap<>(), Lock.class);
        Class<? extends MapperProxy> demoClass = mapperProxy.getClass();

        //object基类中定义的方法
        final Method toStringMethod = demoClass.getMethod("toString");
        final Class<?> declaringClass = toStringMethod.getDeclaringClass();
        final String toString = (String)toStringMethod.invoke(mapperProxy);
        System.out.println(toString);

        //自己本类定义的方法
        final Method testMethod = demoClass.getMethod("test");
        final Class<?> declaringClass1 = testMethod.getDeclaringClass();
        final String testString = (String)testMethod.invoke(mapperProxy);
        System.out.println(testString);

        //实现接口定义的方法
        final Method invokeMethod = demoClass.getMethod("invoke", Object.class, Method.class, Object[].class);
        final Class<?> declaringClass2 = invokeMethod.getDeclaringClass();
        final Object testInvoke = invokeMethod.invoke(demoClass);


    }

}
