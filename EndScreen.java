import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EndScreen extends JPanel{
	private Dimension size;
	private GameWindow game;
	private JButton restartButton = new JButton("Restart");
	private JButton exitButton = new JButton("Exit Game");
	public EndScreen(Dimension size) {
		setLayout(null);
		this.size = size;
		Font buttonFont = new Font("Calibri", Font.BOLD, 40);
		restartButton.setFont(buttonFont);
		restartButton.setBounds(size.width/2-350, 500, 700, 70);
		restartButton.setBackground(Color.WHITE);
		restartButton.setForeground(Color.RED);
		restartButton.addActionListener(new RestartListener());

		exitButton.setFont(buttonFont);
		exitButton.setBounds(size.width/2-350, 700, 700, 70);
		exitButton.setBackground(Color.WHITE);
		exitButton.setForeground(Color.RED);
		exitButton.addActionListener(new ExitListener());
		
		this.add(restartButton);
		this.add(exitButton);
		this.setBackground(Color.RED);
	}
	public Dimension getPreferredSize() {
		return size;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		String title = "You Died...";
		Font titleFont = new Font("Helvetica", Font.BOLD, 72);
		FontMetrics titleMetrics = g.getFontMetrics(titleFont);
		g.setFont(titleFont);
		g.drawString(title, size.width/2-titleMetrics.stringWidth(title)/2, 300);
		
		g.setColor(Color.YELLOW);
		String desc = "You survived for " + Double.toString(game.getTime()) + " seconds!";
		Font descFont = new Font("Sans Serif", Font.ITALIC, 20);
		FontMetrics descMetrics = g.getFontMetrics(descFont);
		g.setFont(descFont);
		g.drawString(desc, size.width/2-descMetrics.stringWidth(desc)/2, 400);
	}
	public void setGame(GameWindow game) {
		this.game = game;
	}
	private class RestartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.restart();
		}
	}
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.exit();
		}
	}
}
