package gorunum;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.SystemTray;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import Mapping.Nokta;
import Ortak.GlobalDegiskenler;
import Ortak.IsTipi;
import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import kontrol.GridLabelKontrol;
import kontrol.RobotKontrol;


public class GridLabel extends JLabel {
	private int x =0;
	private int y =0;
	private GridLabel gridLabel;

	 
	    public GridLabel(int x_,int y_){
	    	gridLabel =this;
		x=x_;
		y=y_;
		OrtakMetotlar.setBilesenBoyutu(this, OrtakSabitler.GRID_LABEL_BOY, OrtakSabitler.GRID_LABEL_BOY);

	}


}

