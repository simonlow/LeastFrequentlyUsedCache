package assignment4_f20;

import java.util.HashMap;

public class Cache_LFU implements Cache {
  
  HashMap<String, CacheFrame> map; 
    // allocate from java collections lib
    // do it this way so we all start with default size and 
    // default lambda and default hash function for string keys
  MinBinHeap heap; // your own heap code above
  int limit;       // max num elts the cache can hold
  int size;        // current number elts in the cache
  
  Cache_LFU (int maxElts) {
    this.map = new HashMap<String, CacheFrame>();
    this.heap = new MinBinHeap(maxElts);
    this.limit = maxElts;
    this.size = 0;
  }
  
  // don't change this we need it for grading
  public MinBinHeap getHeap() { return this.heap; }
  public HashMap getHashMap() { return this.map; }
  
  //1
  public int size() {
	  return this.limit;
  }
  
  //1
  public int numElts() {
	  return this.size;
  }
  
  //1
  public boolean isFull() {
	  if (getHeap().size() + 1 == getHeap().getHeap().length) {
		  return true;
	  }
	  return false;
  }
  
  //1
  public boolean refer( String address ) {
	  if (getHashMap().containsKey(address)) {
		  CacheFrame elt = (CacheFrame)getHashMap().get(address);
		  getHeap().incElt(elt);
		  return true;
	  }
	  else {
		  CacheFrame c = new CacheFrame(address, 1);
		  if (this.size == size()) {
			  this.map.remove(this.getHeap().getMin().getValue());
		  }
		  else {
			  this.size++;
		  }
		  getHeap().insert(c);
		  this.map.put(address, c);
		  
		  return false; 
	  }
  }
  
}