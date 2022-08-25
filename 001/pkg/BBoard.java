package pkg;
import java.io.File;
import java.util.*;
import java.io.*;

public class BBoard {		// This is your main file that connects all classes.
	// Think about what your global variables need to be.
	private String title;
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Message> messageList = new ArrayList<Message>();
	private User currentUser;
	private int ID = 0;
	private String password;
	File userFile;
	private Scanner scanner;
	private Scanner input;
	// Default constructor that creates a board with a defaulttitle, empty user and message lists,
	// and no current user
	public BBoard() {
		title = "defaulttitle";
		users.add(new User()); //! done?
		
	}

	// Same as the default constructor except it sets the title of the board
	public BBoard(String ttl) {
		title = ttl; //! not finished
		users.add(new User());
	}

	// Gets a filename of a file that stores the user info in a given format (users.txt)
	// Opens and reads the file of all authorized users and passwords
	// Constructs a User object from each name/password pair, and populates the userList ArrayList.
	public void loadUsers(String inputFile) throws FileNotFoundException {
		scanner = new Scanner(new File(inputFile));
		while (scanner.hasNextLine()) {
			String temp = scanner.nextLine();
			users.add(new User(temp.substring(0, temp.indexOf(" ")), temp.substring(temp.indexOf(" ")+1)));
		}
		login();
	}

	// Asks for and validates a user/password. 
	// This function asks for a username and a password, then checks the userList ArrayList for a matching User.
	// If a match is found, it sets currentUser to the identified User from the list
	// If not, it will keep asking until a match is found or the user types 'q' or 'Q' as username to quit
	// When the users chooses to quit, say "Bye!" and return from the login function
	public void login(){
		System.out.println(title);
		
		input = new Scanner(System.in);
		boolean gotIn = false;

		while (true) {
			System.out.print("Enter your username ('Q' or 'q' to quit): ");
			String uName = input.nextLine();
			if (uName.equals("Q") || uName.equals("q")){System.out.println("\n\nBye!"); return;}
			System.out.print("Enter your password ('Q' or 'q' to quit): ");
			String pWrd = input.nextLine();
			if (pWrd.equals("Q") || uName.equals("q")){System.out.println("\n\nBye!"); return;}

			for (int i=0; i<users.size(); i++) {
				if (users.get(i).check(uName, pWrd)) {
					gotIn = true;
					currentUser = users.get(i);
					password = pWrd;
					break;
				}
			}
			if (gotIn) {
				break;
			} else
			System.out.println("\nInvalid Username or Password.\n");
		}
		System.out.println("\n Welcome back " + currentUser.getUsername());
	}
	
	// Contains main loop of Bulletin Board
	// IF and ONLY IF there is a valid currentUser, enter main loop, displaying menu items
	// --- Display Messages ('D' or 'd')
	// --- Add New Topic ('N' or 'n')
	// --- Add Reply ('R' or 'r')
	// --- Change Password ('P' or 'p')
	// --- Quit ('Q' or 'q')
	// With any wrong input, user is asked to try again
	// Q/q should reset the currentUser to 0 and then end return
	// Note: if login() did not set a valid currentUser, function must immediately return without showing menu
	public void run(){
		while(true){
			System.out.println("<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>");
			System.out.print("Menu\n--- Display Messages ('D' or 'd')\n--- Add New Topic ('N' or 'n')\n--- Add Reply ('R' or 'r')\n--- Change Password ('P' or 'p')\n--- Quit ('Q' or 'q')\nChoose an action: ");
			String uInput = input.nextLine();
			if (uInput.equals("D") || uInput.equals("d")){
				display();
				break;
			} else if (uInput.equals("N") || uInput.equals("n")){
				addTopic();
				break;
			} else if (uInput.equals("R") || uInput.equals("r")){
				addReply();
				break;
			} else if (uInput.equals("P") || uInput.equals("p")){
				setPassword();
				break;
			}  else if(uInput.equals("Q") || uInput.equals("q")) {
				System.out.println("\n\nBye!");
				return;
			}
		}
	}

	// Traverse the BBoard's message list, and invote the print function on Topic objects ONLY
	// It will then be the responsibility of the Topic object to invoke the print function recursively on its own replies
	// The BBoard display function will ignore all reply objects in its message list
	private void display(){
		if(messageList.size() > 0) {
			System.out.println("\n-------------------------------------------");
			for (int i=0; i<messageList.size(); i++) {
				if(!messageList.get(i).isReply()) {
					messageList.get(i).print(0);
				}
			}
			System.out.println("\n-------------------------------------------");
		} else {
			System.out.print("\nNothing to display.\n\n");
		}
		run();
	}


