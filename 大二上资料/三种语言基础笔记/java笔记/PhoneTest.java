public class PhoneTest {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.brand = "华为";
        p.price = 1999.99;
        System.out.println(p.brand);
        System.out.println(p.price);
        p.call();
        p.playGame();

        Phone p2=new Phone();
        p2.brand="苹果";
        p2.price=8999.99;
        System.out.println(p2.brand);
        System.out.println(p2.price);
        p2.call();
        p2.playGame();
    }

}
