package assignment4_f20;

import java.util.Arrays;

public class simon {
	public static void main (String [] args) {
		/*
		Cache cache = new Cache_LFU(3);
		 
		cache.refer("234A");
		cache.refer("ABCD");
		cache.refer("1101");
		cache.refer("2202");
		for (int i = 1; i < cache.size();i++) {
			System.out.println(cache.getHeap().getHeap()[i]);
		}
		
		
		System.out.println(cache.getHashMap().get("ABCD"));
		System.out.println(cache.isFull());
		
		
		Cache cache = new Cache_LFU(8);
		CacheFrame a = new CacheFrame("a", 3);
		CacheFrame b = new CacheFrame("b", 5);
		CacheFrame c = new CacheFrame("c", 9);
		CacheFrame d = new CacheFrame("d", 7);
		CacheFrame e = new CacheFrame("e", 4);
		CacheFrame f = new CacheFrame("f", 6);
		CacheFrame g = new CacheFrame("g", 2);
		
		cache.getHeap().insert(a);
		cache.getHeap().insert(b);
		cache.getHeap().insert(c);
		cache.getHeap().insert(d);
		cache.getHeap().insert(e);
		cache.getHeap().insert(f);
		cache.getHeap().insert(g);
		
		cache.getHeap().incElt(g);
		cache.getHeap().incElt(g);
		cache.getHeap().decElt(d);
		cache.getHeap().decElt(d);
		cache.getHeap().incElt(b);
		cache.getHeap().incElt(e);
		cache.getHeap().incElt(e);
		cache.getHeap().decElt(d);
		cache.getHeap().decElt(d);
		cache.getHeap().decElt(d);
		cache.getHeap().decElt(f);
		cache.getHeap().decElt(f);
		cache.getHeap().decElt(f);
		
		
		cache.getHeap().delMin();
		//cache.getHeap().delMin();
		for (int i = 1; i < cache.size(); i++) {
			System.out.println(cache.getHeap().getHeap()[i]);
		}
		*/
		
		Heap mbh = new MinBinHeap(5);
		mbh.delMin();
		System.out.println(mbh.getMin());
		
	}
}
