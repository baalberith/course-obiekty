using System;
using System.Collections;

namespace l2z4 {
	class ListaLeniwa {
		protected int[] lista;
		protected int rozmiar = 0;
		
		private Random rn = new Random();
		
		protected virtual int nastepny() {
			return (int) rn.Next();
		}
		
		public int element(int indeks) {
			if (indeks + 1 > rozmiar) {
				int[] tmp = new int[indeks + 1];
				
				for (int i = 0; i < rozmiar; i++)
					tmp[i] = lista[i];
		
				for (int i = rozmiar; i <= indeks; i++) {
					tmp[i] = nastepny();
				}
				
				rozmiar = indeks + 1;
				lista = new int[rozmiar];
				lista = tmp;
			}
			
			return lista[indeks];
		}
				
	}
	
	class Pierwsze : ListaLeniwa {
		private int poprzednia = 1;
		
		private bool pierwsza(int num) {
			int sqrt = (int) Math.Sqrt(num);
			for (int i = 2; i <= sqrt; i++)
				if (num % i == 0)
					return false;
			
			return true;
		}
		
		protected override int nastepny() {
			if (poprzednia > 2) {
				poprzednia += 2;
				while (!pierwsza(poprzednia))
					poprzednia += 2;
			}
			else if (poprzednia == 2)
				poprzednia = 3;
			else if (poprzednia == 1)
				poprzednia = 2;
			
			return poprzednia;
		}
	}
	
	class MainClass {
		public static void Main(string[] args) {
			ListaLeniwa ll = new ListaLeniwa();
			for (int i = 0; i < 10; i++) 
				Console.WriteLine("{0}", ll.element(i));
			Console.WriteLine("{0}", ll.element(9));
			
			Pierwsze lp = new Pierwsze();
			for (int i = 0; i < 10; i++) 
				Console.WriteLine("{0}", lp.element(i));
			Console.WriteLine("{0}", lp.element(9));
		}
	}
}