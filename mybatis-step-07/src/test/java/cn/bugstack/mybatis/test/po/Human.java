package cn.bugstack.mybatis.test.po;



public class Human {
    
    public String name;

    private int age;

    public Human(){};

    private Human(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName(String name){
        return name;
    }

    private int getAge(int age){
        return age;
    }
}
