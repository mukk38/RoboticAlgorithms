package gorunum;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import com.jidesoft.dialog.AbstractDialogPage;
import com.jidesoft.dialog.MultiplePageDialog;
import com.jidesoft.dialog.PageList;
import com.jidesoft.swing.JideSwingUtilities;

import Enum.Algoritmalar;
import Ortak.OrtakMetotlar;

public class RobotBilgiDialog extends MultiplePageDialog {

	private int indeks;
	private RobotBilgiDialog robotBilgiDialog;
	private AbstractDialogPage aStarAlgo;
	private AbstractDialogPage leeAlgo;
	private AbstractDialogPage potentialFieldAlgo;
	private AbstractDialogPage rrtAlgo ;
	private AbstractDialogPage rrtStarAlgo;
	private AbstractDialogPage dynamicWindowsApproachAlgo;
	private AbstractDialogPage grafikler;
	private AbstractDialogPage katEdilenYolGrafik;
	private AbstractDialogPage acisalHizGrafik;
	private AbstractDialogPage harcananZamanGrafik;
//	private AbstractDialogPage AStarKatedilenYolDialog;
//	private AbstractDialogPage AStarZamanDialog;
//	private AbstractDialogPage AStarAcisalHizDegisimiDialog;
//	private AbstractDialogPage AStarBasariOraniDialog;
//	private AbstractDialogPage LeeKatedilenYolDialog;
//	private AbstractDialogPage LeeZamanDialog;
//	private AbstractDialogPage LeeAcisalHizDegisimiDialog;
//	private AbstractDialogPage LeeBasariOraniDialog;
//	private AbstractDialogPage potentialFieldKatedilenYolDialog;
//	private AbstractDialogPage potentialFieldZamanDialog;
//	private AbstractDialogPage potentialFieldAcisalHizDegisimiDialog;
//	private AbstractDialogPage potentialFieldBasariOraniDialog;
//	private AbstractDialogPage rrtKatedilenYolDialog;
//	private AbstractDialogPage rrtZamanDialog;
//	private AbstractDialogPage rrtAcisalHizDegisimiDialog;
//	private AbstractDialogPage rrtBasariOraniDialog;
//	private AbstractDialogPage rrtStarKatedilenYolDialog;
//	private AbstractDialogPage rrtStarZamanDialog;
//	private AbstractDialogPage rrtStarAcisalHizDegisimiDialog;
//	private AbstractDialogPage rrtStarBasariOraniDialog;
//	private AbstractDialogPage dynamicWindowKatedilenYolDialog;
//	private AbstractDialogPage dynamicWindowZamanDialog;
//	private AbstractDialogPage dynamicWindowAcisalHizDegisimiDialog;
//	private AbstractDialogPage dynamicWindowBasariOraniDialog;

