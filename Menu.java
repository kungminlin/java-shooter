import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JPanel {
	private Dimension size;
	private GameWindow game;
	private JButton exitButton = new JButton("Exit Game");
	public Menu(Dimension size) {
		this.size = size;
		setLayout(null);
		
		Font buttonFont = new Font("Calibri", Font.BOLD, 40);
		exitButton.setFont(buttonFont);
		exitButton.setBounds(size.width/2-350, 700, 700, 70);
		exitButton.setBackground(Color.WHITE);
		exitButton.setForeground(Color.BLACK);
		exitButton.addActionListener(new ExitListener());
		
		this.add(exitButton);
	}
	public Dimension getPreferredSize() {
		return size;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String title = "Menu";
		Font titleFont = new Font("Helvetica", Font.BOLD, 72);
		FontMetrics titleMetrics = g.getFontMetrics(titleFont);
		g.setFont(titleFont);
		g.drawString(title, size.width/2-titleMetrics.stringWidth(title)/2, 300);
		
		String desc = "Press [ESC] to continue";
		Font descFont = new Font("Helvetica", Font.BOLD, 30);
		FontMetrics descMetrics = g.getFontMetrics(descFont);
		g.setFont(descFont);
		g.drawString(desc, size.width/2-descMetrics.stringWidth(desc)/2, 400);
	}
	public void setGame(GameWindow game) {
		this.game = game;
	}
	class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.exit();
		}
	}
}
