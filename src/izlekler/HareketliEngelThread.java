package izlekler;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import Enum.Yonler;
import Mapping.Engel;
import Mapping.GridEngel;
import Mapping.Nokta;
import Ortak.GlobalDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import dwa2.GlobalDegisken;
import kontrol.GridPanelKontrol;
import potentialField2.PotentialKontrol;

public class HareketliEngelThread extends Thread {

	public static int GRID_EN = OrtakMetotlar.getGridXsayisi();
	public static int GRID_BOY = OrtakMetotlar.getGridYsayisi();

	@Override
	public void run() {
		while (GlobalDegiskenler.getAnaPencere().getEngelEklePanel().getHareketliEngelCalissinMiCheckBoks()
				.isSelected()) {
			Engel[] engelHedefleri = GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri();
			for (int i = 0; i < OrtakSabitler.MAKSIMUM_ENGEL_SAYISI; i++) {
				if (engelHedefleri[i] != null && GlobalDegiskenler.getHareketliEngelPanelleri().get(i).isVisible()) {
			int engelIndeks = engelIndeksGetir(i);
					Nokta baslamaNoktasi = GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).getBaslamaNoktasi();
					Nokta bitisNoktasi = GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).getBitisNoktasi();
					int baslamaX = baslamaNoktasi.getX();
					int baslamaY = baslamaNoktasi.getY();
					int bitisX = bitisNoktasi.getX();
					int bitisY = bitisNoktasi.getY();
					switch (GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).getYon()) {
					case KUZEY:
						if (baslamaX <= OrtakSabitler.HAREKETLI_ENGEL_OFSET) {
							GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).setYon(Yonler.GUNEY);
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX(),
											engelHedefleri[i].getBaslamaNoktasi().getY() + 10));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX(),
											engelHedefleri[i].getBitisNoktasi().getY() + 10));

						} else {
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX(),
											engelHedefleri[i].getBaslamaNoktasi().getY() - 10));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX(),
											engelHedefleri[i].getBitisNoktasi().getY() - 10));

						}
						break;
					case GUNEY:
						if (bitisX >= (GRID_BOY - OrtakSabitler.HAREKETLI_ENGEL_OFSET)) {
							GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).setYon(Yonler.KUZEY);
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX(),
											engelHedefleri[i].getBaslamaNoktasi().getY() - 10));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX(),
											engelHedefleri[i].getBitisNoktasi().getY() - 10));

						} else {
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX(),
											engelHedefleri[i].getBaslamaNoktasi().getY() + 10));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX(),
											engelHedefleri[i].getBitisNoktasi().getY() + 10));

						}
						break;
					case DOGU:
						if (bitisY >= (GRID_EN - OrtakSabitler.HAREKETLI_ENGEL_OFSET)) {
							GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).setYon(Yonler.BATI);
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX() - 10,
											engelHedefleri[i].getBaslamaNoktasi().getY()));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX() - 10,
											engelHedefleri[i].getBitisNoktasi().getY()));

						} else {
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX() + 10,
											engelHedefleri[i].getBaslamaNoktasi().getY()));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX() + 10,
											engelHedefleri[i].getBitisNoktasi().getY()));

						}
						break;
					case BATI:
						if (baslamaY <= OrtakSabitler.HAREKETLI_ENGEL_OFSET) {
							GlobalDegiskenler.getHareketliEngelNoktalari().get(engelIndeks).setYon(Yonler.DOGU);
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX() + 10,
											engelHedefleri[i].getBaslamaNoktasi().getY()));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX() + 10,
											engelHedefleri[i].getBitisNoktasi().getY()));

						} else {
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBaslamaNoktasi(new Nokta(engelHedefleri[i].getBaslamaNoktasi().getX() - 10,
											engelHedefleri[i].getBaslamaNoktasi().getY()));
							GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i]
									.setBitisNoktasi(new Nokta(engelHedefleri[i].getBitisNoktasi().getX() - 10,
											engelHedefleri[i].getBitisNoktasi().getY()));

						}
						break;
					default:
						break;
					}
					GlobalDegisken.obstacleDegisti();
							Nokta baslamaGridLabelIndis = hareketliEngelIndisBul(
									GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i].getBaslamaNoktasi());
							Nokta bitisGridLabelIndis = hareketliEngelIndisBul(
									GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri()[i].getBitisNoktasi());
							GridPanelKontrol.hareketliEngelIndisleriniBelirle(baslamaGridLabelIndis, bitisGridLabelIndis, i);
							
				PotentialKontrol.obstacleDegisti();
					

					GlobalDegiskenler.GetGridPanel().revalidate();
					GlobalDegiskenler.GetGridPanel().repaint();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}

		}

	}

	private int engelIndeksGetir(int indis) {
		for(int i=0;i<GlobalDegiskenler.getHareketliEngelNoktalari().size();i++){
			if(indis == GlobalDegiskenler.getHareketliEngelNoktalari().get(i).getEngelIndeks()){
				return i;
			}
		}
		return 0;
	}

	public static Nokta hareketliEngelIndisBul(Nokta noktta) {
		int noktaX = (int) noktta.getX();
		int noktaY = (int) noktta.getY();
		int xMinimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[0][0].getLocation().x;
		int yMinimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[0][0].getLocation().y;
		int xMaksimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[GRID_BOY - 1][GRID_EN - 1].getLocation().x;
		int yMaksimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[GRID_BOY - 1][GRID_EN - 1].getLocation().y;
		if (noktaX < xMinimum) {
			noktaX = xMinimum;
		}
		if (noktaX > xMaksimum) {
			noktaX = xMaksimum;
		}
		if (noktaY < yMinimum) {
			noktaY = yMinimum;
		}
		if (noktaY > yMaksimum) {
			noktaY = yMaksimum;
		}
		for (int i = 0; i < GRID_BOY; i++) {
			for (int j = 0; j < GRID_EN; j++) {
				int x1 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocation().x;
				int x2 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocation().x
						+ OrtakSabitler.GRID_LABEL_BOY;
				int y1 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocation().y;
				int y2 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocation().y
						+ OrtakSabitler.GRID_LABEL_BOY;
				if (noktaX <= x2 && noktaX >= x1 && noktaY <= y2 && noktaY >= y1) {
					Nokta gridIndis = new Nokta(i, j);
					return gridIndis;
				}

			}
		}
		return null;
	}
}
