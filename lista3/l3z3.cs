using System;
using singleton;

namespace l3z3 {
	class MainClass {
		public static void Main(string[] args)
		{
			Singleton a = Singleton.Instance("a");
			Singleton b = Singleton.Instance("b");
			Singleton c = Singleton.Instance("c");
			Singleton d = Singleton.Instance("d");
			
			Console.WriteLine("{0}", b.Name() == c.Name());
			Console.WriteLine("{0}", a.Name() == d.Name());
		}
	}
}