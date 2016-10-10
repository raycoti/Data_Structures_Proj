//FileIONew.java
//This class and method reads data from a text file to construct Student objects and populate ConnBook with other users
//Adapted from example given by Prof. Chung
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;

import java.io.FileWriter;

public class FileIONew{
	
	
	//Class variables
	private HashTable myHashTable;
	private LinkedList<Student> Friends;	//will eventually be an AVLTree
	//private Heap Events;
	public FileIONew(){
	}
	//VARIABLE ACCESSOR METHODS
	/**
	* Returns HashTable
	*/
	public HashTable getHashTable(){
			return myHashTable;
		
	}
	
	/**
	* Returns Friends
	*/
	public LinkedList<Student> getFriends(){
		return Friends;
	}
	
	/**
	* Returns Events
	*/
	//public Heap getEvents(){
		//return Events;
	//}

	//MAIN METHODS
	/**
	* For importing text file data
	*/
	public void importInfo(){
		//this try-catch statement is needed around this file input code
		//because the FileInputStream may throw a FileNotFoundException
		try {
			Scanner lineScanner = new Scanner(new FileInputStream("userdata.txt"));
			//Constructs a new HashTable myHashTable and Friends AVL tree
			HashTable myHashTable = new HashTable();
			LinkedList<Student> Friends = new LinkedList<Student>();	//will eventually be an AVLTree
			
			while (lineScanner.hasNext()) {		//while more of the input file is still available for reading
				
				//INITIAL DATA
				//==========================
				//Gets basic data to construct Student object
				String name = lineScanner.nextLine();  //reads an entire line of input for name
				System.out.println("Name is: " + name);

				String email = lineScanner.nextLine();	//sets email address for username
				System.out.println("Email is: " + email);
				
				String pass = lineScanner.nextLine();	//sets password
				System.out.println("Password is: " + pass);
				
				String year = lineScanner.nextLine();	//sets class year
				System.out.println("Year is: " + year);
				
				//Constructs a Student object -------------------
				Student myStudent =  new Student(name, year, email, pass);
				//-----------------------------------------------
				System.out.println("+++++++++++++Student object constructed++++++++++++++");
				
				String status = lineScanner.nextLine();
				System.out.println("Status is: " + status);
				myStudent.status = status;
				//==========================
				
				// //EVENTS
				// //==========================
				// String events = lineScanner.nextLine(); //sets event data
				
				// //now create a secondary scanner to scan through this list of events
				// // to break them up into individual events
				// Scanner eventsScanner = new Scanner(events);
				// String[] eventsArray = new String[10];	//will store the individual events for now
				// int i = 0; //array index counter
				// //on this line of data, events are in quotes and delimited by commas, 
				// // so the scanner looks for: a quotation mark followed by a comma (",) to delimit each event
				// eventsScanner.useDelimiter("\","); 		//backslash (\) is escape character for quote (")
				// String e; 								//will hold each individual event
				// System.out.println("Events are: ");
				// while (eventsScanner.hasNext()){
					// System.out.println("--------------------------");
					// e = eventsScanner.next();
					// e = e.substring(1, e.length()); //cut off the leading quotation mark of each event
					// System.out.println(e);
					// eventsArray[i] = e;
					// i++;
					
					// Scanner eScanner = new Scanner(e); 	//yet another scanner just for this particular event
					
					// month = eScanner.nextInt();
					// System.out.println("Month: " + month);
					// day = eScanner.nextInt();
					// System.out.println("Day: " + day);
					// year = eScanner.nextInt();
					// System.out.println("Year: " + year);
					// hour = eScanner.nextInt();
					// System.out.println("Hour: " + hour);
					// minutes =  eScanner.nextInt();
					// System.out.println("Minutes: " + minutes);
					
					// String desc = ""; 					//to hold the description of the event
					// while (eScanner.hasNext()){ 		//while there are words left...
						// desc = desc + " " + eScanner.next();	//reads the description one word at a time
					// }
					// System.out.println("Description: " + desc);
					
					// //New Event object initialization
					// myEvent = Event(title, month, day, year, hour, minutes,location);
					// //Event added to heap automatically in Event class constructor
				// }				
				// //==========================
				
				// //WALL MESSAGES
				// //==========================
				// /* reads in next line and then breaks it into separate wall messages
				 // * code is analagous to events, so refer to above comments for explanation. */
				// String wallMsgs = lineScanner.nextLine();
				// Scanner wallMsgScanner = new Scanner(wallMsgs);
				// String[] wallMsgArray = new String[5];
				// i = 0;
				// wallMsgScanner.useDelimiter("\",");
				// String message; 
				// System.out.print("Wall messages are: ");
				// while (wallMsgScanner.hasNext()) {
					// message = wallMsgScanner.next();
					// System.out.print(message + "\",");
					// message = message.substring(1, message.length());
					// wallMsgArray[i] = message; //stores message into array of messages
					// //i don't do anything with this array, but it is here just to demonstrate
					// //(you may or may not be using an array to store the list of wall messages.)
					// i++;
				// }
				// /**
				// *Integration with our Student class: Public "wallPosts" variable in Student class is set to = "wallMsgArray"
				// *Alternative was to use Student class method "addNewWallPost", but we decided to set the public variable for entire array in Student 
				// * class all at once rather than add each user individually using the "addNewWallPost" method
				// * because we already have that array here. Student class variable wallPosts is an array of Strings that will store
				// * wall posts with who the post came from. In other words, Student wallPosts array (String) values INCLUDE the delimiter used
				// * to differentiate origin user and post itself. This method does not handle that, but passes Student class entire string with all post data.
				// */
				// myStudent.wallPosts = wallMsgArray;
				// System.out.println();
				
				// /*	test code for printing contents of array
				// for (int j = 0; j < wallMsgArray.length; j++){
					// System.out.println(wallMsgArray[j]);
				// }*/
				// //==========================
				
				// //FRIENDS
				// //==========================
				// System.out.println("+++++++++++++++++++++++++++");
				// /* reads in next line and then breaks it into separate friends
				 // * now the delimiter is just a comma because there are no quotes around
				 // * each data item.  so this is a bit simpler than above procedure.*/
				// String friends = lineScanner.nextLine();
				// Scanner friendScanner = new Scanner(friends);
				// String[] friendArray = new String[20]; 
				// i = 0;
				// friendScanner.useDelimiter(",");  
				// String friend;
				// System.out.print("Friends are: ");
				// while (friendScanner.hasNext()) {
					// friend = friendScanner.next();
					// System.out.print(friend + ",");
					// //Integration with our "Friends"
					// Friends.add(friend);
				// }
				// System.out.println();
				// System.out.println("+++++++++++++++++++++++++++");
				
				//Adds the new Student object to the HashTable myHashTable
				myHashTable.insert(myStudent);
			}
			
		//Error handling for "FileNotFoundException"
		} catch(FileNotFoundException ex) {
			System.out.println("File not Found");
			System.exit(0);
		}	
	}
	
