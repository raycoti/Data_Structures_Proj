import java.io.*;
import java.util.*;
public class Main {
	public static void loggedIn(Student s){//called once user is logged in
		String newstatus;
		boolean addfriendans;
		boolean remfriendans;
		String remfriend;
		String newfriend;
		char ans2;
		char ans;
		do{
			System.out.println("My status: "+s.status);
			//next event to take place
			//last five messages on wall
			//events the student plans to attend
			System.out.println("Enter 1 to change your status");
			System.out.println("Enter 2 to add an event to your schedule");
			System.out.println("Enter 3 to create an event");
			System.out.println("Enter 4 to see your friend list");
			System.out.println("Enter 5 to add or remove a friend");
			System.out.println("Enter 6 to log out");
			ans=getChar();
			switch(ans){
				case '1': //change status
					System.out.println("Enter a new status message: ");
					newstatus=getString();
					s.changestatus(newstatus);
					break;
				//case '2'://add an event
				case '2':
					Event[] eventArray=allEvents.display();
					System.out.println("Enter the number of the event you would like to join");
					char eventChar=getChar();
					int eventNum=Character.getNumericValue(eventChar);
					s.addEvent(eventArray[eventNum]); //add event to student's event heap
					break;
				//case 3 to create an event
				case '3':
					Event newEvent= new Event();
					allEvents.insert(newEvent);
					s.addEvent(newEvent); //the student will be going to their own event. whether they like it or not
					
				case '4': //see friend list
					System.out.println("Here are all of your friends on ConnBook: ");
					s.getFriendList();
					break;
				case '5'://add or delete a friend
					System.out.println("Enter 1 to add a friend, enter 2 to remove a friend: ");
					ans2=getChar();
					if (ans2=='1'){
						System.out.println("Enter the username of the friend you wish to add: ");
						newfriend=getString();
						addfriendans=s.addFriend(newfriend);
						if (addfriendans==true){
							System.out.println("You have successfully added your new friend.");
						}
						else{
							System.out.println("The username you entered could not be found on ConnBook.");
						}
					}
					else{ //remove a friend
						System.out.println("Enter the username of the friend you wish to remove: ");
						remfriend=getString();
						remfriendans=s.removeFriend(remfriend);
						if (remfriendans==true){
							System.out.println("You have successfully removed this person from your friend list.");
						}
						else{
							System.out.println("The friend you wanted to remove could not be found in your list of friends.");
						}
					}	
					break;
				case '6': //Log out
					System.out.println("You have successfully logged out of your ConnBook account.");
					break;
				default:
					System.out.println("Invalid entry.  Please enter 1, 2, 3, 4, or 5");
			}//end switch
		}while (ans != '5');//end while
	}//end loggedin 
			
					
	public static void main(String args[]) throws IOException{
		FileIONew alldata= new FileIONew();
		HashTable allstudents=alldata.getHashTable();
		
		
		String checkname;
		String checkpassword;
		Student cur = null;
		
		//test event class
		System.out.println("This is only here now to test the event class.");
		Event myevent= new Event();
		myevent.display();
		
		char choice;
		System.out.println("Welcome to ConnBook! Please select an option below.");
		do{
			System.out.println("Create new acount (1) \nLog in (2)\nQuit (3)");
			choice= getChar();
			switch(choice){
				case '1': //create a new account
					System.out.println("What is your name?");
					String name=getString();
					System.out.println("What is your class year?");
					String year=getString();
					System.out.println("What is your Conn username?");
					String username=getString();
					System.out.println("What would you like your password to be?");
					String password=getString();
					Student newstudent= new Student(name, year, username, password);
					newstudent.display();
					allstudents.add(newstudent); //goes to end of list
					//student will be added here into the hashtable
					loggedIn(newstudent);
					break;
				case '2': //log into existing account
					System.out.println("Please enter your username");
					checkname=getString();
					int counter=0;
					boolean isFound=false; //username is found
					boolean matches=false; //password matches username
					while(isFound == false && counter < allstudents.size()){
						cur=allstudents.get(counter);
						if(cur.getUsername().equals(checkname)){
							isFound=true;
						}
						else{
							counter++;
							if(counter==allstudents.size()){
								System.out.println("Username not found. Please try again or enter Q to quit");
								checkname=getString();
								counter=0;
								if(checkname.equals("q")|| checkname.equals("Q")){
									System.out.println("Bye! Have a nice day.");
									System.exit(0);
								}
							}	
						}
					}
					//check password to match user
					
					System.out.println("Please enter your password");
					checkpassword=getString();
					while (matches==false){
						if(cur.getPassword().equals(checkpassword)){
							matches=true;
							System.out.println("Welcome, "+cur.getName());
							loggedIn(cur);
						}
						else{
							System.out.println("Please try your password again or enter 'q' to quit.");
							checkpassword=getString();
						}
					}
					
					break;
				case '3': //quit
					System.out.println("Bye! Have a nice day.");
					
					break;
				default: //if user did not enter 1, 2, or 3
					System.out.println("Not a valid entry. Please type '1', '2', or '3'.");
				} //end switch
		}while (choice != '3');
			 
	} //end of main method
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//Professor Chung's Method for getting user input
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();

		return s;
	}
	
	//Professor Chung's Method for getting user input	
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}	
}
