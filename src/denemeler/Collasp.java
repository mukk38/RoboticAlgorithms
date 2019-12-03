package denemeler;

import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import com.jidesoft.pane.CollapsiblePane;
import com.jidesoft.swing.JideButton;
import com.jidesoft.swing.JideSwingUtilities;

import Ortak.OrtakMetotlar;
import gorunum.RobotEklePanel;

public class Collasp {
	public Collasp(){
		JDialog abc = new JDialog();
		OrtakMetotlar.setBilesenBoyutu(abc, 500, 500);
		abc.setContentPane(createFileFolderTaskPane());
		abc.setVisible(true);
	}
	
    private  CollapsiblePane createFileFolderTaskPane() {
        CollapsiblePane pane = new CollapsiblePane("Files and Folders");
        pane.setName("Files and Folders");
        pane.setTitleIcon(new ImageIcon(RobotEklePanel.BIR_NUMARA_DOSYA_PATH));
// uncomment following for a different style of collapsible pane
//        panel.setStyle(CollapsiblePane.TREE_STYLE);
        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.setLayout(new GridLayout(6, 1, 1, 0));
        labelPanel.add(createHyperlinkButton("Pictures", new ImageIcon(RobotEklePanel.BIR_NUMARA_DOSYA_PATH)));
        labelPanel.add(createHyperlinkButton("Files", new ImageIcon(RobotEklePanel.IKI_NUMARA_DOSYA_PATH)));
        labelPanel.add(createHyperlinkButton("Calendar", new ImageIcon(RobotEklePanel.UC_NUMARA_DOSYA_PATH)));
        labelPanel.add(createHyperlinkButton("Chart", new ImageIcon(RobotEklePanel.DORT_NUMARA_DOSYA_PATH)));
        labelPanel.add(createHyperlinkButton("Database", new ImageIcon(RobotEklePanel.BES_NUMARA_DOSYA_PATH)));
        labelPanel.add(createHyperlinkButton("Documents", new ImageIcon(RobotEklePanel.BIR_NUMARA_DOSYA_PATH)));
        labelPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        pane.setContentPane(JideSwingUtilities.createTopPanel(labelPanel));
        pane.setEmphasized(true);
        return pane;
    }
     JComponent createHyperlinkButton(String name, Icon icon) {
        final JideButton button = new JideButton(name, icon);
        button.setButtonStyle(JideButton.HYPERLINK_STYLE);

        button.setOpaque(false);
        button.setHorizontalAlignment(SwingConstants.LEADING);

        button.setRequestFocusEnabled(true);
        button.setFocusable(true);

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }
	
	public static void main(String[] args) {
		Collasp a = new Collasp();
	}

}
