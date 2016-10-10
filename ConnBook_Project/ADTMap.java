import java.util.*;
public class ADTMap {
	public int size;
	public LinkedList<Student> map;//creates a map, which is a linked list
	/** 
	 * constructor method for ADTMAp
	 */
	public ADTMap(){
		size=0;//initialize size
		 map = new LinkedList<Student>();//linked list to be used for separate chaining
	}
	/**
	 * Method to return size of the map
	 * @return Integer value of the size
	 */
	public int Size(){
		return size;//returns the size of the map
	}
	/**
	 * Method to check if the ADTMap is empty
	 * @return true if it is 
	 * @return false if else
	 */
	 
	public boolean isEmpty(){
		if (size==0){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Method that returns the student with same username k,
	 * @param k, string- the username
	 * @return the student with same username
	 * @return null if student is not found
	 */
	public Student Get(String k){

		for (int j=0;j<size;j++){//checks each student in map
			Student Cur=map.get(j);//
			if (Cur.getUsername().equals(k)){//if usernames match
				return Cur;//returns the student
			}	
		}
		return null;//not found returns null
	}
	/**
	* Method to return student by indexing
	* @param int w
	* @return Student at index w
	*/
	public Student extract(int w){
		return map.get(w);
	}
	/**
	 * inserts student stu at the end of the map
	 * @param stu Student to be inserted
	 */
	public void Put(Student stu){
		map.addLast(stu);//inserts student at end of map
		size ++;//increase size
	}
	/**
	 * removes student with same username k
	 * @param k the username of the student to be removed
	 * @return the removed student
	 */
	public Student Remove(String k){//removes student from map
		if (size != 0){//if not empty
			for (int i=0; i<size; i++){
				Student Cur=map.get(i);
				if (Cur.getUsername().equals(k)){
					map.remove(Cur);//remove student from map
					size--;//decrease size
					return Cur;//returns the removed student
				}
			}
		}
		return null;//not found returns null
	}
	/**
	 * Displays the user information of each student in the map
	 * Used to check if separate chaining works
	 */
	public void DisplayAll(){
		for (int i=0; i<size; i++){//goes through each student in map
			Student Cur=map.get(i);
		
			Cur.display();//displays info of each student
		}
	}
}
	class ADTMapTest{
		public static void main(String args[]){
			Student stu1= new Student("Ray","2012","rcoti","victory");
			Student stu2= new Student("BOB","9090","B","hahaha");
			Student stu3= new Student("ME", "3434","yo","coolio");
			ADTMap m= new ADTMap();
			m.Put(stu1);
			m.Put(stu2);
			m.Put(stu3);
			m.DisplayAll();
			Student me = m.Remove("B");
			if (me!=null){
				me.display();
			}
			System.out.println("\n \n \n");
			m.DisplayAll();
		}
	}
			
			