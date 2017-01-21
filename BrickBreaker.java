// Deven Pattni
// June 12, 2015
// This program will allow a user to play a game of brickbreaker

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

public class BrickBreaker extends JPanel implements KeyListener
{
    int x = 0, y = 545; // x and y integer variables which will be used for the coordinates of the spaceship


    int ballx = 0, bally = 100; // x and y coordinate variable for the possition of the each charcter in the name

    int ballxdir = 3; // variables for the x and y coordinate change of the name (speed of movement)
    int ballydir = 3;

    boolean hitbottom = true;

    boolean menu = true;
    boolean instructions = false;
    boolean game = false;

    int score = 0; // integer which will be used for the score counting

    int lives = 3;

    int level = 1;

    int rows = 2;

    int xa[] [];  // array used for the x coordinates of the bricks
    int ya[]; // array used for the y coordinates of the bricks

    int bulletcount = 0; // variable to count bullets

    int xdir = 1;   // variables for direction change amount

    boolean pressedLeft, pressedRight, pressedSpace, pressedEnter; // boolean variables used for key presses
    //String s = "";

    static Image menuBG; // background image

    static Image arrowkeys; // background image

    static Image spacebar; // background image

    public BrickBreaker () throws IOException
    { // add the listener
	this.addKeyListener (this);
	setFocusable (true); // need this to set the focus of the keyboard

	menuBG = ImageIO.read (getClass ().getResource ("brickbreaker.jpg")); // gets the menu background image from the same folder as this directory

	arrowkeys = ImageIO.read (getClass ().getResource ("arrowkeys.jpg")); // gets the arrow keys image from the same folder as this directory

	spacebar = ImageIO.read (getClass ().getResource ("spacebar.png")); // gets the space bar image from the same folder as this directory

	xa = new int [4] [10];  //sets 2D array for the x-corrdinates of each brick (2 rows, 8 bricks per row)
	ya = new int [4]; // sets # of elements in the array to 2 (2 rows of aliens)


    }


    // These methods are needed for the KeyListener interface
    // keyTyped, keyPressed, keyReleased

    // called to handle character keys
    public void keyTyped (KeyEvent e)
    {
	//repaint ();
    }


    // called to handle arrow keys, space, enter, and escape
    public void keyPressed (KeyEvent e)
    {
	char key = (char) e.getKeyCode (); // gets the keycode of the key entered

	if (key == e.VK_LEFT) // if the key entered is left
	    pressedLeft = true; // the boolean for left equals true

	else if (key == e.VK_RIGHT) // if the key entered is left
	    pressedRight = true; // the boolean for right equals true

	else if (key == e.VK_SPACE) // if the key entered is space
	{
	    pressedSpace = true;  // the boolean for the space equals true
	    // xb [bulletcount] = x + 68; // sets the x and y coordinates for the current bullents possition
	    // yb [bulletcount] = y;
	    bulletcount++; // adds to the bullet counter
	}
	else if (key == e.VK_ENTER) // if the key entered is space
	{
	    pressedEnter = true;
	}

	else if (key == e.VK_ESCAPE) //quit the program
	    System.exit (0);
	repaint ();
    }


    public void keyReleased (KeyEvent e)
    {
	pressedLeft = pressedRight = pressedSpace = pressedEnter = false; // pressedDown
	repaint ();
	//System.out.println ("keyReleased=" + KeyEvent.getKeyText (e.getKeyCode ()));
    }


