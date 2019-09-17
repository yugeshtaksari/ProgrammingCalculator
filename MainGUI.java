//============================================================================
//Name        : TestGUI.java
//Author      : Yugesh Taksari
//Course      : UTDallas CS 2336
//Version     : 1.0
//Copyright   : 2019
//Description : This is a main method of our calculator application that is 
// is specifically made for programming calculations. we have options to do 
// calculations in binary, hex, decimal and octal mode.
//
//============================================================================

import javax.swing.*;
public class MainGUI {
		  public static void main(String args[]) throws Exception
		  {
		   // create a instance of our calculator object
		   Calculator frame=new Calculator();
		   frame.setTitle("Calculator"); // title of the window
		   frame.setLocation(1020,50); // location of the window at startup
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		   frame.setSize(411,621); // dimension of the calculator window
		   frame.setVisible(true);
		  }

		
		
		
		
		
	}
