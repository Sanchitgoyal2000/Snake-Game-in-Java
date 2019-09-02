import javax.swing.JFrame;
import java.awt.Color;
public class Main {
/*JFrame is like the new window where we can add new components 
	like buttons,labels*/
	public static void main(String[] args) 
	{
       JFrame f=new JFrame();
       f.setTitle("Snake Game");//name for above toolbar
       f.setBounds(10,10,905,700);
       //setBounds(x,y,width,height);
       //x y puts window at upper left corner at (x,y);
       f.setResizable(false);
       //we cant resize the window panel
       f.setVisible(true);
       //The setVisible() method in Java makes 
       //the GUI component visible to the user 
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       /*If you forget to call setDefaultCloseOperation() you
       will get JFrame.HIDE_ON_CLOSE by default. This can be 
       frustrating, because it looks like you have "killed" 
       the program, but it keeps on running, and you see no 
       frame.*/
       f.setBackground(Color.GRAY);//not working
       Gameplay gameplay=new Gameplay();
       f.add(gameplay);
	}
}
