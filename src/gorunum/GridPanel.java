package gorunum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.sun.javafx.geom.Line2D;
import com.sun.javafx.geom.Point2D;
import com.sun.javafx.geom.RectBounds;

import Enum.Algoritmalar;
import Mapping.Engel;
import Mapping.Nokta;
import Ortak.GlobalDegiskenler;
import Ortak.IsTipi;
import Ortak.KureselDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import dwa2.GlobalDegisken;
import javafx.scene.canvas.GraphicsContext;
import kontrol.AraclarPanelKontrol;
import kontrol.GridPanelKontrol;
import kontrol.PotentialFieldKontrol;
import kontrol.RobotKontrol;
import model.AlgoritmaModel;
import model.Modeller;
import motionPlanning.Node;

public class GridPanel extends JPanel {

	public static int GRID_EN = OrtakMetotlar.getGridXsayisi();
	public static int GRID_BOY = OrtakMetotlar.getGridYsayisi();
	private GridPanel gridPanel;
	private GridLabel gridBorder[][] = new GridLabel[GRID_BOY][GRID_EN];
	private Point[] robotPoint = new Point[OrtakSabitler.MAKSIMUM_ROBOT_SAYISI];
	private Point[] robotHedefPoint = new Point[OrtakSabitler.MAKSIMUM_ROBOT_SAYISI];
	private ArrayList<ArrayList<Point>> gidilenYollar = new ArrayList<ArrayList<Point>>();
	private Engel[] engelHedefleri = new Engel[OrtakSabitler.MAKSIMUM_ENGEL_SAYISI];
	private Engel[] hareketliEngelHedefleri = new Engel[OrtakSabitler.MAKSIMUM_ENGEL_SAYISI];
	private List<Node> RRTPoints = new ArrayList<>();
	private List<Point> PRMPoints = new ArrayList<>();
	private int RRTMultiplier = 8;
    private int numOfConnections = 5;
    private List<Point> closest = new ArrayList<>();
    public boolean targetBulundu =false;
    private ArrayList<Engel> scriptEngeller = new ArrayList<Engel>();

	public GridPanel() {
		gridPanel = this;
		panelOlustur();
		gridPanel.setBorder((new LineBorder(new Color(80, 80, 80), 2)));
		GridPanelKontrol.robotImajlariYapilandir();
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
			gidilenYollar.add(new ArrayList<Point>());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		GridPanelKontrol.baslamaNoktasiCiz(g, robotPoint);
		GridPanelKontrol.bitisNoktasiCiz(g, robotHedefPoint);
		GridPanelKontrol.scriptEngelCiz(g,scriptEngeller);
		if (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
				.getSelectedIndex() == OrtakSabitler.POTENTIAL_FIELD_ALGORTHM_INDEKS ||GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
						.getSelectedIndex()==OrtakSabitler.DYNAMIC_WINDIW_APPROACH|| GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
								.getSelectedIndex()==7) {
			GridPanelKontrol.hareketliEngellerCiz(g, hareketliEngelHedefleri);
		} else if (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
				.getSelectedIndex() == 3) {
			GridPanelKontrol.prmCiz(g, PRMPoints);
		} else if (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
				.getSelectedIndex() == 4) {
			GridPanelKontrol.rrtCiz(g, RRTPoints, engelHedefleri);
		} else if (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
				.getSelectedIndex() == 5) {
			GridPanelKontrol.rrtStarCiz(g, RRTPoints, engelHedefleri);
		}
		if(GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
				.getSelectedIndex() == 7){
			GridPanelKontrol.potential2yiCiz(g);
		}
		GridPanelKontrol.gidilenYollariCiz(g, gidilenYollar);
		// GridPanelKontrol.engellerCiz(g, engelHedefleri);
		if (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks().getSelectedIndex() == 2
				&& KureselDegiskenler.isPotentialFieldBaslatidi()) {
			GridPanelKontrol.potentialFieldVeriyiCiz(g);
		}
		if (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks().getSelectedIndex() == 6
				&& KureselDegiskenler.isDynamicWindowsApproachBaslatidi()) {
			GridPanelKontrol.dynamicWindowsApproachVeriyiCiz(g);
		}
	
	}

