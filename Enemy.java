import java.awt.*;

public class Enemy {
	private int x;
	private int y;
	private int speed;
	private Rectangle enemyCollider;
	private int roundTime;
	private int speedBuffer = 1;
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		enemyCollider = new Rectangle(x, y, 20, 20);
		if (roundTime%1000==0) {
			speedBuffer++;
		}
		speed = (int)(Math.random()*3)+2+speedBuffer;
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawOval(x, y, 20, 20);
		g.fillOval(x, y, 20, 20);
		enemyCollider.setLocation(x, y);
	}
	public void move(Player player) {
		double moveDir = Math.atan2(player.getY()-y, player.getX()-x);
		x += (int)speed*Math.cos(moveDir);
		y += (int)speed*Math.sin(moveDir);
		enemyCollider.setLocation(x, y);
	}
	public Rectangle getCollider() {
		return enemyCollider;
	}
	public void setRoundTime(int roundTime) {
		this.roundTime = roundTime;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
