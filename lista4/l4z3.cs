using System; 

namespace l4z3 {
	class Godzina {
		private int godzina = 0, minuta = 0, sekunda = 0;
		
		public int Sekundy {
			get {
				return 3600 * godzina + 60 * minuta + sekunda;
			}
		}
		
		public void dodaj(int s) {
			int sekundy = this.Sekundy + s;
			
			godzina = sekundy / 3600;
			godzina = godzina % 24;
			sekundy = sekundy % 3600;
			
			minuta = sekundy / 60;
			sekundy = sekundy % 60;
			
			sekunda = sekundy;
		}
		
		public void dodaj(Godzina g) {
			this.dodaj(g.Sekundy);
		}
		
		public static int operator-(Godzina g1, Godzina g2) {
			return Math.Abs(g1.Sekundy - g2.Sekundy);
		}
		
		public override string ToString() {
			return string.Format("{0:00}:{1:00}:{2:00}", godzina, minuta, sekunda);
		}
	}
	
	class Data {
		int dzien = 1, miesiac = 1, rok = 1;
		int[,] liczba_dni = new int[12, 2] { {31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, 
			{31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31} };
		
		private int przestepny() {
			if (((rok % 4 == 0) && (rok % 100 != 0)) || (rok % 400 == 0)) 
				return 1;
			else
				return 0;
		}
		
		private int przestepne() {
			return (rok - 1) / 4 - (rok - 1) / 100 + (rok - 1) / 400;
		}
		
		public int Dni {
			get {
				int dni = 0;
				for (int i = 0; i < miesiac - 1; i++) {
					dni += liczba_dni[i, przestepny()];
				}
				dni += dzien;
					
				return dni;
			}
		}
		
		private int dni() {
			int lata_przestepne = przestepne();
			int dni = (rok - 1) * 365 + lata_przestepne; 
			dni += this.Dni;
			
			return dni;
		}
		
		public void dodaj(int dni) {
			dzien = dzien + dni;
			
			while (dzien > (liczba_dni[miesiac - 1, przestepny()])) {
				dzien = dzien - liczba_dni[miesiac - 1, przestepny()];
				miesiac = 1 + miesiac % 12;
				if (miesiac == 1)
					rok++;
			}
		}
		
		public static int operator-(Data d1, Data d2) {
			return Math.Abs(d1.dni() - d2.dni());
		}
		
		public override string ToString() {
			return string.Format("{0:00}.{1:00}.{2:00}", dzien, miesiac, rok);
		}
	}
	
	class MainClass {
		public static void Main(string[] args) {
			Godzina g1 = new Godzina();
			g1.dodaj(3600 + 60 + 1);
			
			Godzina g2 = new Godzina();
			g2.dodaj(g1);
			g2.dodaj(3600 + 60 + 1);
			
			Console.WriteLine("g1: {0}", g1);
			Console.WriteLine("g1.Sekundy: {0}", g1.Sekundy);
			Console.WriteLine("g2: {0}", g2);
			Console.WriteLine("g2 - g1: {0}", g2 - g1);
			
			Data d1 = new Data();
			d1.dodaj(365 + 365 + 365 + 366 - 1);
			
			Data d2 = new Data();
			d2.dodaj(365 + 365 + 365 + 366 + 365 - 1);
			
			Console.WriteLine("d1: {0}", d1);
			Console.WriteLine("d1.Dni: {0}", d1.Dni);
			Console.WriteLine("d2: {0}", d2);
			Console.WriteLine("d2.Dni: {0}", d2.Dni);
			Console.WriteLine("d2 - d1: {0}", d2 - d1);
		}
	}
}

