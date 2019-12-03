package Ortak;

import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class IslemDevamEdiyorPanel extends JPanel {

	public static final ImageIcon			IMAGE_ICON1	= new ImageIcon("./external/icon/IslemAnimasyonBuyuk1.png");
	private static final ImageIcon			IMAGE_ICON2	= new ImageIcon("./external/icon/IslemAnimasyonBuyuk2.png");
	private final Timer						timer		= new Timer();
	private final IslemDevamEdiyorTimerTask	timerTask	= new IslemDevamEdiyorTimerTask();
	private ImageIcon						ikon		= IMAGE_ICON1;
	private JLabel							lbDevamIkon;

	public IslemDevamEdiyorPanel() {
		super();
		ilklendir();
	}

	private void ilklendir() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.add(getDevamIkon());
		timerTask.setAnaPanel(this);
		timer.schedule(timerTask, 0, 200);
	}

	private JLabel getDevamIkon() {
		lbDevamIkon = new JLabel();
		lbDevamIkon.setIcon(ikon);
		return lbDevamIkon;
	}

	public void setDevamIkon(boolean durum) {
		if (!durum) {
			ikon = IMAGE_ICON1;
		} else {
			ikon = IMAGE_ICON2;
		}
		lbDevamIkon.setIcon(ikon);
		lbDevamIkon.updateUI();
	}

	public void sonlandir() {
		timer.cancel();
	}

	public class IslemDevamEdiyorTimerTask extends TimerTask {

		private boolean				  hazir	= false;
		private IslemDevamEdiyorPanel islemDevamEdiyorPanel;

		@Override
		public void run() {
			setHazir(!isHazir());
			islemDevamEdiyorPanel.setDevamIkon(isHazir());
		}

		public boolean isHazir() {
			return hazir;
		}

		public void setHazir(boolean hazir) {
			this.hazir = hazir;
		}

		public void setAnaPanel(IslemDevamEdiyorPanel panel) {
			this.islemDevamEdiyorPanel = panel;

		}
	}

}

