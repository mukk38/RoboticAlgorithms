package kontrol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.sun.javafx.geom.Point2D;

import Enum.Yonler;
import Mapping.Engel;
import Mapping.GridEngel;
import Mapping.Nokta;
import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import PotentialField.Obstacle;
import PotentialField.Robot;
import dwa2.GlobalDegisken;
import javafx.scene.canvas.GraphicsContext;
import motionPlanning.Node;
import potentialField2.PotentialKontrol;

public final class GridPanelKontrol {
	private static Image[] robotImage = new Image[OrtakSabitler.MAKSIMUM_ROBOT_SAYISI];
	private static Image bitisNoktasiImaji;
	public static int GRID_EN = OrtakMetotlar.getGridXsayisi();
	public static int GRID_BOY = OrtakMetotlar.getGridYsayisi();
	public final static String BIR_NUMARA_DOSYA_PATH = ".//external//icon//mobile1.png";
	public final static String IKI_NUMARA_DOSYA_PATH = ".//external//icon//mobile2.png";
	public final static String UC_NUMARA_DOSYA_PATH = ".//external//icon//mobile3.png";
	public final static String DORT_NUMARA_DOSYA_PATH = ".//external//icon//mobile4.png";
	public final static String BES_NUMARA_DOSYA_PATH = ".//external//icon//mobile5.png";
	public final static String ALTI_NUMARA_DOSYA_PATH = ".//external//icon//mobile6.png";
	public final static String YEDI_NUMARA_DOSYA_PATH = ".//external//icon//mobile7.png";
	public final static String SEKIZ_NUMARA_DOSYA_PATH = ".//external//icon//mobile8.png";
	public final static String DOKUZ_NUMARA_DOSYA_PATH = ".//external//icon//mobile9.png";
	public final static String ON_NUMARA_DOSYA_PATH = ".//external//icon//mobile10.png";

	public static ArrayList<HashSet<Point>> potentialFieldYollar = new ArrayList<HashSet<Point>>();
	public static ArrayList<HashSet<Point>> dynamicWindowsYollar = new ArrayList<HashSet<Point>>();
	static Point eskiNokta = new Point();

	private GridPanelKontrol() {

	}

