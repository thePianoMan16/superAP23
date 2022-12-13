import pkg.*;
import pkg.Stack;

import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		Stack tugs = new Stack();
		/*tugs.push('5');
		tugs.push('+');
		tugs.push('4');
		tugs.push('6');
		while (!tugs.isEmpty()){
			System.out.print(tugs.pop());
		}*/
		String input = "(5+3)*(3+5)";
		int count = 0;
		int opCount = 0;
		int operators = 0;
		String temp;
		String ops = "";
		String output = "";
		while(count < input.length())
		{
			temp = input.substring(count, count+1);
			char temp2 = temp.charAt(0);
			if (temp.equals("(") || temp.equals(")")) {
				//tugs.push(temp.charAt(0));
			} else if (Character.isDigit(temp2)) {
				opCount++;
				ops += temp;
				if (opCount == 2) {
					output += ops + tugs.pop();
					ops = "";
					opCount = 0;
					operators--;
				}
			} else {
				tugs.push(temp.charAt(0));
				operators++;
			}
			count++;
		}
		for (int i=0; i<operators; i++) {
			output+=tugs.pop();
		}
		
		System.out.print(output);
	}
}
