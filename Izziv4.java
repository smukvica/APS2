import java.util.*;

public class Izziv4{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int [] tabela = new int[n];
        
        for(int i = 0; i < n; i++){
            tabela[i] = sc.nextInt();
        }
        int [] stEnic;
        
        stEnic = napolniEnice(tabela);
        stEnic = urediStevila(stEnic);
        int[] urejenaTabela = sortiraj(tabela, stEnic);
        for(int i = 0; i < urejenaTabela.length; i++){
            System.out.printf("%d ", urejenaTabela[i]);
        }
        System.out.printf("\n");
    }
    
    private static int[] napolniEnice(int[] a){
        int[] stEnic = new int[32];
        for(int i = 0; i < a.length; i++){
            stEnic[getNumEnic(a[i])]++;
        }
        return stEnic;
    }
    
    private static int getNumEnic(int a){
        int c = 0;
        while(a > 0){
            c += a%2;
            a /= 2;
        }
        return c;
    }
    
    private static int[] urediStevila(int[] a){
        int temp = a[0];
        for(int i = 1; i < a.length; i++){
            a[i] += temp;
            temp = a[i];
        }
        return a;
    }
    
    private static int[] sortiraj(int[] tabela, int[] stEnic){
        int [] urejena = new int[tabela.length];
        for(int i = tabela.length - 1; i >= 0; i--){
            int a = tabela[i];
            int index = --stEnic[getNumEnic(a)];
            urejena[index] = a;
            System.out.printf("(%d,%d)\n", a, index);
        }
        return urejena;
    }
}
