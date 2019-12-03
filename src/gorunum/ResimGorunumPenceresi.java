package gorunum;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.jidesoft.icons.IconsFactory;
import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.list.DefaultPreviewImageIcon;
import com.jidesoft.list.ImagePreviewList;
import com.jidesoft.tooltip.ExpandedTipUtils;

import Enum.Algoritmalar;
import Ortak.OrtakSabitler;
import model.AlgoritmaModel;
import model.Modeller;

public class ResimGorunumPenceresi extends JScrollPane {
    protected ImagePreviewList _imagePreviewList;
    private JScrollPane resimGorunumPenceresi;
    private int robotIndeks;
    private Algoritmalar algoritmalar;
    private ArrayList<String> dosyalar;
    private int sayac =0;
	
	public ResimGorunumPenceresi(int robotIndeks_,Algoritmalar algo){
		System.out.println("Robot Indeks "+robotIndeks_+" "+algo.name());
		
		resimGorunumPenceresi = this;
		robotIndeks=robotIndeks_+1;
		algoritmalar=algo;
		ArrayList<String> dosyalar = new ArrayList<String>();
		File dizin = new File(OrtakSabitler.SCREEN_ANA_DOSYA_YOLU+"robot"+robotIndeks);
		File[] f = dizin.listFiles();
		if(f!=null){
		for(int i=0;i<f.length;i++){
			if(f[i].getName().contains(algoritmalar.name())){
				dosyalar.add(f[i].getAbsolutePath());
			}
		}
        DefaultListModel list = new DefaultListModel();
        for(int i=0;i<dosyalar.size();i++){
        	list.addElement(new DefaultPreviewImageIcon(new ImageIcon(dosyalar.get(i)),""));
        }
  
        _imagePreviewList = new ImagePreviewList(list) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(900, 700);
            }
        };
        _imagePreviewList.setShowDetails(ImagePreviewList.SHOW_TITLE | ImagePreviewList.SHOW_DESCRIPTION | ImagePreviewList.SHOW_SIZE);
        _imagePreviewList.setCellDimension(new Dimension(400, 400));
       

        ExpandedTipUtils.install(_imagePreviewList);
                
        _imagePreviewList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("GET VALUE ADJUST "+e.getValueIsAdjusting());
			sayac++;
			if(sayac%2==0){			
				 dosyalar.clear();
					File dizin = new File(OrtakSabitler.SCREEN_ANA_DOSYA_YOLU+"robot"+robotIndeks);
					File[] f = dizin.listFiles();
					if(f!=null){
					for(int i=0;i<f.length;i++){
						if(f[i].getName().split("-")[0].equals(algoritmalar.name())){
							System.out.println(f[i].getAbsolutePath());
							dosyalar.add(f[i].getAbsolutePath());
						}
					}
				if(_imagePreviewList.getSelectedIndex()!=-1){
				File dosya = new File(dosyalar.get(_imagePreviewList.getSelectedIndex()));
				String[] part = dosya.getName().split("-");
				String part2[] = part[2].split("\\.");
				int algoritmaIndeks = Integer.valueOf(part2[0]);
				ResimOzellikPenceresi ozellikPenceresi = new ResimOzellikPenceresi(robotIndeks, algoritmalar, algoritmaIndeks, dosyalar.get(_imagePreviewList.getSelectedIndex()));
				ozellikPenceresi.setVisible(true);
				AlgoritmaModel algoritmaModel = null;
				switch (algoritmalar) {
				case ASTAR:
					algoritmaModel = Modeller.aStarRobotModel.get(robotIndeks-1).getAlgoritmaModel().get(algoritmaIndeks);	
					break;
				case LEE:
					algoritmaModel = Modeller.leeRobotModel.get(robotIndeks-1).getAlgoritmaModel().get(algoritmaIndeks);	
					break;
				case POTENTIAL_FIELD:
					algoritmaModel = Modeller.potentialFieldRobotModel.get(robotIndeks-1).getAlgoritmaModel().get(algoritmaIndeks);	
					break;
				case RRT:
					algoritmaModel = Modeller.rrtRobotModel.get(robotIndeks-1).getAlgoritmaModel().get(algoritmaIndeks);	
					break;
				case RRTSTAR:
					algoritmaModel = Modeller.rrtStarRobotModel.get(robotIndeks-1).getAlgoritmaModel().get(algoritmaIndeks);	
					break;
				case DYNAMIC_WINDOWS_APPROACH:
					algoritmaModel = Modeller.dynamicWindowsApproachRobotModel.get(robotIndeks-1).getAlgoritmaModel().get(algoritmaIndeks);	
					break;

				default:
					break;
				}
				
				ozellikPenceresi.labellariGuncelle(algoritmaModel);
				System.out.println(_imagePreviewList.getSelectedIndex());
				}
			}
			}
			}
		});
        resimGorunumPenceresi = new JScrollPane(_imagePreviewList);
		}
    
	}
	public JScrollPane getResimGorunumPenceresi(){
		return resimGorunumPenceresi;
	}
	public void resimlerGuncellendi(Algoritmalar algo){
		algoritmalar=algo;
		 dosyalar = new ArrayList<String>();
		File dizin = new File(OrtakSabitler.SCREEN_ANA_DOSYA_YOLU+"robot"+robotIndeks);
		File[] f = dizin.listFiles();
		if(f!=null){
		for(int i=0;i<f.length;i++){
			if(f[i].getName().split("-")[0].equals(algoritmalar.name())){
				System.out.println(f[i].getAbsolutePath());
				dosyalar.add(f[i].getAbsolutePath());
			}
		}
        DefaultListModel list = new DefaultListModel();
        for(int i=0;i<dosyalar.size();i++){
        	list.addElement(new DefaultPreviewImageIcon(new ImageIcon(dosyalar.get(i)),""));
        }
		_imagePreviewList.setModel(list);
	}
}
}
