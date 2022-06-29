package cn.bugstack.mybatis.test.po;



public class Human {
    
    public String name;

    private int age;

    public Human(){};

    private Human(String name,int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }

    private void setAge(int age){
        this.age = age;
    }
}
