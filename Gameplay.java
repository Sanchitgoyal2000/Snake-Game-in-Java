import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
//extend jpanel because f.add(gameplay) should be of component type
public class Gameplay extends JPanel implements KeyListener,ActionListener
{
	private static final long serialVersionUID = 1L;

	private ImageIcon titleImage;
	
	private int[] snakexlength=new int[750];
	private int[] snakeylength=new int[750];
	
	private boolean right=false;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakeimage;
	
	private Timer timer;//for speed of snake when key is pressed
	private int delay=100;
	private int moves=0;
	private int lengthofsnake=3;//intially
	
	private int [] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625,650,
			675,700,725,750,775,800,825,850};
	private int [] enemyypos= {75,100,125,150,175,200,225,250,275,300,325,
			350,375,400,425,450,475,500,525,550,575,600,625};
	
	private int score=0;
	private ImageIcon enemyimage;
	private Random random=new Random();
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(23);// only generating index
	//and not value
	public Gameplay()  //caling constructor
	{	
		addKeyListener(this);//object of same class
		//actionperformed method will be called
		setFocusable(true);//necessary
		setFocusTraversalKeysEnabled(true);
		timer =new Timer(delay,this);//for snake speed
		// this is object of same component class
		// delay is for speed
		timer.start();
	}
 /*The paint( ) method is automatically called by the 
  * appletviewer or the web browser whenever the applet 
  * needs to be refreshed.
  */
  public void paint(Graphics g)
  {
	  if(moves==0)
	  {
		  snakexlength[0]=100;
		  snakexlength[1]=75;
		  snakexlength[2]=50;
		  snakeylength[0]=100;
		  snakeylength[1]=100;
		  snakeylength[2]=100;
	  }
	  
	  //border of title image
	  g.setColor(Color.white);
	  g.drawRect(24, 10, 851, 55);
	  titleImage=new ImageIcon("snaketitle.jpg");
	  //titleimage ia an object type
	  titleImage.paintIcon(this,g,25,11);
	//border of gameplay
	  g.setColor(Color.white);
	  g.drawRect(24, 74, 851, 577);
	  g.setColor(Color.black);
	  g.fillRect(25, 75, 850, 576);
	  
	  //draw the score
	  g.setColor(Color.white);
      g.setFont(new Font("arial",Font.PLAIN,14));
      g.drawString("Scores: "+score, 780, 30);
      
      //draw the score
	  g.setColor(Color.white);
      g.setFont(new Font("arial",Font.PLAIN,14));
      g.drawString("Length: "+lengthofsnake, 780, 50);
      //coordinates for text
	  rightmouth=new ImageIcon("rightmouth.png");
      rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
	  //to print mouth of snake
	  for(int a=0;a<lengthofsnake;a++)
	  {
		  if(a==0&&right)
		  {
		   rightmouth=new ImageIcon("rightmouth.png");
           rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
		  }
		  if(a==0&&left)
		  {
			  leftmouth=new ImageIcon("leftmouth.png");
           leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
		  }
		  if(a==0&&up)
		  {
			  upmouth=new ImageIcon("upmouth.png");
           upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
		  }
		  if(a==0&&down)
		  {
			  downmouth=new ImageIcon("downmouth.png");
           downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
		  }
		  if(a!=0)//to print snake body
		  {
			  snakeimage=new ImageIcon("snakeimage.png");
			  snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
		  }
	  }
	  enemyimage =new ImageIcon("enemy.png");
	  enemyimage.paintIcon(this, g, enemyxpos[xpos],enemyypos[ypos]);
	  if((enemyxpos[xpos]==snakexlength[0]) && (enemyypos[ypos]==snakeylength[0]))
	  {
		  score++;
		  lengthofsnake++;
		  xpos=random.nextInt(34);
		  ypos=random.nextInt(23);
	  }
	  
	  for(int b=1;b<lengthofsnake;b++)
	  {
		  if(snakexlength[b]==snakexlength[0]&&snakeylength[b]==snakeylength[0])
		  {
			  right=false;
			  left=false;
			  up=false;
			  down=false;
			  
			  g.setColor(Color.white);
			  g.setFont(new Font("arial",Font.BOLD,50));
			  g.drawString("Game Over", 300, 300);
			  
			  g.setColor(Color.white);
			  g.setFont(new Font("arial",Font.BOLD,20));
			  g.drawString("Press Space To Restart", 350, 340);
		  }
	  }
	    //g.dispose();//not necessary
  }
	@Override
	public void actionPerformed(ActionEvent e) //called by timer
	{
		// TODO Auto-generated method stub
		if(right)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakeylength[i+1]=snakeylength[i];
				//for changing y coordinates 
			}
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					snakexlength[i]=snakexlength[i]+25;
					//25 is the size of snake mouth and snake 
					//body image so add 25 to it.(in pixels)
				}
				else
				{
				snakexlength[i]=snakexlength[i-1];
				}
				if(snakexlength[i]>850)
					snakexlength[i]=25;
			}
			repaint();// paint method will be called
		}
		if(left)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakeylength[i+1]=snakeylength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					snakexlength[i]=snakexlength[i]-25;
				}
				else
				{ 
				snakexlength[i]=snakexlength[i-1];
				}
				if(snakexlength[i]<25)
					snakexlength[i]=850;
			}
			repaint();
		}
		if(up)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakexlength[i+1]=snakexlength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					snakeylength[i]=snakeylength[i]-25;
				}
				else
				{
				snakeylength[i]=snakeylength[i-1];
				}
				if(snakeylength[i]<75)
					snakeylength[i]=625;
			}
			repaint();
		}
		if(down)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakexlength[i+1]=snakexlength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					snakeylength[i]=snakeylength[i]+25;
				}
				else
				{
				snakeylength[i]=snakeylength[i-1];
				}
				if(snakeylength[i]>625)//for crossing boundaries
					snakeylength[i]=75;
			}
			repaint();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		 if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		  {
			  moves++;
			  if(!left)// head not collide with body
			  right=true;
			  else
			  {
				  right=false;
				  left=true;
			  }
			  up=false;
			  down=false;
		  }
		  if(e.getKeyCode()==KeyEvent.VK_LEFT)
		  {
			  moves++;
			  if(!right)// head not collide with body
			  left=true;
			  else
			  {
				  left=false;
				  right=true;
			  }
			  up=false;
			  down=false;
		  } 
		  if(e.getKeyCode()==KeyEvent.VK_UP)
		  {
			  moves++;
			  if(!down)// head not collide with body
			  up=true;
			  else
			  {
				  up=false;
				  down=true;
			  }
			  right=false;
			  left=false;
		  }
		  if(e.getKeyCode()==KeyEvent.VK_DOWN)
		  {
			  moves++;
			  if(!up)// head not collide with body
			  down=true;
			  else
			  {
				  down=false;
				  up=true;
			  }
			  right=false;
			  left=false;
		  }
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
/*   24,10                             875
 *   --------------------------------------
 *   |
 65  |
74    --------------------------------------
     |
 *   |
 *   |
 *   |
 *   |
 *   |
651   -----------------------------------------
 * 
 */ 
