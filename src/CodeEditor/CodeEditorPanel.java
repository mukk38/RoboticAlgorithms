package CodeEditor;

/*
 * @(#)CodeEditorDemo.java 5/30/2006
 *
 * Copyright 2002 - 2006 JIDE Software Inc. All rights reserved.
 */

import com.jidesoft.editor.CodeEditor;
import com.jidesoft.editor.ListDataCodeEditorIntelliHints;
import com.jidesoft.editor.language.LanguageSpec;
import com.jidesoft.editor.language.LanguageSpecManager;
import com.jidesoft.editor.status.CodeEditorStatusBar;
import com.jidesoft.editor.tokenmarker.JavaScriptTokenMarker;
import com.jidesoft.editor.tokenmarker.JavaTokenMarker;
import com.jidesoft.plaf.LookAndFeelFactory;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.Searchable;
import com.jidesoft.swing.SearchableBar;


import Ortak.OrtakMetotlar;
import Ortak.OrtakSabitler;
import kontrol.ScriptUygulaKontrol;
import pencereler.FarkliKaydetIsimGirmePenceresi;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Set;


public class CodeEditorPanel extends JDialog {
    public CodeEditor _editor;
    public JPanel editorPanel;
    public JPanel dugmelerPanel;
    public JButton farkliKaydet;
    public JButton uygulaDugmesi;
    public JButton	scriptYukleDugmesi;
    private CodeEditorPanel codeEditorPanel;

    public CodeEditorPanel() {
    	codeEditorPanel = this;
    	OrtakMetotlar.setBilesenBoyutu(this, 500, 500);
    	this.setContentPane(getEditorPanel());
    }
    
    public JPanel getEditorPanel(){
    	if (editorPanel == null) {
			editorPanel = new JPanel(new BorderLayout());
			editorPanel.add(getDemoPanel(),BorderLayout.CENTER);
			editorPanel.add(getDugmelerPanel(),BorderLayout.SOUTH);
		}

		return editorPanel;
    }


    private JButton getFarkliKaydet() {
		if (farkliKaydet == null) {
			farkliKaydet = new JButton("Farklý Kaydet");
			farkliKaydet.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(_editor.getText());
					FarkliKaydetIsimGirmePenceresi pencere = new FarkliKaydetIsimGirmePenceresi(_editor.getText());
					pencere.setLocationRelativeTo(_editor);
					pencere.setVisible(true);
				}
			});
		}

		return farkliKaydet;
	}
    
    private JButton getUygulaDugmesi(){
    	if (uygulaDugmesi == null) {
			uygulaDugmesi = new JButton("Uygula");
			uygulaDugmesi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ScriptUygulaKontrol.uygulaDugmesineBasildi(_editor.getText());
					codeEditorPanel.setVisible(false);
				}
			});
		}

		return uygulaDugmesi;
    }
    
    private JButton getScriptYukleDugmesi(){
    	if (scriptYukleDugmesi == null) {
			scriptYukleDugmesi = new JButton("Script seç/yükle");
			scriptYukleDugmesi.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					jfc.setDialogTitle("Choose a directory to save your file: ");
					jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					jfc.setCurrentDirectory(new File(OrtakSabitler.ALGORITMA_SEC));
					int returnValue = jfc.showSaveDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						if (jfc.getSelectedFile().isFile()) {
							System.out.println("You selected the directory: " + jfc.getSelectedFile());
							_editor.setText(OrtakMetotlar.dosyaOku(jfc.getSelectedFile().toString()));
							
						}
					}
				}
			});
		}

		return scriptYukleDugmesi;
    }
    
    private JPanel getDugmelerPanel(){
    	if (dugmelerPanel == null) {
			dugmelerPanel = new JPanel();
			dugmelerPanel.add(getFarkliKaydet());
			dugmelerPanel.add(getUygulaDugmesi());
			dugmelerPanel.add(getScriptYukleDugmesi());
		}

		return dugmelerPanel;
    }

	public Component getDemoPanel() {
        _editor = new CodeEditor();
        _editor.setTokenMarker(new JavaScriptTokenMarker());
		LanguageSpec languageSpec = LanguageSpecManager.getInstance().getLanguageSpec("JavaScript");
		if (languageSpec != null) {
		    languageSpec.configureCodeEditor(_editor);
		}
         
		_editor.setPreferredSize(new Dimension(600, 500));
		_editor.setHorizontalScrollBarPolicy(ScrollPane.SCROLLBARS_AS_NEEDED);
		_editor.setVerticalScrollBarPolicy(ScrollPane.SCROLLBARS_AS_NEEDED);
		Set<String> stringSet = JavaScriptTokenMarker.getKeywords().keyWordSet();
		String[] strings = stringSet.toArray(new String[stringSet.size()]);
		Arrays.sort(strings);
		new ListDataCodeEditorIntelliHints<String>(_editor, strings);

		final JPanel panel = new JPanel(new BorderLayout());
		panel.add(_editor.createOverlay());
		final JPanel barPanel = new JPanel(new BorderLayout());
		barPanel.add(new CodeEditorStatusBar(_editor), BorderLayout.CENTER);
		panel.add(barPanel, BorderLayout.AFTER_LAST_LINE);
		Searchable searchable = _editor.getSearchable();
		searchable.setRepeats(true);
		SearchableBar _textAreaSearchableBar = SearchableBar.install(searchable, KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK), new SearchableBar.Installer() {
		    public void openSearchBar(SearchableBar searchableBar) {
		        barPanel.add(searchableBar, BorderLayout.AFTER_LAST_LINE);
		        barPanel.invalidate();
		        barPanel.revalidate();
		    }

		    public void closeSearchBar(SearchableBar searchableBar) {
		        barPanel.remove(searchableBar);
		        barPanel.invalidate();
		        barPanel.revalidate();
		    }
		});
		_textAreaSearchableBar.setVisibleButtons(~SearchableBar.SHOW_REPEATS);
		_textAreaSearchableBar.setShowMatchCount(true);
		_textAreaSearchableBar.getInstaller().openSearchBar(_textAreaSearchableBar);
		return panel;
      
    }



    static public void main(String[] s) {
    CodeEditorPanel a = new CodeEditorPanel();
    a.setVisible(true);

    }

	public void icerikAyarla(String string) {
		_editor.setText(string);
		
	}
}