	public static Nokta gridLabelIndisBul(Point locationOnScreen) {
		int noktaX = (int) locationOnScreen.getX();
		int noktaY = (int) locationOnScreen.getY();
		int xMinimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[0][0].getLocationOnScreen().x;
		int yMinimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[0][0].getLocationOnScreen().y;
		int xMaksimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[GRID_BOY - 1][GRID_EN - 1]
				.getLocationOnScreen().x;
		int yMaksimum = GlobalDegiskenler.GetGridPanel().getGridBorder()[GRID_BOY - 1][GRID_EN - 1]
				.getLocationOnScreen().y;
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
				int x1 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocationOnScreen().x;
				int x2 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocationOnScreen().x
						+ OrtakSabitler.GRID_LABEL_BOY;
				int y1 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocationOnScreen().y;
				int y2 = GlobalDegiskenler.GetGridPanel().getGridBorder()[i][j].getLocationOnScreen().y
						+ OrtakSabitler.GRID_LABEL_BOY;
				if (noktaX <= x2 && noktaX >= x1 && noktaY <= y2 && noktaY >= y1) {
					Nokta gridIndis = new Nokta(i, j);
					return gridIndis;
				}

			}
		}
		System.out.println("--------------------------------------------------------------");
		return null;
	}

	public static void baslamaNoktasiCiz(Graphics g, Point[] robotPoint) {
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (robotPoint[i] != null && GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()) {
				g.drawImage(robotImage[i], robotPoint[i].x, robotPoint[i].y, GlobalDegiskenler.GetGridPanel());
			}
		}

	}

	public static void robotImajlariYapilandir() {
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			try {
				robotImage[i] = ImageIO.read(new File(imageDosyaYoluGetir(i)));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bitisNoktasiImaji = ImageIO.read(new File(OrtakSabitler.BITIS_NOKTASI_DOSYA_PATH)).getScaledInstance(30, 30,
					Image.SCALE_SMOOTH);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String imageDosyaYoluGetir(int seciliIndeks) {

		switch (seciliIndeks) {
		case 0:
			return BIR_NUMARA_DOSYA_PATH;
		case 1:
			return IKI_NUMARA_DOSYA_PATH;
		case 2:
			return UC_NUMARA_DOSYA_PATH;
		case 3:
			return DORT_NUMARA_DOSYA_PATH;
		case 4:
			return BES_NUMARA_DOSYA_PATH;
		case 5:
			return ALTI_NUMARA_DOSYA_PATH;
		case 6:
			return YEDI_NUMARA_DOSYA_PATH;
		case 7:
			return SEKIZ_NUMARA_DOSYA_PATH;
		case 8:
			return DOKUZ_NUMARA_DOSYA_PATH;
		case 9:
			return ON_NUMARA_DOSYA_PATH;

		default:
			return BIR_NUMARA_DOSYA_PATH;

		}

	}

	public static void bitisNoktasiCiz(Graphics g, Point[] robotHedefPoint) {

		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (robotHedefPoint[i] != null && GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()) {
				g.drawImage(bitisNoktasiImaji, robotHedefPoint[i].x, robotHedefPoint[i].y,
						GlobalDegiskenler.GetGridPanel());
			}
		}
	}

	public static void engellerCiz(Graphics g, Engel[] engelHedefleri) {
		// g.setColor(Color.RED);
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ENGEL_SAYISI; i++) {
			if (engelHedefleri[i] != null && GlobalDegiskenler.getEngelRadioButonlar().get(i).isVisible()) {
				int x = 0;
				int y = 0;
				if (engelHedefleri[i].getBitisNoktasi() == null
						|| engelHedefleri[i].getBaslamaNoktasi().getX() <= engelHedefleri[i].getBitisNoktasi().getX()) {
					x = engelHedefleri[i].getBaslamaNoktasi().getX();
				} else {
					x = engelHedefleri[i].getBitisNoktasi().getX();
				}
				if (engelHedefleri[i].getBitisNoktasi() == null
						|| engelHedefleri[i].getBaslamaNoktasi().getY() <= engelHedefleri[i].getBitisNoktasi().getY()) {
					y = engelHedefleri[i].getBaslamaNoktasi().getY();
				} else {
					y = engelHedefleri[i].getBitisNoktasi().getY();
				}
				g.drawRect(x, y, engelHedefleri[i].getWeight(), engelHedefleri[i].getHeight());
				g.setColor(Color.BLACK);
				g.fillRect(x, y, engelHedefleri[i].getWeight(), engelHedefleri[i].getHeight());
			}
		}

	}

	public static void hareketliEngellerCiz(Graphics g, Engel[] engelHedefleri) {
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ENGEL_SAYISI; i++) {
			if (engelHedefleri[i] != null && GlobalDegiskenler.getHareketliEngelPanelleri().get(i).isVisible()) {
				int x = 0;
				int y = 0;
				if (engelHedefleri[i].getBitisNoktasi() == null
						|| engelHedefleri[i].getBaslamaNoktasi().getX() <= engelHedefleri[i].getBitisNoktasi().getX()) {
					x = engelHedefleri[i].getBaslamaNoktasi().getX();
				} else {
					x = engelHedefleri[i].getBitisNoktasi().getX();
				}
				if (engelHedefleri[i].getBitisNoktasi() == null
						|| engelHedefleri[i].getBaslamaNoktasi().getY() <= engelHedefleri[i].getBitisNoktasi().getY()) {
					y = engelHedefleri[i].getBaslamaNoktasi().getY();
				} else {
					y = engelHedefleri[i].getBitisNoktasi().getY();
				}
				g.drawRect(x+15, y+15, engelHedefleri[i].getWeight()-15, engelHedefleri[i].getHeight()-15);
				g.setColor(Color.BLACK);
				g.fillRect(x+15, y+15, engelHedefleri[i].getWeight()-15, engelHedefleri[i].getHeight()-15);
			}
		}

	}
	public static void scriptEngelCiz(Graphics g, ArrayList<Engel> scriptEngeller) {
		if(OrtakMetotlar.isScriptUygulanýyorMu()){
			for(Engel engel : scriptEngeller){
				g.drawRect(engel.getBaslamaNoktasi().getX()+10, engel.getBaslamaNoktasi().getY()+10, engel.getWeight()-20, engel.getHeight()-20);
				g.setColor(Color.BLACK);
				g.fillRect(engel.getBaslamaNoktasi().getX()+10, engel.getBaslamaNoktasi().getY()+10, engel.getWeight()-20, engel.getHeight()-20);
			}
		}
		
	}

	public static void engelIndisleriniBelirle(Nokta baslamaPoint, Nokta bitisPoint, int indeks) {
		engelNoktalariIndekseGoreSil(indeks);
		int baslamaX, baslamaY, bitisX, bitisY;
		if (baslamaPoint.getX() <= bitisPoint.getX()) {
			baslamaX = baslamaPoint.getX();
			bitisX = bitisPoint.getX();
		} else {
			baslamaX = bitisPoint.getX();
			bitisX = baslamaPoint.getX();
		}
		if (baslamaPoint.getY() <= bitisPoint.getY()) {
			baslamaY = baslamaPoint.getY();
			bitisY = bitisPoint.getY();
		} else {
			baslamaY = bitisPoint.getY();
			bitisY = baslamaPoint.getY();
		}
		GridEngel gridEngel = new GridEngel();
		gridEngel.setEngelIndeks(indeks);
		gridEngel.setxGenislik(bitisX - baslamaX);
		gridEngel.setyGenislik(bitisY - baslamaY);
		ArrayList<Nokta> noktalar = new ArrayList<Nokta>();

		for (int i = baslamaX; i < bitisX - 1; i++) {
			for (int j = baslamaY; j < bitisY - 1; j++) {
				Nokta nokta = new Nokta(i, j);
				noktalar.add(nokta);

			}
		}
		gridEngel.setNoktalar(noktalar);
		GlobalDegiskenler.getEngelNoktalari().add(gridEngel);
		sabitEngelInisleriniCiz();
	}

	private static void engelNoktalariIndekseGoreSil(int indeks) {
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			if (GlobalDegiskenler.getEngelNoktalari().get(i).getEngelIndeks() == indeks) {
				for (int j = 0; j < GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size(); j++) {
					GlobalDegiskenler.GetGridPanel().getGridBorder()[GlobalDegiskenler.getEngelNoktalari().get(i)
							.getNoktalar().get(j).getX()][GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar()
									.get(j).getY()].setIcon(null);
				}
			}
		}
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			if (GlobalDegiskenler.getEngelNoktalari().get(i).getEngelIndeks() == indeks) {
				GlobalDegiskenler.getEngelNoktalari().remove(i);
			}
		}
		sabitEngelInisleriniCiz();
	}

	public static void hareketliEngelIndisleriniBelirle(Nokta baslamaPoint, Nokta bitisPoint, int indeks) {
		Yonler yonYedek = Yonler.KUZEY;
		for (int i = 0; i < GlobalDegiskenler.getHareketliEngelNoktalari().size(); i++) {
			if (GlobalDegiskenler.getHareketliEngelNoktalari().get(i).getEngelIndeks() == indeks) {
				yonYedek = GlobalDegiskenler.getHareketliEngelNoktalari().get(i).getYon();
			}
		}
		hareketliEngelNoktalariIndekseGoreSil(indeks);
		int baslamaX, baslamaY, bitisX, bitisY;
		if (baslamaPoint.getX() <= bitisPoint.getX()) {
			baslamaX = baslamaPoint.getX();
			bitisX = bitisPoint.getX();
		} else {
			baslamaX = bitisPoint.getX();
			bitisX = baslamaPoint.getX();
		}
		if (baslamaPoint.getY() <= bitisPoint.getY()) {
			baslamaY = baslamaPoint.getY();
			bitisY = bitisPoint.getY();
		} else {
			baslamaY = bitisPoint.getY();
			bitisY = baslamaPoint.getY();
		}
		GridEngel gridEngel = new GridEngel();
		gridEngel.setEngelIndeks(indeks);
		gridEngel.setxGenislik(bitisX - baslamaX + 1);
		gridEngel.setyGenislik(bitisY - baslamaY + 1);
		gridEngel.setBitisNoktasi(bitisPoint);
		gridEngel.setBaslamaNoktasi(baslamaPoint);
		gridEngel.setYon(yonYedek);
		ArrayList<Nokta> noktalar = new ArrayList<Nokta>();
		for (int i = baslamaX; i < bitisX + 1; i++) {
			for (int j = baslamaY; j < bitisY + 1; j++) {
				Nokta nokta = new Nokta(i, j);
				noktalar.add(nokta);

			}
		}
		gridEngel.setNoktalar(noktalar);
		GlobalDegiskenler.getHareketliEngelNoktalari().add(gridEngel);
		
	}

	private static void hareketliEngelNoktalariIndekseGoreSil(int indeks) {

		for (int i = 0; i < GlobalDegiskenler.getHareketliEngelNoktalari().size(); i++) {
			if (GlobalDegiskenler.getHareketliEngelNoktalari().get(i).getEngelIndeks() == indeks) {
				GlobalDegiskenler.getHareketliEngelNoktalari().remove(i);
			}
		}
		GlobalDegisken.obstacleDegisti();
	}

	public static void sabitEngelInisleriniCiz() {
		int baslamaX, baslamaY, bitisX, bitisY;

		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			if (GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar() != null
					&& GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size() > 0) {
				baslamaX = GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(0).getX();
				baslamaY = GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(0).getY();
				bitisX = GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar()
						.get(GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size() - 1).getX();
				bitisY = GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar()
						.get(GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size() - 1).getY();
				for (int j = baslamaX + 3; j < bitisX - 3; j++) {
					for (int k = baslamaY + 3; k < bitisY - 3; k++) {
						GlobalDegiskenler.GetGridPanel().getGridBorder()[j][k]
								.setIcon(new ImageIcon(OrtakSabitler.ENGEL_DOSYA_PATH));

					}
				}
			}
		}

		// for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size();
		// i++) {
		// for (int j = 0; j <
		// GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size();
		// j++) {
		// Nokta nokta =
		// GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(j);
		// GlobalDegiskenler.GetGridPanel().getGridBorder()[nokta.getX()][nokta.getY()]
		// .setIcon(new ImageIcon(OrtakSabitler.ENGEL_DOSYA_PATH));
		//
		// }
		// }
		GlobalDegiskenler.GetGridPanel().revalidate();
		GlobalDegiskenler.GetGridPanel().repaint();
	}

	public static synchronized void potentialFieldVeriyiCiz(Graphics g) {
		ArrayList<Robot> robotlar = PotentialFieldKontrol.getRobotlar();
		// ArrayList<Obstacle> engeller = (ArrayList<Obstacle>)
		// PotentialFieldKontrol.getObstacles().clone();

		g.setColor(Color.BLACK);
		// for (Obstacle ob :engeller) {
		// // Obstacle ob = (Obstacle) iter.next();
		// g.fillArc((int) (ob.p.x - ob.diam / 2), (int) (ob.p.y - ob.diam / 2),
		// (int) ob.diam, (int) ob.diam, 0,
		// 360);
		// }
		for (int i = 0; i < robotlar.size(); i++) {
			potentialFieldYollar.get(i).add(new Point((int) robotlar.get(i).x, (int) robotlar.get(i).y));
			// Iterator iter = engeller.get(i).iterator();
			// ArrayList<Obstacle> engel = engeller.get(i);

			g.setColor(Color.BLUE);
			for (Point p : potentialFieldYollar.get(i)) {
				g.fillArc((int) (p.x - robotlar.get(i).diam / 4), (int) (p.y - robotlar.get(i).diam / 4),
						(int) robotlar.get(i).diam / 2, (int) robotlar.get(i).diam / 2, 0, 360);
			}

			g.setColor(Color.RED);
			g.fillArc((int) (robotlar.get(i).x - robotlar.get(i).diam / 2),
					(int) (robotlar.get(i).y - robotlar.get(i).diam / 2), (int) robotlar.get(i).diam,
					(int) robotlar.get(i).diam, 0, 360);
			GlobalDegiskenler.GetGridPanel().getRobotPoint()[i] = new Point((int) robotlar.get(i).x,
					(int) robotlar.get(i).y);
			g.drawLine((int) robotlar.get(i).x, (int) robotlar.get(i).y, (int) (robotlar.get(i).x + robotlar.get(i).vx),
					(int) (robotlar.get(i).y + robotlar.get(i).vy));

		}
	}

	public static void potentialFieldAyarlamalariYap() {
		potentialFieldYollar.clear();
		int counter = 0;
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
					&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null
					&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
				counter++;
			}
		}
		for (int i = 0; i < counter; i++) {
			HashSet<Point> s = new HashSet<Point>();
			potentialFieldYollar.add(s);
		}

	}

	public static void gidilenYollariCiz(Graphics g, ArrayList<ArrayList<Point>> gidilenYollar) {
		g.setColor(Color.BLUE);
		synchronized (gidilenYollar) {
			for (int i = 0; i < gidilenYollar.size(); i++) {
				for (Point p : gidilenYollar.get(i)) {
					g.setColor(Color.BLUE);
					g.fillArc((int) (p.x - 4), (int) (p.y - 4), 5, 5, 0, 360);
				}
				if (gidilenYollar.get(i).size() > 3) {
					Point p1 = gidilenYollar.get(i).get(gidilenYollar.get(i).size() - 3);
					Point p2 = gidilenYollar.get(i).get(gidilenYollar.get(i).size() - 1);
					g.setColor(Color.RED);
					g.fillArc((int) (p1.x - 8), (int) (p1.y - 8), (int) 15, 15, 0, 360);
					g.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
				}
			}
		}
	}

	public static void prmCiz(Graphics g, List<Point> pRMPoints) {
		int b = 2;
		for (int i = 0; i < pRMPoints.size(); i++) {
			double x = pRMPoints.get(i).x;
			double y = pRMPoints.get(i).y;
			g.setColor(Color.BLUE);
			g.fillOval((int) x - b, (int) y - b, 2 * b, 2 * b);
		}
		// if (KureselDegiskenler.isPrmBaglaTiklandi()) {
		// for (int j = 0; j < pRMPoints.size(); j++) {
		// Point point = pRMPoints.get(j);
		// for (int i = 0; i < 5; i++) {
		// Point close = GlobalDegiskenler.GetGridPanel().getClosest().get(i);
		// if (close != null) {
		// g.setColor(Color.BLACK);
		// g.drawLine(close.x, close.y, point.x, point.y);
		// }
		// }
		// }
		// }

	}

	public static void rrtCiz(Graphics g, List<Node> pRMPoints, Engel[] engelHedefleri) {
		int d = 2;
		Random rand = new Random();
		;
		int r = rand.nextInt(255);
		int ge = rand.nextInt(255);
		int b = rand.nextInt(255);
		
		
		for (Node dugum : pRMPoints) {
			g.setColor(Color.BLUE);
			g.fillOval((int) dugum.point.x - d, (int) dugum.point.y - d, 2 * d, 2 * d);

			g.setColor(Color.BLACK);
			if (dugum.parent != null) {
				if(!dugum.hedefeGidenYolMu){
					g.setColor(Color.BLACK);
				}else{
					g.setColor(Color.RED);
				}
				g.drawLine((int) dugum.parent.point.x, (int) dugum.parent.point.y, (int) dugum.point.x - d,
						(int) dugum.point.y - d);
			}
		}
	}

	public static void rrtStarCiz(Graphics g, List<Node> rRTPoints, Engel[] engelHedefleri) {
		rrtCiz(g, rRTPoints, engelHedefleri);

	}

	public static void dynamicWindowsApproachVeriyiCiz(Graphics g) {
		for (int i = 0; i < DynamicWindowApproach.getRobotlar().size(); i++) {
			
			dynamicWindowsYollar.get(i).add(new Point((int) DynamicWindowApproach.getRobotlar().get(i).xPosition,
					(int) DynamicWindowApproach.getRobotlar().get(i).yPosition));
			// System.out.println("Dynamic Windows Yollar size
			// "+dynamicWindowsYollar.get(i).size());
			// System.out.println(" X Position
			// "+DynamicWindowApproach.getRobotlar().get(i).xPosition+" Y
			// position "+DynamicWindowApproach.getRobotlar().get(i).yPosition);
			// System.out.println("XXXXX
			// "+DynamicWindowApproach.getRobotlar().get(i).xPosition+" YYY
			// "+DynamicWindowApproach.getRobotlar().get(i).yPosition);
	
			// g.setColor(Color.RED);
			// g.fillArc((int) (robotlar.get(i).x - robotlar.get(i).diam / 2),
			// (int) (robotlar.get(i).y - robotlar.get(i).diam / 2), (int)
			// robotlar.get(i).diam,
			// (int) robotlar.get(i).diam, 0, 360);

			
			for (Point p : dynamicWindowsYollar.get(i)) {

				g.fillArc((int) (p.x - 4), (int) (p.y - 4), 4, 4, 0, 360);
			}
			g.setColor(Color.RED);
//			g.fillArc((int) (sondanBirPoint.x - 2),
//					(int) (sondanBirPoint.y - 2), 2,2, 0, 360);
			if( dynamicWindowsYollar.get(i).size()>3){
			g.drawLine(eskiNokta.x, eskiNokta.y, (int)DynamicWindowApproach.getRobotlar().get(i).xPosition,
					(int)DynamicWindowApproach.getRobotlar().get(i).yPosition);
			}
			 eskiNokta = new Point((int)DynamicWindowApproach.getRobotlar().get(i).xPosition, (int)DynamicWindowApproach.getRobotlar().get(i).yPosition);
			GlobalDegiskenler.GetGridPanel().getRobotPoint()[i] = new Point(
					(int) DynamicWindowApproach.getRobotlar().get(i).xPosition,
					(int) DynamicWindowApproach.getRobotlar().get(i).yPosition);

		}

	}

	public static void dynamicWindowsAyarlamalariYap() {

		dynamicWindowsYollar.clear();
		int counter = 0;
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
					&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null
					&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
				counter++;
			}
		}
		for (int i = 0; i < counter; i++) {
			HashSet<Point> s = new HashSet<Point>();
			dynamicWindowsYollar.add(s);
		}

	}

	public static ArrayList<HashSet<Point>> getPotentialFieldYollar() {
		return potentialFieldYollar;
	}

	public static ArrayList<HashSet<Point>> getDynamicWindowsYollar() {
		return dynamicWindowsYollar;
	}

	public static void potential2yiCiz(Graphics g) {
		for(int i=0;i<PotentialKontrol.threadler.size();i++){
			synchronized (PotentialKontrol.threadler.get(i)) {				
			for (Point p : PotentialKontrol.threadler.get(i).yollar) {
				g.setColor(Color.BLUE);
				g.fillArc((int) (p.x - 4), (int) (p.y - 4), 5, 5, 0, 360);
			}
			}
		}
		
	}

	

}
