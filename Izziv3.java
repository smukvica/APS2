import java.util.*;

public class Izziv3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        int[] a = new int[n];
        int[] b = new int[m];
        
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }
        
        for(int i = 0; i < m; i++){
            b[i] = sc.nextInt();
        }
        
        int i = 0;
        int j = 0;
        int[] c = new int[n+m];
        while(i < n && j < m){
            if(a[i] <= b[j]){
                System.out.printf("a");
                c[i+j] = a[i];
                i++;
            }
            else{
                System.out.printf("b");
                c[i+j] = b[j];
                j++;
            }
        }
        while(i < n){
            System.out.printf("a");
            c[i+j] = a[i];
            i++;
        }
        while(j < m){
            System.out.printf("b");
            c[i+j] = b[j];
            j++;
        }
        System.out.printf("\n");
        for(int k = 0; k < c.length; k++){
            System.out.printf("%d ", c[k]);
        }
        System.out.printf("\n");
    }
}
