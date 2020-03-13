import java.util.*;
public class Izziv2{
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		int stElementov = sc.nextInt();
		int[] tabela = new int[stElementov];
		for(int i = 0; i < stElementov; i++){
			tabela[i] = sc.nextInt();
		}
		zgradiHeap(tabela);
		urediHeap(tabela);
		printTabela(tabela, stElementov);
	}

	static void printTabela(int[] a, int d){
		int stEle = 1;
		int stNivo = 1;
		for(int i = 0; i < d; i++){
            if(stEle == 0){
                System.out.print(" | ");
                stEle = 1;
                for(int k = 0; k < stNivo; k++){
                    stEle *= 2;
                }
                stNivo++;
            }
			System.out.print(a[i] + " ");
			stEle--;
		}
		System.out.print("\n");
	}

	static void urediHeap(int[] a){
		int n = a.length;

		for(int i = n-1; i >= 0; i--){
			int temp = a[i];
			a[i] = a[0];
			a[0] = temp;

			pogrezni(a, 0, i);
			printTabela(a, i);
		}
	}

	static void pogrezni(int[] a, int i, int dolzKopice){
		int naj = i;
		int levi = 2*i + 1;
		int desni = 2*i + 2;

		if(levi < dolzKopice && a[levi] > a[naj] ){
			naj = levi;
		}

		if(desni < dolzKopice && a[desni] > a[naj]){
			naj = desni;
		}

		if(naj != i){
			int temp = a[naj];
			a[naj] = a[i];
			a[i] = temp;

			pogrezni(a, naj, dolzKopice);
		}
	}
	
	static void zgradiHeap(int[] a){
        int n = a.length;
        for(int i = n / 2 - 1; i >= 0; i--){
            pogrezni(a, i, n);
        }
        printTabela(a, n);
	}
}
