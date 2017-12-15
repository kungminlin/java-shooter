import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Scoreboard extends JPanel {
	private Dimension size;
	private int score;
	private double time;
	private ArrayList<Upgrade> pendingUpgrades = new ArrayList();
	private ArrayList<Upgrade> appliedUpgrades = new ArrayList();
	private int rounds;
	private GameWindow game;
	private ArrayList<Double> currSkillCooldown = new ArrayList();
	public Scoreboard() {
		setLayout(null);
		score = 0;
		rounds = 0;
	}
	public Scoreboard(Dimension size, GameWindow game) {
		setLayout(null);
		this.size = size;
		this.game = game;
		score = 0;
		rounds = 0;
		setOpaque(false);
	}
	public Dimension getPreferredSize() {
		return size;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		String text = "The current score is: " + score;
		Font font = new Font("Helvetica", Font.BOLD, 25);
		g.setFont(font);
		g.drawString(text, 30, 50);
		
		g.setColor(Color.BLUE);
		String timeText = Double.toString(time);
		Font timeFont = new Font("Sans Serif", Font.ITALIC, 20);
		g.setFont(timeFont);
		g.drawString(timeText, 30, 80);
		
		g.setColor(Color.BLACK);
		g.drawString("Upgrades: ", 30, 120);
		for (int i=0; i<appliedUpgrades.size(); i++) {
			String upgradesText = appliedUpgrades.get(i).getUpgradeName() + " | " + currSkillCooldown.get(i);
			g.drawString(upgradesText, 30, 150+25*i);
		}
		
		Font instructionFont = new Font("Comic Sans", Font.PLAIN, 20);
		FontMetrics instructionsMetrics = g.getFontMetrics(instructionFont);
		g.setFont(instructionFont);
		String[] instructions = {
				"Use [WASD] to move around",
				"Use your cursor to aim at your enemies",
				"Enemies are RED",
				"Press [SPACE] to shoot",
				"Press [ESC] to pause",
				"Killing an enemy gives you 10 points",
				"Being hit by an enemy costs you 30 points",
				"Game ends when you reach negative points",
				"You won't survive over 5 minutes",
				"Good Luck Have Fun!"
				};
		for (int i=0; i<instructions.length; i++) {
			g.drawString(instructions[i], size.width-instructionsMetrics.stringWidth(instructions[i])-80, i*(instructionsMetrics.getHeight()+20)+50);
		}
		
		
		
		if (time!=0.0 && time%5==0 && time<5*3) {
			pendingUpgrades.add(new Upgrade(rounds, game));
			rounds++;
		}
		
		for (int i=0; i<pendingUpgrades.size(); i++) {
			pendingUpgrades.get(i).setText(pendingUpgrades.get(i).getUpgradeName() + " (Cost: " + pendingUpgrades.get(i).getCost() + ")");
			Font buttonFont = new Font("Calibri", Font.PLAIN, 30);
			pendingUpgrades.get(i).setFont(buttonFont);
			pendingUpgrades.get(i).setBounds(size.width-pendingUpgrades.get(i).getWidth()-80, i*(pendingUpgrades.get(i).getHeight()+20)+500, 500, 40);
			pendingUpgrades.get(i).setBackground(Color.WHITE);
			pendingUpgrades.get(i).setForeground(Color.RED);
			this.add(pendingUpgrades.get(i));
		}
	}
	public void addScore(int score) {
		this.score += score;
	}
	public void removeScore(int score) {
		this.score -= score;
	}
	public int getScore() {
		return score;
	}
	public void reset() {
		score = 0;
		rounds = 0;
		appliedUpgrades.clear();
		pendingUpgrades.clear();
		this.removeAll();
	}
	public void setTime(int time) {
		this.time = (double)time/100;
	}
	public double getTime() {
		return time;
	}
	public void updateUpgrades(int upgradeId) {
		Upgrade targetUpgrade = pendingUpgrades.get(0);
		int i=0;
		while(targetUpgrade.getUpgradeId()!=upgradeId && i<pendingUpgrades.size()) {
			i++;
			targetUpgrade = pendingUpgrades.get(i);
		}
		appliedUpgrades.add(targetUpgrade);
		currSkillCooldown.add(0.0);
		score-=targetUpgrade.getCost();
		this.remove(pendingUpgrades.get(i));
		pendingUpgrades.remove(i);
		i=0;
	}
	public ArrayList<Upgrade> getUpgrades() {
		return appliedUpgrades;
	}
	public ArrayList<Upgrade> getPendingUpgrades() {
		return pendingUpgrades;
	}
	public ArrayList<Double> getCooldown() {
		return currSkillCooldown;
	}
	public void resetCooldown(int upgradeId) {
		Upgrade targetUpgrade = appliedUpgrades.get(0);
		int i=0;
		while(targetUpgrade.getUpgradeId()!=upgradeId && i<pendingUpgrades.size()) {
			i++;
			targetUpgrade = appliedUpgrades.get(i);
		}
		currSkillCooldown.set(i, targetUpgrade.getCooldown());
	}
}
