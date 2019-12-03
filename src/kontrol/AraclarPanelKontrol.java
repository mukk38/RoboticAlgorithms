package kontrol;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Enum.Algoritmalar;
import Mapping.Nokta;
import Mapping.Robot;
import Ortak.GlobalDegiskenler;
import Ortak.KureselDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import denemeler.AStar2;
import Mapping.Node;
import denemeler.Dfs;
import denemeler.Dugum;
import denemeler.Labirent;
import denemeler.LeeAlgorithm;
import dwa2.RobotState;
import dwa2.Sabitler;
import model.AlgoritmaModel;
import model.Modeller;
import model.RobotModel;
import potentialField2.PotentialKontrol;

public class AraclarPanelKontrol {
	private static AraclarPanelKontrol araclarPanelKontrol = null;
	public static int GRID_EN = OrtakMetotlar.getGridXsayisi();
	public static int GRID_BOY = OrtakMetotlar.getGridYsayisi();
	private static ArrayList<List<Node>> pathler = new ArrayList<List<Node>>();

	private AraclarPanelKontrol() {

	}

	public static AraclarPanelKontrol getInstance() {
		if (araclarPanelKontrol == null) {
			araclarPanelKontrol = new AraclarPanelKontrol();

		}
		return araclarPanelKontrol;
	}

