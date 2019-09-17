//============================================================================
//Name        : Calculator.java
//Author      : Yugesh Taksari
//Description : This is a calculator that is made to be functional as a programming
// calculator and is inspired by the looks and similar functionality of a windows OS
// programming calculators. Program used different GUI features to make this 
// calculator possible. This calculator uses action listener method extensively
// to get the commands from user and process that input.
//
//============================================================================

import javax.swing.*;
import java.util.Stack;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Calculator extends JFrame implements ActionListener , KeyListener, MouseListener {

	// fonts used in the program
	Font font1 = new Font("Segoe pro", Font.BOLD, 33); // display font
	Font font3 = new Font("Segoe pro", Font.BOLD, 14); // hexdecbin display
	Font font2 = new Font("Segoe pro", Font.PLAIN,21); // programmer
	Font font4 = new Font("Segoe pro", Font.BOLD, 19); // numpad

	// variabels used in the program
	JButton menu, word;
	JTextArea hexDisplay,decDisplay, octDisplay, binDisplay;
	static JTextField display,screen, minidisplay;
	JSeparator separate, separate1, hexup, decup, octup, binup, bindown, screenup;
	JLabel programmerLabel, ms, mstar, Lsh, Rsh, Or, Xor, Not, And,up, up1, numpad, numpad1;
	JRadioButton hexOption, decOption, octOption, binOption, Hex, Dec, Oct, Bin;
	String s1,s2,s3,s4,s5;
	static boolean hex, dec = true, oct, bin, firstOp= true,zeroFlag = false;
	String answer;
	int operation, wordMode = 0;
	static String minidisplayText = "", hexText = "", octText = "", binText = "";
	numPanel p1;
	Color backColor, numPadColor, btnColor, white,optionColor ;
	ImageIcon imageIcon, imageIcon1, menuIcon;
	NumberFormat numberFormat;


	public Calculator()throws Exception
	{
		// different color needed for the program
		UIManager.put("Button.font",font4);
		optionColor = new Color(40,147,251);
		backColor   = new Color(238,238,238);
		numPadColor = new Color(254,254,254);
		btnColor 	 = new Color(200,200,200);
		white 		 = new Color(255, 255, 255);
		numberFormat = NumberFormat.getNumberInstance(Locale.US);
		this.getContentPane().setLayout(null);

		// setup for the panel that contains all the working buttons
		p1 = new numPanel();
		p1.setLayout(new GridLayout(5,6));
		add(p1);
		p1.setBounds(2, 320, 406,280);

		// setting Image icon needed for number system options
		imageIcon = new ImageIcon("rec.png"); // load the image to a imageIcon
		imageIcon1 = new ImageIcon("blue.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image image1 = imageIcon1.getImage(); // transform it 
		Image newimg = image.getScaledInstance(12, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		Image newimg1 = image1.getScaledInstance(12, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);
		imageIcon1 = new ImageIcon(newimg1);

		// setting up menu button at the top which is inactive
		menuIcon = new ImageIcon("icon1.png");
		Image image2 = menuIcon.getImage(); // transform it
		Image newimg2 = image2.getScaledInstance(30, 20,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		menuIcon = new ImageIcon(newimg2);

		menu = new JButton(menuIcon);//new ImageIcon("menu.png"));//"≡");// 
		menu.setFont(font1);
		add(menu);
		menu.setBounds(8,14,30,20);

		// programmer label at the top
		programmerLabel = new JLabel("Programmer");
		programmerLabel.setFont(font2);
		programmerLabel.setBounds(60, -3, 240, 50);
		add(programmerLabel);

		// button to choose operation in hex mode
		Hex = new JRadioButton("HEX", imageIcon);
		Hex.setFont(font3);
		Hex.setBounds(-11, 122, 70, 20);
		add(Hex);
		Hex.setBorderPainted(false);

		// definition for the display to show hex number
		hexDisplay =new JTextArea("0",1,10);
		hexDisplay.setFont(font3);
		add(hexDisplay);
		hexDisplay.setBounds(60,122,351,20);
		hexDisplay.setEditable(false);

		// button to choose operation in decimal mode
		Dec = new JRadioButton("DEC", imageIcon1);
		Dec.setFont(font3);
		Dec.setBounds(-11, 143, 70, 20);
		add(Dec);	  
		Dec.setBorderPainted(false);
		Dec.setForeground(optionColor);


		// definition for the display to show decimal number
		decDisplay =new JTextArea("0",1,10);
		decDisplay.setFont(font3);
		add(decDisplay);
		decDisplay.setBounds(60,143,351,20);
		decDisplay.setEditable(false);

		// button to choose operation in octal mode
		Oct = new JRadioButton("OCT", imageIcon);
		Oct.setFont(font3);
		Oct.setBounds(-11, 164, 70, 20);
		add(Oct);	 
		Oct.setBorderPainted(false);

		// definition for the display to show octal number
		octDisplay =new JTextArea("0",1,10);
		octDisplay.setFont(font3);
		add(octDisplay);
		octDisplay.setBounds(60,164,351,20);
		octDisplay.setEditable(false);

		// button to choose operation in binary mode
		Bin = new JRadioButton("BIN", imageIcon);
		Bin.setFont(font3);
		Bin.setBounds(-11, 185, 70, 20);
		add(Bin);	
		Bin.setBorderPainted(false);

		// definition for the display to show binary number
		binDisplay =new JTextArea("0",2, 38);
		binDisplay.setFont(font3);
		add(binDisplay);
		binDisplay.setBounds(60,185,300,40);
		binDisplay.setEditable(false);
		binDisplay.setWrapStyleWord(true);
		binDisplay.setLineWrap(true);


		// button group of the number option to only allow to choose 1 at a time
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(Hex);
		bgroup.add(Dec);
		bgroup.add(Oct);
		bgroup.add(Bin);
		Hex.addActionListener(this);
		Dec.addActionListener(this);
		Oct.addActionListener(this);
		Bin.addActionListener(this);
		

		// separator used in program to create a separation effect
		screenup = new JSeparator(); screenup.setBounds(-11, 65,420, 390);
		hexup = new JSeparator(); hexup.setBounds(-11, 117,420, 390);
		decup = new JSeparator(); decup.setBounds(-11, 138,420, 390);
		octup = new JSeparator(); octup.setBounds(-11, 159,420, 390);
		binup = new JSeparator(); binup.setBounds(-11, 180,420, 390);
		bindown = new JSeparator(); bindown.setBounds(-11,220, 420, 390);
		add(screenup); screenup.setVisible(false);
		add(hexup); hexup.setVisible(false);
		add(decup); decup.setVisible(false);
		add(octup); octup.setVisible(false);
		add(binup); binup.setVisible(false);
		add(bindown); bindown.setVisible(false);
		separate = new JSeparator();
		add(separate);
		separate.setBounds(4, 237, 403, 390);
		separate1 = new JSeparator();
		add(separate1);
		separate1.setBounds(4, 273, 403, 390);

		// unused or inactive buttons for num buttons as in orginal calculator
		numpad = new JLabel(new ImageIcon("numpad1.png"));
		add(numpad);
		numpad.setBounds(13, 254, 50, 15);
		numpad1 = new JLabel(new ImageIcon("numpad2.png"));
		add(numpad1);
		numpad1.setBounds(78, 254, 50, 17);

		// word button definition
		word = new JButton("QWORD");
		word.setFont(new Font("Segoe pro", Font.BOLD, 14));
		add(word);
		word.setBounds(182, 250, 60, 18);
		word.setBorder(null);
		word.addActionListener(this);

		
		// setting up the unused labels/buttons as directed by the assignment
		up = new JLabel("↑");
		up1 = new JLabel("↑");
		Xor = new JLabel("Xor");
		Not = new JLabel("Not");
		And = new JLabel("And");
		Or = new JLabel("Or");
		Rsh = new JLabel("Rsh");
		Lsh = new JLabel("Lsh");
		mstar = new JLabel("M▾");
		ms = new JLabel("MS");
		add(ms);
		add(mstar);
		add(Lsh);
		add(up);
		add(Rsh);
		add(up1);
		add(Or);
		add(Xor);
		add(Not);
		add(And);
		And.setBounds(359, 289, 30, 10);
		Not.setBounds(292, 289, 30, 10);
		Xor.setBounds(228, 289, 30, 10);
		Or.setBounds(164, 289, 30, 10);
		up1.setBounds(102, 303, 10, 10);
		up.setBounds(32, 303, 10, 10);
		Lsh.setBounds(25, 289, 30, 10);
		ms.setBounds(294, 254, 50, 10);
		mstar.setBounds(361, 254, 50, 10);
		Rsh.setBounds(94, 289, 30, 10);
		
		// descriptio of the mini display at the top right that tracks the operation
		minidisplay = new JTextField();
		minidisplay.setFont(new Font("Segoe pro", Font.PLAIN, 15));
		minidisplay.setBackground(new Color(238,238,238));
		minidisplay.setHorizontalAlignment(JTextField.TRAILING); // sets the text alignment
		add(minidisplay);
		minidisplay.setBounds(10,40,385,19);
		minidisplay.setEditable(false);
		minidisplay.setText("");
		minidisplay.setBorder(null);

		// display is used for getting numbers for operations
		display = new JTextField();
		display.setBounds(0,50,406,54);
		display.setEditable(false);
		display.setBorder(null);

		// screen is used to show changes in the display to users
		screen = new JTextField("0");
		screen.setFont(font1);
		screen.setBackground(new Color(238,238,238));
		screen.setHorizontalAlignment(JTextField.TRAILING);
		setLayout(null);
		add(screen);
		screen.setBounds(10,70,385,44);
		screen.setEditable(false);
		screen.setBorder(null);


		//color section
		hexDisplay.setBackground(backColor);
		decDisplay.setBackground(backColor);
		octDisplay.setBackground(backColor);
		binDisplay.setBackground(backColor);
		
		// Option to change the numpad Color
//		p1.btn1.setBackground(numPadColor);
//		p1.btn2.setBackground(numPadColor);
//		p1.btn3.setBackground(numPadColor);
//		p1.btn4.setBackground(numPadColor);
//		p1.btn5.setBackground(numPadColor);
//		p1.btn6.setBackground(numPadColor);
//		p1.btn7.setBackground(numPadColor);
//		p1.btn8.setBackground(numPadColor);
//		p1.btn9.setBackground(numPadColor);
//		p1.btn0.setBackground(numPadColor);

		// startup setting of calculator 
		p1.btnequals.setContentAreaFilled(false);
		p1.btnA.setEnabled(false);	// deselect all A-F buttons
		p1.btnB.setEnabled(false);
		p1.btnC.setEnabled(false);
		p1.btnD.setEnabled(false);
		p1.btnE.setEnabled(false);
		p1.btnF.setEnabled(false);
		p1.btndot.setEnabled(false);


		// add action listener for capturing user's clicking of buttons
		p1.btn0.addActionListener(this);
		p1.btn1.addActionListener(this);
		p1.btn2.addActionListener(this);
		p1.btn3.addActionListener(this);
		p1.btn4.addActionListener(this);
		p1.btn5.addActionListener(this);
		p1.btn6.addActionListener(this);
		p1.btn7.addActionListener(this);
		p1.btn8.addActionListener(this);
		p1.btn9.addActionListener(this);
		p1.btnA.addActionListener(this);
		p1.btnB.addActionListener(this);
		p1.btnC.addActionListener(this);
		p1.btnD.addActionListener(this);
		p1.btnE.addActionListener(this);
		p1.btnF.addActionListener(this);
		p1.btnOpenbrace.addActionListener(this);
		p1.btnClosebrace.addActionListener(this);
		p1.btnpm.addActionListener(this);
		p1.btndot.addActionListener(this);
		p1.btnequals.addActionListener(this);
		p1.btnadd.addActionListener(this);
		p1.btnsubtract.addActionListener(this);
		p1.btnmultiply.addActionListener(this);
		p1.btndiv.addActionListener(this);
		p1.btndel.addActionListener(this);
		p1.c.addActionListener(this);
		p1.ce.addActionListener(this);
		p1.btnMod.addActionListener(this);
		p1.btnshift.addActionListener(this);
		
		// add Key Listener for keyboard enabled calculator experience
		p1.btn0.addKeyListener(this);
		p1.btn1.addKeyListener(this);
		p1.btn2.addKeyListener(this);
		p1.btn3.addKeyListener(this);
		p1.btn4.addKeyListener(this);
		p1.btn5.addKeyListener(this);
		p1.btn6.addKeyListener(this);
		p1.btn7.addKeyListener(this);
		p1.btn8.addKeyListener(this);
		p1.btn9.addKeyListener(this);
		p1.btnA.addKeyListener(this);
		p1.btnB.addKeyListener(this);
		p1.btnC.addKeyListener(this);
		p1.btnD.addKeyListener(this);
		p1.btnE.addKeyListener(this);
		p1.btnF.addKeyListener(this);
		p1.btnOpenbrace.addKeyListener(this);
		p1.btnClosebrace.addKeyListener(this);
		p1.btnpm.addKeyListener(this);
		p1.btndot.addKeyListener(this);
		p1.btnequals.addKeyListener(this);
		p1.btnadd.addKeyListener(this);
		p1.btnsubtract.addKeyListener(this);
		p1.btnmultiply.addKeyListener(this);
		p1.btndiv.addKeyListener(this);
		p1.btndel.addKeyListener(this);
		p1.c.addKeyListener(this);
		p1.ce.addKeyListener(this);
		p1.btnMod.addKeyListener(this);
		p1.btnshift.addKeyListener(this);
		Lsh.addKeyListener(this);
		Rsh.addKeyListener(this);
		Or.addKeyListener(this);
		Xor.addKeyListener(this);
		Not.addKeyListener(this);
		And.addKeyListener(this);
		mstar.addKeyListener(this);
		ms.addKeyListener(this);
		word.addKeyListener(this);
		numpad.addKeyListener(this);
		numpad1.addKeyListener(this);
		hexDisplay.addKeyListener(this);
		decDisplay.addKeyListener(this);
		octDisplay.addKeyListener(this);
		binDisplay.addKeyListener(this);
		display.addKeyListener(this);
		screen.addKeyListener(this);
		programmerLabel.addKeyListener(this);
		menu.addKeyListener(this);
		separate.addKeyListener(this);
		separate1.addKeyListener(this);
		
		// add mouse listener for hovering effects
		screen.addMouseListener(this);
		hexDisplay.addMouseListener(this);
		decDisplay.addMouseListener(this);
		octDisplay.addMouseListener(this);
		binDisplay.addMouseListener(this);	  
	}
	
	// method to change the active buttons according to the mode chose
	public void hexActiveButtons() {
		p1.btnA.setEnabled(true);		  p1.btnD.setEnabled(true);
		p1.btnB.setEnabled(true);		  p1.btnE.setEnabled(true);
		p1.btnC.setEnabled(true);		  p1.btnF.setEnabled(true);
		p1.btn1.setEnabled(true);		  p1.btn6.setEnabled(true);
		p1.btn2.setEnabled(true);		  p1.btn7.setEnabled(true);
		p1.btn3.setEnabled(true);		  p1.btn8.setEnabled(true);		  
		p1.btn4.setEnabled(true);		  p1.btn9.setEnabled(true);
		p1.btn5.setEnabled(true);		  p1.btn0.setEnabled(true);
		p1.btndot.setEnabled(false);
	}
	public void decActiveButtons() {
		p1.btnA.setEnabled(false);		  p1.btnD.setEnabled(false);
		p1.btnB.setEnabled(false);		  p1.btnE.setEnabled(false);
		p1.btnC.setEnabled(false);		  p1.btnF.setEnabled(false);
		p1.btn1.setEnabled(true);		  p1.btn6.setEnabled(true);
		p1.btn2.setEnabled(true);		  p1.btn7.setEnabled(true);
		p1.btn3.setEnabled(true);		  p1.btn8.setEnabled(true);		  
		p1.btn4.setEnabled(true);		  p1.btn9.setEnabled(true);
		p1.btn5.setEnabled(true);		  p1.btn0.setEnabled(true);
		p1.btndot.setEnabled(false);
	}
	public void octActiveButtons() {
		p1.btnA.setEnabled(false);		  p1.btnD.setEnabled(false);
		p1.btnB.setEnabled(false);		  p1.btnE.setEnabled(false);
		p1.btnC.setEnabled(false);		  p1.btnF.setEnabled(false);
		p1.btn1.setEnabled(true);		  p1.btn6.setEnabled(true);
		p1.btn2.setEnabled(true);		  p1.btn7.setEnabled(true);
		p1.btn3.setEnabled(true);		  p1.btn8.setEnabled(false);		  
		p1.btn4.setEnabled(true);		  p1.btn9.setEnabled(false);
		p1.btn5.setEnabled(true);		  p1.btn0.setEnabled(true);
		p1.btndot.setEnabled(false);



	}
	public void binActiveButtons() {
		p1.btnA.setEnabled(false);		  p1.btnD.setEnabled(false);
		p1.btnB.setEnabled(false);		  p1.btnE.setEnabled(false);
		p1.btnC.setEnabled(false);		  p1.btnF.setEnabled(false);
		p1.btn1.setEnabled(true);		  p1.btn6.setEnabled(false);
		p1.btn2.setEnabled(false);		  p1.btn7.setEnabled(false);
		p1.btn3.setEnabled(false);		  p1.btn8.setEnabled(false);		  
		p1.btn4.setEnabled(false);		  p1.btn9.setEnabled(false);
		p1.btn5.setEnabled(false);		  p1.btn0.setEnabled(true);
		p1.btndot.setEnabled(false);


	}

	// methods to change the icon of the number system option to show as blue when selected
	public void hexIconSet() {
		Hex.setIcon(imageIcon1);
		Dec.setIcon(imageIcon);
		Oct.setIcon(imageIcon);
		Bin.setIcon(imageIcon);
	}
	public void decIconSet() {
		Hex.setIcon(imageIcon);
		Dec.setIcon(imageIcon1);
		Oct.setIcon(imageIcon);
		Bin.setIcon(imageIcon);

	}	
	public void octIconSet() {
		Hex.setIcon(imageIcon);
		Dec.setIcon(imageIcon);
		Oct.setIcon(imageIcon1);
		Bin.setIcon(imageIcon);

	}	
	public void binIconSet() {
		Hex.setIcon(imageIcon);
		Dec.setIcon(imageIcon);
		Oct.setIcon(imageIcon);
		Bin.setIcon(imageIcon1);
	}
	
	// methods to set the numbers in different number displays according to the mode choosen
	public void hexView() throws Exception{
		hexDisplay.setText(display.getText());
		decDisplay.setText(String.valueOf(Long.parseLong(display.getText(),16)));
		octDisplay.setText(Long.toOctalString(Long.parseLong(display.getText(),16)));
		binDisplay.setText(Long.toBinaryString(Long.parseLong(display.getText(),16)));
		spacing();
	}

	public void decView() throws Exception{
		hexDisplay.setText(Long.toHexString(Long.valueOf(display.getText())).toUpperCase());
		decDisplay.setText(String.valueOf(Long.valueOf(display.getText())));
		octDisplay.setText(Long.toOctalString(Long.valueOf(display.getText())));
		binDisplay.setText(Long.toBinaryString(Long.valueOf(display.getText())));
		spacing();
	}

	public void octView() throws Exception{
		hexDisplay.setText(Long.toHexString(Long.parseLong(display.getText(),8)).toUpperCase());
		decDisplay.setText(String.valueOf(Long.parseLong(display.getText(),8)));
		octDisplay.setText(String.valueOf(Long.valueOf(display.getText())));
		binDisplay.setText(Long.toBinaryString(Long.parseLong(display.getText(),8)));
		spacing();

	}

	public  void binView() throws Exception{
		hexDisplay.setText(Long.toHexString(Long.parseLong(display.getText(),2)).toUpperCase());
		decDisplay.setText(String.valueOf(Long.parseLong(display.getText(),2)));
		octDisplay.setText(Long.toOctalString(Long.parseLong(display.getText(),2)));
		binDisplay.setText(String.valueOf(Long.valueOf(display.getText())));
		spacing();

	}
	// spacing settings for all number display settings
	public void spacing() {
		int len =  binDisplay.getText().length();
		for (int i =len, j = 0;j <(len/4+1) ; ) {
			binDisplay.insert(" ", i); i -=4;j++;
		}
		int lenh =  hexDisplay.getText().length();
		for (int i =lenh, j = 0;j <(lenh/4+1) ; ) {
			hexDisplay.insert(" ", i); i -=4;j++;
		}
		int lend =  decDisplay.getText().length();
		for (int i =lend-3, j = 0;j <(lend/3) ; ) {
			if(i>=1)
				decDisplay.insert(",", i); i -=3;j++;
		}
		int leno =  octDisplay.getText().length();
		for (int i =leno, j = 0;j <(leno/3+1) ; ) {
			octDisplay.insert(" ", i); i -=3;j++;
		}
	}

	// spacing setting
	public void mainDisplaySpacing() {
		if (dec)
			screen.setText(numberFormat.format(Long.valueOf(screen.getText())));

		else
			screen.setText(screen.getText());
	}

	// method to clear the contents of all display variables
	public void clear() {
		display.setText("");
		screen.setText("0");
		hexDisplay.setText("");
		decDisplay.setText("");
		octDisplay.setText("");
		binDisplay.setText("");
		p1.btnequals.setContentAreaFilled(false);
		firstOp = true;
	}

	// method to handle all button actions by user
	public void actionPerformed(ActionEvent e)
	{	// action listener for choosing different numbering system and bit limit
		if(e.getSource()==word) {
			wordMode++;
			if(wordMode == 4) wordMode = 0;
			if(wordMode == 1) 		word.setText("DWORD");
			else if(wordMode == 2)  	word.setText("WORD");
			else if(wordMode == 3) 	word.setText("BYTE");
			else if(wordMode == 0) 	word.setText("QWORD");
			word.setBorder(null);

		}
		if(e.getSource()== Hex) {
			Calculator.hex = true;
			Calculator.dec = false; Calculator.bin = false; Calculator.oct =false;
			Hex.setForeground(optionColor);
			Dec.setForeground(Color.BLACK);
			Oct.setForeground(Color.BLACK);
			Bin.setForeground(Color.BLACK);	  
			hexActiveButtons();
			hexIconSet();
			e.setSource(p1.ce);
			actionPerformed(e);


		}
		if(e.getSource()== Dec) {
			Calculator.dec = true;
			Calculator.hex = false; Calculator.bin = false; Calculator.oct =false;
			Hex.setForeground(Color.BLACK);
			Dec.setForeground(optionColor);
			Oct.setForeground(Color.BLACK);
			Bin.setForeground(Color.BLACK);
			decActiveButtons();		 
			decIconSet();
			e.setSource(p1.ce);
			actionPerformed(e);

		}	  
		if(e.getSource()== Oct) {
			Calculator.oct = true;
			Calculator.dec = false; Calculator.bin = false; Calculator.hex =false;
			Hex.setForeground(Color.BLACK);
			Dec.setForeground(Color.BLACK);
			Oct.setForeground(optionColor);
			Bin.setForeground(Color.BLACK);
			octActiveButtons();
			octIconSet();
			e.setSource(p1.ce);
			actionPerformed(e);

		}	  
		if(e.getSource()== Bin) {
			Calculator.bin = true;
			Calculator.dec = false; Calculator.hex = false; Calculator.oct =false;
			Hex.setForeground(Color.BLACK);
			Dec.setForeground(Color.BLACK);
			Oct.setForeground(Color.BLACK);
			Bin.setForeground(optionColor);
			binActiveButtons();
			binIconSet();
			e.setSource(p1.ce);
			actionPerformed(e);

		}

		// Action Listener for all the buttons and operations

		if((e.getSource()==p1.btn1)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}

			s2="1";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}
		if((e.getSource()==p1.btn2)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="2";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}
		if((e.getSource()==p1.btn3)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="3";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getSource()==p1.btn4)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="4";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();
		}
		if((e.getSource()==p1.btn5)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="5";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();
		}
		if((e.getSource()==p1.btn6)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="6";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getSource()==p1.btn7)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="7";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getSource()==p1.btn8)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="8";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getSource()==p1.btn9)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="9";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getSource()==p1.btn0)&&(display.getText().length() < 18))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}

			s2="0";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}

		if((e.getSource()==p1.btnA)&&(display.getText().length() < 15))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}

			s2="A";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}

		if((e.getSource()==p1.btnB)&&(display.getText().length() < 15))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="B";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}
		if((e.getSource()==p1.btnC)&&(display.getText().length() < 15))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}

			s2="C";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}

		if((e.getSource()==p1.btnD)&&(display.getText().length() < 15))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}

			s2="D";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}
		if((e.getSource()==p1.btnE)&&(display.getText().length() < 15))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="E";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}

		if((e.getSource()==p1.btnF)&&(display.getText().length() < 15))
		{
			if(operation != 1)  
				s1=display.getText();
			else {
				s1 = "";
				operation = 0;}
			s2="F";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}

		if(e.getSource()==p1.btndel && (display.getText().length()>0))
		{
			int index = display.getText().length()-1;
			if (index!=0) {
				StringBuilder sb = new StringBuilder(display.getText());
				sb.deleteCharAt(index);
				s3 = sb.toString();}
			else
				s3 = "0";

			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();
		}

		if(e.getSource()==p1.btnshift)
		{
			p1.btnequals.setContentAreaFilled(false);
			if (Lsh.getText() == "Lsh") {
				Lsh.setText("RoL");
				Rsh.setText("RoR");}

			else {
				Lsh.setText("Lsh");
				Rsh.setText("Rsh");
			}
		}

		if(e.getSource()==p1.btndot)
		{	p1.btnequals.setContentAreaFilled(false);
			s1=display.getText();
			s2=".";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();
		}
		if(e.getSource()==p1.btnadd)
		{	p1.btnequals.setContentAreaFilled(false);
			s4=display.getText();
			display.setText("");
			operation=10;
			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" + ");
			minidisplay.setText(minidisplayText);

		}
		if(e.getSource()==p1.btnmultiply)
		{	p1.btnequals.setContentAreaFilled(false);
			s4=display.getText();
			display.setText("");
			operation=1;
			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" * ");
			minidisplay.setText(minidisplayText);

		}
		if(e.getSource()==p1.btnsubtract)
		{	p1.btnequals.setContentAreaFilled(false);
			s4=display.getText();
			display.setText("");
			operation=2;
			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" - ");
			minidisplay.setText(minidisplayText);

		}
		if(e.getSource()==p1.btndiv)
		{	p1.btnequals.setContentAreaFilled(false);
			s4=display.getText();
			display.setText("");
			operation=3;
			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" / ");
			minidisplay.setText(minidisplayText);

		}
		if(e.getSource()==p1.btnOpenbrace)
		{	p1.btnequals.setContentAreaFilled(false);
			s4=display.getText();
			display.setText("");
			screen.setText("");

			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" ( ");
			minidisplay.setText(minidisplayText);

		}
		if(e.getSource()==p1.btnClosebrace)
		{	p1.btnequals.setContentAreaFilled(false);
			s4=display.getText();
			display.setText("");
			screen.setText("");
			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" ) ");
			minidisplay.setText(minidisplayText);
		}

		if(e.getSource()==p1.c)
		{	p1.btnequals.setContentAreaFilled(false);
			display.setText("");
			screen.setText("0");
			minidisplay.setText("");
			minidisplayText = "";
			hexDisplay.setText("");
			decDisplay.setText("");
			octDisplay.setText("");
			binDisplay.setText("");
			p1.btnequals.setContentAreaFilled(false);
			s1 = ""; s2 = ""; s3 = ""; s4 = ""; s5= "";
			firstOp = true;
		}
		if(e.getSource()==p1.btnMod)
		{	p1.btnequals.setContentAreaFilled(false);
			operation = 9;
			s4 = display.getText();
			display.setText("");
			minidisplayText = minidisplayText + s4;
			minidisplayText = minidisplayText + (" Mod ");
			minidisplay.setText(minidisplayText);
		}

		if(e.getSource()==p1.ce)
		{	p1.btnequals.setContentAreaFilled(false);
			display.setText("");
			screen.setText("0");
			hexDisplay.setText("0");
			decDisplay.setText("0");
			octDisplay.setText("0");
			binDisplay.setText("0");
			firstOp = true;
		}

		if(e.getSource()==p1.btnpm)
		{
			p1.btnequals.setContentAreaFilled(false);
			s4 = display.getText();
			s4 = " ( "+s4+" - 2 * "+s4+" )";
			screen.setText("-"+display.getText());

			operation=7;
		}
		
		if(e.getSource()==p1.btnequals)
		{
			s5=display.getText();
			p1.btnequals.setContentAreaFilled(true);
			p1.btnequals.setBackground(new Color(40,147,251));
			if(operation==7) { // for negation
				minidisplayText = minidisplayText+s4;
			}
			else {
				minidisplayText = minidisplayText + display.getText();
			}
			minidisplay.setText("");	

			// get the answer in correct number system
			if ((operation != 9)) {
				if(dec) {
					answer = String.valueOf(calculate(minidisplayText));
				}
				else if(hex)
					answer = Long.toHexString(calculate(minidisplayText)).toUpperCase();
				else if(bin)
					answer = Long.toBinaryString(calculate(minidisplayText));
				else if(oct)
					answer = Long.toOctalString(calculate(minidisplayText));}
			// operation in mod
			else
				answer = String.valueOf(Long.valueOf(s4)%Long.valueOf(s5));
			// display answer to the screen
			screen.setText(answer);
			display.setText(answer);
			mainDisplaySpacing();
			setAllDisplay();
			
			//reset
			minidisplayText = "";
			minidisplay.setText("");
			operation = 1;
		}
	}

	//method to set all display according to the ongoing flow of program
	public void setAllDisplay() {
		try {
			mainDisplaySpacing();
			if(hex)		hexView();
			if(dec)		decView();
			if(oct)		octView();
			if(bin)		binView();
		} catch (Exception e1) {}

	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {
		if (e.getSource()== hexDisplay) {
			hexup.setForeground(white);
			decup.setForeground(white);
			hexup.setVisible(true);
			decup.setVisible(true);}
		
		if (e.getSource() == decDisplay) {
			octup.setForeground(white);
			decup.setForeground(white);
			octup.setVisible(true);
			decup.setVisible(true);}
		
		if (e.getSource() == octDisplay) {
			octup.setForeground(white);
			binup.setForeground(white);
			octup.setVisible(true);
			binup.setVisible(true);}
		
		if (e.getSource() == binDisplay) {
			binup.setForeground(white);
			bindown.setForeground(white);
			bindown.setVisible(true);
			binup.setVisible(true);	}
		
		if (e.getSource() == screen) {
			hexup.setForeground(white);
			screenup.setForeground(white);
			hexup.setVisible(true);
			screenup.setVisible(true);}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource()== hexDisplay) {
			hexup.setVisible(false);
			decup.setVisible(false);
		}
		if (e.getSource() == decDisplay) {
			octup.setVisible(false);
			decup.setVisible(false);
		}
		if (e.getSource() == octDisplay) {
			octup.setVisible(false);
			binup.setVisible(false);
		}
		if (e.getSource() == binDisplay) {
			bindown.setVisible(false);
			binup.setVisible(false);
		}
		if (e.getSource() == screen) {
			screenup.setVisible(false);
			hexup.setVisible(false);
		}

	}

	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		if((e.getKeyCode()== KeyEvent.VK_1)&&(display.getText().length() < 18))
		{
			s1=display.getText();
			clear();
			s2="1";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

			return;
		}
		if((e.getKeyCode()==KeyEvent.VK_2)&&(display.getText().length() < 18) && (p1.btn2.isEnabled()))
		{
			s1=display.getText();
			s2="2";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();
			return;
		}
		if((e.getKeyCode()==KeyEvent.VK_3)&&(display.getText().length() < 18) && (p1.btn3.isEnabled()))
		{
			s1=display.getText();
			s2="3";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_4)&&(display.getText().length() < 18)&& (p1.btn4.isEnabled()))
		{
			s1=display.getText();
			s2="4";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_5)&&(display.getText().length() < 18)&& (p1.btn5.isEnabled()))
		{
			s1=display.getText();
			s2="5";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_6)&&(display.getText().length() < 18)&& (p1.btn6.isEnabled()))
		{
			s1=display.getText();
			s2="6";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);	
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_7)&&(display.getText().length() < 18)&& (p1.btn7.isEnabled()))
		{
			s1=display.getText();
			s2="7";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);		
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_8)&&(display.getText().length() < 18)&& (p1.btn8.isEnabled()))
		{
			s1=display.getText();
			s2="8";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);		
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_9)&&(display.getText().length() < 18)&& (p1.btn9.isEnabled()))
		{
			s1=display.getText();
			s2="9";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();

		}
		if((e.getKeyCode()==KeyEvent.VK_0)&&(display.getText().length() < 18)&& (p1.btn0.isEnabled()))
		{
			s1=display.getText();
			s2="0";
			s3=s1+s2;
			display.setText(s3);
			screen.setText(s3);
			setAllDisplay();
		}
	}

	public static long calculate(String expressions) 
	{ 
		char[] tokens = expressions.toCharArray(); 

		Stack<Long> values = new Stack<Long>(); 		//Stack for numbers as values 
		Stack<Character> ops = new Stack<Character>();  // Stack for Operators: 'ops' 

		for (int i = 0; i < tokens.length; i++) 
		{ 

			if (tokens[i] == ' ')  			// Current char token is a whitespace, skip it 
				continue; 
			// Current token is a number or letters from A-F, push it to stack for numbers 
			if ((tokens[i] >= '0' && tokens[i] <= '9') ||(tokens[i] >= 'A' && tokens[i] <= 'F') ) 
			{ 
				StringBuffer sbuf = new StringBuffer();  
				// There might be more than one digits in number 
				
				while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9')|| (tokens[i] >= 'A' && tokens[i] <= 'F') )) 
					sbuf.append(tokens[i++]); 
				
				// get value according to the mode setup
				if(dec)
					values.push(Long.parseLong(sbuf.toString())); 
				if(hex)
					values.push(Long.parseLong(sbuf.toString(),16)); 
				if(oct)
					values.push(Long.parseLong(sbuf.toString(),8)); 
				if(bin)
					values.push(Long.parseLong(sbuf.toString(),2)); 

			} 

			// conditional statement when the token is an open brace
			else if (tokens[i] == '(') 
				ops.push(tokens[i]); 

			// conditional statement when the token is an close brace
			else if (tokens[i] == ')') 
			{ 
				while (ops.peek() != '(') 
					values.push(applyOp(ops.pop(), values.pop(), values.pop())); 
				ops.pop(); 
			} 

			// Conditional statement for other operators. 
			else if (tokens[i] == '+' || tokens[i] == '-' || 
					tokens[i] == '*' || tokens[i] == '/') 
			{ 
				// While top of 'ops' has same or greater precedence to current 
				// token, which is an operator. Apply operator on top of 'ops' 
				// to top two elements in values stack 
				while (!ops.empty() && precedenceCheck(tokens[i], ops.peek())) 
					values.push(applyOp(ops.pop(), values.pop(), values.pop())); 

				// Push current token to 'ops'. 
				ops.push(tokens[i]); 
			} 
		} 

		// Entire expressions has been parsed at this point, apply remaining 
		// ops to remaining values 
		while (!ops.empty()) 
			values.push(applyOp(ops.pop(), values.pop(), values.pop())); 

		// Top in the 'values' stack will contains result, return it 
		return values.pop(); 
	} 

	// Returns true if 'op2' has higher or same precedence as 'op1', 
	// otherwise returns false. 
	public static boolean precedenceCheck(char op1, char op2) 
	{  // method to check the precedence of the expression
		if (op2 == '(' || op2 == ')') 
			return false; 
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
			return false; 
		else
			return true; 
	} 

	// A  method to apply an operator '+,-,/,*'
	public static long applyOp(char op, long b, long a) 
	{ 
		switch (op) 
		{ 
		case '+': 
			return a + b; 
		case '-': 
			return a - b; 
		case '*': 
			return a * b; 
		case '/': 
			if (b == 0)
			{zeroFlag = true; //exception Handling
			minidisplayText = "";
			screen.setText("Cannot divide by Zero");
			throw new
			UnsupportedOperationException("Cannot divide by zero"); }
			return a / b; 
		} 
		return 0; 
	}}