	// This function asks the user to create a new Topic (i.e. the first message of a new discussion "thread")
	// Every Topic includes a subject (single line), and body (single line)

	/* 
	Subject: "Thanks"
	Body: "I love this bulletin board that you made!"
	*/

	// Each Topic also stores the username of currentUser; and message ID, which is (index of its Message + 1)

	// For example, the first message on the board will be a Topic who's index will be stored at 0 in the messageList ArrayList,
	// so its message ID will be (0+1) = 1
	// Once the Topic has been constructed, add it to the messageList
	// This should invoke your inheritance of Topic to Message
	private void addTopic(){
		ID++;
		System.out.print("Subject: ");
		String sub = "\""+input.nextLine()+"\"";
		System.out.print("Body: ");
		String bod = "\""+input.nextLine()+"\"";
		messageList.add(new Topic(currentUser.getUsername(), sub, bod, ID));
		run();
	}

	// This function asks the user to enter a reply to a given Message (which may be either a Topic or a Reply, so we can handle nested replies).
	//		The addReply function first asks the user for the ID of the Message to which they are replying;
	//		if the number provided is greater than the size of messageList, it should output and error message and loop back,
	// 		continuing to ask for a valid Message ID number until the user enters it or -1.
	// 		(-1 returns to menu, any other negative number asks again for a valid ID number)
	
	// If the ID is valid, then the function asks for the body of the new message, 
	// and constructs the Reply, pushing back the Reply on to the messageList.
	// The subject of the Reply is a copy of the parent Topic's subject with the "Re: " prefix.
	// e.g., suppose the subject of message #9 was "Thanks", the user is replying to that message:


	/*
			Enter Message ID (-1 for Menu): 9
			Body: It was a pleasure implementing this!
	*/

	// Note: As before, the body ends when the user enters an empty line.
	// The above dialog will generate a reply that has "Re: Thanks" as its subject
	// and "It was a pleasure implementing this!" as its body.

	// How will we know what Topic this is a reply to?
	// In addition to keeping a pointer to all the Message objects in BBoard's messageList ArrayList
	// Every Message (wheather Topic or Reply) will also store an ArrayList of pointers to all of its Replies.
	// So whenever we build a Reply, we must immediately store this Message in the parent Message's list. 
	// The Reply's constructor should set the Reply's subject to "Re: " + its parent's subject.
	// Call the addChild function on the parent Message to push back the new Message (to the new Reply) to the parent's childList ArrayList.
	// Finally, push back the Message created to the BBoard's messageList. 
	// Note: When the user chooses to return to the menu, do not call run() again - just return fro mthis addReply function. 
	private void addReply(){
		while (true) {
			System.out.print("\nEnter Message ID (-1 for menu): ");
			int check = input.nextInt();
			input.nextLine();
			if(check == -1) {
				break;
			} else if (check > ID || check < -1 || check == 0) {
				System.out.print("\nInvalid Message ID. Please Re-enter.");
			} else {
				System.out.print("\nBody: ");
				String temp = input.nextLine();
				ID++;
				Message temp1 = new Message(currentUser.getUsername(), "Re: ", "\""+temp+"\"", ID);
				messageList.get(check-1).addChild(temp1);
				messageList.add(temp1);
				break;
			}
			
		}
		run();
	}

	// This function allows the user to change their current password.
	// The user is asked to provide the old password of the currentUser.
	// 		If the received password matches the currentUser password, then the user will be prompted to enter a new password.
	// 		If the received password doesn't match the currentUser password, then the user will be prompted to re-enter the password. 
	// 		The user is welcome to enter 'c' or 'C' to cancel the setting of a password and return to the menu.
	// Any password is allowed except 'c' or 'C' for allowing the user to quit out to the menu. 
	// Once entered, the user will be told "Password Accepted." and returned to the menu.
	private void setPassword(){
		while (true) {
			System.out.print("\nOld Password (\'C\' or \'c\' for Menu): ");
			String temp = input.nextLine();
			if (temp.equals(password)){
				System.out.print("\nNew Password: ");
				temp = input.nextLine();
				currentUser.setPassword(password, temp);
				password = temp;
				break;
			} else if(temp.equals("C") || temp.equals("c")) {
				break;
			} else {
				System.out.println("Invalid Password. Please Re-enter.");
			}
		}
		run();
	}

}
