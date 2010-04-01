using System;

namespace l4z2 {
	public class Buff<T> {
		private T[] elems;
		private int length;
		private int begin, end;
		private int size;
		
		public Buff(int length) {
			this.length = length;
			this.elems = new T[length];
			this.begin = this.end = 0;
			this.size = 0;
		}
		
		private bool overflow() {
			return size == length;
		}
		
		public bool empty() {
			return size == 0;
		}
		
		public static bool operator+(Buff<T> buff, T elem) { 
			bool status = buff.overflow();
			
			if (!status) {
				buff.elems[buff.end] = elem;
				buff.end = (buff.end + 1) % buff.length;
				buff.size = buff.size + 1;
			}
			
			return !status;
        } 
		
		public T Elem {
			get {
				if (empty()) {
					throw new Exception("Empty Buffer!");
				}
				else {
					T elem = elems[begin];
					begin = (begin + 1) % length;
					size = size - 1;
					
					return elem;
				}
			}
		}
	}
			
	class MainClass {
		public static void Main(string[] args) {
			Buff<int> buff = new Buff<int>(6);
			
			int i = 0;
			while (buff + i) 
				i++;
			
			Console.WriteLine("{0}", buff.Elem);
			Console.WriteLine("{0}", buff.Elem);
			Console.WriteLine("{0}", buff.Elem);
			
			while (buff + i) 
				i++;
			
			while (!buff.empty()) {
				Console.WriteLine("{0}", buff.Elem);
			}
		}
	}
}