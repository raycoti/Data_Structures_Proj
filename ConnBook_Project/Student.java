import java.util.*;
/**
 * Student class
 * @author abrenner, jrobins8, rcoti, zchodosh
 *
 */
public class Student {
	public String name;
	public String[] wallPosts;
	public Heap myEvents; 
	public LinkedList <Student> friends; //needs to be AVL
	public String status;
	public String year;
	public String username;
	public String password;
	public int numWallPosts;
	public int wallPostPointer;
	
	/**
	 * Constructor method for Student
	 * @param Username
	 * @param Password
	 * @param Name
	 * @param Year
	 */
	public Student(String Name, String Year, String Username, String Password){
		username= Username;
		password= Password;
		name= Name;
		year=Year;
		wallPosts= new String[5];
		numWallPosts=4;
		wallPostPointer=0;
		myEvents= new Heap(); //will be Hqueue
		friends= new LinkedList <Student>(); //will be AVL
		status="Friend me! My name is "+name+" and I am new to ConnBook!"; //default first status
	}
	
	/**
	* @returns String name of student
	*/
	public String getName(){
		return name;
	}
	
	//will be called when student writes on another students wall
	public void addWallPost(String p, String n){ //2nd arg will be student
		//String n=student.name();
		p=n+": "+p;
		wallPosts[numWallPosts]=p;
		numWallPosts--;
		if (numWallPosts<0){
			numWallPosts=4;
		}	
	}
	
	/**
	* @returns the linked list of wallPosts for student
	*/
	public void showWallPosts(){ //wallposts will stay as an array
		for(int i=numWallPosts+1; i<6+numWallPosts; i++){
			if (wallPosts[i%5] != null){
				System.out.println(wallPosts[i%5]);
			}
		}
	}
	
	/**
	*@returns HQueue of events student will be attending
	*/
	public LinkedList <String> getEvents(){ //needs to be changed to HQueue
		return myEvents;
	}

	/**
	* @returns avl tree containing the student's friends
	*/
	public LinkedList <Student> getFriends(){ //needs to be changed to AVL
		return friends;
	}
	
	/**
	* @returns String of Student’s status 
	*/
	public String getStatus(){
		return status;
	}
	
	/**
	*@returns string of student’s class year
	*/
	public String getYear(){
		return year;
	}
	
	/**
	*@returns String of the student’s Username
	*/
	public String getUsername(){
		return username;
	}
	
	/**
	*@returns String of the student’s password
	*/
	public String getPassword(){
		return password;
	}
	/**
	* Displays the personal information in the student object
	*/
	public void display(){
		System.out.println("Name: "+name);
		System.out.println("Class year: "+year);
		System.out.println("Username: "+username);
		System.out.println("Password: "+password);
	}
	/**
	* Changes the user's status message
	* @param stat 
	*		The user's new status message
	*/
	public void changestatus(String stat){
		status=stat;
	}
	/**
	* Prints a list of all the student's friends
	*/
	public void getFriendList(){
		int counter=1;
		Student temp;
		for (int i=0; i<friends.size(); i++){
			temp=friends.get(i);
			System.out.println(counter+ ". Name: "+temp.name+" Status: "+ temp.status);
			counter++;
		}
	}
	/**
	* Adds a new student to the list of the student's friends
	* @param newfriend 
	*		The username of the friend the student wishes to add
	* @return
	*		True if the friend was successfully added, false if the friend was not found
	*/
	public boolean addFriend(String newfriend){
		int counter=0; 
		boolean isfound=false;
		Student cur;
		while (isfound==false && counter<allstudents.size()){
			cur=allstudents.get(counter);
			if (cur.getUsername().equals(newfriend)){
				friends.add(cur);
				isfound=true;
			}
			else{
				counter++;
			}
		}
		return isfound;
	}
	/**
	* Removes a student from the list of the student's friends
	* @param remfriend 
	*		The username of the friend the student wishes to delete
	* @return
	*		True if the friend was removed successfully, false if the friend was not found
	*/
	public boolean removeFriend(String remfriend){
		int counter=0;
		boolean isfound=false;
		Student cur;
		while (isfound==false && counter<friends.size()){
			cur=friends.get(counter);
			if (cur.getUsername().equals(remfriend)){
				friends.remove(counter);
				isfound=true;
			}
			else{
				counter++;
			}
		}
		return isfound;
	}
	
	/**
	 * adds an Event to student's heap of events
	 * @param E
	 * 	event to be added to student's heap
	 */
	public void addEvent(Event E){
		myEvents.insert(E);
	}
	
	public void showMyEvents(){
		myEvents.display();
	}
					
}

