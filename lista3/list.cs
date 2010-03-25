using System;

namespace list {
	public class Node<T> {
		public Node<T> prev;
		public Node<T> next;
		public T val; 
 
		public Node(Node<T> p, Node<T> n, T v) {
			prev = p;
			next = n;
			val = v;
		}
	} 

	public class LinkedList<T> {
		Node<T> front;
		Node<T> end;
		int count;
		
		public LinkedList() {
			front = null;
			end = null;
			count = 0;
		}
		
		public void AddE(T val) {
			if (end == null) 
				front = end = new Node<T>(null, null, val);
			else 
				end = end.next = new Node<T>(end, null, val);
			
			count++;
		} 
		
		public void AddF(T val) {
			if (front == null) 
				front = end = new Node<T>(null, null, val);
			else 
				front = front.prev = new Node<T>(null, front, val);
			
			count++;
		} 
		
		public T GetE() {
			if (end == null)
				throw new Exception("There is no node!");
			else {
				T val = end.val;
				if (end.prev == null) {
					front = end = null;
				}
				else {
					end = end.prev;
					end.next = null;
				}
				
				count--;
				return val;
			}
		}
		
		public T GetF() {
			if (front == null)
				throw new Exception("There is no node!");
			else {
				T val = front.val;
				if (front.next == null) {
					front = end = null;
				}
				else {
					front = front.next;
					front.prev = null;
				}
				
				count--;
				return val;
			}
		}
		
		public int Size() {
			return count;
		}
	} 
}