	public RobotBilgiDialog(int indeks_) {
		robotBilgiDialog = this;
		robotBilgiDialog.setName("Algoritma Bilgiler");
		robotBilgiDialog.setStyle(MultiplePageDialog.TREE_STYLE);
		PageList model = new PageList();
		robotBilgiDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 aStarAlgo = new VsnetOptionPage("A * Star Algorithm",indeks_,Algoritmalar.ASTAR);
//		AStarKatedilenYolDialog = new VsnetOptionPage("Katedilen Yol(m)",indeks_,Algoritmalar.ASTAR);
//		AStarZamanDialog = new VsnetOptionPage("Zaman",indeks_,Algoritmalar.ASTAR);
//		AStarAcisalHizDegisimiDialog = new VsnetOptionPage("Açýsal Hýz Deðiþimi",indeks_,Algoritmalar.ASTAR);
//		AStarBasariOraniDialog = new VsnetOptionPage("Baþarý Oraný",indeks_,Algoritmalar.ASTAR);
//		AStarKatedilenYolDialog.setParentPage(aStarAlgo);
//		AStarZamanDialog.setParentPage(aStarAlgo);
//		AStarAcisalHizDegisimiDialog.setParentPage(aStarAlgo);
//		AStarBasariOraniDialog.setParentPage(aStarAlgo);
		 leeAlgo = new VsnetOptionPage("Lee Algorithm",indeks_,Algoritmalar.LEE);
//		LeeKatedilenYolDialog = new VsnetOptionPage("Katedilen Yol(m)",indeks_,Algoritmalar.LEE);
//		LeeZamanDialog = new VsnetOptionPage("Zaman",indeks_,Algoritmalar.LEE);
//		LeeAcisalHizDegisimiDialog = new VsnetOptionPage("Açýsal Hýz Deðiþimi",indeks_,Algoritmalar.LEE);
//		LeeBasariOraniDialog = new VsnetOptionPage("Baþarý Oraný",indeks_,Algoritmalar.LEE);
//		LeeKatedilenYolDialog.setParentPage(leeAlgo);
//		LeeZamanDialog.setParentPage(leeAlgo);
//		LeeAcisalHizDegisimiDialog.setParentPage(leeAlgo);
//		LeeBasariOraniDialog.setParentPage(leeAlgo);
		 potentialFieldAlgo = new VsnetOptionPage("Potential Field Algorithm",indeks_,Algoritmalar.POTENTIAL_FIELD);
//		potentialFieldKatedilenYolDialog = new VsnetOptionPage("Katedilen Yol(m)",indeks_,Algoritmalar.POTENTIAL_FIELD);
//		potentialFieldZamanDialog = new VsnetOptionPage("Zaman",indeks_,Algoritmalar.POTENTIAL_FIELD);
//		potentialFieldAcisalHizDegisimiDialog = new VsnetOptionPage("Açýsal Hýz Deðiþimi",indeks_,Algoritmalar.POTENTIAL_FIELD);
//		potentialFieldBasariOraniDialog = new VsnetOptionPage("Baþarý Oraný",indeks_,Algoritmalar.POTENTIAL_FIELD);
//		potentialFieldKatedilenYolDialog.setParentPage(potentialFieldAlgo);
//		potentialFieldZamanDialog.setParentPage(potentialFieldAlgo);
//		potentialFieldAcisalHizDegisimiDialog.setParentPage(potentialFieldAlgo);
//		potentialFieldBasariOraniDialog.setParentPage(potentialFieldAlgo);
		 rrtAlgo = new VsnetOptionPage("Rrt Algorithm",indeks_,Algoritmalar.RRT);

		 rrtStarAlgo = new VsnetOptionPage("Rrt Star Algorithm",indeks_,Algoritmalar.RRTSTAR);

		 dynamicWindowsApproachAlgo = new VsnetOptionPage("Dynamic Windows Aproach Algorithm",indeks_,Algoritmalar.DYNAMIC_WINDOWS_APPROACH);

		 grafikler =new GrafikDialogPage("Grafikler");
		 katEdilenYolGrafik =new KatEdilenYolDialogPage("Kat Edilen Yol Grafik",indeks_);
		 acisalHizGrafik = new AcisalHizDialogPage("Açýsal Hýz Grafik",indeks_);
		 harcananZamanGrafik = new HarcananZamanDialogPage("Haracanan Zaman Grafik",indeks_);
		 katEdilenYolGrafik.setParentPage(grafikler);
		 acisalHizGrafik.setParentPage(grafikler);
		 harcananZamanGrafik.setParentPage(grafikler);
		   model.append(aStarAlgo);
	        model.append(leeAlgo);
	        model.append(potentialFieldAlgo);
	        model.append(rrtAlgo);
	        model.append(rrtStarAlgo);
	        model.append(dynamicWindowsApproachAlgo);
	        model.append(grafikler);
	        model.append(katEdilenYolGrafik);
	        model.append(harcananZamanGrafik);
	        model.append(acisalHizGrafik);

	        robotBilgiDialog.setPageList(model);
	        robotBilgiDialog.setInitialPageTitle("Bilgiler");
	        robotBilgiDialog.pack();
	        JideSwingUtilities.globalCenterWindow(robotBilgiDialog);
		indeks = indeks_;
		OrtakMetotlar.setBilesenBoyutu(robotBilgiDialog, 500, 500);
	}
	public void yenile(){
		aStarAlgo.lazyInitialize();
		 leeAlgo.lazyInitialize();
		 potentialFieldAlgo.lazyInitialize();
		 rrtAlgo.lazyInitialize();
		 rrtStarAlgo.lazyInitialize();
		 dynamicWindowsApproachAlgo.lazyInitialize();
		 grafikler.lazyInitialize();
		 katEdilenYolGrafik.lazyInitialize();
		 acisalHizGrafik.lazyInitialize();
		 harcananZamanGrafik.lazyInitialize();
//		 AStarKatedilenYolDialog.lazyInitialize();
//		 AStarZamanDialog.lazyInitialize();
//		 AStarAcisalHizDegisimiDialog.lazyInitialize();
//		 AStarBasariOraniDialog.lazyInitialize();
//		 LeeKatedilenYolDialog.lazyInitialize();
//		 LeeZamanDialog.lazyInitialize();
//		 LeeAcisalHizDegisimiDialog.lazyInitialize();
//		 LeeBasariOraniDialog.lazyInitialize();
//		 potentialFieldKatedilenYolDialog.lazyInitialize();
//		 potentialFieldZamanDialog.lazyInitialize();
//		 potentialFieldAcisalHizDegisimiDialog.lazyInitialize();
//		 potentialFieldBasariOraniDialog.lazyInitialize();
//		 rrtKatedilenYolDialog.lazyInitialize();
//		 rrtZamanDialog.lazyInitialize();
//		 rrtAcisalHizDegisimiDialog.lazyInitialize();
//		 rrtBasariOraniDialog.lazyInitialize();
//		 rrtStarKatedilenYolDialog.lazyInitialize();
//		 rrtStarZamanDialog.lazyInitialize();
//		 rrtStarAcisalHizDegisimiDialog.lazyInitialize();
//		 rrtStarBasariOraniDialog.lazyInitialize();
//		 dynamicWindowKatedilenYolDialog.lazyInitialize();
//		 dynamicWindowZamanDialog.lazyInitialize();
//		 dynamicWindowAcisalHizDegisimiDialog.lazyInitialize();
//		 dynamicWindowBasariOraniDialog.lazyInitialize();
	}

	public static class VsnetOptionPage extends AbstractDialogPage {
		private int robotIndeks;
		private Algoritmalar algoritmalar;
		private ResimGorunumPenceresi resimGorunumPenceresi;
		public VsnetOptionPage(String name,int robotIndeks_,Algoritmalar algo) {
			super(name);
			robotIndeks = robotIndeks_;
			algoritmalar = algo;
		
		}

		public void lazyInitialize() {
			initComponents();
		}

		public void initComponents() {
			setLayout(new BorderLayout());
			getResimPenceresi().resimlerGuncellendi(algoritmalar);
			add(getResimPenceresi().getResimGorunumPenceresi(),BorderLayout.CENTER);
			// add(new JLabel("This is just a demo. \"" + getFullTitle() + "\"
			// page is not implemented yet.", JLabel.CENTER),
			// BorderLayout.CENTER);
		}
		private ResimGorunumPenceresi getResimPenceresi(){
			if (resimGorunumPenceresi == null) {
				resimGorunumPenceresi = new ResimGorunumPenceresi(robotIndeks,algoritmalar);
				
			}

			return resimGorunumPenceresi;
		}


	}

}
