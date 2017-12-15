import java.awt.event.*;

public class Target extends MouseMotionAdapter {
	private GameWindow window;
	public Target(GameWindow window) {
		this.window = window;
	}
	public void mouseMoved(MouseEvent e) {
		window.setMousePoint(e.getPoint());
	}
}
