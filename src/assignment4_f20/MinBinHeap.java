package assignment4_f20;

public class MinBinHeap implements Heap {
  private CacheFrame[] array; // load this array
  private int size;      // how many items currently in the heap
  private int arraySize; // Everything in the array will initially
                         // be null. This is ok! Just build out
                         // from array[1]

  public MinBinHeap(int nelts) {
    this.array = new CacheFrame[nelts+1];  // remember we don't use slot 0
    this.arraySize = nelts+1;
    this.size = 0;
    this.array[0] = new CacheFrame(null, 0); // 0 not used, so this is arbitrary
  }

  // Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public CacheFrame[] getHeap() { 
	  return this.array; 
  }
  
  //insert will take care of removing an element if the cache is full
  public void insert(CacheFrame elt) {
	  int i = 1;
	  while(getHeap()[i] != null) {
		  i++;
		  if (i == getHeap().length) 
			  break;
	  }
	  if (i == getHeap().length) {
		  delMin(); //size will decrease by 1 in this call
		  getHeap()[this.size()+1] = elt;
		  elt.setSlot(this.size()+1);
		  this.size++;
		  bubbleUp(this.size(), getHeap());
	  } 
	  else {
		  getHeap()[i] = elt;
		  elt.setSlot(i);
		  this.size++;
		  bubbleUp(i, getHeap());
	  }
  }
  
  public void delMin() {
	 if (this.size() > 1) {
		 getHeap()[1] = null;
		 getHeap()[1] = getHeap()[this.size];
		 getHeap()[1].setSlot(1);
		 getHeap()[this.size()] = null;
		 this.size--;
		 delDown(1, getHeap());
	 }
	 else if (this.size() == 1) {
		 getHeap()[1] = null;
		 this.size--;
	 }
  }
  
  public CacheFrame getMin() {
	  return this.getHeap()[1];
  }
  
  public int size() {
	  return this.size;
  }
  
  public void incElt( CacheFrame elt ) {
	  int index=0; 
	  for (int i = 1; i <= size();i++) {
		  if (getHeap()[i].getValue().equals(elt.getValue())) {
			  elt.setPriority(elt.getPriority()+1);
			  index = i;
			  break;
		  }
	  }
	  if ((index*2)+1 < this.arraySize) 
		  bubbleDown(index, getHeap());
	  else if ((index*2) < this.arraySize) {
		  if (getHeap()[index].getPriority() > getHeap()[index*2].getPriority()) {
				 CacheFrame temp = getHeap()[index*2];
				 getHeap()[index*2] = getHeap()[index];
				 getHeap()[index*2].setSlot(index*2);
				 getHeap()[index] = temp;
				 getHeap()[index].setSlot(index);
				 index *= 2;
			 }
	  }
  }
  
  public void decElt( CacheFrame elt) {
	  if (elt.getPriority() == 1) 
		  return;
	  int index=0; 
	  for (int i = 1; i <= size();i++) {
		  if (getHeap()[i].getValue().equals(elt.getValue())) {
			  elt.setPriority(elt.getPriority()-1);
			  index = i;
		  }
	  }
	  bubbleUp(index, getHeap());
  }
  
  /*
   * Helper Methods below
   */
  
  private static void incHelp (CacheFrame elt, CacheFrame [] cache, int index) {
	  if (cache.length >= (2*index)+1) 
		  return;
	  if (cache[index].getPriority() < cache[index*2].getPriority()
			  && cache[index].getPriority() < cache[(index*2)+1].getPriority()) {
		  	return;
	  }
	  else if (cache[2*index].getPriority() < cache[2*index+1].getPriority()) {
		  CacheFrame temp = elt;
		  cache[index] = cache[2*index];
		  cache[index].setSlot(index);
		  cache[2*index] = temp;
		  cache[2*index].setSlot(index*2);
		  incHelp(cache[2*index], cache, 2*index);
	  }
	  else {
		  CacheFrame temp = elt;
		  cache[index] = cache[2*index+1];
		  cache[index].setSlot(index);
		  cache[2*index+1] = temp;
		  cache[2*index+1].setSlot(index*2+1);
		  incHelp(cache[2*index], cache, 2*index);
	  }
	  return;
  }
  private static void bubbleDown (int i, CacheFrame [] cache) {
	  if (cache.length > i*2+1) {
		  if (cache[i*2] != null) {
			  if (cache[i].getPriority() > cache[i*2].getPriority()) {
				  CacheFrame temp = cache[i*2];
				  cache[i*2] = cache[i];
				  cache[i*2].setSlot(i*2);
				  cache[i] = temp;
				  cache[i].setSlot(i);
				  i *= 2; 
				  bubbleDown(i/2, cache);
				  bubbleDown(i, cache);
				  
			  }
			  else if (cache[i*2+1] != null) {
				  if (cache[i].getPriority() > cache[i*2+1].getPriority()) {
					 CacheFrame temp = cache[i*2+1];
					 cache[i*2+1] = cache[i];
					 cache[i*2+1].setSlot(i*2+1);
					 cache[i] = temp;
					 cache[i].setSlot(i);
					 i *= 2;
					 i += 1; 
					 bubbleDown((i-1)/2, cache);
					 bubbleDown(i, cache);
					 
				  }
			  }
		  }
	  }
	  else if (cache.length == i*2+1) {
		  if (cache[i*2] != null) {
			  if (cache[i].getPriority() > cache[i*2].getPriority()) {
				 CacheFrame temp = cache[i*2];
				 cache[i*2] = cache[i];
				 cache[i*2].setSlot(i*2);
				 cache[i] = temp;
				 cache[i].setSlot(i);
				 i *= 2;
				 bubbleDown(i, cache);
				 bubbleDown(i/2, cache);
			 }
		  }
	  } 
	  else {
		  return; 
	  }
  }
  
  private static void bubbleUp (int i, CacheFrame [] cache) {
	  while ((i/2) > 0) {
		  if (cache[i].getPriority() < cache[i/2].getPriority()) {
			 CacheFrame temp = cache[i/2];
			 cache[i/2] = cache[i];
			 cache[i/2].setSlot(i/2);
			 cache[i] = temp;
			 cache[i].setSlot(i);
			 i /= 2;
			 bubbleUp(i, cache);
		 }
		 else {
			 break;
		 }
	  }
  }
  
  private static void delDown(int i, CacheFrame [] cache) {
	  if (2*i < cache.length) {  
		  if (cache[2*i] != null) {
			  if (2*i+1 < cache.length) { 
				  if (cache[2*i+1] != null) {
					  if (cache[i].getPriority() > cache[2*i].getPriority()) {
						  if (cache[2*i].getPriority() > cache[2*i+1].getPriority()) {
							  CacheFrame temp = cache[i];
							  cache[i] = cache[i*2+1];
							  cache[i].setSlot(i);;
							  cache[i*2+1] = temp;
							  cache[i*2+1].setSlot(i*2+1);
							  delDown(i*2+1, cache);
						  }
						  else {
							  CacheFrame temp = cache[i];
							  cache[i] = cache[i*2];
							  cache[i].setSlot(i);;
							  cache[i*2] = temp;
							  cache[i*2].setSlot(i*2);
							  delDown(i*2, cache);
						  }
					  }
					  else if (cache[i].getPriority() > cache[i*2+1].getPriority()) {
						  CacheFrame temp = cache[i];
						  cache[i] = cache[i*2+1];
						  cache[i].setSlot(i);;
						  cache[i*2+1] = temp;
						  cache[i*2+1].setSlot(i*2+1);
						  delDown(i*2+1, cache);
					  }
				  }
				  else {
					  if (cache[i].getPriority() > cache[2*i].getPriority()) {
						  CacheFrame temp = cache[i];
						  cache[i] = cache[i*2];
						  cache[i].setSlot(i);;
						  cache[i*2] = temp;
						  cache[i*2].setSlot(i*2);
						  delDown(i*2, cache);
					  }
				 
				  }
			  }
		  }
	  }
  }
}
