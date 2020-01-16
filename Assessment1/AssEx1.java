/* 
    Instructions - Assessed Exercise 1
    - This exercise tests the things you've learnt in week 1.
    - Add your code to this file, leaving the main method unchanged (except for task 3).
    - Add all of your methods *below* the main method, in question order.
    - Each of the tasks involves writing a single method.
    - Total number of marks is 22.
    - Read the instructions for each task *carefully*.
    - Submit a single .java file on moodle by the deadline (details on moodle).
    - Do not change the name of the Java file, but ensure you add your name and 
        matric as requested below...
    - You do not need to add any additional import lines.
    - Everything you need to do these tasks is covered in the week 1 slides.
*/

/*
    Non task-specific marks:
     - Add a comment with your name and matric here - KYRIACOS SOPHOCLEOUS 2471955s [1 mark]
     - Clear code commenting [1 mark]
     - Use of correct Java variable naming conventions [1 mark]
*/

import java.util.Scanner;

public class AssEx1 {
    public static void main(String[] args) {
       String name = "Fred";
       
       /* Task 1 [2 marks]
           Create a method called welcome that prints the word "Hello" followed by whatever
           is stored in the variable 'name'. It will be called by the line below.
       */
       welcome(name);

       /* Task 2 [3 marks]
           Write a method called multiples that prints out all multiples of some integer m between m and
           another intger max in individual lines.
           For example multiples(3,11) should print:
           3
           6
           9
           Your method *must* use a loop
       */
       int m = 2;
       int max = 5;
       multiples(m,max);

       /* Task 3 [3 marks]
           Write a method called check that takes an integer as an argument and 
           returns a boolean value that is true if the integer is greater
           than 5 and less than 10. Call the method three times with the integers
           1, 6 and 12 respectively, printing the output each time on a new line. 
       */
       // Call the method and print the output three times below...
       
       System.out.println(check(1));
       System.out.println(check(6));
       System.out.println(check(12));
       
       /* Task 4 [6 marks]
           Write a method called calculator that prompts the user to input 
           an integer, an operator ('+' or '-') and finally another integer.
           The method should then perform the calculation and print the answer using
           String.format. The result of the claculation should be padded with 
           zeros to make it length 4. E.g. if the user enters:
           4
           -
           3
           The program should output:
           "Calculation: 4 - 3 = 0001"
           The inputs should be on separate lines.
           You don't need to do any error checking on the user input.
       */
       calculator();

       /* Task 5 [5 marks]
           Write a method called table that takes two integers as arguments that correspond to
           a number of rows and a number of columns respectively.
           If either integer is <0 or >= 10, the program should print:
           "Error, rows and columns must be >0 and <10"
           and return.
           If the number of rows and columns is between >=0 and <=9 the method
           should print a table with the table co-ordinates in each position. For example, if
           the number of rows is 2 and the number of columns 4:
             (0,0)  (0,1)  (0,2)  (0,3)
             (1,0)  (1,1)  (1,2)  (1,3)
           Important:
            - You *must* only use System.out.print and *not* System.out.println...
            - Separate each co-ordinate pair with a tab ('\t')
            - You may *not* use for loops! I.e. you *must* use a do or a while loop.
       */
       table(13,5);
       table(-2,7);
       table(3,6);
    }
    
    
    public static void welcome(String theName) {
    	System.out.println("Hello "+theName);
    }
   
    public static void multiples(int a,int b) { /*method is passed two integer variables. a: the number which is multiplied. b: the times we have to do the multiplication */
    	for (int i=1;i<=b;i++) {				/*for loop in order to move to the next multiplication. i is the counter*/
    		int result=a*i;						/*i is also used as in the multiplication */
    		System.out.println(result);
    	}
    }
    
    public static boolean check(int num) { /*method takes an integer as an argument and returns a boolean value*/
    	if (num>5 && num<10) { 			   /*if the integer is greater than 5 and less than 10, method returns true. If not method returns false*/
    		return true;
    	}else {
    		return false;
    	}
    }
    public static void calculator() {
    	Scanner s=new Scanner(System.in); /*declaration of scanner s*/
    	System.out.print("Number A: ");
    	int num1=s.nextInt(); 			  /*user has to insert a number and press enter. The number is saved in num1 variable*/				
    	s.nextLine();					  /*we use s.nextline() because the user pressed enter after he insert the  integer number. 
    									  The variable num1 is decleared as an integer variable which means it will only save the integer part of the input. "Enter" is string("\n") and the nextLine() removes the extra \n*/
    	System.out.print("Operator (+ OR -): "); 
    	String operator = s.nextLine();	  /*user chooses the operation*/
    	System.out.print("Number B: ");
    	int num2=s.nextInt();			  /*this is the same as above for num2 variable*/
    	s.nextLine();
    	int result=0;
    	if (operator.equals("+")) {		  /*an if statement to check which operator user inserted and do the appropriate mathematical operation*/
    		result=num1+num2;
    	}else if(operator.equals("-")){
    		result=num1-num2;
    	}
    	String line=String.format("Calculation: "+num1+" "+operator+" "+num2+" = "+"%04d",result); /*string formatting. we padded the result with zeros*/
    	System.out.println(line);		
    }
    public static void table(int rows,int columns) {	/*method takes two integer arguments. One for number of the rows and one for number of the columns. It returns nothing*/
    	if (rows<0 || rows>=10 || columns<0 || columns>=10) { /*If either integer is <0 or >= 10, the program prints error message and returns*/
    		System.out.print("Error, rows and columns must be >0 and <10\n");
    		return;
    	}
		int m=0;	/*declaration and initialization of m and n integer variables so we can use them in while loops*/
		int n=0;
		String line = String.format("(%d,%d)\t",m,n); /*declaration and initialization of line String variable. String formatting*/
    	while (m<rows) { 							  /*usage of 2 while loops. The first one to change and print the number which is referred to the rows*/
    		n=0;									  /*re-initialization of n variable to zero. When any single repeat of the first while loop ends, n number becomes zero again so we can use it in the second loop */
    		while (n<columns){						  /*Second loop to chance and print the number which is referred to the columns*/
    			line = String.format("(%d,%d)\t",m,n);
    			System.out.print(line);
    			n++;								  /*to move to the next repeat of the second loop*/
    		}
    		System.out.print("\n");
    		m++;									  /*to move to the next repeat of the first loop*/	
    	}
    }
}
