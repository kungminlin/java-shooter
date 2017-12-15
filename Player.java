import java.awt.*;

public class Player {
	private String name;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private Shooter shooter;
	private Rectangle playerCollider;
	public Player(Shooter shooter) {
		x = 500;
		y = 500;
		this.shooter = shooter;
		playerCollider = new Rectangle(x, y, 30, 30);
	}
	public void draw(Graphics g) {
		shooter.setX(x);
		shooter.setY(y);
		shooter.draw(g);
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 30, 30);
		g.fillOval(x,  y,  30,  30);
		playerCollider.setLocation(x, y);
		Font nameFont = new Font("Sans Serif", Font.BOLD, 12);
		g.setFont(nameFont);
		FontMetrics nameMetrics = g.getFontMetrics(nameFont);
		g.drawString(name, x-nameMetrics.stringWidth(name)/2+15, y+50);
	}
	public void move() {
		x+=dx;
		y+=dy;
		playerCollider.setLocation(x, y);
	}
	public void setDX(int dx)  {
		this.dx = dx;
	}
	public void setDY(int dy) {
		this.dy = dy;
	}
	public Shooter getShooter() {
		return shooter;
	}
	public void shoot() {
		shooter.shoot();
	}
	public void setShooter(Shooter shooter) {
		this.shooter = shooter;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public Rectangle getCollider() {
		return playerCollider;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
