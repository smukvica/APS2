import java.util.*;

public class Naloga2_Stevila{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String nacin = sc.nextLine();
        int baza = Integer.parseInt(sc.nextLine());
        String a = sc.nextLine();
        String b = sc.nextLine();

        switch(nacin){
            case "os":
                os(a, b, baza);
                break;
            case "dv":
                System.out.printf("%s\n", deleteZeros(dv(a, b, baza)));
                break;
            case "ka":
                System.out.printf("%s\n", deleteZeros(ka(a, b, baza)));
                break;
        }
    }

    private static void os(String x, String y, int baza){
        String vsota = "";
        int nicle = x.length() - 1;
        for(int i = 0; i < y.length(); i++){
            String znakA = y.charAt(i) + "";
            String temp = "";
            int nicle2 = y.length() - 1;
            for(int k = 0; k < x.length(); k++){
                String znakB = x.charAt(k) + "";
                String temp2 = zmnozi(znakA, znakB, baza);
                for(int j = 0; j < nicle2; j++){
                    temp2 += "0";
                }
                nicle2--;
                temp = sestej(temp2, temp, baza);
            }
            System.out.printf("%s\n", deleteZeros(temp));
            for(int j = 0; j < nicle; j++){
                temp += "0";
            }
            nicle--;
            vsota = sestej(vsota, temp, baza);
        }
        vsota = deleteZeros(vsota);
        for(int k = 0; k < vsota.length(); k++){
            System.out.printf("-");
        }
        System.out.printf("\n%s\n", vsota);
    }

    private static String dv(String a, String b, int baza){
        a = deleteZeros(a);
        b = deleteZeros(b);

        System.out.printf("%s %s\n", a, b);

        if(a.equals("0") || b.equals("0")){
            return "0";
        }

        if(a.length() == 1 || b.length() == 1){
            return zmnozi(deleteZeros(a), deleteZeros(b), baza);
        }


        int n = a.length();
        if(n < b.length()){
            n = b.length();
        }
        if(n % 2 != 0){
            n++;
        }
        a = napumpaj(a, n);
        b = napumpaj(b, n);

        String a1 = subString(a, 0);
        String a0 = subString(a, 1);
        String b1 = subString(b, 0);
        String b0 = subString(b, 1);

        String a0b0 = dv(a0, b0, baza);
        System.out.printf("%s\n", deleteZeros(a0b0));

        String a0b1 = dv(a0, b1, baza);
        System.out.printf("%s\n", deleteZeros(a0b1));

        String a1b0 = dv(a1, b0, baza);
        System.out.printf("%s\n", deleteZeros(a1b0));

        String a1b1 = dv(a1, b1, baza);
        System.out.printf("%s\n", deleteZeros(a1b1));

        String z = izDesetisko(baza, baza);
        String baza1 = z;
        int i = 1;
        while(i < n){
            baza1 = zmnozi(baza1, z, baza);
            i++;
        }
        String baza2 = z;
        i = 1;
        while(i < n / 2){
            baza2 = zmnozi(baza2, z, baza);
            i++;
        }
        String clen1 = zmnozi(baza1, a1b1, baza);

        String clen2 = sestej(a0b1, a1b0, baza);
        clen2 = zmnozi(clen2, baza2, baza);

        String clen3 = a0b0;

        String ab = sestej(clen1, clen2, baza);
        ab = sestej(ab, clen3, baza);

        return ab;
    }

    private static String ka(String a, String b, int baza){
        a = deleteZeros(a);
        b = deleteZeros(b);

        System.out.printf("%s %s\n", a, b);

        if(a.equals("0") || b.equals("0")){
            return "0";
        }

        if(a.length() == 1 || b.length() == 1){
            return zmnozi(a, b, baza);
        }


        int n = a.length();
        if(n < b.length()){
            n = b.length();
        }
        if(n % 2 != 0){
            n++;
        }
        a = napumpaj(a, n);
        b = napumpaj(b, n);

        String a1 = subString(a, 0);
        String a0 = subString(a, 1);
        String b1 = subString(b, 0);
        String b0 = subString(b, 1);

        String a0b0 = ka(a0, b0, baza);
        System.out.printf("%s\n", deleteZeros(a0b0));

        String a1b1 = ka(a1, b1, baza);
        System.out.printf("%s\n", deleteZeros(a1b1));

        String a0a1 = sestej(a0, a1, baza);
        String b0b1 = sestej(b0, b1, baza);
        String other = ka(a0a1, b0b1, baza);
        System.out.printf("%s\n", deleteZeros(other));

        String z = izDesetisko(baza, baza);
        String baza1 = z;
        int i = 1;
        while(i < n){
            baza1 = zmnozi(baza1, z, baza);
            i++;
        }
        String baza2 = z;
        i = 1;
        while(i < n / 2){
            baza2 = zmnozi(baza2, z, baza);
            i++;
        }
        String clen1 = zmnozi(baza1, a1b1, baza);

        String clen2 = odstej(other, a1b1, baza);
        clen2 = odstej(clen2, a0b0, baza);
        clen2 = zmnozi(clen2, baza2, baza);

        String clen3 = a0b0;

        String ab = sestej(clen1, clen2, baza);
        ab = sestej(ab, clen3, baza);

        return ab;
    }

    private static int dolzina(int n){
        int d = 0;
        while(n > 0){
            d++;
            n /= 10;
        }
        return d;
    }


    // v desetisko k neznam racunat v ostalih bazah kekw
    private static String znaki = "0123456789abcdefghij";

    private static int vDesetisko(char s){
        return znaki.indexOf(s);
    }

    private static String izDesetisko(int n, int baza){
        String stevilo = "";

        while(n > 0){
            int x = (int)(n % baza);
            String a = znaki.charAt(x) + "";
            stevilo = a + stevilo;
            n /= baza;
        }
        if(stevilo.equals("")){
            return "0";
        }
        return stevilo;
    }
    // konc desetiskih pomoci metod

    private static String zmnozi(String a, String b, int baza){
        String res = "";
        int nicle = 0;
        for(int i = a.length() - 1; i >= 0; i--){
            char prvi = a.charAt(i);
            int x = vDesetisko(prvi);
            String temp = "";
            int nicle2 = 0;
            for(int k = b.length() - 1; k >= 0; k--) {
                char drugi = b.charAt(k);
                int y = vDesetisko(drugi);
                int z = x * y;
                int ostanek = z % baza;
                int carry = z / baza;
                String temp2 = izDesetisko(z, baza);
                for(int j = 0; j < nicle2; j++){
                    temp2 += "0";
                }
                nicle2++;
                temp = sestej(temp, temp2, baza);
            }
            for(int j = 0; j < nicle; j++){
                temp += "0";
            }
            nicle++;
            res = sestej(res, temp, baza);
        }
        return res;
    }

    private static String sestej(String a, String b, int baza){
        String res = "";
        if(a.length() == 0){
            return b;
        }
        if(b.length() == 0){
            return a;
        }
        int n = a.length();
        if(b.length() > n){
            n = b.length();
        }
        a = napumpaj(a, n);
        b = napumpaj(b, n);

        int carry = 0;
        for(int i = n - 1; i >= 0; i--){
            int x = vDesetisko(a.charAt(i));
            int y = vDesetisko(b.charAt(i));
            int z = x + y + carry;
            int stevka = z % baza;
            carry = z / baza;
            res = izDesetisko(stevka, baza) + res;
        }
        res = izDesetisko(carry, baza) + res;
        return res;
    }

    private static String odstej(String a, String b, int baza){
        String res = "";
        
        return res;
    }

    private static String deleteZeros(String s){
        String res = "";
        int n = s.length();
        boolean prvaStevka = false;
        for(int i = 0; i < n; i++){
            if(prvaStevka == true){
                res += s.charAt(i);
            }else{
                if(s.charAt(i) != '0'){
                    res += s.charAt(i);
                    prvaStevka = true;
                }
            }
        }
        if(res.length() == 0){
            return "0";
        }
        return res;
    }

    private static String subString(String s, int position){
        int polovica = s.length() / 2;
        String a = "";
        if(position == 0){
            for(int i = 0; i < polovica; i++){
                a += s.charAt(i);
            }
        }else{
            for(int i = polovica; i < s.length(); i++){
                a += s.charAt(i);
            }
        }

        return a;
    }

    private static String napumpaj(String a, int n){
        int d = a.length();
        while(n > d){
            a = "0" + a;
            d++;
        }
        return a;
    }
}