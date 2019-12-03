package Ortak;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import Enum.Algoritmalar;
import Mapping.Node;
import Mapping.Nokta;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import model.Modeller;
import model.RobotModel;

public class OrtakMetotlar {
	private static int anaEkranXBoy;
	private static int anaEkranYBoy;
	private static int  gridXsayisi;
	private static int gridYsayisi;
	private static JDialog					messageDialog;
	public static final int					COMP_HGAP							  = 8;
	public static final int					COMP_VGAP							  = 6;
	private static IslemDevamEdiyorPanel	islemDevamEdiyorPanel;
	private static Color					BORDER_COLOR						  = new Color(
	        135, 135, 135);
	private static boolean scriptUygulanýyorMu = false;
	private static boolean potential2UygulaniyorMu= false;
	
	public static void setBilesenBoyutu(Component component,int width,int height){
		Dimension dimension = new Dimension(width,height);
		component.setMinimumSize(dimension);
		component.setPreferredSize(dimension);
		component.setMaximumSize(dimension);
	}

	public static void bilesenKonumunuMerkezle(Component comp) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		comp.setLocation(dim.width / 2 - comp.getSize().width / 2,
		        dim.height / 2 - comp.getSize().height / 2);
	}
	
	public static int getAnaEkranXBoy() {
		return anaEkranXBoy+50;
	}
	public static void setAnaEkranXBoy(int anaEkranXBoy) {
		OrtakMetotlar.anaEkranXBoy = anaEkranXBoy;
	}
	public static int getAnaEkranYBoy() {
		return anaEkranYBoy;
	}
	public static void setAnaEkranYBoy(int anaEkranYBoy) {
		OrtakMetotlar.anaEkranYBoy = anaEkranYBoy;
	}
	public static int getGridXsayisi() {
		return gridXsayisi;
	}
	public static void setGridXsayisi(int gridXsayisi) {
		OrtakMetotlar.gridXsayisi = gridXsayisi;
	}
	public static int getGridYsayisi() {
		return gridYsayisi;
	}
	public static void setGridYsayisi(int gridYsayisi) {
		OrtakMetotlar.gridYsayisi = gridYsayisi;
	}
	public static Border getBorder() {
		Color renk = new Color(135, 135, 135);
		return (BorderFactory.createLineBorder(renk));
	}
	public static void islemDevamEdiyorMesajiGoster(String sMessage,
	        JDialog parent) {
		if (messageDialog == null) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					messageDialog = new JDialog(parent);
					Dimension dms = new Dimension(375, 60);
					messageDialog.setPreferredSize(dms);
					messageDialog.setMinimumSize(dms);
					messageDialog.setMaximumSize(dms);
					messageDialog.setSize(dms);

					messageDialog.setUndecorated(true);
					messageDialog.setResizable(false);
					messageDialog.setAlwaysOnTop(true);
					messageDialog.setModal(true);

					JPanel messagePanel = new JPanel();
					JLabel messageText = new JLabel(sMessage);
					messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT,
					        COMP_HGAP, COMP_VGAP));
					islemDevamEdiyorPanel = new IslemDevamEdiyorPanel();
					messagePanel.add(islemDevamEdiyorPanel);
					messagePanel.add(messageText);
					messagePanel.setBorder(
					        BorderFactory.createLineBorder(BORDER_COLOR));
					messageDialog.add(messagePanel);
					if (parent != null) {
						messageDialog.setLocationRelativeTo(parent);
					} else {
						messageDialog.setLocation(
						        (Toolkit.getDefaultToolkit()
						                .getScreenSize().width) / 2 - 187,
						        (Toolkit.getDefaultToolkit()
						                .getScreenSize().height) / 2 - 30);
					}
					messageDialog.setVisible(true);
				}
			});
		}
	}
	public static void islemDevamEdiyorMesajiKapat() {
		if (messageDialog != null) {
			if (islemDevamEdiyorPanel != null) {
				islemDevamEdiyorPanel.sonlandir();
			}
			messageDialog.dispose();
			messageDialog = null;
		}

	}
	
	public static Object convertIntoJavaObject(Object scriptObj) {
	    if (scriptObj instanceof ScriptObjectMirror) {
	        ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) scriptObj;
	        if (scriptObjectMirror.isArray()) {
	            List<Object> list = new ArrayList<Object>();
	            for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
	                list.add(convertIntoJavaObject(entry.getValue()));
	            }
	            return list;
	        } else {
	            Map<String, Object> map =new HashMap<>();
	            for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet()) {
	                map.put(entry.getKey(), convertIntoJavaObject(entry.getValue()));
	            }
	            return map;
	        }
	    } else {
	        return scriptObj;
	    }
	}
	
	public static String dosyaOku(String dosyaAdi){
		StringBuilder builder = new StringBuilder();
		List<String> allLines = null;
		Path path = Paths.get(dosyaAdi);
		try {
			byte[] bytes = Files.readAllBytes(path);
			 allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String line : allLines){
			builder.append(line);
			builder.append("\n");
		}

		return builder.toString();
	}
	public static void pathiCizdir(List<Node> list,int indeks) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {

					if(list.size()>0){
						for(int i=0;i<list.size();i++){
							Nokta nokta = new Nokta(list.get(i).getRow(), list.get(i).getCol());
							GlobalDegiskenler.GetGridPanel().gridPanelKarakterCiz(nokta,indeks);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
			}
		}).start();
		
	}
	public static void pathiCizdir(List<Node> list,int indeks,int algoritmaIndeks,Algoritmalar algo) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long hesaplananBaslangicZamani=System.currentTimeMillis();
					if(list.size()>0){
						for(int i=0;i<list.size();i++){
							Nokta nokta = new Nokta(list.get(i).getRow(), list.get(i).getCol());
							GlobalDegiskenler.GetGridPanel().gridPanelKarakterCiz(nokta,indeks);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					long hesaplananBitisZamani= System.currentTimeMillis();
					modelGuncelle(algo,indeks,algoritmaIndeks,(hesaplananBitisZamani-hesaplananBaslangicZamani));
			}
		}).start();
		
	}
	private static void modelGuncelle(Algoritmalar algo,int robotIndeks,int algoritmaIndeks,long harcananZaman){
		switch (algo) {
		case ASTAR:
			modelGunceller(Modeller.aStarRobotModel, robotIndeks, algoritmaIndeks, harcananZaman);
			break;
		case LEE:
			modelGunceller(Modeller.leeRobotModel, robotIndeks, algoritmaIndeks, harcananZaman);
			break;
		case POTENTIAL_FIELD:
			modelGunceller(Modeller.potentialFieldRobotModel, robotIndeks, algoritmaIndeks, harcananZaman);
			break;
		case RRT:
			modelGunceller(Modeller.rrtRobotModel, robotIndeks, algoritmaIndeks, harcananZaman);
			break;
		case RRTSTAR:
			modelGunceller(Modeller.rrtStarRobotModel, robotIndeks, algoritmaIndeks, harcananZaman);
			break;
		case DYNAMIC_WINDOWS_APPROACH:
			modelGunceller(Modeller.dynamicWindowsApproachRobotModel, robotIndeks, algoritmaIndeks, harcananZaman);
			break;

		default:
			break;
		}
	}

	private static void modelGunceller(ArrayList<RobotModel> RobotModel, int robotIndeks, int algoritmaIndeks,
			Long harcananZaman) {
		RobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks).setHarcananZaman(harcananZaman);
		Integer yolMetre =RobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks).getKatEdilenYol();
		RobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks).setAcisalHiz(yolMetre.doubleValue()/harcananZaman.doubleValue());
		RobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks).setBasariOrani(true);
		
	}

	public static boolean isScriptUygulanýyorMu() {
		return scriptUygulanýyorMu;
	}

	public static void setScriptUygulanýyorMu(boolean scriptUygulanýyorMu) {
		OrtakMetotlar.scriptUygulanýyorMu = scriptUygulanýyorMu;
	}

	public static boolean isPotential2UygulaniyorMu() {
		return potential2UygulaniyorMu;
	}

	public static void setPotential2UygulaniyorMu(boolean potential2UygulaniyorMu) {
		OrtakMetotlar.potential2UygulaniyorMu = potential2UygulaniyorMu;
	}


}
