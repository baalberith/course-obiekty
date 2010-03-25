using System;

namespace singleton {
	public sealed class Singleton {
		const int MAX = 3;
		
		static Singleton[] instances = new Singleton[MAX];
		static int count = 0;
		
		private string name;
		
    	Singleton(string n) {
			name = n;
		}
		
		public string Name() {
			return name;
		}
    	
		public static Singleton Instance(string n) {
    		if (count < MAX) 
      			instances[count] = new Singleton(n);
			
			count++;
			
      		return instances[(count - 1) % MAX];
   		}
	}
}
