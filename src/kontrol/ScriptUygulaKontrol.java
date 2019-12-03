package kontrol;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.SwingUtilities;

import Mapping.Nokta;
import Mapping.Robot;
import Ortak.GlobalDegiskenler;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import Mapping.Engel;
import Mapping.GridEngel;
import Mapping.Node;

public final class ScriptUygulaKontrol {
	private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
	private static int obstacle[][];

	public static void uygulaDugmesineBasildi(String text) {
		algoritmaScriptiniCalistir(text);

	}

	private static void algoritmaScriptiniCalistir(String text) {
		Object scriptObj = null;
		GlobalDegiskenler.GetGridPanel().scriptEngelleriniTemizle();
		OrtakMetotlar.setScriptUygulanýyorMu(true);
		try {
			int sayi = 0;
			for(Engel engel :GlobalDegiskenler.GetGridPanel().getEngelHedefleri()){
				if(engel!=null &&engel.getBaslamaNoktasi()!=null&& engel.getBitisNoktasi()!=null){
				Nokta baslamaNoktasi = engel.getBaslamaNoktasi();
				Nokta bitisNoktasi = engel.getBitisNoktasi();
				int baslamaNoktasiX,bitisNoktasiX,baslamaNoktasiY,bitisNoktasiY;
				if(baslamaNoktasi.getX()<=bitisNoktasi.getX()) {
					baslamaNoktasiX = baslamaNoktasi.getX()-8;
					bitisNoktasiX = bitisNoktasi.getX()+8;
				}else {
					baslamaNoktasiX=bitisNoktasi.getX()-8;
					bitisNoktasiX=baslamaNoktasi.getX()+8;
				}
				if(baslamaNoktasi.getY()<=bitisNoktasi.getY()) {
					baslamaNoktasiY = baslamaNoktasi.getY()-8;
					bitisNoktasiY = bitisNoktasi.getY()+8;
				}else {
					baslamaNoktasiY = bitisNoktasi.getY()-8;
					bitisNoktasiY = baslamaNoktasi.getY()+8;
				}
				sayi=sayi+ 2*(bitisNoktasiY-baslamaNoktasiY)+2*(bitisNoktasiX-baslamaNoktasiX);
				}
			}
			obstacle = new int[sayi][2];
			sayi = 0;
			for(Engel engel :GlobalDegiskenler.GetGridPanel().getEngelHedefleri()){
				if(engel!=null &&engel.getBaslamaNoktasi()!=null&& engel.getBitisNoktasi()!=null){
					int baslamaNoktasiX = engel.getBaslamaNoktasi().getX()-8;
					int baslamaNoktasiY = engel.getBaslamaNoktasi().getY()-8;
					int bitisNoktasiX = engel.getBitisNoktasi().getX()+8;
					int bitisNoktasiY = engel.getBitisNoktasi().getY()+8;
					for(int i =baslamaNoktasiX;i<bitisNoktasiX;i++) {
						int a[] = {i,baslamaNoktasiY};
						obstacle[sayi] =a;
						sayi ++;
						int a1[] = {i,bitisNoktasiY};
						obstacle[sayi] =a1;
						sayi ++;
						
					}
					for(int i =baslamaNoktasiY;i<bitisNoktasiY;i++) {
						int a[] = {baslamaNoktasiX,i};
						obstacle[sayi] =a;
						sayi ++;
						int a1[] = {bitisNoktasiX,i};
						obstacle[sayi] =a1;
						sayi ++;
						
					}
					
				
			}
			}

			engine.eval(text);
			scriptObj = ((Invocable) engine).invokeFunction("findShortestPath", obstacle);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		ArrayList<Object> javaObj = (ArrayList<Object>) OrtakMetotlar.convertIntoJavaObject(scriptObj);
		List<Node> gidilecekYol = new ArrayList<Node>();
		List<Engel> engeller = new ArrayList<Engel>();
		for (Object o : javaObj) {
			String xYkoordiant = (String) o;
			String[] xyDiziler = xYkoordiant.split(" ");
			System.out.println(xyDiziler[0] + " " + xyDiziler[1]);
			if(xyDiziler[0].equalsIgnoreCase("Engel")){
				Nokta bitisNokta = new Nokta(Integer.parseInt(xyDiziler[1])+Integer.parseInt(xyDiziler[3]), Integer.parseInt(xyDiziler[2])+Integer.parseInt(xyDiziler[4]));
				engeller.add(new Engel(new Nokta(Integer.parseInt(xyDiziler[1]), Integer.parseInt(xyDiziler[2])), Integer.parseInt(xyDiziler[3]),Integer.parseInt(xyDiziler[4]),bitisNokta));
				
			}else{
				Node node = new Node(Integer.parseInt(xyDiziler[0]), Integer.parseInt(xyDiziler[1]));
				gidilecekYol.add(node);
			}
			
		}
		if(engeller.size()>0){
			for(int i=0;i<engeller.size();i++){
			GlobalDegiskenler.GetGridPanel().scripttenEngelCiz(engeller.get(i), i);
			}
		}
		GlobalDegiskenler.GetGridPanel().scripttenPathCiz(gidilecekYol);

		// for (int i = 0; i < OrtakSabitler.MAKSIMUM_ROBOT_SAYISI; i++) {
		// if (GlobalDegiskenler.getRobotRadioButonlar().get(i).isVisible()
		// && GlobalDegiskenler.getRobotlar().get(i).getBaslamaNoktasi() != null
		// && GlobalDegiskenler.getRobotlar().get(i).getBitisNoktasi() != null)
		// {
		//
		//
		// Robot robot = GlobalDegiskenler.getRobotlar().get(i);
		// Nokta baslamaNoktasi = robot.getBaslamaNoktasi();
		// Nokta bitisNoktasi = robot.getBitisNoktasi();
		// Object scriptObj = null;
		// try {
		// engine.eval(text);
		// scriptObj = ((Invocable) engine).invokeFunction("algoritma",
		// baslamaNoktasi.getX(),
		// baslamaNoktasi.getY(), bitisNoktasi.getX(), bitisNoktasi.getY());
		// } catch (NoSuchMethodException e) {
		// e.printStackTrace();
		// } catch (ScriptException e) {
		// e.printStackTrace();
		// }
		// Point nokta;
		// int x = 0, y = 0;
		// ArrayList<Object> javaObj = (ArrayList<Object>)
		// OrtakMetotlar.convertIntoJavaObject(scriptObj);
		// List<Node> gidilecekYol = new ArrayList<Node>();
		// for (Object o : javaObj) {
		// String xYkoordiant = (String) o;
		// String[] xyDiziler = xYkoordiant.split(":");
		// Node node = new Node(Integer.parseInt(xyDiziler[0]),
		// Integer.parseInt(xyDiziler[1]));
		// gidilecekYol.add(node);
		// System.out.println("Row" + node.getRow() + " Colomn " +
		// node.getCol());
		// }
		// OrtakMetotlar.pathiCizdir(gidilecekYol, i);
		// System.out.println(javaObj);
		//
		// }
		// }

	}

}