    // graphics method
    public void paintComponent (Graphics g)
    {
	super.paintComponent (g);

	if (menu)
	{
	    g.drawImage (menuBG, 0, 0, 600, 600, this);
	    g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
	    g.setColor (Color.black); // sets colour to orange
	    g.drawString ("[Enter] To play", 240, 400); // prints message in top left
	    g.drawString ("[Space] for instructions", 200, 450); // prints message in top left


	    xa [0] [0] = 90;   //sets aliens variables x posititions
	    xa [0] [1] = 130;
	    xa [0] [2] = 170;
	    xa [0] [3] = 210;
	    xa [0] [4] = 250;
	    xa [0] [5] = 290;
	    xa [0] [6] = 330;
	    xa [0] [7] = 370;
	    xa [0] [8] = 410;
	    xa [0] [9] = 450;

	    xa [1] [0] = 90;
	    xa [1] [1] = 130;
	    xa [1] [2] = 170;
	    xa [1] [3] = 210;
	    xa [1] [4] = 250;
	    xa [1] [5] = 290;
	    xa [1] [6] = 330;
	    xa [1] [7] = 370;
	    xa [1] [8] = 410;
	    xa [1] [9] = 450;

	    xa [2] [0] = 90;
	    xa [2] [1] = 130;
	    xa [2] [2] = 170;
	    xa [2] [3] = 210;
	    xa [2] [4] = 250;
	    xa [2] [5] = 290;
	    xa [2] [6] = 330;
	    xa [2] [7] = 370;
	    xa [2] [8] = 410;
	    xa [2] [9] = 450;

	    xa [3] [0] = 90;
	    xa [3] [1] = 130;
	    xa [3] [2] = 170;
	    xa [3] [3] = 210;
	    xa [3] [4] = 250;
	    xa [3] [5] = 290;
	    xa [3] [6] = 330;
	    xa [3] [7] = 370;
	    xa [3] [8] = 410;
	    xa [3] [9] = 450;

	    ya [0] = 50; //sets aliens variables y posititions
	    ya [1] = 100;
	    ya [2] = 150;
	    ya [3] = 200;


	    if (level == 1)
	    {
		rows = 2;
	    }

	    if (level == 2)
	    {
		rows = 3;
	    }

	    if (level == 3)
	    {
		rows = 4;
	    }



	    if (pressedEnter)
	    {
		menu = false;
		game = true;
	    }

	    if (pressedSpace)
	    {
		menu = false;
		instructions = true;
		pressedSpace = false;
	    }

	}

	else if (instructions)
	{
	    g.setColor (Color.white); // sets the colour to black
	    g.fillRect (0, 0, size ().width, size ().height); // fills the backgound in white

	    g.setFont (new Font ("Impact", Font.PLAIN, 28)); // sets the font
	    g.setColor (Color.black); // sets colour to orange
	    g.drawString ("How To Play", 220, 30);

	    g.drawImage (spacebar, 80, 200, 202, 68, this);
	    g.drawImage (arrowkeys, 320, 100, 200, 200, this);

	    g.setFont (new Font ("Helvetica", Font.PLAIN, 14)); // sets the font
	    g.setColor (Color.black); // sets colour to orange
	    g.drawString ("Use Left and Right Arrow leys to move your paddle", 130, 350);
	    g.drawString ("When the ball is on the paddle, use the space bar to releae the paddle in a random direction", 0, 380);
	    g.drawString ("Follow on-screen instructions when you loose all lives, or beat a level/the game", 50, 410);
	    
	    g.drawString ("Hit escape to exit the game from any screen", 140, 460);

	    g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
	    g.setColor (Color.black); // sets colour to orange
	    g.drawString ("[Space] to return to menu", 180, 550); // prints message in top left

	    if (pressedSpace)
	    {
		menu = true;
		instructions = false;
		pressedSpace = false;
	    }

	}



	else if (game)
	{
	
	    g.setColor (Color.black); // sets the colour to black
	    g.fillRect (0, 0, size ().width, size ().height); // fills the backgound in black


	    int w = size ().width; // get the current width & height of the window and sets to integer variables
	    int h = size ().height;
	    int size = 10; // integer variable for the length of the whole name

	    // ball
	    g.setColor (Color.white); // sets the colour to white
	    g.fillOval (ballx, bally, 10, 10); // draws the ball

	    ballx = ballx + ballxdir; // add the direction values to each coordinate (x and y) each time so the whole name would move
	    bally = bally + ballydir;

	    if (ballx + size >= w) // if the ball hits the right side wall...
	    {
		ballxdir = -1 * ballxdir; // change the x direction value to negative
	    }

	    if (ballx <= 0) // if the ball hits the left side wall...
	    {
		ballxdir = -1 * ballxdir; // change the x direction value to negative
	    }

	    if (bally <= 0) // if the ball hits the top wall...
	    {
		ballydir = -1 * ballydir; // change the y direction value to negative
	    }

	    if (bally + size >= h) // if the ball hits the bottom wall...
	    {
		lives = lives - 1;
		hitbottom = true;


		//ballydir = -1 * ballydir; // change the y direction value to negative
	    }

	    if (bally + 3 >= h - 20 && ballx + size >= x && ballx <= x + 80) // if the ball hits the paddle...
	    {
		ballydir = -1 * ballydir; // change the y direction value to negative
	    }



	    if (lives == 0)
	    {
		ballxdir = 0;
		ballydir = 0;

		g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
		g.setColor (Color.orange); // sets colour to orange
		g.drawString ("Game over!", 200, 280); // prints message in top left
		g.drawString ("Your Score: " + score, 200, 310);
		g.drawString ("Hit [enter] to play again", 165, 350);
		g.drawString ("Hit [space] to go back to menu", 160, 380);
		if (pressedEnter)
		{
		    level = 1;
		    score = 0;
		    lives = 3;
		    hitbottom = true;
		    menu = true;
		    game = false;
		    //pressedEnter = false;
		}
		if (pressedSpace)
		{
		    level = 1;
		    score = 0;
		    lives = 3;
		    hitbottom = true;
		    menu = true;
		    game = false;
		    pressedSpace = false;
		}
	    }



	    if (hitbottom)
	    {
		ballxdir = 0;
		ballydir = 0;

		ballx = x + 40;
		bally = y - 5;

		if (pressedSpace)
		{
		    int random = (int) (Math.random () * 4 + 1);
		    if (random == 1)
		    {
			ballxdir = (-3);
		    }
		    if (random == 2)
		    {
			ballxdir = (-2);
		    }
		    if (random == 3)
		    {
			ballxdir = (-1);
		    }
		    if (random == 4)
		    {
			ballxdir = (1);
		    }
		    if (random == 5)
		    {
			ballxdir = (2);
		    }
		    if (random == 6)
		    {
			ballxdir = (3);
		    }
		    //ballxdir = (int) (Math.random () * 3 + 1);
		    ballydir = -3;
		    hitbottom = false;
		}

	    }


	    // if (ballx == x + 40 && bally == y-5 && pressedSpace == true)
	    // {
	    //     ballxdir = 3;
	    //     ballydir = -3;
	    //     hitbottom = false;
	    // }


	    // Paddle
	    g.setColor (Color.green); // sets the colur to green
	    g.fillRect (x, y, 80, 20); // draws the ship


	    /////// Bricks ///////

	    // draws the first row of bricks
	    g.setColor (Color.white); // sets the colour
	    g.fillRect (xa [0] [0], ya [0], 35, 15);

	    g.setColor (Color.red); // sets the colour
	    g.fillRect (xa [0] [1], ya [0], 35, 15);

	    g.setColor (Color.blue); // sets the colour
	    g.fillRect (xa [0] [2], ya [0], 35, 15);

	    g.setColor (Color.orange); // sets the colour
	    g.fillRect (xa [0] [3], ya [0], 35, 15);

	    g.setColor (Color.green); // sets the colour
	    g.fillRect (xa [0] [4], ya [0], 35, 15);

	    g.setColor (Color.white); // sets the colour
	    g.fillRect (xa [0] [5], ya [0], 35, 15);

	    g.setColor (Color.orange); // sets the colour
	    g.fillRect (xa [0] [6], ya [0], 35, 15);

	    g.setColor (Color.blue); // sets the colour
	    g.fillRect (xa [0] [7], ya [0], 35, 15);

	    g.setColor (Color.yellow); // sets the colour
	    g.fillRect (xa [0] [8], ya [0], 35, 15);

	    g.setColor (Color.red); // sets the colour
	    g.fillRect (xa [0] [9], ya [0], 35, 15);


	    // draws the second row of bricks
	    g.setColor (Color.yellow); // sets the colour
	    g.fillRect (xa [1] [0], ya [1], 35, 15);

	    g.setColor (Color.pink); // sets the colour
	    g.fillRect (xa [1] [1], ya [1], 35, 15);

	    g.setColor (Color.green); // sets the colour
	    g.fillRect (xa [1] [2], ya [1], 35, 15);

	    g.setColor (Color.white); // sets the colour
	    g.fillRect (xa [1] [3], ya [1], 35, 15);

	    g.setColor (Color.orange); // sets the colour
	    g.fillRect (xa [1] [4], ya [1], 35, 15);

	    g.setColor (Color.white); // sets the colour
	    g.fillRect (xa [1] [5], ya [1], 35, 15);

	    g.setColor (Color.pink); // sets the colour
	    g.fillRect (xa [1] [6], ya [1], 35, 15);

	    g.setColor (Color.white); // sets the colour
	    g.fillRect (xa [1] [7], ya [1], 35, 15);

	    g.setColor (Color.yellow); // sets the colour
	    g.fillRect (xa [1] [8], ya [1], 35, 15);

	    g.setColor (Color.green); // sets the colour
	    g.fillRect (xa [1] [9], ya [1], 35, 15);



	    if (rows >= 3)
	    {
		g.setColor (Color.yellow); // sets the colour to
		g.fillRect (xa [2] [0], ya [2], 35, 15);

		g.setColor (Color.pink); // sets the colour to
		g.fillRect (xa [2] [1], ya [2], 35, 15);

		g.setColor (Color.green); // sets the colour to green
		g.fillRect (xa [2] [2], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [3], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [4], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [5], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [6], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [7], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [8], ya [2], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [2] [9], ya [2], 35, 15);
	    }

	    if (rows >= 4)
	    {
		g.setColor (Color.yellow); // sets the colour to
		g.fillRect (xa [3] [0], ya [3], 35, 15);

		g.setColor (Color.pink); // sets the colour to
		g.fillRect (xa [3] [1], ya [3], 35, 15);

		g.setColor (Color.green); // sets the colour to
		g.fillRect (xa [3] [2], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [3], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [4], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [5], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [6], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [7], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [8], ya [3], 35, 15);

		g.setColor (Color.white); // sets the colour to
		g.fillRect (xa [3] [9], ya [3], 35, 15);
	    }




	    //left
	    if (pressedLeft) // if the left key is hit...
	    {
		if (x > 5) // if the x possiton of the ship is on the screen
		{
		    x = x - 8; // move the ship
		}
	    }

	    //right
	    if (pressedRight) // if the right key is hit...
	    {
		if (x < 500) // if the x possiton of the ship is on the screen
		{
		    x = x + 8; // move the ship
		}
	    }



	    // my hit detection for the bricks
	    for (int a = 0 ; a < rows ; a++) // for loop for hit detection on brick
	    {

		for (int i = 0 ; i < 10 ; i++) // for loop for hit detection on bricks
		{

		    if (ballx + 10 >= xa [a] [i] && ballx <= xa [a] [i] + 35 && bally <= ya [a] + 15 && bally + size >= ya [a]) // bottom of brick colision
		    {
			ballydir = -1 * ballydir; // change the y direction value to negative
			xa [a] [i] = xa [a] [i] + 4000;
			score = score + 5;
		    }

		    // if (ballx >= xa [a][i] && ballx <= xa [a][i] + 35 && bally + size >= ya [a] && bally <= ya [a] + 15)
		    // {
		    // ballydir = -1 * ballydir;
		    // xa [a] [i] = xa [a] [i] + 4000;
		    // score = score + 5;
		    // }

		    // if (ballx + size == xa [0] [0] && bally + size >= ya [0] && bally <= ya [0] + 15)
		    // {
		    // ballxdir = -1 * ballxdir; // change the x direction value to negative
		    // xa [0] [0] = xa [0] [0] + 4000;
		    // }

		}
	    }

	    if (level == 1)
	    {
		if (xa [0] [0] > 1000 && xa [0] [1] > 1000 && xa [0] [2] > 1000 && xa [0] [3] > 1000 && xa [0] [4] > 1000 && xa [0] [5] > 1000 && xa [0] [6] > 1000 && xa [0] [7] > 1000 && xa [0] [8] > 1000 && xa [0] [9] > 1000 && xa [1] [0] > 1000 && xa [1] [1] > 1000 && xa [1] [2] > 1000 && xa [1] [3] > 1000 && xa [1] [4] > 1000 && xa [1] [5] > 1000 && xa [1] [6] > 1000 && xa [1] [7] > 1000 && xa [1] [8] > 1000 && xa [1] [9] > 1000)
		{
		    ballxdir = 0;
		    ballydir = 0;

		    g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
		    g.setColor (Color.orange); // sets colour to orange
		    g.drawString ("You have defeated level 1", 150, 300); // prints message

		    g.drawString ("Hit [enter] to move onto level 2", 150, 340);

		    if (pressedEnter)
		    {
			level = 2;
			//score = 0;
			//lives = lives + 3;
			hitbottom = true;
			menu = true;
			game = false;
			//pressedEnter = false;
		    }
		}
	    }

	    if (level == 2)
	    {
		if (xa [0] [0] > 1000 && xa [0] [1] > 1000 && xa [0] [2] > 1000 && xa [0] [3] > 1000 && xa [0] [4] > 1000 && xa [0] [5] > 1000 && xa [0] [6] > 1000 && xa [0] [7] > 1000 && xa [0] [8] > 1000 && xa [0] [9] > 1000 && xa [1] [0] > 1000 && xa [1] [1] > 1000 && xa [1] [2] > 1000 && xa [1] [3] > 1000 && xa [1] [4] > 1000 && xa [1] [5] > 1000 && xa [1] [6] > 1000 && xa [1] [7] > 1000 && xa [1] [8] > 1000 && xa [1] [9] > 1000 && xa [2] [0] > 1000 && xa [2] [1] > 1000 && xa [2] [2] > 1000 && xa [2] [3] > 1000 && xa [2] [4] > 1000 && xa [2] [5] > 1000 && xa [2] [6] > 1000 && xa [2] [7] > 1000 && xa [2] [8] > 1000 && xa [2] [9] > 1000)
		{
		    ballxdir = 0;
		    ballydir = 0;

		    g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
		    g.setColor (Color.orange); // sets colour to orange
		    g.drawString ("You have defeated level 2", 150, 300); // prints message

		    g.drawString ("Hit [enter] to move onto level 3", 150, 340);

		    if (pressedEnter)
		    {
			level = 3;
			//score = 0;
			//lives = lives + 3;
			hitbottom = true;
			menu = true;
			game = false;
			//pressedEnter = false;
		    }
		}
	    }

	    if (level == 3)
	    {
		if (xa [0] [0] > 1000 && xa [0] [1] > 1000 && xa [0] [2] > 1000 && xa [0] [3] > 1000 && xa [0] [4] > 1000 && xa [0] [5] > 1000 && xa [0] [6] > 1000 && xa [0] [7] > 1000 && xa [0] [8] > 1000 && xa [0] [9] > 1000 && xa [1] [0] > 1000 && xa [1] [1] > 1000 && xa [1] [2] > 1000 && xa [1] [3] > 1000 && xa [1] [4] > 1000 && xa [1] [5] > 1000 && xa [1] [6] > 1000 && xa [1] [7] > 1000 && xa [1] [8] > 1000 && xa [1] [9] > 1000 && xa [2] [0] > 1000 && xa [2] [1] > 1000 && xa [2] [2] > 1000 && xa [2] [3] > 1000 && xa [2] [4] > 1000 && xa [2] [5] > 1000 && xa [2] [6] > 1000 && xa [2] [7] > 1000 && xa [2] [8] > 1000 && xa [2] [9] > 1000 && xa [3] [0] > 1000 && xa [3] [1] > 1000 && xa [3] [2] > 1000 && xa [3] [3] > 1000 && xa [3] [4] > 1000 && xa [3] [5] > 1000 && xa [3] [6] > 1000 && xa [3] [7] > 1000 && xa [3] [8] > 1000 && xa [3] [9] > 1000)
		{
		    ballxdir = 0;
		    ballydir = 0;

		    g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
		    g.setColor (Color.orange); // sets colour to orange
		    g.drawString ("You have defeated level 3", 150, 300); // prints message
		    g.drawString ("You have beat the game!", 150, 340); // prints message

		    g.drawString ("Hit [enter] to play again", 150, 380);
		    g.drawString ("Hit [space] to go back to the menu", 150, 400);
		    

		    if (pressedEnter)
		    {
			level = 1;
			score = 0;
			lives = 3;
			hitbottom = true;
			menu = true;
			game = false;
			//pressedEnter = false;
		    }

		    if (pressedSpace)
		    {
			level = 1;
			score = 0;
			lives = 3;
			hitbottom = true;
			menu = true;
			game = false;
			pressedSpace = false;
		    }
		}
	    }

	    g.setFont (new Font ("Helvetica", Font.PLAIN, 14)); // sets the font
	    g.setColor (Color.orange); // sets colour to orange
	    g.drawString ("Score: " + score, 500, 15); // displays the score to the top right
	    g.drawString ("Lives: " + lives, 500, 550); // displays the score to the top right
	    
	    g.setFont (new Font ("Impact", Font.PLAIN, 20)); // sets the font
	    g.drawString ("Round: " + level, 260, 20); // displays the score to the top right



	    repaint (); // calls a repaint
	    MyLib.delay (8); // delay
	}

    }


    public static void main (String[] args) throws IOException
    {
	JFrame frame = new JFrame ("Brick Breaker"); // names the run window
	frame.getContentPane ().add (new BrickBreaker ());
	frame.setSize (600, 600); // sets the size of the window
	frame.setVisible (true);
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
}


