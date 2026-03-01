public class loseWeight 
{
    //属性
    String name;
    int weight;
    //行为
    public void lose(int w)
    {
        weight=weight-w;
        System.out.println("小王体重减少了"+w+"公斤，现在体重为"+weight+"公斤");
    }


}
