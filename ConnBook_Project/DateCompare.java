import java.util.Calendar;
import java.util.Scanner;

/* See Java docs online for more methods available in Calendar class. */

public class DateCompare
{
	public static void main (String [] args)
	{
	    /*scanner for reading user input from system console*/ 
		Scanner scan = new Scanner(System.in);
		/*create calendar object to store user inputted time*/
		Calendar userCal = Calendar.getInstance();  

		/*get user inputted date and time*/
		int month, day, year, hour, min;
		System.out.print("Please enter a month MM: ");
		month = scan.nextInt();
		System.out.print("Please enter a day DD: ");
		day = scan.nextInt();
		System.out.print("Please enter a year YYYY: ");
		year = scan.nextInt();
		System.out.print("Please enter an hour of the day (0-23): ");
		hour = scan.nextInt();
		System.out.print("Please enter the minute of the hour (00-59): ");
		min = scan.nextInt();
		
		System.out.println("\nYou entered: " + month + "/" + day + "/" + year + " at " + hour + ":" + min);
		
		/*pass user-inputted values into calendar object*/
		userCal.set(year, month-1, day, hour, min); //sets date/time in calendar object
		 
		/*check that time in calendar object now matches user input*/
		System.out.println("The calendar object been set to: " + userCal.getTime()); 

		/* see the official online documentation on calendar objects to 
		 * learn specifically what these calendar methods do... go here:
		 * http://download.oracle.com/javase/1.5.0/docs/api/java/util/Calendar.html
		 * and scroll down to "method summary." */
		
		/*also note that any given date/time has a unique integer (long) value associated with it...*/
		long userTimeValue = userCal.getTimeInMillis(); //another representation of the time
		System.out.println("\nYour date/time corresponds to " + userTimeValue + 
								" milliseconds gone by since 1970."); 
		
		/*compare the user-inputted time with the current time to see if their event date has passed*/
		Calendar now = Calendar.getInstance();  //creates second calendar object with current date/time
		System.out.println("\nThe current date/time is: " + now.getTime());
		
		/*compare the two times using their respective integer representations*/
		String moreOrLess; //will indicate whether user's time is earlier than current time
		if (now.getTimeInMillis() > userCal.getTimeInMillis())
			moreOrLess = "earlier";
		else
			moreOrLess = "later";
	
		System.out.println("\nThe time you entered is " + moreOrLess + " than the current time.");
		
	}//end userInput
	
}//end class