package gorunum;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Ortak.GlobalDegiskenler;
import Ortak.IsTipi;

public class RobotOzellikPanel extends JPanel {
	private RobotOzellikPanel robotOzellikPanel;
	private JRadioButton baslangicNoktasiSec;
	private JRadioButton hedefNoktasiSec;
	private JRadioButton engelCizRadioButon;
	private JRadioButton hareketliEngelCizRadioButon;

	public RobotOzellikPanel() {
		robotOzellikPanel = this;
		panelOlustur();
		ButtonGroup butonGrup = new ButtonGroup();
		butonGrup.add(getBaslangicNoktasiSecRadioButon());
		butonGrup.add(getHedefNoktasiSecRadioButon());
		butonGrup.add(getEngelCizRadioButon());
		butonGrup.add(getHareketliEngelCizRadioButon());
	}

	private void panelOlustur() {
		robotOzellikPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(2, 2, 2, 2);

		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		robotOzellikPanel.add(getBaslangicNoktasiSecRadioButon(), gbc);
		gbc.gridy++;
		robotOzellikPanel.add(getHedefNoktasiSecRadioButon(), gbc);
		gbc.gridy++;
		gbc.gridwidth = 1;
		robotOzellikPanel.add(getEngelCizRadioButon(), gbc);
		gbc.gridy++;
		robotOzellikPanel.add(getHareketliEngelCizRadioButon(), gbc);
	}

	public JRadioButton getBaslangicNoktasiSecRadioButon() {
		if (baslangicNoktasiSec == null) {
			baslangicNoktasiSec = new JRadioButton("Baslangýç Noktasý Seç");
			baslangicNoktasiSec.setSelected(true);
			baslangicNoktasiSec.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					if (arg0.getStateChange() == ItemEvent.SELECTED) {
						GlobalDegiskenler.setSecilenIstipi(IsTipi.BASLANGIC);
					}

				}
			});
		}

		return baslangicNoktasiSec;
	}

	public JRadioButton getHedefNoktasiSecRadioButon() {
		if (hedefNoktasiSec == null) {
			hedefNoktasiSec = new JRadioButton("Hedef Noktasi");
			hedefNoktasiSec.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						GlobalDegiskenler.setSecilenIstipi(IsTipi.BITIS);
					}

				}
			});

		}

		return hedefNoktasiSec;
	}

	public JRadioButton getEngelCizRadioButon() {
		if (engelCizRadioButon == null) {
			engelCizRadioButon = new JRadioButton("Engel Çiz");
			engelCizRadioButon.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						GlobalDegiskenler.setSecilenIstipi(IsTipi.ENGEL);
					}

				}
			});
		}
		return engelCizRadioButon;
	}

	public JRadioButton getHareketliEngelCizRadioButon() {
		if (hareketliEngelCizRadioButon == null) {
			hareketliEngelCizRadioButon = new JRadioButton("Hareketli Engel Çiz");
			hareketliEngelCizRadioButon.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						GlobalDegiskenler.setSecilenIstipi(IsTipi.HAREKETLI_ENGEL);
					}

				}
			});
		}

		return hareketliEngelCizRadioButon;
	}

}
