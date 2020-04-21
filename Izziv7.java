import java.util.*;

public class Izziv7 {
    private static LinkedList<Integer> prastevila = new LinkedList<Integer>();

    public static void main(String[] args){
        prastevila.add(1);
        prastevila.add(2);
        prastevila.add(3);

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        LinkedList<Integer> koreni = findP(n);

        kreatMetrix(koreni, n, koreni.getLast());
    }

    private static void kreatMetrix(LinkedList<Integer> koreni, int n, int p){
        int[][] voldemort = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int k = 0; k < n; k++){
                if(k == 0 || i == 0){
                    voldemort[i][k] = 1;
                }else{
                    voldemort[i][k] = (int)Math.pow(koreni.get(0), k) * voldemort[i - 1][k] % p;
                }
            }
        }
        printMatrik(voldemort);
    }

    private static LinkedList findP(int n){
        int p = prapra(n + 1);
        LinkedList<Integer> primitivniKoreni = pke(p, n);
        while(primitivniKoreni.size() == 0){
            p = prapra(p + 1);
            primitivniKoreni = pke(p, n);
        }
        primitivniKoreni.add(p);
        return primitivniKoreni;
    }

    private static LinkedList pke(int p, int n){
        LinkedList<Integer> kandidati = new LinkedList<>();
        LinkedList<Integer> primitivniKoreni = new LinkedList<>();
        for(int i = 0; i < p; i++){
            if(Math.pow(i, n) % p == 1){
                kandidati.add(i);
            }
        }
        for(int i = 0; i < kandidati.size(); i++){
            for(int k = 1; k < n; k++){
                if(Math.pow(kandidati.get(i), k) % p != 1){
                    if(k == n - 1) {
                        primitivniKoreni.add(kandidati.get(i));
                    }
                }else{
                    break;
                }
            }
        }
        if(primitivniKoreni.size() != 0){
            System.out.printf("%d:", p);
            for(int i = 0; i < primitivniKoreni.size(); i++){
                System.out.printf(" %d", primitivniKoreni.get(i));
            }
            System.out.printf("\n");
        }
        return primitivniKoreni;
    }

    private static int prapra(int n){
        int i = 0;
        while(i < prastevila.size()){
            if(prastevila.get(i) <= n) {
                i++;
            }else{
                return prastevila.get(i);
            }
        }
        prastevila.add(getNextPrime());
        return prastevila.getLast();
    }

    private static int getNextPrime(){
        int prime = prastevila.getLast() + 1;
        int i = 1;
        while(i < prastevila.size()) {
            if(prime % prastevila.get(i) != 0) {
                i++;
            }else{
                i = 1;
                prime++;
            }
        }
        return prime;
    }

    private static void printMatrik(int[][] matrik){
        for(int i = 0; i < matrik[0].length; i++){
            for(int k = 0; k < matrik[0].length; k++){
                System.out.printf("%2d ", matrik[i][k]);
            }
            System.out.printf("\n");
        }
    }
}
