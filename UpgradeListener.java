import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class UpgradeListener implements ActionListener {
	private int upgradeId;
	private GameWindow game;
	public UpgradeListener(int upgradeId, GameWindow game) {
		this.upgradeId = upgradeId;
		this.game = game;
	}
	public void actionPerformed(ActionEvent e) {
		ArrayList<Upgrade> pendingUpgrades = game.getScoreboard().getPendingUpgrades();
		Upgrade targetUpgrade = pendingUpgrades.get(0);
		int i=0;
		while(targetUpgrade.getUpgradeId()!=upgradeId && i<pendingUpgrades.size()) {
			i++;
			targetUpgrade = pendingUpgrades.get(i);
		}
		if (game.getScoreboard().getScore() >= targetUpgrade.getCost()) game.getScoreboard().updateUpgrades(upgradeId);
	}
}