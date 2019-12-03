package gorunum;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import com.jidesoft.chart.Chart;
import com.jidesoft.chart.axis.Axis;
import com.jidesoft.chart.axis.CategoryAxis;
import com.jidesoft.chart.model.ChartCategory;
import com.jidesoft.chart.model.ChartPoint;
import com.jidesoft.chart.model.DefaultChartModel;
import com.jidesoft.chart.model.Highlight;
import com.jidesoft.chart.model.RealPosition;
import com.jidesoft.chart.render.DefaultBarRenderer;
import com.jidesoft.chart.style.ChartStyle;
import com.jidesoft.dialog.AbstractDialogPage;
import com.jidesoft.range.CategoryRange;
import com.jidesoft.range.NumericRange;

import model.Modeller;

public class HarcananZamanDialogPage extends TabbedPaneOrtakDialogPage {

	 public HarcananZamanDialogPage(String name,int indeks) {
			super(name,indeks);
		}

	@Override
	public JPanel getGrafikPanel(int i) {

		
		JPanel    grafikPanel = new JPanel();
        grafikPanel.setMinimumSize(new Dimension(400, 400));
        grafikPanel.setLayout(new GridLayout(2, 1));

         aStarHighlight = new Highlight("A Star");
         leeHighlight = new Highlight("Lee");
         potentialFieldHighlight = new Highlight("Potential Field");
         rrtHighlight = new Highlight("RRT");
         rrtStarHighlight = new Highlight("RRT Star");
         dynamicWindowApproachFieldHighlight = new Highlight("Dynamic Window Approach");

        aStar = new ChartCategory<String>("AStar", aStarHighlight);
        Lee = new ChartCategory<String>("Lee", leeHighlight);
        potentialField = new ChartCategory<String>("Potential Field ", potentialFieldHighlight);
        rrt = new ChartCategory<String>("RRT ", rrtHighlight);
        rrtStar = new ChartCategory<String>("RRT Star", rrtStarHighlight);
        dynamicWindowApproach = new ChartCategory<String>("Dynamic Window Approach", dynamicWindowApproachFieldHighlight);
        CategoryRange<String> colors = new CategoryRange<String>().add(aStar).add(Lee).add(potentialField).add(rrt).add(rrtStar).add(dynamicWindowApproach);



   
        DefaultChartModel    chartModel = chartModelOlustur();
        Axis xAxis = new CategoryAxis<String>(colors);
        Axis yAxis = new Axis(new NumericRange(0, yAxisMax));

            barChart = new Chart();
        grafikPanel.add(barChart);
        barChart.setTitle("Harcanan Zaman");
        barChart.setMinimumSize(new Dimension(100, 100));
        barChart.setPreferredSize(new Dimension(100, 100));
        ChartStyle     style = new ChartStyle();
        style.setBarsVisible(true);
        style.setLinesVisible(false);
        barChart.setStyle(chartModel, style);
        barChart.setSelectionShowsOutline(false);
        barChart.setGridColor(new Color(150, 150, 150));
        barChart.setChartBackground(new GradientPaint(0f, 0f, Color.lightGray.brighter(), 300f, 300f, Color.lightGray));
        barChart.addModel(chartModel);
        barChart.setLayout(new BorderLayout());
        barChart.setBarGap(5);
        barChart.setXAxis(xAxis);
        barChart.setYAxis(yAxis);
        barChart.setVerticalGridLinesVisible(false);
        DefaultBarRenderer barRenderer = (DefaultBarRenderer) barChart.getBarRenderer();
        barRenderer.setAlwaysShowOutlines(false);
        barRenderer.setOutlineWidth(outlineWidth);
        useColorHighlights();


        return grafikPanel;    	
	}
	
	private DefaultChartModel chartModelOlustur() {
		double aStarDeger = 0, leeDeger = 0, potentialFieldDeger = 0, rrtDeger = 0, rrtStarDeger = 0,
				dynamicWindowApproachDeger = 0;
		if (Modeller.aStarRobotModel.get(robotIndeks).getAlgoritmaModel().size()>algoritmaIndeks) {
			aStarDeger = Modeller.aStarRobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks)
					.getHarcananZaman();
		}
		if (Modeller.leeRobotModel.get(robotIndeks).getAlgoritmaModel().size()>algoritmaIndeks) {
			leeDeger = Modeller.leeRobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks).getHarcananZaman();
		}
		if (Modeller.potentialFieldRobotModel.get(robotIndeks).getAlgoritmaModel().size()>algoritmaIndeks) {
			potentialFieldDeger = Modeller.potentialFieldRobotModel.get(robotIndeks).getAlgoritmaModel()
					.get(algoritmaIndeks).getHarcananZaman();
		}
		if (Modeller.rrtRobotModel.get(robotIndeks).getAlgoritmaModel().size()>algoritmaIndeks) {
			rrtDeger = Modeller.rrtRobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks).getHarcananZaman();
		}
		if (Modeller.rrtStarRobotModel.get(robotIndeks).getAlgoritmaModel().size()>algoritmaIndeks) {
			rrtStarDeger = Modeller.rrtStarRobotModel.get(robotIndeks).getAlgoritmaModel().get(algoritmaIndeks)
					.getHarcananZaman();
		}
		if (Modeller.dynamicWindowsApproachRobotModel.get(robotIndeks).getAlgoritmaModel().size()>algoritmaIndeks) {
			dynamicWindowApproachDeger = Modeller.dynamicWindowsApproachRobotModel.get(robotIndeks).getAlgoritmaModel()
					.get(algoritmaIndeks).getHarcananZaman();
		}
		List<Double> degerler = new ArrayList<Double>();
		degerler.add(aStarDeger);
		degerler.add(leeDeger);
		degerler.add(potentialFieldDeger);
		degerler.add(rrtDeger);
		degerler.add(rrtStarDeger);
		degerler.add(dynamicWindowApproachDeger);
		Collections.sort(degerler);
		yAxisMax = degerler.get(degerler.size() - 1)*5/4;

		DefaultChartModel chartModel = new DefaultChartModel("Harcanan Zaman Grafik");
		chartModel.addPoint(new ChartPoint(aStar, new RealPosition(aStarDeger), aStarHighlight));
		chartModel.addPoint(new ChartPoint(Lee, new RealPosition(leeDeger), leeHighlight));
		chartModel.addPoint(
				new ChartPoint(potentialField, new RealPosition(potentialFieldDeger), potentialFieldHighlight));
		chartModel.addPoint(new ChartPoint(rrt, new RealPosition(rrtDeger), rrtHighlight));
		chartModel.addPoint(new ChartPoint(rrtStar, new RealPosition(rrtStarDeger), rrtStarHighlight));
		chartModel.addPoint(new ChartPoint(dynamicWindowApproach, new RealPosition(dynamicWindowApproachDeger),
				dynamicWindowApproachFieldHighlight));
		return chartModel;
	}



}
