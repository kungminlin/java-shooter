import java.awt.*;

public class Bullet {
	
	private int x;
	private int y;
	private int speed;
	private double rotation;
	private Rectangle bulletCollider;
	
	public Bullet(int x, int y, int speed, double rotation) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.rotation = rotation;
		bulletCollider = new Rectangle(x, y, 5, 5);
	}
	public void shoot(Graphics g) {
		move(rotation);
		bulletCollider.setLocation(x, y);
		draw(g);
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(x+15, y+15, 5, 5);
		g.fillOval(x+15, y+15, 5, 5);
	}
	public void move(double rotation) {
		x += speed * Math.cos(rotation);
		y += speed * Math.sin(rotation);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Rectangle getCollider() {
		return bulletCollider;
	}
}
