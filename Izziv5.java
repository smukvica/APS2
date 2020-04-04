import java.util.*;

public class Izziv5{

	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		String a = s.nextLine();
		Scanner sc = new Scanner(a);
		List<Integer> list = new ArrayList<Integer>();
		while(sc.hasNext()){
			list.add(sc.nextInt());
		}
		deliInVladaj(list);
	}

	private static int deliInVladaj(List<Integer> list){
		if(list.size() == 1){
			System.out.println(list + ": " + list.get(0));
			return list.get(0);
		}else{
			int sredina = (int)Math.ceil(list.size() / 2.0);
			List<Integer> levi = list.subList(0, sredina);
			List<Integer> desni = list.subList(sredina , list.size());

			int vsotaL = deliInVladaj(levi);
			int vsotaD = deliInVladaj(desni);
			int vsotaS = srednja(levi, desni);
			int vsota = vsotaL;
			if(vsota < vsotaD){
				vsota = vsotaD;
			}
			if(vsota < vsotaS){
				vsota = vsotaS;
			}
			System.out.println(list + ": " + vsota);
			return vsota;
		}
	}

	private static int srednja(List<Integer> levi, List<Integer> desni){
		int max = levi.get(levi.size() - 1) + desni.get(0);
		int kandidat = max;
		int vsota = max;
		int index = levi.size() - 2;

		while(index >= 0){
			kandidat += levi.get(index);

			if(kandidat > vsota){
				vsota = kandidat;
			}

			index--;
		}

		kandidat = vsota;

		index = 1;

		while(index < desni.size()){
			kandidat += desni.get(index);

			if(kandidat > vsota){
				vsota = kandidat;
			}

			index++;
		}
		return vsota;
	}
}