	public void panelOlustur() {

		gridPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		for (int i = 0; i < GRID_BOY; i++) {
			for (int j = 0; j < GRID_EN; j++) {
				gridBorder[i][j] = new GridLabel(i, j);
				gridPanel.add(gridBorder[i][j], gbc);
				gbc.gridx++;
			}
			gbc.gridx = 0;
			gbc.gridy++;
		}
		this.revalidate();
		this.repaint();
	}

	public GridLabel[][] getGridBorder() {
		return gridBorder;
	}

	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent arg0) {
			requestFocus();
			switch (GlobalDegiskenler.getSecilenIstipi()) {
			case BASLANGIC:
				int seciliRobotIndeks = GlobalDegiskenler.radioButonlardanHangisiSecili();
				if (seciliRobotIndeks == -1) {
					return;
				} else {
					robotPoint[seciliRobotIndeks] = arg0.getPoint();
					Nokta baslamaPoint = GridPanelKontrol.gridLabelIndisBul(arg0.getLocationOnScreen());
					GlobalDegiskenler.getRobotlar().get(seciliRobotIndeks).setBaslamaNoktasi(baslamaPoint);
				}
				break;
			case BITIS:
				int robotIndeks = GlobalDegiskenler.radioButonlardanHangisiSecili();
				if (robotIndeks == -1) {
					UyariPenceresi uyariPenceresi = new UyariPenceresi("Hiç bir robot seçilmemiþ");
					uyariPenceresi.setVisible(true);
					return;
				} else {
					robotHedefPoint[robotIndeks] = arg0.getPoint();
					Nokta hedefPoint = GridPanelKontrol.gridLabelIndisBul(arg0.getLocationOnScreen());
					GlobalDegiskenler.getRobotlar().get(robotIndeks).setBitisNoktasi(hedefPoint);
				}
				break;
			case ENGEL:
				int engelIndeks = GlobalDegiskenler.engellerdenHangisiSecili();
				if (engelIndeks != -1) {
					Engel yeniEngel = new Engel();
					Nokta yeniNokta = new Nokta(arg0.getX(), arg0.getY());
					yeniEngel.setBaslamaNoktasi(yeniNokta);
					yeniEngel.setEkrandakiBaslamaNoktasi(arg0.getLocationOnScreen());
					engelHedefleri[engelIndeks] = yeniEngel;
				}
			case HAREKETLI_ENGEL:
				int hareketliEngelIndeks = GlobalDegiskenler.hareketliEngellerdenHangisiSecili();
				if (hareketliEngelIndeks != -1) {
					Engel yeniEngel = new Engel();
					Nokta yeniNokta = new Nokta(arg0.getX(), arg0.getY());
					yeniEngel.setBaslamaNoktasi(yeniNokta);
					yeniEngel.setEkrandakiBaslamaNoktasi(arg0.getLocationOnScreen());
					hareketliEngelHedefleri[hareketliEngelIndeks] = yeniEngel;
				}
			default:
				break;
			}
			gridPanel.revalidate();
			gridPanel.repaint();

		}

		public void mouseDragged(MouseEvent e) {
			if (GlobalDegiskenler.getSecilenIstipi() == IsTipi.ENGEL) {
				int engelIndeks = GlobalDegiskenler.engellerdenHangisiSecili();
				if (engelIndeks != -1) {
					engelBitisHesapla(engelIndeks, e.getX(), e.getY());
					Nokta bitisNoktasi = new Nokta(e.getX(), e.getY());
					engelHedefleri[engelIndeks].setBitisNoktasi(bitisNoktasi);
					engelHedefleri[engelIndeks].setEkrandakiBitisNoktasi(e.getLocationOnScreen());

					Nokta baslamaPoint = GridPanelKontrol
							.gridLabelIndisBul(engelHedefleri[engelIndeks].getEkrandakiBaslamaNoktasi());
					Nokta bitisPoint = GridPanelKontrol.gridLabelIndisBul(e.getLocationOnScreen());
					GridPanelKontrol.engelIndisleriniBelirle(baslamaPoint, bitisPoint, engelIndeks);
					gridPanel.revalidate();
					gridPanel.repaint();
				}
			} else if (GlobalDegiskenler.getSecilenIstipi() == IsTipi.HAREKETLI_ENGEL) {
				int engelIndeks = GlobalDegiskenler.hareketliEngellerdenHangisiSecili();
				if (engelIndeks != -1) {
					hareketliEngelBitisHesapla(engelIndeks, e.getX(), e.getY());
					Nokta bitisNoktasi = new Nokta(e.getX(), e.getY());
					hareketliEngelHedefleri[engelIndeks].setBitisNoktasi(bitisNoktasi);
					hareketliEngelHedefleri[engelIndeks].setEkrandakiBitisNoktasi(e.getLocationOnScreen());
					gridPanel.revalidate();
					gridPanel.repaint();
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (GlobalDegiskenler.getSecilenIstipi() == IsTipi.ENGEL) {
				int engelIndeks = GlobalDegiskenler.engellerdenHangisiSecili();
				if (engelIndeks != -1) {
					engelBitisHesapla(engelIndeks, e.getX(), e.getY());
					gridPanel.revalidate();
					gridPanel.repaint();
					Nokta bitisNoktasi = new Nokta(e.getX(), e.getY());
					engelHedefleri[engelIndeks].setBitisNoktasi(bitisNoktasi);
					engelHedefleri[engelIndeks].setEkrandakiBitisNoktasi(e.getLocationOnScreen());
					Nokta baslamaPoint = GridPanelKontrol
							.gridLabelIndisBul(engelHedefleri[engelIndeks].getEkrandakiBaslamaNoktasi());
					Nokta bitisPoint = GridPanelKontrol.gridLabelIndisBul(e.getLocationOnScreen());
					GridPanelKontrol.engelIndisleriniBelirle(baslamaPoint, bitisPoint, engelIndeks);
				}
			} else if (GlobalDegiskenler.getSecilenIstipi() == IsTipi.HAREKETLI_ENGEL) {
				int hareketliEngelIndeks = GlobalDegiskenler.hareketliEngellerdenHangisiSecili();
				if (hareketliEngelIndeks != -1) {
					hareketliEngelBitisHesapla(hareketliEngelIndeks, e.getX(), e.getY());
					
					gridPanel.revalidate();
					gridPanel.repaint();
					Nokta bitisNoktasi = new Nokta(e.getX(), e.getY());
					hareketliEngelHedefleri[hareketliEngelIndeks].setBitisNoktasi(bitisNoktasi);
					hareketliEngelHedefleri[hareketliEngelIndeks].setEkrandakiBitisNoktasi(e.getLocationOnScreen());
					Nokta baslamaPoint = GridPanelKontrol.gridLabelIndisBul(
							hareketliEngelHedefleri[hareketliEngelIndeks].getEkrandakiBaslamaNoktasi());
					GlobalDegisken.engellerDegisti(hareketliEngelHedefleri);
					Nokta bitisPoint = GridPanelKontrol.gridLabelIndisBul(e.getLocationOnScreen());
					GridPanelKontrol.hareketliEngelIndisleriniBelirle(baslamaPoint, bitisPoint, hareketliEngelIndeks);
				}

			}
		}
	}

	protected void engelBitisHesapla(int engelIndeks, int x, int y) {
		int baslangicX = engelHedefleri[engelIndeks].getBaslamaNoktasi().getX();
		int baslangicY = engelHedefleri[engelIndeks].getBaslamaNoktasi().getY();
		int weight = Math.abs(baslangicX - x);
		int height = Math.abs(baslangicY - y);
		engelHedefleri[engelIndeks].setWeight(weight);
		engelHedefleri[engelIndeks].setHeight(height);
	}

	protected void hareketliEngelBitisHesapla(int engelIndeks, int x, int y) {
		int baslangicX = hareketliEngelHedefleri[engelIndeks].getBaslamaNoktasi().getX();
		int baslangicY = hareketliEngelHedefleri[engelIndeks].getBaslamaNoktasi().getY();
		int weight = Math.abs(baslangicX - x);
		int height = Math.abs(baslangicY - y);
		hareketliEngelHedefleri[engelIndeks].setWeight(weight);
		hareketliEngelHedefleri[engelIndeks].setHeight(height);
		
	}

	public void gridPanelKarakterCiz(Nokta nokta, int robotIndeks) {
		String dosyaAdi = KureselDegiskenler.getImajAdiGetir(robotIndeks);
		// gridBorder[nokta.getX()][nokta.getY()].setIcon(new
		// ImageIcon(dosyaAdi));
		Point gridinYeri = new Point((int) (gridBorder[nokta.getX()][nokta.getY()].getLocation().getX() - 10),
				(int) (gridBorder[nokta.getX()][nokta.getY()].getLocation().getY() - 10));
		synchronized (gidilenYollar) {
			gidilenYollar.get(robotIndeks).add(gridinYeri);
		}

		robotPoint[robotIndeks] = gridinYeri;
		revalidate();
		repaint();
	}
	
	public void rrtKarakterCiz(Nokta nokta, int robotIndeks) {
	Point hedef = new Point(nokta.getX(), nokta.getY());
		synchronized (gidilenYollar) {
			gidilenYollar.get(robotIndeks).add(hedef);
		}

		robotPoint[robotIndeks] = hedef;
		revalidate();
		repaint();
	}

	public void temizleDugmesineTiklandi() {
		for (int i = 0; i < GRID_BOY; i++) {
			for (int j = 0; j < GRID_EN; j++) {
				gridBorder[i][j].setIcon(null);
			}
		}
		for (int i = 0; i < GlobalDegiskenler.getRobotlar().size(); i++) {
			Nokta nokta = GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi();
			if (robotPoint[i] != null&&nokta!=null) {
				robotPoint[i] = new Point((int) (gridBorder[nokta.getX()][nokta.getY()].getLocation().getX() - 10),
						(int) (gridBorder[nokta.getX()][nokta.getY()].getLocation().getY() - 10));
			}
		}
		GridPanelKontrol.sabitEngelInisleriniCiz();
		PRMPoints.clear();
		RRTPoints.clear();
		revalidate();
		repaint();
		for (int i = 0; i < gidilenYollar.size(); i++) {
			gidilenYollar.get(i).clear();
		}

	}

	public Engel[] getHareketliEngelHedefleri() {
		return hareketliEngelHedefleri;
	}

	public Point[] getRobotPoint() {
		return robotPoint;
	}

	public Point[] getRobotHedefPoint() {
		return robotHedefPoint;
	}

	public Engel[] getEngelHedefleri() {
		return engelHedefleri;
	}

	public void addPRM(int n) {
		List<RectBounds> obstacles = new ArrayList<>();
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			obstacles.add(new RectBounds((float) engelHedefleri[i].getBaslamaNoktasi().getX(),
					(float) engelHedefleri[i].getBaslamaNoktasi().getY(),
					(float) engelHedefleri[i].getBitisNoktasi().getX(),
					(float) engelHedefleri[i].getBitisNoktasi().getY()));
		}

		for (int i = 0; i < n; i++) {
			boolean free = false;
			int x = 0;
			int y = 0;

			while (!free) {
				Random r = new Random();
				x = r.nextInt((int) getWidth());
				y = r.nextInt((int) getHeight());

				free = true;
				for (RectBounds rect : obstacles) {
					if (rect.contains(new Point2D(x, y))) {
						free = false;
						break;
					}
				}
			}
			PRMPoints.add(new Point(x, y));
		}
		revalidate();
		repaint();
	}

	public void addRRT(int n) {
		rrtyeIlkDegerVer();
		List<RectBounds> obstacles = new ArrayList<>();
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			obstacles.add(new RectBounds((float) engelHedefleri[i].getBaslamaNoktasi().getX(),
					(float) engelHedefleri[i].getBaslamaNoktasi().getY(),
					(float) engelHedefleri[i].getBitisNoktasi().getX(),
					(float) engelHedefleri[i].getBitisNoktasi().getY()));
		}
		Random rand = new Random();

		for (int j = 0; j < n; j++) {

			int x = rand.nextInt((int) getWidth());
			int y = rand.nextInt((int) getHeight());

			double closestDistance = 99999;
			Node closestNode = null;

			boolean tooClose = false;

			for (Node node : RRTPoints) {

				double dist = Math
						.sqrt((node.point.x - x) * (node.point.x - x) + (node.point.y - y) * (node.point.y - y));

				if (dist < 10) {
					tooClose = true;
				}

				if (dist < closestDistance) {
					closestDistance = dist;
					closestNode = node;
				}
			}

			if (tooClose || closestNode == null) {
				continue;
			}

			double delX = RRTMultiplier* ((x - closestNode.point.x) / closestDistance);
			double delY = RRTMultiplier * ((y - closestNode.point.y) / closestDistance);

			float newX = (float) delX + closestNode.point.x;
			float newY = (float) delY + closestNode.point.y;

			boolean collision = false;

			Line2D line = new Line2D(closestNode.point.x, closestNode.point.y, newX, newY);
			for (RectBounds r : obstacles) {
				if (line.intersects(r)) {
					collision = true;
					break;
				}
			}

			if (collision) {
				collision = false;

				newX = (float) delX / 2 + closestNode.point.x;
				newY = (float) delY / 2 + closestNode.point.y;
				line = new Line2D(closestNode.point.x, closestNode.point.y, newX, newY);

				for (RectBounds r : obstacles) {
					if (line.intersects(r)) {
						collision = true;
						break;
					}
				}

				if (!collision) {
					RRTPoints.add(new Node(closestNode, new Point2D(newX, newY)));

				}
			} else {

				RRTPoints.add(new Node(closestNode, new Point2D(newX, newY)));

			}
		}
		noktalarArasindaHedefVarMi(RRTPoints,Algoritmalar.RRT);
		revalidate();
		repaint();
	}

	public void addRRTStar(int n) {
		rrtyeIlkDegerVer();
		List<RectBounds> obstacles = new ArrayList<>();
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			obstacles.add(new RectBounds((float) engelHedefleri[i].getBaslamaNoktasi().getX(),
					(float) engelHedefleri[i].getBaslamaNoktasi().getY(),
					(float) engelHedefleri[i].getBitisNoktasi().getX(),
					(float) engelHedefleri[i].getBitisNoktasi().getY()));
		}
		Random rand = new Random();

		for (int j = 0; j < n; j++) {

			int x = rand.nextInt(((int) getWidth()));
			int y = rand.nextInt(((int) getHeight()));

			double closestDistance = 99999;
			boolean tooClose = false;

			Node closestNode = null;

			for (Node node : RRTPoints) {

				double dist = Math
						.sqrt((node.point.x - x) * (node.point.x - x) + (node.point.y - y) * (node.point.y - y));

				if (dist < 10) {
					tooClose = true;
				}

				if (dist < closestDistance) {
					closestDistance = dist;
					closestNode = node;
				}

			}

			if (tooClose || closestNode == null) {
				continue;
			}

			double delX = RRTMultiplier * ((x - closestNode.point.x) / closestDistance);
			double delY = RRTMultiplier * ((y - closestNode.point.y) / closestDistance);

			float newX = (float) delX + closestNode.point.x;
			float newY = (float) delY + closestNode.point.y;

			List<Node> closeNodes = new ArrayList<>();
			int maxDist = OrtakSabitler.OPTIMISI_DISTANCE;

			for (Node node : RRTPoints) {

				double dist = Math.sqrt(
						(node.point.x - newX) * (node.point.x - newX) + (node.point.y - newY) * (node.point.y - newY));

				if (dist < maxDist) {
					node.helper = dist;
					closeNodes.add(node);
				}

			}

			closestNode = null;
			double smallestDist = 9999;

			for (Node node : closeNodes) {

				boolean collision = false;
				Line2D line = new Line2D(node.point.x, node.point.y, newX, newY);
				for (RectBounds r : obstacles) {
					if (line.intersects(r)) {
						collision = true;
						break;
					}
				}
				if (!collision) {
					if (node.distance + node.helper < smallestDist) {
						smallestDist = node.distance + node.helper;
						closestNode = node;
					}
				}
			}

			if (closestNode == null) {
				continue;
			}

			Node toAdd;

			int d = 2;

			int r = rand.nextInt(255);
			int ge = rand.nextInt(255);
			int b = rand.nextInt(255);

			toAdd = new Node(closestNode, new Point2D(newX, newY));
			RRTPoints.add(toAdd);
			boolean changed = false;
			for (Node node : closeNodes) {

				if (node.helper + toAdd.distance < node.distance) {

					boolean canConnect = true;
					Line2D line = new Line2D(node.point.x, node.point.y, newX, newY);

					for (RectBounds rect : obstacles) {
						if (line.intersects(rect)) {
							canConnect = false;
							break;
						}
					}

					if (canConnect) {
						node.parent = toAdd;
						node.distance = node.parent.distance + node.helper;
						changed = true;
					}
				}

			}

		}
		noktalarArasindaHedefVarMi(RRTPoints,Algoritmalar.RRTSTAR);
		revalidate();
		repaint();
	}

	private void noktalarArasindaHedefVarMi(List<Node> rrTPoints,Algoritmalar algo) {
		boolean hedefBulundu = false;
		if (robotHedefPoint[0] != null && GlobalDegiskenler.getRobotRadioButonlar().get(0).isVisible()) {
			for(int i=1;i<rrTPoints.size();i++){
				
//				Line2D line = new Line2D(rrTPoints.get(i).point.x, rrTPoints.get(i).point.y, rrTPoints.get(i).parent.point.x, rrTPoints.get(i).parent.point.y);
//				if(line.intersects(arg0, arg1, arg2, arg3)){
//					System.out.println("KESISME VAR");
//				}
				if(kesismeVarmi(robotHedefPoint[0], rrTPoints.get(i).point)){
					System.out.println("KESISME BULUNDU");
					hedefBulundu = true;
					targetBulundu =true;
					rrTPoints.get(i).hedefeGidenYolMu=true;
					Node a =rrTPoints.get(i);
					ArrayList<Nokta> gidilenYolRRt = new ArrayList<Nokta>();
					while(a.parent!=null){
						gidilenYolRRt.add(new Nokta((int)a.point.x, (int)a.point.y));
						a=a.parent;
						a.hedefeGidenYolMu = true;
					}
					Collections.reverse(gidilenYolRRt);
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							for(Nokta nokta:gidilenYolRRt){
								rrtKarakterCiz(nokta, 0);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}																
						}
					}).start();
					break;
				}
			}
		}
		if(hedefBulundu){
			if(algo==Algoritmalar.RRT){
		AraclarPanelKontrol.takeSnapShot(GlobalDegiskenler.GetGridPanel(), 0);	
		Modeller.rrtRobotModel.get(0).getAlgoritmaModel().add(new AlgoritmaModel(rrTPoints.size()+" Nokta Sayisi "+RRTMultiplier));
			}else{
				AraclarPanelKontrol.takeSnapShot(GlobalDegiskenler.GetGridPanel(), 0);	
				Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel().add(new AlgoritmaModel(rrTPoints.size()+" Nokta Sayisi "+RRTMultiplier));
			}
		}
	}
	private boolean kesismeVarmi(Point robotHedef,Point2D rrtNokta){		
		if(((int)rrtNokta.x>robotHedef.x &&(int)rrtNokta.x-30<robotHedef.x)
				&& ((int)rrtNokta.y>robotHedef.y &&(int)rrtNokta.y-30<robotHedef.y)){
			return true;
		}
		
		return false;
	}

	private void rrtyeIlkDegerVer() {
		if (RRTPoints.size() == 0) {
			if(robotPoint[0]!=null){
				RRTPoints.add(new Node(new Point2D(robotPoint[0].x, robotPoint[0].y)));	
			}else{
			RRTPoints.add(new Node(new Point2D(250, 250)));
			}
		}
	}

	public void setRRtMultiplier(int value) {
		RRTMultiplier = value;
		
	}
	 public void connect(){
	        for(int j = 0; j < PRMPoints.size(); j++){
	            Point point = PRMPoints.get(j);
	            List<Double> closestDist = new ArrayList<>();
	            for(int i = 0; i < numOfConnections; i++) {
	                closest.add(null);
	                closestDist.add(99999.0);
	            }
	            for(Point p: PRMPoints){
	                if(p != point){
	                    double dist = Math.sqrt((p.x-point.x)*(p.x-point.x) + (p.y-point.y)*(p.y-point.y));
	                    for(int i = 0; i < numOfConnections; i++){
	                        if(closestDist.get(i) > dist){
	                            closestDist.add(i, dist);
	                            closest.add(i, p);
	                            break;
	                        }
	                    }
	                }
	            }
	            List<Point> edges = new ArrayList<>();
	            for(int i = 0; i < numOfConnections; i++) {

	                Point close = closest.get(i);
	                if(close != null){
	                    Graphics g = getGraphics();
	                    g.setColor(Color.BLACK);
	                    g.drawLine(close.x, close.y, point.x, point.y);
	                    edges.add(close);
	                }
	            }

	        }

	    }

	public List<Point> getClosest() {
		return closest;
	}

	public void scripttenPathCiz(List<Mapping.Node> gidilecekYol) {
		gidilenYollar.get(0).clear();
		robotPoint[0]=  new Point(gidilecekYol.get(0).getRow(), gidilecekYol.get(0).getCol());
		robotHedefPoint[0] =new Point(gidilecekYol.get(gidilecekYol.size()-1).getRow(), gidilecekYol.get(gidilecekYol.size()-1).getCol());
		GlobalDegiskenler.getRobotRadioButonlar().get(0).setVisible(true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(Mapping.Node node : gidilecekYol){
					Point yeni = new Point(node.getRow(), node.getCol());
					synchronized (gidilenYollar) {
						
					gidilenYollar.get(0).add(yeni);
					
					}
					gridPanel.revalidate();
					gridPanel.repaint();
					robotPoint[0] = yeni;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				OrtakMetotlar.setScriptUygulanýyorMu(false);
//				gridPanel.revalidate();
//				gridPanel.repaint();
			}
		}).start();		
	}
	public void scripttenEngelCiz(Engel engel,int engelIndeks){
		scriptEngeller.add(engel);
//		engelHedefleri[engelIndeks]=engel;
//		engelBitisHesapla(engelIndeks, engel.getBaslamaNoktasi().getX()+engel.getWeight(), engel.getBaslamaNoktasi().getY()+engel.getHeight());
//		gridPanel.revalidate();
//		gridPanel.repaint();
//		Nokta bitisNoktasi = new Nokta(engel.getBaslamaNoktasi().getX()+engel.getWeight(), engel.getBaslamaNoktasi().getY()+engel.getHeight());
//		engelHedefleri[engelIndeks].setBitisNoktasi(bitisNoktasi);
//		Nokta baslamaPoint = GridPanelKontrol
//				.gridLabelIndisBul(new Point(engel.getBaslamaNoktasi().getX(),engel.getBaslamaNoktasi().getY()));
//		Nokta bitisPoint = GridPanelKontrol.gridLabelIndisBul(new Point(engel.getBaslamaNoktasi().getX()+engel.getWeight(), engel.getBaslamaNoktasi().getY()+engel.getHeight()));
//		GridPanelKontrol.engelIndisleriniBelirle(baslamaPoint, bitisPoint, engelIndeks);
	}

	public void scriptEngelleriniTemizle() {
		scriptEngeller.clear();
		
	}
}
