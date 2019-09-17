import javax.swing.JButton;
import javax.swing.JPanel;

public class numPanel extends JPanel{
	 // declare variables of all buttons
	 JButton btn1,btn2,btn3,btn4,btn5, btn6,btn7,btn8,btn9,btn0;
	 JButton ce,c,btndot,btnadd,btnsubtract,btndiv,btnmultiply,btnequals,btnpm,btndel,btnshift,btnMod; 
	 JButton btnA, btnB, btnC, btnD, btnE, btnF, btnOpenbrace, btnClosebrace;

	 // create a instance of all button in the panel constructor
	 public numPanel() {
		  btnshift =  new JButton ("↑");
		  btnMod =  new JButton ("Mod");
		  ce=new JButton("CE");
		  c=new JButton("C");
		  btn1=new JButton("1");
		  btn2=new JButton("2");
		  btn3=new JButton("3");
		  btn4=new JButton("4");
		  btn5=new JButton("5");
		  btn6=new JButton("6");
		  btn7=new JButton("7");
		  btn8=new JButton("8");
		  btn9=new JButton("9");
		  btn0=new JButton("0");
		  btndot=new JButton(".");
		  btnA =  new JButton("A");
		  btnB =  new JButton("B");
		  btnC =  new JButton("C");
		  btnD =  new JButton("D");
		  btnE =  new JButton("E");
		  btnF =  new JButton("F");
		  btnOpenbrace = new JButton ("(");
		  btnClosebrace = new JButton (")");
		  btnadd=new JButton("+");
		  btnsubtract=new JButton("-");
		  btndiv=new JButton("÷");
		  btnequals=new JButton("=");
		  btnmultiply=new JButton("x");	
		  btnpm=new JButton("+-");		  
		  btndel=new JButton("«");
		  
		  // add sequentially as they occur in the original calculator
		  add(btnshift);	add(btnMod);		add(ce);	add(c);		add(btndel);	add(btndiv);
		  add(btnA);		add(btnB);			add(btn7); 	add(btn8);	add(btn9);		add(btnmultiply);	
		  add(btnC); 		add(btnD);			add(btn4); 	add(btn5);	add(btn6); 		add(btnsubtract);	
		  add(btnE); 		add(btnF);			add(btn1);	add(btn2);	add(btn3);		add(btnadd);		  		 	  			  	 
		  add(btnOpenbrace);add(btnClosebrace);	add(btnpm);	add(btn0);	add(btndot);	add(btnequals);
	 }
}
