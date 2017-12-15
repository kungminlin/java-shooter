import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Upgrade extends JButton {
	private GameWindow game;
	private int upgradeId;
	private String upgradeName;
	private String upgradeDesc;
	private int cost;
	private double cooldown;
	public Upgrade(int upgradeId, GameWindow game) {
		this.upgradeId = upgradeId;
		this.addActionListener(new UpgradeListener(upgradeId, game));
		
		switch(upgradeId) {
			case 0:
				upgradeName = "Triple Shooter";
				upgradeDesc = "Your bullets can deal AoE damage to enemies.";
				cost = 60;
				upgradeDesc += " Cost: " + cost;
				cooldown = 0;
				break;
			case 1:
				upgradeName = "Orbital Shooter";
				upgradeDesc = "Shoots bullets around you every ";
				cost = 400;
				upgradeDesc += " Cost: " + cost;
				cooldown = 0;
				break;
			case 2:
				upgradeName = "Shield";
				upgradeDesc = "You have a shield around you that can protect you against incoming enemies.";
				cost = 120;
				upgradeDesc += " Cost: " + cost;
				cooldown = 0;
				break;
			case 3:
				upgradeName = "[R] Massacre";
				upgradeDesc = "Ult Attack: You can kill all enemies on the screen instantly.";
				cost = 400;
				upgradeDesc += " Cost: " + cost;
				cooldown = 10.00;
				break;
			case 4:
				upgradeName = "[N] Nullify";
				upgradeDesc = "Ult Attack: You can slow down enemies and make yourself untrackable.";
				cost = 350;
				upgradeDesc += " Cost: " + cost;
				cooldown = 10.00;
				break;
			default:
				break;
		}	
		this.setText(upgradeName);
		this.setToolTipText(upgradeDesc);
	}
	public String getUpgradeName() {
		return upgradeName;
	}
	public int getUpgradeId() {
		return upgradeId;
	}
	public int getCost() {
		return cost;
	}
	public double getCooldown() {
		return cooldown;
	}
}
