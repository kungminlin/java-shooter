import java.awt.*;
import java.awt.geom.*;
public class Shooter {
	private int x;
	private int y;
	private double rotation;
	private AffineTransform transform = new AffineTransform();
	private boolean isShooting;
	private boolean isTriple = false;
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform oldTransform = g2d.getTransform();
		g2d.setColor(Color.BLUE);
		Rectangle rect = new Rectangle(x, y+10, 50, 10);
		g2d.transform(transform);
		g2d.draw(rect);
		g2d.fill(rect);
		g2d.setTransform(oldTransform);
	}
	public void shoot() {
		isShooting = true;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	public double getRotation() {
		return rotation;
	}
	public void setTransform(AffineTransform transform) {
		this.transform = transform;
	}
	public void setIsShooting(boolean isShooting) {
		this.isShooting = isShooting;
	}
	public boolean getIsShooting() {
		return isShooting;
	}
	public void setTriple(boolean isTriple) {
		this.isTriple = isTriple;
	}
	public boolean getIsTriple() {
		return isTriple;
	}
}
