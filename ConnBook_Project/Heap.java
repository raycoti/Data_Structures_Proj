import java.util.Calendar;
/**
 * 
 * @author Ari, James, Ray, Zoe
 *
 */

public class Heap {
	
	public int n; //number of events
	public Event[] A;
	
	public Heap(){
		n=0;
		A= new Event[200];
	}
	
	/**
	 * returns position of left child of i in array
	 * @param i
	 * 	index of position to find left child
	 * @return
	 * 	position of left child of i
	 */
	public int leftChild(int i){ 
		return i*2;
	}
	
	/**
	 * returns position of right child of i in array
	 * @param i
	 * 	index of position to find right child
	 * @return
	 * 	position of right child of i
	 */
	public int rightChild(int i){ 
		return i*2+1;
	}
	
	/**
	 * returns parent position of position i in array
	 * @param i
	 * 	index of position to find parent
	 * @return
	 * 	parent position of i
	 */
	public int parent(int i){
		return i/2;
	}
	
	/**
	 * returns the event with highest priority, the first event
	 * @return
	 * 	event with highest priority (first event)
	 */
	public Event getMin(){
		return A[1]; //root of tree will be highest priority
	}
	
	/**
	 * checks to see if array is empty
	 * @return
	 * 	boolean true if array is empty, false otherwise
	 */
	public boolean isEmpty(){
		return (n==0); //true if no events in array
	}
	
	/**
	 * swaps events in position i and j of array
	 * @param i
	 * 	event that will be swapped with event j
	 * @param j
	 * event that will be swapped with event i
	 */
	public void swap(int i, int j){
		Event first=A[i]; //save element in first position
		A[i]=A[j];
		A[j]=first;
	}
	/**
	 * Inserts event into priority queue
	 * event.time is the priority
	 * @param k
	 * 	event to be inserted
	 */
	public void insert(Event k){
		long newtime=k.time;//time to compare to
		A[n+1]=k;//put new event at end
		n++;//increment size
		int p=parent(n); //parent position
		int cur=n; //current position
		while(p >0 && newtime<A[p].time){
			swap(p,cur); //swap events at current position and parent 
			cur=p; //new current position is where parent was
			p=parent(cur); //new parent position is parent of new cur
		}
	}
	
	/**
	 * Returns true if parent has Right child
	 * @param par
	 * 	index of parent
	 * @return
	 * true if parent has Right child, false if no
	 */
	public boolean hasRight(int par){ //return true if node at i has a right child
		if (A[rightChild(par)] != null){ //not empty.
			return true;
		}
		else{ //empty spot. return false
			return false;
		}
	}
	
	/**
	 * Returns true if parent has left child
	 * @param par
	 * 	index of parent
	 * @return
	 * true if parent has left child, false if no
	 */
	public boolean hasLeft(int par){ //return true if node at i has a right child
		if (A[leftChild(par)] != null){ //not empty.
			return true;
		}
		else{ //empty spot. return false
			return false;
		}
	}
	
	/**
	 * Returns and removes first event in heap
	 * @return
	 * 	null if array is empty, otherwise returns first event
	 */
	public Event extractMin(){
		if (n==0){//array is empty
			return null;	
		}
		
		if (n==1){//only has root element. return root and decrement size
			n--;
			return A[1];
		}
		
		Event first=A[1]; //event to be extracted is at root. first event to appear
		A[1]=A[n]; //last event is new root. (which will bubble down) and size is decremented
		A[n]=null;
		n--;
		int p=1;//location of parent
		boolean isFound=false;
		
		while(hasLeft(p) && isFound==false){
			int l= leftChild(p);
			int r= rightChild(p);
			int priorityPos=l; //current position of priority event. (may be changed to right)
			
			if(hasRight(p)){ //p has a right child
				if (A[r].time<A[l].time){ //right event comes first
					priorityPos=r; //right has priority 
				}
			}
			
			if (A[priorityPos].time < A[p].time){ //child has priority over parent
				swap(p,priorityPos); //bubble down
				p=priorityPos; //priorityPos becomes new parent
			}
			
			else{
				isFound=true;
			}
		}//end while loop
		return first; //removed event
	}
	
	
	/**
	 * Removes events that have already passed from the array
	 */
	public void cleanUp(){
		Calendar now = Calendar.getInstance();
		while(n>1 && getMin().time <now.getTimeInMillis()){
			extractMin();
		}
	}
	
	/**
	 * creates and returns an array with first 3 events in order
	 * @return
	 * 	an array containing first 3 events or null if no events
	 */
	public Event[] firstThree(){
		Event[] firstEvents= new Event[3];
		//all will be null if no events
		
		if (n>=1){//at least a root
			firstEvents[0]=getMin(); //first event
		}
		
		if(n>=2){//at least a root and left child
			firstEvents[1]=A[2]; //left child of root
		}
		
		if(n>=3){//at least a root with 2 children
			if (A[2].time<A[3].time){ //event at right child comes first
				firstEvents[2]=A[3]; 
			}
			else{ //event at left child comes first
				firstEvents[1]=A[3];//2nd event is left child
				firstEvents[2]=A[2];//3rd event is right child	
			}
		}
		return firstEvents;//return array with 3 events in order	
	}
	
	/**
	 * creates array of up to 7 upcoming events in order
	 * @return
	 * 	array of up to first 7 events
	 */
	private Event[] firstSeven(){ //array of up to first 7 events
		int count=0;
		Event[] firstEvents=new Event[7]; //array of upcoming events
	
		while (count<7 && getMin() != null){ //up to 7 events or less if less events
			firstEvents[count]=extractMin(); //put pull out min and put into array
			count++;
		}
		count=0; //reset count
		while(count<7 && firstEvents[count] != null){ //walk through array
			insert(firstEvents[count]); //put back into array
			count++; 
		}
		return firstEvents;
	}
	
	/**
	 * displays the first 3 upcoming events
	 * (will display less if there are less than 3 events)
	 * @return
	 * 	up to 7 of first upcoming events
	 */
	public Event[] display(){ //displays first 3 events
		Event[] firstEvents = firstSeven();
		if (firstEvents[0]==null){ //no events
			System.out.println("There no upcoming events");
		}
		else{
			for(int i=0; i<7; i++){
				if (firstEvents[i] != null){
					System.out.print(i+1+": ");
					firstEvents[i].display();
				}
			}
		}
		return firstEvents;
	}
}