	/**
	*For exporting data to text file. Called when user selects "Quit"
	*/
	public void exportInfo(Student[] studentArray){ 	//gets passed an array of Students
		try {
			//Export FileIO code
			//File saveFile = new File("userdata.txt");			//text file for saved data
		
			//FileWriter fw = new FileWriter(File.getAbsoluteFile());
			FileWriter fw = new FileWriter("userdata.txt");
			BufferedWriter bw = new BufferedWriter(fw);
		
			/* EXAMPLE
			* Leo Higdon
			* lhigdon@conncoll.edu
			* Camels2011
			* 2050
			* raising money for the college and celebrating its centennial
			* 12 02 2012 18 30 Great Beginnings Centennial Event in Blaustein,...
			* Happy Centennial!, Go Camels!, ...
			* abengoch@conncoll.edu, aross@conncoll.edu, tpamm@conncoll.edu,
			*/
		
			for(int i=0; i<studentArray.length; i++){
				Student outputStudent = studentArray[i];		//selects student from array
				bw.write(outputStudent.getName() +"\n");			//name
				bw.write(outputStudent.getUsername()+"\n");		//username
				bw.write(outputStudent.getPassword()+"\n");		//password
				bw.write(outputStudent.getYear()+"\n");			//year
				bw.write(outputStudent.getStatus()+"\n");			//status
				}
				
				//events
				//wall posts
				//friends
			
			bw.close();
			fw.close();
			System.out.println("Information exported");
		
		}catch(IOException e) {
			System.out.println("File not Found");
			System.exit(0);
		}
	}
}

class testfile{
	public static void main(String args[]){
		HashTable H=  new HashTable();
		Student stu1= new Student("Ray","2012","Rcoti@conncoll.edu","victory");//creating 4 student objects
		Student stu2= new Student("BOB","9090","B@conncoll.edu.","hahaha");
		Student stu3= new Student("ME", "3434","me@conncoll.edu","coolio");
		Student stu4= new Student("Mr ","1029","B@conncoll.edu","adfs");
		H.insert(stu1);//inserts them 
		H.insert(stu2);
		H.insert(stu3);
		H.insert(stu4);
		Student[] allusers= H.getUsers();
		FileIONew file = new FileIONew();
		file.exportInfo(allusers);
		file.importInfo();
		HashTable B=file.getHashTable();
		if( B!=null){
		B.alldisplayed();
		}
	}
}





