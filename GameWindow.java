// Created by Kung-Min Lin
// Commented out lines of code/Unused code = future improvements
// Player instructions are given during the game
// Have fun!

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class GameWindow extends JPanel implements ActionListener {
	
	// Should be shared
	private Timer timer;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int roundTimer;
	
	private Player player;
	private Shooter shooter = new Shooter();
	private Controls controls;
	private Menu menu;
	private Scoreboard scoreboard;
	private StarterScreen starterScreen;
	private EndScreen endScreen;
	private Point mouse;
	private JFrame frame;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private Rectangle gameBorder = new Rectangle(screenSize);
	private int spawnAmount = 1;
	
	public GameWindow() {
		player = new Player(shooter);
		scoreboard = new Scoreboard(screenSize, this);
		starterScreen = new StarterScreen(screenSize);
		endScreen = new EndScreen(screenSize);
		menu = new Menu(screenSize);
		controls = new Controls(this, player, 5, screenSize);
		for (int i=0; i<10; i++) {
			int x = (int)Math.round(Math.random())*screenSize.width;
			int y = (int)Math.round(Math.random())*screenSize.height;
			enemies.add(new Enemy(x, y));
		}
		starterScreen.setPlayer(player);
		
		frame = new JFrame("JamSheep Shooter v1.0");
		frame.setResizable(true);
		setBackground(Color.WHITE);
		this.setLayout(null);
		frame.setContentPane(starterScreen);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		scoreboard.setBounds(0, 0, this.getMaximumSize().width, this.getMaximumSize().height);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addKeyListener(controls);
		frame.setFocusable(true);
		frame.addMouseMotionListener(new Target(this));
	}
	public Dimension getPreferredSize() {
		return screenSize;
	}
	public static void main(String[] args) {
		GameWindow window = new GameWindow();
		window.starterScreen.setGame(window);
		window.endScreen.setGame(window);
		window.menu.setGame(window);
		window.frame.setVisible(true);
		
		window.timer = new Timer(10, window);
	}
	
	public void drawFrame(Graphics g) {
		ArrayList<Integer> removeBullets = new ArrayList<Integer>();
		ArrayList<Integer> removeEnemies = new ArrayList<Integer>();
		for (int i=0; i<enemies.size(); i++) {
			enemies.get(i).move(player);
			enemies.get(i).draw(g);
			if (enemies.get(i).getCollider().intersects(player.getCollider())) {
				scoreboard.removeScore(30);
				removeEnemies.add(i);
			}
		}
		if (!player.getCollider().intersects(gameBorder)) {
			if (player.getX()<0) player.setX(gameBorder.width);
			if (player.getX()>gameBorder.width) player.setX(0);
			if (player.getY()<0) player.setY(gameBorder.height);
			if (player.getY()>gameBorder.height) player.setY(0);
		}
		if (player.getShooter().getIsTriple() && player.getShooter().getIsShooting()) {
			Bullet bullet1 = new Bullet(player.getX(), player.getY(), 15, player.getShooter().getRotation()+Math.toRadians(10));
			Bullet bullet2 = new Bullet(player.getX(), player.getY(), 15, player.getShooter().getRotation()-Math.toRadians(10));
			bullets.add(bullet1);
			bullets.add(bullet2);
		}
		if (player.getShooter().getIsShooting()) {
			Bullet bullet = new Bullet(player.getX(), player.getY(), 15, player.getShooter().getRotation());
			bullets.add(bullet);
			player.getShooter().setIsShooting(false);
		}
		for (int i=0; i<bullets.size(); i++) {
			bullets.get(i).shoot(g);
			if(!bullets.get(i).getCollider().intersects(gameBorder)) {
				removeBullets.add(i);
			}
			for (int j=0; j<enemies.size(); j++) {
				if (bullets.get(i).getCollider().intersects(enemies.get(j).getCollider())) {
					removeEnemies.add(j);
					removeBullets.add(i);
				}
			}
		}
		player.draw(g);
		player.move();
		removeEntities(removeBullets, removeEnemies);
	}
	
	public void removeEntities(ArrayList<Integer> removeBullets, ArrayList<Integer> removeEnemies) {
		if (removeBullets!=null) for (int i=0; i<removeBullets.size(); i++) {
			bullets.remove((int)removeBullets.get(i));
			removeBullets.clear();
		}
		if (removeEnemies!=null) for (int i=0; i<removeEnemies.size(); i++) {
			enemies.remove((int)removeEnemies.get(i));
			scoreboard.addScore(10);
			removeEnemies.clear();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawFrame(g);
	}
	
	public void actionPerformed(ActionEvent event) {
		
		screenSize = frame.getContentPane().getSize();

		if (mouse.getLocation() != null) {
			Point target = new Point(mouse.x-player.getX(), mouse.y-player.getY());
			AffineTransform transform = new AffineTransform().getRotateInstance(Math.atan2(target.getY(), target.getX()), player.getX()+15, player.getY()+15);
			double rotation = Math.atan2(target.getY(), target.getX());
			player.getShooter().setTransform(transform);
			player.getShooter().setRotation(rotation);
		}
		
		roundTimer++;
		if (roundTimer%2000==0) spawnAmount*=2;
		if (roundTimer%50==0) {
			for (int i=0; i<spawnAmount; i++) enemies.add(new Enemy((int)Math.round(Math.random())*screenSize.width, (int)Math.round(Math.random())*screenSize.height));
		}
		
		scoreboard.setTime(roundTimer);
		for (int i=0; i<enemies.size(); i++) {
			enemies.get(i).setRoundTime(roundTimer);
		}	
		
		if (controls.isPaused()) {
			frame.setContentPane(menu);
			frame.repaint();
			frame.pack();
			roundTimer--;
		}
		else {
			frame.setContentPane(this);
			frame.repaint();
			frame.pack();
		}
		
		if (scoreboard.getScore()<0) {
			frame.setContentPane(endScreen);
			frame.repaint();
			frame.pack();
			
			timer.stop();
		}
		
		for (int i=0; i<scoreboard.getUpgrades().size(); i++) {
			switch(scoreboard.getUpgrades().get(i).getUpgradeId()) {
				case 0:
					shooter.setTriple(true);
					scoreboard.resetCooldown(scoreboard.getUpgrades().get(i).getUpgradeId());
					break;
				case 1:
					if (roundTimer%15==0) {
						for (int j=0; j<19; j++) {
							Bullet orbitalBullet = new Bullet(player.getX(), player.getY(), 15, j*18);
							bullets.add(orbitalBullet);
						}
					}
					scoreboard.resetCooldown(scoreboard.getUpgrades().get(i).getUpgradeId());
					break;
				case 2:
					System.out.println("Shield");
					scoreboard.resetCooldown(scoreboard.getUpgrades().get(i).getUpgradeId());
					break;
				case 3:
					break;
				case 4:
					scoreboard.resetCooldown(scoreboard.getUpgrades().get(i).getUpgradeId());
					break;
				default:
					break;
			}
		}
		
		if (scoreboard.getCooldown()!=null) for (int i=0; i<scoreboard.getCooldown().size(); i++) {
			while(scoreboard.getCooldown().get(i)>0.0) {
				scoreboard.getCooldown().set(i, scoreboard.getCooldown().get(i)-1.0);
			}
		}
		
		repaint();
	}
	
	public void setMousePoint(Point mouse) {
		this.mouse = mouse;
	}
	
	public void clearEnemies() {
		enemies.clear();
	}
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public void continueGame() {
		frame.setContentPane(this);
		frame.remove(menu);
		frame.add(scoreboard);
		frame.repaint();
		frame.pack();
	}
	
	public void restart() {
		scoreboard.reset();
		
		player.setX(500);
		player.setY(500);
		init();
		enemies.clear();
		bullets.clear();
		roundTimer = 0;
		spawnAmount = 1;
		shooter.setTriple(false);
	}
	
	public void init() {
		frame.setContentPane(this);
		frame.add(scoreboard);
		frame.repaint();
		frame.pack();
		
		timer.start();
	}
	
	public void exit() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	public double getTime() {
		return scoreboard.getTime();
	}
}
