public class GirlFriend {
    private String name;
    private int age;
    private String gender;

    //对于私有化成员变量，提供公共的get和set方法
    //set方法：给成员变量赋值
    public void setName(String n){
        name=n;
    }
    public void setAge(int a){
        if(a>=18&&a<=50){
            age=a;
        }else{
            System.out.println("年龄不合法");
        }
    }
    public void setGender(String g){
        gender=g;
    }
    //get方法：获取成员变量的值
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
}
