import java.util.Scanner;//导包
import java.util.Arrays; 
public class test {
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Integer []a=new Integer[105];
        int n=sc.nextInt();
        for(int i=1;i<=n;i++)
        {
            a[i]=sc.nextInt();
        }
        Arrays.sort(a,1,n+1,(x,y)->y-x);
        for(int i=1;i<=n;i++) System.out.printf("%d ",a[i]);

    }
}