	public void calistirDugmesineTiklandi() {
		// for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
		//
		// if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
		// && GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null
		// && GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null)
		// {
		// takeSnapShot(GlobalDegiskenler.GetGridPanel(),i);
		// }
		// }
		switch (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks().getSelectedIndex()) {
		case OrtakSabitler.ASTAR_INDEKS:
			new Thread(new Runnable() {

				@Override
				public void run() {
					OrtakMetotlar.islemDevamEdiyorMesajiGoster(
							GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
									.getSelectedItem().toString() + " Hesaplamalar yapiliyor",
							GlobalDegiskenler.getAnaPencere());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int toplamEngelSayisi = 0;
					for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
						for (int j = 0; j < GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size(); j++) {
							toplamEngelSayisi++;
						}
					}

					int[][] blocksArray = new int[toplamEngelSayisi][2];
					System.out.println("Toplam Engel Sayýsý " + toplamEngelSayisi);
					int counter = 0;
					for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
						for (int j = 0; j < GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size(); j++) {
							int deneme[] = new int[2];
							deneme[0] = GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(j).getX();
							deneme[1] = GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(j).getY();
							System.out.println("Engel X " + deneme[0] + " Y " + deneme[1]);
							blocksArray[counter] = deneme;
							counter++;
						}
					}
					System.out.println("Counter" + counter);
					for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
						long hesaplananBaslangicZamani = System.currentTimeMillis();
						if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
								&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null
								&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
							Robot robot = GlobalDegiskenler.getRobotlar().get(i);
							Nokta baslamaNoktasi = robot.getBaslamaNoktasi();
							Nokta bitisNoktasi = robot.getBitisNoktasi();
							System.out.println("Robot Indkes " + i + " X : " + baslamaNoktasi.getX() + " Y : "
									+ baslamaNoktasi.getY());
							Node initialNode = new Node(baslamaNoktasi.getX(), baslamaNoktasi.getY());
							Node finalNode = new Node(bitisNoktasi.getX(), bitisNoktasi.getY());
							AStar2 aStar = new AStar2(OrtakMetotlar.getGridYsayisi(), OrtakMetotlar.getGridXsayisi(),
									initialNode, finalNode);
							aStar.setBlocks(blocksArray);
							List<Node> path = aStar.findPath();
							pathler.add(path);
							long hesaplananBitisZamani = System.currentTimeMillis();
							takeSnapShot(GlobalDegiskenler.GetGridPanel(), i);
							Modeller.aStarRobotModel.get(i).getAlgoritmaModel().add(
									new AlgoritmaModel(hesaplananBitisZamani - hesaplananBaslangicZamani, path.size()));
						}
					}

					for (int i = 0; i < pathler.size(); i++) {
						int algoritmaIndeks = algoritmaIndeksGetir(i, Algoritmalar.ASTAR) - 1;
						OrtakMetotlar.pathiCizdir(pathler.get(i), i, algoritmaIndeks, Algoritmalar.ASTAR);
					}
					OrtakMetotlar.islemDevamEdiyorMesajiKapat();

				}
			}).start();
			pathler.clear();
			break;
		case OrtakSabitler.LEE_INDEKS:
			new Thread(new Runnable() {
				@Override
				public void run() {
					OrtakMetotlar.islemDevamEdiyorMesajiGoster(
							GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks()
									.getSelectedItem().toString() + " Hesaplamalar yapiliyor",
							GlobalDegiskenler.getAnaPencere());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
						long hesaplananBaslangicZamani = System.currentTimeMillis();
						if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
								&& GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null
								&& GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null) {
							LeeAlgorithm l = new LeeAlgorithm(GlobalDegiskenler.getEngelNoktalari());
							ArrayList<Dugum> output = l.findPath(
									new Dugum(GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi().getX(),
											GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi().getY()),
									new Dugum(GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi().getX(),
											GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi().getY()));
							List<Node> path = null;
							if (output != null) {
								path = dugumuPatheCevir(output);
							}
							pathler.add(path);
							long hesaplananBitisZamani = System.currentTimeMillis();
							takeSnapShot(GlobalDegiskenler.GetGridPanel(), i);
							Modeller.leeRobotModel.get(i).getAlgoritmaModel().add(
									new AlgoritmaModel(hesaplananBitisZamani - hesaplananBaslangicZamani, path.size()));
						}
					}
					for (int i = 0; i < pathler.size(); i++) {
						if (pathler.get(i) != null) {

							int algoritmaIndeks = algoritmaIndeksGetir(i, Algoritmalar.LEE) - 1;
							OrtakMetotlar.pathiCizdir(pathler.get(i), i, algoritmaIndeks, Algoritmalar.LEE);

						}
					}
					OrtakMetotlar.islemDevamEdiyorMesajiKapat();
				}
			}).start();
			pathler.clear();
			break;
		case OrtakSabitler.POTENTIAL_FIELD_ALGORTHM_INDEKS:
			GridPanelKontrol.potentialFieldAyarlamalariYap();
			KureselDegiskenler.setPotentialFieldBaslatidi(true);
			PotentialFieldKontrol.algoritmaHesaplaVeriyiCiz(GlobalDegiskenler.GetGridPanel().getRobotPoint(),
					GlobalDegiskenler.GetGridPanel().getRobotHedefPoint(),
					GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri(),
					GlobalDegiskenler.GetGridPanel().getEngelHedefleri());
			break;
		case OrtakSabitler.RAPIDLY_EXPANDING_RANDOM_TREE:
			new Thread(new Runnable() {

				@Override
				public void run() {
					GlobalDegiskenler.GetGridPanel().targetBulundu = false;
					Long baslamaZamani = System.currentTimeMillis();
					while (!GlobalDegiskenler.GetGridPanel().targetBulundu) {
						GlobalDegiskenler.GetGridPanel().addRRT(50);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Long bitisZamani = System.currentTimeMillis();
					Modeller.rrtRobotModel.get(0).getAlgoritmaModel()
							.get(Modeller.rrtRobotModel.get(0).getAlgoritmaModel().size() - 1)
							.setHarcananZaman(bitisZamani - baslamaZamani);
					double acisalHiz = Double.parseDouble(Modeller.rrtRobotModel.get(0).getAlgoritmaModel()
							.get(Modeller.rrtRobotModel.get(0).getAlgoritmaModel().size() - 1).getRrtDeger()
							.split(" ")[0]) / (bitisZamani - baslamaZamani);
					Modeller.rrtRobotModel.get(0).getAlgoritmaModel()
							.get(Modeller.rrtRobotModel.get(0).getAlgoritmaModel().size() - 1).setAcisalHiz(acisalHiz);

				}
			}).start();
			break;
		case OrtakSabitler.RAPIDLY_EXPANDING_RANDOM_TREE_STAR:
			new Thread(new Runnable() {

				@Override
				public void run() {
					GlobalDegiskenler.GetGridPanel().targetBulundu = false;
					Long baslamaZamani = System.currentTimeMillis();
					while (!GlobalDegiskenler.GetGridPanel().targetBulundu) {
						GlobalDegiskenler.GetGridPanel().addRRTStar(50);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Long bitisZamani = System.currentTimeMillis();
					Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel()
							.get(Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel().size() - 1)
							.setHarcananZaman(bitisZamani - baslamaZamani);
					double acisalHiz = Double.parseDouble(Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel()
							.get(Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel().size() - 1).getRrtDeger()
							.split(" ")[0]) / (bitisZamani - baslamaZamani);
					Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel()
							.get(Modeller.rrtStarRobotModel.get(0).getAlgoritmaModel().size() - 1).setAcisalHiz(acisalHiz);

				}
			}).start();
			break;
		case OrtakSabitler.DYNAMIC_WINDIW_APPROACH:
			GridPanelKontrol.dynamicWindowsAyarlamalariYap();
			System.out.println("Dynamic Window Approach Baslatildi");
			KureselDegiskenler.setDynamicWindowsApproachBaslatidi(true);
			DynamicWindowApproach.algoritmaHesaplaVeriyiCiz(GlobalDegiskenler.GetGridPanel().getRobotPoint(),
					GlobalDegiskenler.GetGridPanel().getRobotHedefPoint(),
					GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri(),
					GlobalDegiskenler.GetGridPanel().getEngelHedefleri());
			break;
		case 7:
			PotentialKontrol.algoritmaHesaplaVeriyiCiz(GlobalDegiskenler.GetGridPanel().getRobotPoint(),
					GlobalDegiskenler.GetGridPanel().getRobotHedefPoint(),
					GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri(),
					GlobalDegiskenler.GetGridPanel().getEngelHedefleri());
		default:
			break;
		}
	}

	protected List<Node> dugumuPatheCevir(ArrayList<Dugum> output) {
		List<Node> path = new ArrayList<Node>();
		// for(int i=0;i<output.size();i++){
		// Node nod = new Node(output.get(i).getX(), output.get(i).getY());
		// path.add(nod);
		// }
		for (int i = output.size(); i > 0; i--) {
			Node nod = new Node(output.get(i - 1).getX(), output.get(i - 1).getY());
			path.add(nod);
		}
		return path;
	}

	// protected void pathiCizdir(List<Node> list,int indeks) {
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	//
	// if(list.size()>0){
	// for(int i=0;i<list.size();i++){
	// Nokta nokta = new Nokta(list.get(i).getRow(), list.get(i).getCol());
	// GlobalDegiskenler.GetGridPanel().gridPanelKarakterCiz(nokta,indeks);
	// try {
	// Thread.sleep(50);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	//
	// }
	// }).start();
	//
	// }
	protected int[][] yeniMazeGetir() {
		int[][] maze = new int[GRID_BOY][GRID_EN];
		for (int i = 0; i < GlobalDegiskenler.getEngelNoktalari().size(); i++) {
			for (int j = 0; j < GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size(); j++) {
				maze[GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(j).getX()][GlobalDegiskenler
						.getEngelNoktalari().get(i).getNoktalar().get(j).getY()] = 100;
			}
		}

		return maze;
	}

	public void temizleDugmesisTiklandi() {
		GlobalDegiskenler.GetGridPanel().temizleDugmesineTiklandi();
		KureselDegiskenler.setPrmBaglaTiklandi(false);
		PotentialKontrol.threadler.clear();
	}

	public static void takeSnapShot(JPanel panel, int robotIndeks) {
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {

		Algoritmalar algo = algoritmaEnumGetir();
		int algoritmaIndeks = algoritmaIndeksGetir(robotIndeks, algo);
		String dosyaAd = dosyaAdGetir(robotIndeks, algoritmaIndeks, algo);
		// try {
		// Thread.sleep(5000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		BufferedImage bufImage = new BufferedImage(panel.getSize().width, panel.getSize().height,
				BufferedImage.TYPE_INT_RGB);
		panel.paint(bufImage.createGraphics());
		File imageFile = new File(dosyaAd);
		try {
			imageFile.createNewFile();
			ImageIO.write(bufImage, "jpeg", imageFile);
		} catch (Exception ex) {
		}

		// }
		// }).start();

	}

	private static int algoritmaIndeksGetir(int robotIndeks, Algoritmalar algo) {
		switch (algo) {
		case ASTAR:
			return algortimaIndeksBul(Modeller.aStarRobotModel, robotIndeks);
		case LEE:
			return algortimaIndeksBul(Modeller.leeRobotModel, robotIndeks);
		case POTENTIAL_FIELD:
			return algortimaIndeksBul(Modeller.potentialFieldRobotModel, robotIndeks);
		case RRT:
			return algortimaIndeksBul(Modeller.rrtRobotModel, robotIndeks);
		case RRTSTAR:
			return algortimaIndeksBul(Modeller.rrtStarRobotModel, robotIndeks);
		case DYNAMIC_WINDOWS_APPROACH:
			return algortimaIndeksBul(Modeller.dynamicWindowsApproachRobotModel, robotIndeks);

		}
		return 1;
	}

	private static int algortimaIndeksBul(ArrayList<RobotModel> RobotModel, int robotIndeks) {
		if (RobotModel.size() == 0) {
			return 1;
		} else {
			for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
				if (robotIndeks == RobotModel.get(i).getRobotIndeks()) {
					if (RobotModel.get(i).getAlgoritmaModel() != null) {
						return RobotModel.get(i).getAlgoritmaModel().size();
					}
				}
			}
			return 1;
		}
	}

	private static Algoritmalar algoritmaEnumGetir() {
		switch (GlobalDegiskenler.getAnaPencere().getAraclarPanel().getAlgoritmaSecComboBoks().getSelectedIndex()) {
		case OrtakSabitler.ASTAR_INDEKS:
			return Algoritmalar.ASTAR;
		case OrtakSabitler.LEE_INDEKS:
			return Algoritmalar.LEE;
		case OrtakSabitler.POTENTIAL_FIELD_ALGORTHM_INDEKS:
			return Algoritmalar.POTENTIAL_FIELD;
		case OrtakSabitler.RAPIDLY_EXPANDING_RANDOM_TREE:
			return Algoritmalar.RRT;
		case OrtakSabitler.RAPIDLY_EXPANDING_RANDOM_TREE_STAR:
			return Algoritmalar.RRTSTAR;
		case OrtakSabitler.DYNAMIC_WINDIW_APPROACH:
			return Algoritmalar.DYNAMIC_WINDOWS_APPROACH;

		}
		return Algoritmalar.ASTAR;
	}

	private static String dosyaAdGetir(int robotIndeks, int algoritmaIndeks, Algoritmalar algo) {
		switch (robotIndeks) {
		case 0:
			return OrtakSabitler.ROBOT_1_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 1:
			return OrtakSabitler.ROBOT_2_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 2:
			return OrtakSabitler.ROBOT_3_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 3:
			return OrtakSabitler.ROBOT_4_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 4:
			return OrtakSabitler.ROBOT_5_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 5:
			return OrtakSabitler.ROBOT_6_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 6:
			return OrtakSabitler.ROBOT_7_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 7:
			return OrtakSabitler.ROBOT_8_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 8:
			return OrtakSabitler.ROBOT_9_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";
		case 9:
			return OrtakSabitler.ROBOT_10_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-"
					+ algoritmaIndeks + ".jpeg";

		}
		return OrtakSabitler.ROBOT_1_SCREEN_DOSYA_YOLU + algo.name() + "-Robot" + robotIndeks + "-" + algoritmaIndeks
				+ ".jpeg";
	}

	public void robotBilgiDialoglariniGuncelle() {

	}

}
