import java.util.*;

public class Naloga1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        String [] argumenti = line.split(" ");

        int[] tabela = new int[Integer.parseInt(argumenti[3])];

        for(int i = 0; i < tabela.length; i++){
            tabela[i] = sc.nextInt();
        }
        boolean smer;
        if(argumenti[2].equals("up")){
            smer = true;
        }
        else{
            smer = false;
        }

        switch(argumenti[0]){
            case "trace":
                trace(argumenti, tabela, smer);
                break;
            case "count":
                count(argumenti, tabela, smer);
                break;
        }
    }

    private static void printTabela(int[] tabela, int zacetek, int konec){
        for(int i = zacetek; i < konec; i++){
            System.out.printf("%d ", tabela[i]);
        }
    }

    private static void trace(String [] argumenti, int[] tabela, boolean smer){
        switch(argumenti[1]){
            case "bs":
                for(int i = 0; i < tabela.length; i++){
                    printTabela(tabela, 0, i);
                    System.out.printf("| ");
                    printTabela(tabela, i, tabela.length);
					bs(tabela, i, smer);
                    System.out.printf("\n");
                }
                break;
            case "ss":
                System.out.printf("| ");
                printTabela(tabela, 0, tabela.length);
                System.out.printf("\n");

                for(int i = 0; i < tabela.length - 1; i++){
                    ss(tabela, i, smer);
                    printTabela(tabela, 0, i + 1);
                    System.out.printf("| ");
                    printTabela(tabela, i + 1, tabela.length);
                    System.out.printf("\n");
                }
                break;
			case "is":
				for(int i = 1; i < tabela.length; i++){
					printTabela(tabela, 0, i);
					System.out.printf("| ");
					printTabela(tabela, i, tabela.length);
					System.out.printf("\n");
					is(tabela, i, smer);
				}
				printTabela(tabela, 0, tabela.length);
				System.out.printf("| \n");
				break;
			case "hs":
				zgradiHeap(tabela, smer);
				printHeap(tabela, tabela.length);
				for(int i = tabela.length - 1; i > 0; i--){
					urediHeap(tabela, i, smer);
					printHeap(tabela, i);
				}
				break;
			case "qs":
				qs(tabela, 0, tabela.length - 1, smer, true);
				break;
			case "ms":
				ms(tabela, 0, tabela.length - 1, smer, true);
				break;
			case "cs":
				cs(tabela, smer);
				break;
			case "rs":
				rs(tabela, smer);
				break;
        }
    }

    private static void count(String [] argumenti, int[] tabela, boolean smer){
        int stPrimerjav = 0;
        int stPrirejanj = 0;
		int[] a = new int[2];
        switch(argumenti[1]){
            case "bs":
                for(int i = 0; i < tabela.length - 1; i++){
                    a = bs(tabela, i, smer);
                    stPrimerjav += a[0];
                    stPrirejanj += a[1];
                }
                System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

                stPrimerjav = 0;
                stPrirejanj = 0;
                for(int i = 0; i < tabela.length - 1; i++){
                    a = bs(tabela, i, smer);
                    stPrimerjav += a[0];
                    stPrirejanj += a[1];
                }
                System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

                stPrimerjav = 0;
                stPrirejanj = 0;
                for(int i = 0; i < tabela.length - 1; i++){
                    a = bs(tabela, i, !smer);
                    stPrimerjav += a[0];
                    stPrirejanj += a[1];
                }
                System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);
                break;
            case "ss":
                for(int i = 0; i < tabela.length - 1; i++){
                    a = ss(tabela, i, smer);
                    stPrimerjav += a[0];
                    stPrirejanj += a[1];
                }
                System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

                stPrimerjav = 0;
                stPrirejanj = 0;
                for(int i = 0; i < tabela.length - 1; i++){
                    a = ss(tabela, i, smer);
                    stPrimerjav += a[0];
                    stPrirejanj += a[1];
                }
                System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

                stPrimerjav = 0;
                stPrirejanj = 0;
                for(int i = 0; i < tabela.length - 1; i++){
                    a = ss(tabela, i, !smer);
                    stPrimerjav += a[0];
                    stPrirejanj += a[1];
                }
                System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);
			case "is":
				for(int i = 0; i < tabela.length; i++){
					a = is(tabela, i, smer);
					stPrimerjav += a[0];
					stPrirejanj += a[1];
				}
				System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

				stPrimerjav = 0;
				stPrirejanj = 0;
				for(int i = 0; i < tabela.length; i++){
					a = is(tabela, i, smer);
					stPrimerjav += a[0];
					stPrirejanj += a[1];
				}
				System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

				stPrimerjav = 0;
				stPrirejanj = 0;
				for(int i = 0; i < tabela.length; i++){
					a = is(tabela, i, !smer);
					stPrimerjav += a[0];
					stPrirejanj += a[1];
				}
				System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);
				break;
			case "hs":
				a = zgradiHeap(tabela, smer);
				stPrimerjav = a[0];
				stPrirejanj = a[1];
				for(int i = tabela.length - 1; i > 0; i--){
					a = urediHeap(tabela, i, smer);
					stPrimerjav += a[0];
					stPrirejanj += a[1];
				}
				System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

				a = zgradiHeap(tabela, smer);
				stPrimerjav = a[0];
				stPrirejanj = a[1];
				for(int i = tabela.length - 1; i > 0; i--){
					a = urediHeap(tabela, i, smer);
					stPrimerjav += a[0];
					stPrirejanj += a[1];
				}
				System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);

				a = zgradiHeap(tabela, !smer);
				stPrimerjav = a[0];
				stPrirejanj = a[1];
				for(int i = tabela.length - 1; i > 0; i--){
					a = urediHeap(tabela, i, !smer);
					stPrimerjav += a[0];
					stPrirejanj += a[1];
				}
				System.out.printf("%d %d\n", stPrimerjav, stPrirejanj);
				break;
			case "qs":
				a = qs(tabela, 0, tabela.length - 1, smer, false);
				System.out.printf("%d %d\n", a[0], a[1]);
				a = qs(tabela, 0, tabela.length - 1, smer, false);
				System.out.printf("%d %d\n", a[0], a[1]);
				a = qs(tabela, 0, tabela.length - 1, !smer, false);
				System.out.printf("%d %d\n", a[0], a[1]);
				break;
			case "ms":
				a = ms(tabela, 0, tabela.length - 1, smer, false);
				System.out.printf("%d %d\n", a[0], a[1]);
				a = ms(tabela, 0, tabela.length - 1, smer, false);
				System.out.printf("%d %d\n", a[0], a[1]);
				a = ms(tabela, 0, tabela.length - 1, !smer, false);
				System.out.printf("%d %d\n", a[0], a[1]);
				break;
        }
    }

	// bubble
    private static int[] bs(int[] tabela, int index, boolean smer){
        int stPrimerjav = 0;
        int stPrirejanj = 0;
        if(smer){
            for(int i = tabela.length - 1; i > index; i--){
                if(tabela[i] < tabela[i - 1]){
                    int temp = tabela[i];
                    tabela[i] = tabela[i - 1];
                    tabela[i - 1] = temp;
                    stPrirejanj += 3;
                }
                stPrimerjav++;
            }
        }else{
            for(int i = tabela.length - 1; i > index; i--){
                if(tabela[i] > tabela[i - 1]){
                    int temp = tabela[i];
                    tabela[i] = tabela[i - 1];
                    tabela[i - 1] = temp;
                    stPrirejanj += 3;
                }
                stPrimerjav++;
            }
        }
        int[] a = {stPrimerjav, stPrirejanj};
        return a;
    }

	// selection
    private static int[] ss(int[] tabela, int index, boolean smer){
        int stPrimerjav = 0;
        int stPrirejanj = 0;
        if(smer){
            int najmanjsi = tabela[index];
            int indexNajmanjsega = index;
            for(int i = index + 1; i < tabela.length; i++){
                if(tabela[i] < tabela[indexNajmanjsega]){
                    najmanjsi = tabela[i];
                    indexNajmanjsega = i;
                }
                stPrimerjav++;
            }
            tabela[indexNajmanjsega] = tabela[index];
            tabela[index] = najmanjsi;
            stPrirejanj += 3;
        }else{
            int najvecji = tabela[index];
            int indexNajvecjega = index;
            for(int i = index + 1; i < tabela.length; i++){
                if(tabela[i] > tabela[indexNajvecjega]){
                    najvecji = tabela[i];
                    indexNajvecjega = i;
                }
                stPrimerjav++;
            }
            tabela[indexNajvecjega] = tabela[index];
            tabela[index] = najvecji;
            stPrirejanj += 3;
        }
        int [] a = {stPrimerjav, stPrirejanj};
        return a;
    }

	// insert
    private static int[] is(int[] tabela, int index, boolean smer){
        int stPrimerjav = 0;
        int stPrirejanj = 0;
		int i = index - 1;
		int vrednost = tabela[index];
		if(smer){
			while(i >= 0){
				if(tabela[i] > vrednost){
					tabela[i + 1] = tabela[i];
					stPrirejanj += 3;
				}else{
					stPrimerjav++;
					break;
				}
				stPrimerjav++;
				i--;
			}
			tabela[i + 1] = vrednost;
		}else{
			while(i >= 0){
				if(tabela[i] < vrednost){
					tabela[i + 1] = tabela[i];
					stPrirejanj += 3;
				}else{
					stPrimerjav++;
					break;
				}
				stPrimerjav++;
				i--;
			}
			tabela[i + 1] = vrednost;
		}
        int[] a = {stPrimerjav, stPrirejanj};
        return a;
    }

	// heap functions ----------------------------------------------------------
	private static int[] pogrezni(int[] tabela, int i, int dolzKopice, boolean smer){
		int stPrimerjav = 0;
		int stPrirejanj = 0;
		int naj = i;
		int l = 2*i + 1;
		int d = 2*i + 2;
		int[] a = new int[2];
		if(smer){
			if(l < dolzKopice){
				if(tabela[l] > tabela[naj]){
					naj = l;
				}
				stPrimerjav++;
			}
			if(d < dolzKopice){
				if(tabela[d] > tabela[naj]){
					naj = d;
				}
				stPrimerjav++;
			}
			if(naj != i){
				int temp = tabela[naj];
				tabela[naj] = tabela[i];
				tabela[i] = temp;
				stPrirejanj += 3;
				a = pogrezni(tabela, naj, dolzKopice, smer);
				stPrimerjav += a[0];
				stPrirejanj += a[1];
			}
		}else{
			if(l < dolzKopice){
				if(tabela[l] < tabela[naj]){
					naj = l;
				}
				stPrimerjav++;
			}
			if(d < dolzKopice){
				if(tabela[d] < tabela[naj]){
					naj = d;
				}
				stPrimerjav++;
			}
			if(naj != i){
				int temp = tabela[naj];
				tabela[naj] = tabela[i];
				tabela[i] = temp;
				stPrirejanj += 3;
				a = pogrezni(tabela, naj, dolzKopice, smer);
				stPrimerjav += a[0];
				stPrirejanj += a[1];
			}
		}
		a[0] = stPrimerjav;
		a[1] = stPrirejanj;
		return a;
	}

	private static int[] urediHeap(int[] tabela, int i, boolean smer){
		int stPrimerjav = 0;
		int stPrirejanj = 0;
		int [] a = new int[2];
		int temp = tabela[i];
		tabela[i] = tabela[0];
		tabela[0] = temp;
		stPrirejanj += 3;
		a = pogrezni(tabela, 0, i, smer);
		stPrimerjav += a[0];
		stPrirejanj += a[1];
		a[0] = stPrimerjav;
		a[1] = stPrirejanj;
		return a;
	}

	private static int[] zgradiHeap(int[] tabela, boolean smer){
		int stPrimerjav = 0;
		int stPrirejanj = 0;
		int[] a = new int[2];
		for(int i = tabela.length / 2 - 1; i >= 0; i--){
			a = pogrezni(tabela, i, tabela.length, smer);
			stPrimerjav += a[0];
			stPrirejanj += a[1];
			//System.out.printf("%d %d %d %d\n", a[0], a[1], stPrimerjav, stPrirejanj);
		}

		a[0] = stPrimerjav;
		a[1] = stPrirejanj;
		return a;
	}

	private static void printHeap(int[] a, int d){
		int stEle = 1;
		int stNivo = 1;
		for(int i = 0; i < d; i++){
            if(stEle == 0){
                System.out.print("| ");
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
	// heap functions ----------------------------------------------------------

	// quick sort functions ----------------------------------------------------
	private static int[] qs(int[] tabela, int levi, int desni, boolean smer, boolean print){
		int[] a = new int[2];
		if(desni <= levi){
			return a;
		}
		int i = levi;
		int j = desni;
		int p = (i + j) / 2;

		int stPrimerjav = 0;
		int stPrirejanj = 0;

		int pivot = tabela[p];
		if(smer){
			while(i <= j){
				while(tabela[i] < pivot){
					i++;
					stPrimerjav++;
				}
				stPrimerjav++;
				while(tabela[j] > pivot){
					j--;
					stPrimerjav++;
				}
				stPrimerjav++;
				if(i <= j){
					int temp = tabela[i];
					tabela[i] = tabela[j];
					tabela[j] = temp;
					i++;
					j--;
					stPrirejanj += 3;
				}
			}
			stPrirejanj++;
			if(print){
				printQuick(tabela, j + 1, i, levi, desni);
			}
			a = qs(tabela, levi, j, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];

			a = qs(tabela, j+1, i-1, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];

			a = qs(tabela, i, desni, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];
		}else{
			while(i <= j){
				while(tabela[i] > pivot){
					i++;
					stPrimerjav++;
				}
				stPrimerjav++;
				while(tabela[j] < pivot){
					j--;
					stPrimerjav++;
				}
				stPrimerjav++;
				if(i <= j){
					int temp = tabela[i];
					tabela[i] = tabela[j];
					tabela[j] = temp;
					i++;
					j--;
					stPrirejanj += 3;
				}
			}
			stPrirejanj++;
			if(print){
				printQuick(tabela, j + 1, i, levi, desni);
			}
			a = qs(tabela, levi, j, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];

			a = qs(tabela, j+1, i-1, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];

			a = qs(tabela, i, desni, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];
		}
		a[0] = stPrimerjav;
		a[1] = stPrirejanj;
		return a;
	}

	private static void printQuick(int[] tabela, int levaC, int desnaC, int levi, int desni){
		for(int i = levi; i <= desni; i++){
			if(i == levaC && i == desnaC){
				System.out.printf("| | ");
			}
			else if(i == levaC || i == desnaC){
				System.out.printf("| ");
			}
			System.out.printf("%d ", tabela[i]);
		}
		System.out.printf("\n");
	}
	// quick sort functions ----------------------------------------------------

	// merge sort functions ----------------------------------------------------
	private static int[] ms(int[] tabela, int levi, int desni, boolean smer, boolean print){
		int stPrimerjav = 0;
		int stPrirejanj = 0;
		int[] a = new int[2];
		if(levi == desni){
			a[1] = 1;
			return a;
		}
		if(levi < desni){
			int sredina = (levi + desni) / 2;
			if(print){
				printMerge(tabela, levi, desni, true);
			}
			a = ms(tabela, levi, sredina, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];
			a = ms(tabela, sredina + 1, desni, smer, print);
			stPrimerjav += a[0];
			stPrirejanj += a[1];
			a = merge(tabela, levi, desni, sredina, smer, print);
		}
		a[0] += stPrimerjav;
		a[1] += stPrirejanj;
		return a;
	}

	private static int[] merge(int[] tabela, int levi, int desni, int sredina, boolean smer, boolean print){
		int[] a = new int[2];
		int stPrimerjav = 0;
		int stPrirejanj = 0;
		int levaZ = levi;
		int levaK = sredina;
		int desnaZ = sredina + 1;
		int desnaK = desni;
		int n = levaK - levaZ + 1;
		int m = desnaK - desnaZ + 1;
		int i = 0;
		int j = 0;
		int k = 0;
		int[] c = new int[n + m];

		if(smer){
        	while(i < n && j < m){
            	if(tabela[i + levaZ] <= tabela[j + desnaZ]){
                	c[k] = tabela[i + levaZ];
					i++;
            	}
            	else{
                	c[k] = tabela[j + desnaZ];
					j++;
            	}
				stPrimerjav++;
				stPrirejanj++;
				k++;
        	}
		}else{
			while(i < n && j < m){
            	if(tabela[i + levaZ] >= tabela[j + desnaZ]){
                	c[k] = tabela[i + levaZ];
					i++;
            	}
            	else{
                	c[k] = tabela[j + desnaZ];
					j++;
            	}
				stPrimerjav++;
				stPrirejanj++;
				k++;
        	}
		}
        while(i < n){
            c[i + j] = tabela[i + levaZ];
			stPrirejanj++;
            i++;
        }
        while(j < m){
            c[i + j] = tabela[j + desnaZ];
			stPrirejanj++;
            j++;
        }
		if(print){
			printMerge(c, 0, c.length - 1, false);
		}
		int l = 0;
		for(int z = levi; z <= desni; z++){
			tabela[z] = c[l];
			l++;
		}
		a[0] = stPrimerjav;
		a[1] = stPrirejanj;
		return a;
	}

	private static void printMerge(int[] tabela, int levi, int desni, boolean meja){
		int sredina = (levi + desni) / 2 + 1;
		for(int i = levi; i <= desni; i++){
			if(meja && i == sredina){
				System.out.printf("| ");
			}
			System.out.printf("%d ", tabela[i]);
		}
		System.out.printf("\n");
	}
	// merge sort functions ----------------------------------------------------

	// counting sort functions -------------------------------------------------
	private static void cs(int[] tabela, boolean smer){
		int[] stevilo = prestejElemente(tabela, smer);
		stevilo = izracunKumulative(stevilo);
		printTabela(stevilo, 0, stevilo.length);
		System.out.printf("\n");
		tabela = countSort(tabela, stevilo);
		printTabela(tabela, 0, tabela.length);
		System.out.printf("\n");
	}

	private static int[] prestejElemente(int[] tabela, boolean smer){
		int[] a = new int[256];
		for(int i = 0; i < tabela.length; i++){
			if(smer){
				a[tabela[i]]++;
			}else{
				a[255 - tabela[i]]++;
			}
		}
		return a;
	}

	private static int[] izracunKumulative(int[] tabela){
		int temp = tabela[0];
		for(int i = 1; i < tabela.length; i++){
			tabela[i] += temp;
			temp = tabela[i];
		}
		return tabela;
	}

	private static int[] countSort(int[] tabela, int[] stevilo){
		int[] urejena = new int[tabela.length];
		for(int i = tabela.length - 1; i >= 0; i--){
			int a = tabela[i];
			int index = --stevilo[a];
			urejena[index] = a;
			System.out.printf("%d ", index);
		}
		System.out.printf("\n");
		return urejena;
	}
	// counting sort functions -------------------------------------------------

	// radix sort functions ----------------------------------------------------

	private static void rs(int[] tabela, boolean smer){
		for(int i = 0; i < 4; i++){
			tabela = radix(tabela, i, smer);
		}
	}

	private static int[] radix(int[] tabela, int bit, boolean smer){
		int[] indexi = new int[256];
		indexi = izracunIndexov(tabela, bit, smer);
		indexi = kumulativa(indexi);
		printTabela(indexi, 0, indexi.length);
		System.out.printf("\n");
		tabela = sortiraj(tabela, indexi, bit);
		printTabela(tabela, 0, tabela.length);
		System.out.printf("\n");
		return tabela;
	}

	private static int[] izracunIndexov(int[] tabela, int bit, boolean smer){
		int[] a = new int[256];
		for(int i = 0; i < tabela.length; i++){
			if(smer){
				a[getBit(tabela[i], bit)]++;
			}else{
				a[256 - getBit(tabela[i], bit)]++;
			}
		}
		return a;
	}

	private static int getBit(int a, int n){
		return (int)((a >>> (n * 8)) & 0b11111111);
	}

	private static int[] kumulativa(int[] a){
		int temp = a[0];
		for(int i = 1; i < a.length; i++){
			a[i] += temp;
			temp = a[i];
		}
		return a;
	}

	private static int[] sortiraj(int[] tabela, int[] indexi, int bit){
		int[] urejena = new int[tabela.length];
		for(int i = tabela.length - 1; i >= 0; i--){
			int a = tabela[i];
			int index = --indexi[getBit(a, bit)];
			urejena[index] = a;
			System.out.printf("%d ", index);
		}
		System.out.printf("\n");
		return urejena;
	}

	// radix sort functions ----------------------------------------------------
}
