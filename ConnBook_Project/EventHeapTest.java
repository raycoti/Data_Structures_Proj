import java.io.IOException;


public class EventHeapTest {
	public static void main(String args[]) throws IOException{
		System.out.println("new test:");
		Heap eventHeap=new Heap();
		Event first= new Event("first",11,11,2014,11,11,"da bEACH");
		
		eventHeap.insert(first);
		Event second= new Event("LAST",11,11,2114,11,11,"da bEACH");
		
		eventHeap.insert(second);
		Event third= new Event("3rd",11,11,2017,11,11,"da bEACH");
		
		eventHeap.insert(third);
		
		Event forth= new Event("2nd",11,11,2015,11,11,"da bEACH");
		
		eventHeap.insert(forth);
		
		//eventHeap.display();
		
		/*
		eventHeap.extractMin().display();
		eventHeap.extractMin().display();
		eventHeap.extractMin().display();
		*/
		//Event[] myEvents=eventHeap.display();
		
		/*
		System.out.println("Events from array:");
		myEvents[0].display();
		myEvents[1].display();
		myEvents[2].display();
			*/
	}
}
