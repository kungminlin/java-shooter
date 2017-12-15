import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StarterScreen extends JPanel{
	private Dimension size;
	private GameWindow game;
	private Player player;
	private JButton startButton = new JButton("Start Game!");
	private JTextField nameInput = new JTextField();
	public StarterScreen(Dimension size) {
		setLayout(null);
		this.size = size;
		startButton.setFont(new Font("Calibri", Font.BOLD, 40));
		startButton.setBounds(size.width/2-350, 700, 700, 70);
		startButton.setBackground(Color.BLACK);
		startButton.setForeground(Color.WHITE);
		startButton.addActionListener(new ButtonListener());
		
		nameInput.setBounds(size.width/2-350, 500, 700, 70);
		nameInput.setFont(new Font("Helvetica", Font.BOLD, 40));
		nameInput.setForeground(Color.WHITE);
		nameInput.setBackground(Color.BLACK);
		nameInput.addActionListener(new NameListener());
		
		this.add(nameInput);
		this.add(startButton);
	}
	public Dimension getPreferredSize() {
		return size;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String title = "Welcome to JamSheep Shooter v1.0!";
		Font titleFont = new Font("Helvetica", Font.BOLD, 72);
		FontMetrics titleMetrics = g.getFontMetrics(titleFont);
		g.setFont(titleFont);
		g.drawString(title, size.width/2-titleMetrics.stringWidth(title)/2, 300);
		Font desc = new Font("Sans Serif", Font.PLAIN, 18);
		g.setFont(desc);
		g.drawString("Please enter your name below and hit [ENTER]", nameInput.getLocation().x, nameInput.getLocation().y-15);
	}
	public void setGame(GameWindow game) {
		this.game = game;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (player.getName()==null || nameInput.getText().trim().equals("")) player.setName("I Didn't Press [ENTER]");
			game.init();
		}
	}
	class NameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String name = nameInput.getText();
			player.setName(name);
			System.out.println(name);
			game.init();
		}
	}
}
