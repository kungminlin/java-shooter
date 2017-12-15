import java.awt.event.*;
import java.awt.*;

public class Controls extends KeyAdapter {
	private GameWindow game;
	private Player player;
	private int speed;
	private Dimension screenSize;
	private int escapePress = 0;
	public Controls(GameWindow game, Player player, int speed, Dimension screenSize) {
		this.player = player;
		this.speed = speed;
		this.screenSize = screenSize;
		this.game = game;
	}
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key) {
			case 'w':
				player.setDY(-speed);
				break;
			case 'a':
				player.setDX(-speed);
				break;
			case 's':
				player.setDY(speed);
				break;
			case 'd':
				player.setDX(speed);
				break;
			case ' ':
				player.shoot();
				break;
			default:
				break;
		}
		if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			escapePress++;
		}
	}
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		switch(key) {
			case 'w':
				player.setDY(0);
				break;
			case 'a':
				player.setDX(0);
				break;
			case 's':
				player.setDY(0);
				break;
			case 'd':
				player.setDX(0);
				break;
			case 'r':
				if (game.getScoreboard().getCooldown().size()>=4 && game.getScoreboard().getCooldown().get(3).equals(0.0)) {
					game.clearEnemies();
					game.getScoreboard().resetCooldown(3);
				}
				break;
			default:
				break;
		}
	}
	public boolean isPaused() {
		return escapePress%2==1;
	}
}
