import java.lang.Math;

public class HashTable {
	private ADTMap[] Table;//an array of ADTMaps
	private int N;//size of the array
	private int Size;
	public int TotalUsers;
	/**
	 * Constructor for the HashTable()
	 */
	public HashTable(){
		N=200;//size of the hashtable
		Size =0;
		TotalUsers=0;//number of users, initially none
		Table= new ADTMap[N];//creates an array of ADTMaps
		for (int j=0;j<N;j++){// Initialize each spot as an ADT map
			ADTMap M = new ADTMap();
			Table[j]= M;//for separate chaining
		}
		
		
	}
	/**
	 * Takes the username and creates a unique index
	 * after being evaluated in the equation
	 * @param key, the String of the username
	 * @return index, a double value
	 */
	private double HashKey(String key){
		double hash = 0;
		
		for (int i = 0; i<key.length(); i++){
			char c= key.charAt(i);//for each character
			double j= c;//converts character into numbers
			j=j-64;//this makes capital A equal to 1
			
			if (j>=1){//if the character conversion is not a negative value
				double squared=java.lang.Math.pow(j,i+1);//takes value of j to the power of i+i
				
				hash=hash+squared;//adds it to total value
			}
		}
		double index=hash%N;
		return index;
	}
	/**
	 * inserts a student into the hashtable
	 * @param newStudent, the student object to be inserted
	 */
	public void insert(Student newStudent){
		String theKey= newStudent.getUsername();//gets the user name of the student
		int i = (int)HashKey(theKey);//gets the double value of hashkey equation, converts to integer
		
		Table[i].Put(newStudent);//indexes the newstudent using ADTMap put method
		Size ++;//increase size
	}
	/**
	 * Searches the hashtable for the student with the same username
	 * @param name, the username of the student
	 * @return the student with username, null if not found
	 */
	public Student Checkusername(String name){
		int i = (int)HashKey(name);//gets number value from username, converts to integer
		
		return Table[i].Get(name);//gets the Student object at that index with same username
	}
	/**
	*Checks to see if an index in the hash table is empty
	*private because this is for the getUsers method
	*@param int index to be checked
	*@return false if empty
	*/
	private boolean emptyChain(int i){
		if (Table[i].isEmpty()){//if the ADTMap is empty
			return true;//return true
		}
		return false;//false otherwise
	}
	/**
	*Checks index in the hash table 
	*private because this is for the getUsers method
	*@param int index to be checked
	*@return size of chain in index
	*/
	private int Chainsize(int i){
	
		return Table[i].Size();//checks size of ADTMap at index i
	}
	/**
	*@return the size/number of users in the table
	*/
	public int getSize(){
		return Size;
	}
	/**
	*@return student by index of table and index of chain
	@param int a - index of hashtable, int b index of chain
	*/
	private Student getStudent(int a, int b){
		return Table[a].extract(b);
	}
	/**
	@return array of all Student users
	*/
	public Student[] getUsers(){
		
		Student[] all = new Student[Size];//creates an array with the size of number of users
		Student current;
		int index=0;
		for (int i=0;i<N;i++){//for each index in the hashtable
			if (emptyChain(i)){//if chain is empty do nothing
			}
			else{//if chain is not empty
				int numitems=Chainsize(i);//check size of chain
				for (int j=0; j<numitems;j++){//for each Student in the chain
					current =getStudent(i,j);//extract the student
					//current.display();
					all[index]=current;//add the student to the array 
					index ++;//move to next available spot
				}
			}
		}
		return all;//return the array of student objects
	}
	public void alldisplayed(){
		for (int i=0;i<N;i++){//for each index in the hashtable
			if (emptyChain(i)){//if chain is empty do nothing
			}
			else{//if chain is not empty
			Table[i].DisplayAll();
			}
		}
	}
	
}
class HashTableTest{
	public static void main(String args[]){
		HashTable H=  new HashTable();
		Student stu1= new Student("Ray","2012","Rcoti@conncoll.edu","victory");//creating 4 student objects
		Student stu2= new Student("BOB","9090","B@conncoll.edu.","hahaha");
		Student stu3= new Student("ME", "3434","me@conncoll.edu","coolio");
		Student stu4= new Student("Mr ","1029","B@conncoll.edu","adfs");
		H.insert(stu1);//inserts them in order
		H.insert(stu2);
		H.insert(stu3);
		H.insert(stu4);
		int w =H.getSize();//gets the size/number of users
		System.out.println(w);//print out size
		//way to extract all students from hashtable
		Student[] all=H.getUsers();//gets the array of all users from hashtable
		
		Student cur;
		for (int j=0;j<w;j++){//for each index in array
			cur=all[j];//gets student at index j
			System.out.println("\nUser"+(j+1) +"\n");
			cur.display();//simply displays information but could later be used to extract info to be printed.  
		}
		
		
		//Student check= H.Checkusername("B.");
	
	}
}

