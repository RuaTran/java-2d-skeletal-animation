package game;

import entity.Player;
import entity.Poring;
import enums.GameState;
import graphics.*;
import input.KeyInput;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

import javax.swing.*;

import anim.Bone;
import anim.BoneAnimation;
import anim.SkeletonAnimation;
import anim.Skeleton;
import util.ResourceLoader;
import util.SkeletonResourceLoader;
import lib.Reference;
import maps.Level;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = 1L;

	private static JFrame frame = new JFrame(); // Our window object
	public static final int WIDTH = 1024; // The width of our window
	public static final int HEIGHT = WIDTH / 4 * 3; // Creates a nice 4:3 ratio
													// for our window
	public static final String TITLE = "ss"; // The title of our game
	private static Game game = new Game(); // The instance of our game that we
											// will be using
	public static GameState state = GameState.MENU; // We start in this game
													// state

	private boolean running = false; // by default, we need this to be false so
										// we do not exit our start method right
										// away
	private Thread thread; // the thread that will control our game loop

	// private TestSkeletonAnimation tsa;
	private SkeletonAnimation saFull;
	private SkeletonAnimation sa;
	private Level level;
//	public float xx = -50;
	public float loweringOpacity = 1f;
	Player player;
	Poring pet;

	public static Game getInstance()
	{
		return game;
	}

	public void init()
	{
		ResourceLoader.loadGraphics();
		
		// tsa = new TestSkeletonAnimation();
		ArrayList<SpriteSheet> ssl = new ArrayList<SpriteSheet>();
		ssl.add(new SpriteSheet("sprites/characters/m_swordman/m_swordman1.png"));
		ssl.add(new SpriteSheet("sprites/characters/m_swordman/m_swordman2.png"));
		SkeletonResourceLoader.init("resources/animData/characters/m_swordman/filename.txt", ssl);
		saFull = new SkeletonAnimation(SkeletonResourceLoader.getSkeletonList());
		sa = saFull.createSubAnimation(0, 315);
		saFull = null;
		
		// sa = saFull.createSubAnimation(171, 179); weirdswing
		// sa = saFull.createSubAnimation(135, 142); running
		//sa = saFull.createSubAnimation(122,135); attacking
		sa.setLoopable(true);
		level = new Level("level.png");
		player = new Player(300,700);
		player.init();
		pet = new Poring(250,700, player);
		pet.init();
		
		////////////////////////////////////////////////////////////
		addKeyListener(new KeyInput());

	}

	public void tick()
	{
		// tsa.getSkeletonAnimation().tick();
		sa.tick();
		player.tick();
		pet.tick();
//		xx += +8.0;
//		if (xx > Game.WIDTH + 200)
//		{
//			xx = -50;
//		}
	}

	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(2); // Creates the buffer strategy if there
										// isn't already one
			return;
		}

		Graphics g = bs.getDrawGraphics(); // We want to use the graphics that
											// comes from our buffer strategy
		Graphics2D g2 = (Graphics2D) g;
		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
											// game here
		// tsa.getSkeletonAnimation().render(g);
		
		level.render(g);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.70f));
		g2.fillRect(50, 50, 300, 300);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		sa.render(200, 250, g);
		g.setColor(new Color(255,200,200));
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Animation Dummy", 120, 280);	
		g.drawString("Keyframe #: " + Integer.toString(sa.boneAnimationList.get(0).nextAnimationIndex), 120, 300);
		
		pet.render(g);
		player.render(g);
		// /////////////////////////////////////////////////

		g.dispose();
		bs.show();

	}

	@Override
	public void run()
	{
		init(); // Initializes our game
		long lastTime = System.nanoTime(); // gets the time before the loop
		final double numTicks = 40.0; // We want to have 60 updates per second,
										// too much and we will lag like a mofo,
										// too litle and it will appear as
										// though nothing is happening in the
										// game
		double n = 1000000000 / numTicks;
		double delta = 0; // change over time
		int frames = 0; // frames during that second
		int ticks = 0; // ticks or updates during that second
		long timer = System.currentTimeMillis(); // We will be using this as,
													// well, a timer

		while (running)
		{ // we only want to do this stuff if and only if the
			// game is running
			long currentTime = System.nanoTime(); // the current time during the
													// game loop
			delta += (currentTime - lastTime) / n; // because the time is
													// changing, we need to
													// update our delta
			lastTime = currentTime; /*
									 * Our last time now needs to be the previous time during the game loop, we do this by storing the current time, this is why this goes AFTER the previous two lines
									 */

			if (delta >= 1)
			{ // We want to make sure our delta doesn't remain
				// over 1
				tick(); // updates the game
				ticks++; // Adds to the ticks per second
				delta--; // Lowering the delta value for the next run through
			}

			render(); // renders the game
			frames++; // Adds to the frames per second

			if (System.currentTimeMillis() - timer > 1000)
			{ // We are going to
				// print our
				// frames and
				// ticks to the
				// console every
				// second
				timer += 1000; // Time must go on!
				frame.setTitle("GABE N"+"       "+ticks + " Ticks, FPS: " + frames); // Prints
																		// the
																		// TPS
																		// and
																		// FPS
																		// to
																		// the
																		// console
				// frame.setTitle(TITLE + "        Ticks: " + ticks +
				// "    FPS: " + frames); //Use this if you have your frame as a
				// class attribute and wish to have the FPS and TPS in your
				// title bar
				ticks = 0; // If we did not do these 2 lines, we would have
							// 10000000000000000000 ticks and fps at one point,
							// then another
							// 10000000000000000000000000000000000000000 the
							// next second
				frames = 0;
			}
		}
		stop(); // Once exit the loop, stop the game
	}

	public static void main(String args[])
	{
		frame.add(game); // adds our game as a component to the frame
		frame.setTitle(TITLE); // sets the title
		frame.setSize(WIDTH, HEIGHT); // sets the size of our window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes it so
																// that when we
																// click the red
																// X (on
																// windows, red
																// circle i
																// guess for
																// macs) we then
																// exit the game
		frame.setFocusable(true); // This way we are able to use keyboards and
									// our mouse and all that good stuff
		frame.setLocationRelativeTo(null); // makes our frame start in the
											// center of our screen
		frame.setResizable(false); // for now, if we resize our window, we will
									// get a lot of bugs with the graphics
		frame.setVisible(true); // This actually shows the frame/window
		frame.pack(); // packs all our components and settings into one nice
						// frame package (metaphorical of course)
		game.start(); // starts the game
		// SkeletonResourceLoader.init("a.txt", new
		// SpriteSheet("sprites/test/letsago.png"));
		frame.requestFocus();
	}

	/**
	 * This starts the game thread <br>
	 * It is <strong> <i> synchronized </strong> </i> because we do not want to do anything else until this method is <strong> <i> 100% complete
	 */
	private synchronized void start()
	{
		if (running) // if the game is already running, we do not want to run
						// the game again
			return;
		else
			running = true;
		thread = new Thread(this); // this thread controls our game
		thread.start(); // starts the thread, and thus the game loop
	}

	/**
	 * This stops the game thread <br>
	 * It is <strong> <i> synchronized </strong> </i> because we do not want to do anything else until this method is <strong> <i> 100% complete
	 */
	private synchronized void stop()
	{
		if (!running) // if the game has already stopped, why stop it again?
			return;
		else
			running = false;

		try
		{
			thread.join(); // a good way of closing a thread
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(); // it has a chance of failing, so we need to
									// catch the exception and report it to the
									// console
		}

		System.exit(1); // exits the game
	}

}
