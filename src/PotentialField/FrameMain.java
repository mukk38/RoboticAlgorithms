package PotentialField;

import javax.swing.*;

import PotentialField.Obstacle;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FrameMain extends JFrame implements ActionListener {
	JPanel contentPane;
	BorderLayout borderLayout1 = new BorderLayout();
	JCheckBox virtualforce;
	JPanel commands = new JPanel();
	Set<Point> s = new HashSet<Point>();
	Obstacle hedef =null;
	JPanel pnlDraw = new JPanel() {

		public void paint(Graphics g) {          
            super.paint(g);
            g.setColor(Color.BLACK);
            s.add(new Point((int) r.x, (int) r.y));
            Iterator iter = obstacles.iterator();
            while (iter.hasNext()) {
                Obstacle ob = (Obstacle) iter.next();
                g.fillArc((int)(ob.p.x - ob.diam/2), (int)(ob.p.y - ob.diam/2), (int) ob.diam, (int) ob.diam, 0, 360);
            }
            g.setColor(Color.BLUE);
            for(Point p : s) {
              g.fillArc((int)(p.x - r.diam/4), (int)(p.y - r.diam/4), (int)r.diam/2, (int)r.diam/2, 0, 360);
            }
            
            g.setColor(Color.RED);
            g.fillArc((int)(r.x - r.diam/2), (int)(r.y - r.diam/2), (int)r.diam, (int)r.diam, 0, 360);
            g.drawLine((int)r.x, (int)r.y, (int)(r.x+r.vx), (int)(r.y+r.vy));
        }
	};

	ArrayList obstacles = new ArrayList();
	
	{
//		 hedef =new Obstacle(PotentialFieldConstant.HEDEF_NOKTA, PotentialFieldConstant.HEDEF_CHARGE,
//			PotentialFieldConstant.HEDEF_DIAM);
		obstacles.add(new Obstacle(PotentialFieldConstant.HEDEF_NOKTA, PotentialFieldConstant.HEDEF_CHARGE,
				PotentialFieldConstant.HEDEF_DIAM));
	
	}
	Robot r = new Robot(PotentialFieldConstant.ROBOT_BASLANGIC_NOKTASI, obstacles,
			PotentialFieldConstant.ROBOT_BASLANGIC_DT, PotentialFieldConstant.ROBOT_BASLANGIC_MASS,
			PotentialFieldConstant.ROBOT_BASLANGIC_FMAX, PotentialFieldConstant.ROBOT_BASLANGIC_DIAM);
	Thread t = new Thread();

	public FrameMain() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			jbInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * Component initialization.
	 *
	 * @throws java.lang.Exception
	 */
	private void jbInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(borderLayout1);
		setSize(new Dimension(900, 700));
		setTitle("Fuzzy Roboter");
		pnlDraw.addMouseListener(new FrameMain_pnlDraw_mouseAdapter(this));
		JButton bnew = new JButton("New");
		bnew.setActionCommand("new");
		bnew.addActionListener(this);
		commands.add(bnew);
		JButton bstart = new JButton("Start");
		bstart.setActionCommand("start");
		bstart.addActionListener(this);
		commands.add(bstart);
		JButton bpause = new JButton("Pause");
		bpause.setActionCommand("pause");
		bpause.addActionListener(this);
		commands.add(bpause);
		JButton bresume = new JButton("Resume");
		bresume.setActionCommand("resume");
		bresume.addActionListener(this);
		commands.add(bresume);
		virtualforce = new JCheckBox("Virtual Force");
		virtualforce.setActionCommand("vf");
		virtualforce.addActionListener(this);
		commands.add(virtualforce);
		contentPane.add(pnlDraw, java.awt.BorderLayout.CENTER);
		contentPane.add(commands, java.awt.BorderLayout.NORTH);
	}

	public void pnlDraw_mouseReleased(MouseEvent e) {
		obstacles.add(new Obstacle(e.getPoint()));
		pnlDraw.repaint();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("start")) {
			t = new Thread(new Runnable() {
				public void run() {
					while ((r.x > 1) && (r.y > 1)) {
						r.updatePosition();
						pnlDraw.repaint();
						try {
							Thread.sleep(100);
						} catch (InterruptedException ex) {
							System.out.println(ex);
						}
					}
				}
			});
			t.start();
		} else if (e.getActionCommand().equals("new")) {
			r.x = 0;
			r.y = 0;
			r = new Robot(new Point(800, 600), obstacles, 0.1, 40, 4000, 15);
			s = new HashSet<Point>();
			t = new Thread();
			r.virtualforce = virtualforce.isSelected();
			pnlDraw.repaint();
		} else if (e.getActionCommand().equals("pause")) {
			t.suspend();
		} else if (e.getActionCommand().equals("resume")) {
			t.resume();
		} else if (e.getActionCommand().equals("vf")) {
			System.out.println("VIRTUAL FORCE!!");
			r.virtualforce = !r.virtualforce;
		}
	}

}

class FrameMain_pnlDraw_mouseAdapter extends MouseAdapter {
	private FrameMain adaptee;

	FrameMain_pnlDraw_mouseAdapter(FrameMain adaptee) {
		this.adaptee = adaptee;
	}

	public void mouseReleased(MouseEvent e) {
		adaptee.pnlDraw_mouseReleased(e);
	}
}
