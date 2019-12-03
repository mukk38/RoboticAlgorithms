package gorunum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jidesoft.chart.Chart;
import com.jidesoft.chart.model.ChartCategory;
import com.jidesoft.chart.model.Highlight;
import com.jidesoft.chart.style.ChartStyle;
import com.jidesoft.dialog.AbstractDialogPage;
import com.jidesoft.swing.JideTabbedPane;


import model.Modeller;

public abstract class TabbedPaneOrtakDialogPage extends AbstractDialogPage {
	protected Chart    barChart;
	protected ChartCategory<String> aStar,Lee,potentialField,rrt,rrtStar,dynamicWindowApproach;
	protected ChartStyle     aStarRenk,LeeRenk,potentialFieldRenk,rrtRenk,rrtStarRenk,dynamicWindowApproachRenk;
    public  final float outlineWidth = 3f;
    private  JideTabbedPane tabbedPane;
    protected int robotIndeks;
    protected Highlight aStarHighlight;
    protected Highlight	leeHighlight;
    protected Highlight potentialFieldHighlight;
    protected Highlight rrtHighlight;
    protected Highlight rrtStarHighlight;
    protected Highlight dynamicWindowApproachFieldHighlight;
    protected int algoritmaIndeks;
    protected  double yAxisMax = 40.0;
	 public TabbedPaneOrtakDialogPage(String name,int indeks) {
			super(name);
			robotIndeks = indeks;
		}

		@Override
		public void lazyInitialize() {
			setLayout(new BorderLayout());
			add(getJideTabbedPane(),BorderLayout.CENTER);
			
		}
		
		private JideTabbedPane getJideTabbedPane(){
		//	if (tabbedPane == null) {
				tabbedPane = new JideTabbedPane(JideTabbedPane.TOP);
				tabbedPane.setOpaque(true);
				int algoritmaSayi=algoritmaSayisiniBul();
				String[] titles = new String[algoritmaSayi];
				if(algoritmaSayi>0){
					for(int i=0;i<algoritmaSayi;i++){
						titles[i] = String.valueOf(i);
						  JScrollPane scrollPane = new JScrollPane(getGrafikPanel(i));
				            scrollPane.setPreferredSize(new Dimension(530, 530));
				            tabbedPane.addTab(titles[i],  scrollPane);
					}
				}
				
		//	}

			return tabbedPane;
		}
		
		public abstract JPanel getGrafikPanel(int i);

		public  int algoritmaSayisiniBul(){
			List<Integer> sortedList = new ArrayList<Integer>();
			sortedList.add(Modeller.aStarRobotModel.get(robotIndeks).getAlgoritmaModel().size());
			sortedList.add(Modeller.leeRobotModel.get(robotIndeks).getAlgoritmaModel().size());
			sortedList.add(Modeller.potentialFieldRobotModel.get(robotIndeks).getAlgoritmaModel().size());
			sortedList.add(Modeller.rrtRobotModel.get(robotIndeks).getAlgoritmaModel().size());
			sortedList.add(Modeller.rrtStarRobotModel.get(robotIndeks).getAlgoritmaModel().size());
			sortedList.add(Modeller.dynamicWindowsApproachRobotModel.get(robotIndeks).getAlgoritmaModel().size());
			Collections.sort(sortedList);
			return sortedList.get(sortedList.size()-1);
		}
		  protected void useColorHighlights() {
		        aStarRenk = new ChartStyle(new Color(255, 64, 64, 245));
		        LeeRenk = new ChartStyle(new Color(64, 255, 64, 245));
		        potentialFieldRenk = new ChartStyle(new Color(100, 100, 255, 245));
		        rrtRenk = new ChartStyle(new Color(64, 128, 128));
		        rrtStarRenk = new ChartStyle(new Color(128, 128, 128));
		        dynamicWindowApproachRenk = new ChartStyle(new Color(0,0,255));
		        
		        
		    	barChart.setHighlightStyle(aStar.getHighlight(), new ChartStyle(aStarRenk).withBars());
		    	barChart.setHighlightStyle(Lee.getHighlight(), new ChartStyle(LeeRenk).withBars());
		    	barChart.setHighlightStyle(potentialField.getHighlight(), new ChartStyle(potentialFieldRenk).withBars());
		      	barChart.setHighlightStyle(rrt.getHighlight(), new ChartStyle(rrtRenk).withBars());
		    	barChart.setHighlightStyle(rrtStar.getHighlight(), new ChartStyle(rrtStarRenk).withBars());
		    	barChart.setHighlightStyle(dynamicWindowApproach.getHighlight(), new ChartStyle(dynamicWindowApproachRenk).withBars());
		    }
}
