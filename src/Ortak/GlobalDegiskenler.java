package Ortak;

import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;

import Mapping.GridEngel;
import Mapping.Nokta;
import Mapping.Robot;
import gorunum.AnaPencere;
import gorunum.GridPanel;
import paneller.HareketliEngelPanel;

public class GlobalDegiskenler {
	private static GridPanel gridPanel;
	private static AnaPencere	anaPencere;
	private static ArrayList<JRadioButton>  robotRadioButonlar = new ArrayList<JRadioButton>();
	private static ArrayList<JRadioButton>  engelRadioButonlar = new ArrayList<JRadioButton>();
	private static ArrayList<HareketliEngelPanel> hareketliEngelPanelleri =new ArrayList<HareketliEngelPanel>();

	private static IsTipi secilenIstipi =IsTipi.BASLANGIC;
	private static ArrayList<Robot> robotlar;
	private static ArrayList<GridEngel> engelNoktalari = new ArrayList<GridEngel>();
	private static ArrayList<GridEngel> hareketliEngelNoktalari = new ArrayList<GridEngel>();
	

	public static ArrayList<GridEngel> getEngelNoktalari() {
		return engelNoktalari;
	}

	public static IsTipi getSecilenIstipi() {
		return secilenIstipi;
	}

	public static void setSecilenIstipi(IsTipi secilenIstipi) {
		GlobalDegiskenler.secilenIstipi = secilenIstipi;
	}

	public static ArrayList<JRadioButton> getRobotRadioButonlar() {
		return robotRadioButonlar;
	}

	public static void setRobotRadioButonlar(ArrayList<JRadioButton> robotRadioButonlar) {
		GlobalDegiskenler.robotRadioButonlar = robotRadioButonlar;
	}

	public static GridPanel GetGridPanel(){
		if (gridPanel == null) {
			gridPanel = new GridPanel();
			
		}

		return gridPanel;
	}
	
	public static AnaPencere getAnaPencere(){
		if (anaPencere == null) {
			anaPencere = new AnaPencere();
			
		}

		return anaPencere;
	}

	public static int radioButonlardanHangisiSecili(){
		for(int i=0;i<robotRadioButonlar.size();i++){
			if(robotRadioButonlar.get(i).isSelected()){
				return i;
			}
		}
		return -1;
	}
	public static ArrayList<Robot> getRobotlar(){
		if (robotlar == null) {
			robotlar = new ArrayList<Robot>();
			for(int i=0;i<OrtakSabitler.MAKSIMUM_ROBOT_SAYISI;i++){
				Robot robot = new Robot(i);
				robotlar.add(robot);
			}
		}

		return robotlar;
	}
	public static ArrayList<JRadioButton> getEngelRadioButonlar() {
		return engelRadioButonlar;
	}

	public static void setEngelRadioButonlar(ArrayList<JRadioButton> engelRadioButonlar) {
		GlobalDegiskenler.engelRadioButonlar = engelRadioButonlar;
	}

	public static int engellerdenHangisiSecili() {
		for(int i=0;i<engelRadioButonlar.size();i++){
			if(engelRadioButonlar.get(i).isSelected()){
				return i;
			}
		}
		return -1;
	}
	public static int hareketliEngellerdenHangisiSecili(){
		for(int i=0;i<hareketliEngelPanelleri.size();i++){
			if(hareketliEngelPanelleri.get(i).getEnselSecimRadioButon().isSelected()){
				return i;
			}
		}
		return -1;
	}

	public static void setEngelNoktalari(ArrayList<GridEngel> engelNoktalari) {
		GlobalDegiskenler.engelNoktalari = engelNoktalari;
	}

	public static ArrayList<HareketliEngelPanel> getHareketliEngelPanelleri() {
		return hareketliEngelPanelleri;
	}

	public static void setHareketliEngelPanelleri(ArrayList<HareketliEngelPanel> hareketliEngelPanelleri) {
		GlobalDegiskenler.hareketliEngelPanelleri = hareketliEngelPanelleri;
	}

	public static ArrayList<GridEngel> getHareketliEngelNoktalari() {
		return hareketliEngelNoktalari;
	}

	public static void setHareketliEngelNoktalari(ArrayList<GridEngel> hareketliEngelNoktalari) {
		GlobalDegiskenler.hareketliEngelNoktalari = hareketliEngelNoktalari;
	}
}
