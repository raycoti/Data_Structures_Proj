import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//import java.util.*; //used for LL

public class Event {
	public String title;
	//public LinkedList guests; //list of guest
	public long time; //date and time of the event. (miliseconds since 1970)
	//time will be used as priority. smaller the long, higher the priority
	public String location; //location of event
	public String realtime;
	//public String owner; //person who created event
	
	public Event() throws IOException{ //constructor. asks for all event info
		//owner=s.getName();
		System.out.print("What is the name of your event?");
		title=getString();
		System.out.print("Where is your event located?");
		location=getString();
		boolean latertime=false; //checks to see if time of event already happened
		while (latertime==false){    //stay in loop until user enters a time that hasn't happened yet
			/*scanner for reading user input from system console*/ 
			Scanner scan = new Scanner(System.in);
			/*create calendar object to store user inputted time*/
			Calendar userCal = Calendar.getInstance();  
	
			/*get user inputted date and time*/
			int month, day, year, hour, min;
			System.out.print("Please enter the month MM: ");
			month = scan.nextInt();
			System.out.print("Please enter the day DD: ");
			day = scan.nextInt();
			System.out.print("Please enter the year YYYY: ");
			year = scan.nextInt();
			System.out.print("Please enter the hour of the day (0-23): ");
			hour = scan.nextInt();
			System.out.print("Please enter the minute of the hour (00-59): ");
			min = scan.nextInt();
					
			/*pass user-inputted values into calendar object*/
			userCal.set(year, month-1, day, hour, min); //sets date/time in calendar object
			Date caltime=userCal.getTime();
			realtime=""+caltime+"";
			
			long userTimeValue = userCal.getTimeInMillis(); //another representation of the time
			/* System.out.println("\nYour date/time corresponds to " + userTimeValue + 
									" milliseconds gone by since 1970."); 
			
			/*compare the user-inputted time with the current time to see if their event date has passed*/
			Calendar now = Calendar.getInstance();  //creates second calendar object with current date/time
			//System.out.println("\nThe current date/time is: " + now.getTime());
			
			/*compare the two times using their respective integer representations*/
			//String moreOrLess; //will indicate whether user's time is earlier than current time
			if (now.getTimeInMillis() < userCal.getTimeInMillis()){
				time= userTimeValue;
				latertime=true;
			}
			else{
				System.out.println("This time has already happened. Please enter a new time.");
			}
		}//end while loop
		
	}
	
	/**
	 * Constructor to be used for FileI/O
	 * This will only be called by the File I/O class for events that have already been made
	 * (uses data from text file)
	 * @param t
	 * 	time/priority of event (milliseconds after 1970) 
	 * @param mth
	 * 	month of the event
	 * @param d
	 * 	day of the event
	 * @param y
	 * 	year of the event
	 * @param h
	 * 	hour of the event
	 * @param min
	 * 	minute of the event
	 * @param loc
	 * 	location of the event
	 */
	public Event(String t, int mth, int d, int y, int h, int min, String loc){
		title=t;
		location=loc;
		
		Calendar userCal = Calendar.getInstance();  
		userCal.set(y, mth-1, d, h, min); //sets date/time in calendar object
		Date caltime=userCal.getTime();
		realtime=""+caltime+"";
		long userTimeValue = userCal.getTimeInMillis();
		time=userTimeValue;		
	}
	
	public void display(){ //display info about event
		System.out.println("ConnBook Event: "+title+"\nLocation: "+location+"\nTime: "+realtime);
	}
	
	//Professor Chung's Method for getting user input
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();

		return s;
	}

}
