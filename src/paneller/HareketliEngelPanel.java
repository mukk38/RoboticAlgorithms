package paneller;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Enum.Yonler;
import Ortak.GlobalDegiskenler;

public class HareketliEngelPanel extends JPanel {

	private HareketliEngelPanel hareketliEngelPanel;
	private JRadioButton engelSecimRadioButon;
	private JCheckBox yonCheckBoks;
	private int indeks;
	
	
	public HareketliEngelPanel(int indeks_){
		hareketliEngelPanel= this;
		indeks = indeks_;

		panelOlustur();
	}
	
	private void panelOlustur() {
	
		hareketliEngelPanel.add(getEnselSecimRadioButon());
		hareketliEngelPanel.add(getYonCheckBoks());
		
	}

	public JRadioButton getEnselSecimRadioButon(){
		if (engelSecimRadioButon == null) {
			engelSecimRadioButon = new JRadioButton("Engel "+(indeks+1));
			
		}

		return engelSecimRadioButon;
	}
	public JCheckBox getYonCheckBoks(){
		if (yonCheckBoks == null) {
			yonCheckBoks = new JCheckBox("Kuzey/Guney");
			yonCheckBoks.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						yonCheckBoks.setText("Dogu/Batý");
						GlobalDegiskenler.getHareketliEngelNoktalari().get(indeks).setYon(Yonler.DOGU);
					}else if(e.getStateChange()==ItemEvent.DESELECTED){
						yonCheckBoks.setText("Kuzey/Guney");
						GlobalDegiskenler.getHareketliEngelNoktalari().get(indeks).setYon(Yonler.KUZEY);
					}
					
				}
			});
		}

		return yonCheckBoks;
	}


	public int getIndeks() {
		return indeks;
	}
}
