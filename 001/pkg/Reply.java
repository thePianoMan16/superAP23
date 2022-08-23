package pkg;
import java.util.*;
import java.io.*;

public class Reply extends Message {

	// Default Constructor
	public Reply() {
		super();
	}

	// Parameterized Constructor
	public Reply(String auth, String subj, String bod, int i) {
		super(auth, subj, bod, i); //TODO: this too
	}

	// Returns if this is a reply (true)
	public boolean isReply(){
		return true;
	}
}
