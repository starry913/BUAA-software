public class User 
{
    //属性
    private String username;
    private String password;
    
    //空参构造
    public User(){}
    //带参构造
    public User(String username,String password)
    {
        this.username=username;
        this.password=password;
    }
    

    //get和set方法
    //快捷键：鼠标右键，源代码操作
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
