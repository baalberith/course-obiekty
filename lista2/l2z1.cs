using System;

namespace l2z1 {
	class IntStream {
		protected sbyte counter = 0;
		
		public virtual int next() {
			return counter++;
		}
		
		public virtual bool eos() {
			return counter < 0;
		}
		
		public virtual void reset() {
			counter = 0;
		}
	}
	
	class PrimeStream : IntStream {
		public PrimeStream() {
			counter = 2;
		}
		
		private bool not_prime(int num) {
			int sqrt = (int) Math.Sqrt(num);
			for (int i = 2; i <= sqrt; i++)
				if (num % i == 0)
					return true;
			
			return false;
		}
		
		private void next_prime() {
			if (counter == 2)
				counter = 3;
			else {
				counter += 2;
				while (not_prime(counter))
					counter += 2;
			}
		}
		
		public override int next() {
			int tmp = counter;
			next_prime();
			return tmp;
		}
		
		public override bool eos() {
			return counter < 0;
		}
		
		public override void reset() {
			counter = 2;
		}
	}
	
	class RandomStream : IntStream {
		Random rn = new Random();
		
		public override int next() {
			return (byte) rn.Next();
		}
		
		public override bool eos() {
			return false;
		}
		
		public override void reset() {
		}
	}
	
	class RandomWordStream {
		private PrimeStream pstr = new PrimeStream();
		private RandomStream rstr = new RandomStream();
		
		private char random_char() {
			return (char) (97 + rstr.next() % 26);
		}
		
		public string next() {
			string word = "";
			int len = pstr.next();
			
			for (int i = 0; i < len; i++)
				word += random_char().ToString();
			
			return word;
		}
		
		public bool eos() {
			return pstr.eos();
		}
		
		public void reset() {
			pstr.reset();
		}
	}
	
	class MainClass {
		public static void Main(string[] args) {
			Console.WriteLine("IntStream:");
			IntStream istr = new IntStream();
			Console.WriteLine("{0} {1} {2}", istr.next(), istr.next(), istr.next());
			istr.reset();
			while (!istr.eos()) 
				Console.WriteLine("{0}", istr.next());
			
			Console.WriteLine("PrimeStream:");
			PrimeStream pstr = new PrimeStream();
			while (!pstr.eos()) 
				Console.WriteLine("{0}", pstr.next());
			
			Console.WriteLine("RandomStream:");
			RandomStream rstr = new RandomStream();
			for (int i = 0; i < 100; i++)
				Console.WriteLine("{0}", rstr.next());
			
			Console.WriteLine("RandomWordStream:");
			RandomWordStream rwstr = new RandomWordStream();
			while (!rwstr.eos()) 
				Console.WriteLine("{0}", rwstr.next());
		}
	}
}