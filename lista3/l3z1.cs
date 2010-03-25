using System;
using list;

namespace l3z1 {
	class MainClass {
		public static void Main(string[] args) {
			LinkedList<int> ll = new LinkedList<int>();
			
			for (int i = 0; i < 10; i++) {
				ll.AddE(i);
				ll.AddF(i);
			}
			
			while (ll.Size() > 0) {
				Console.WriteLine("{0}", ll.GetE());
			}
		}
	}
}