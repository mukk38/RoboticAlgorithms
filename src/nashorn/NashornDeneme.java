package nashorn;

import java.awt.Component;
import java.awt.Container;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.*;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import Ortak.OrtakMetotlar;

public class NashornDeneme extends JDialog {
	private NashornDeneme nashornDeneme;
	private JPanel		yaziYazimPanel;
	private JTextArea	yaziYazmaAlani;
	
	public NashornDeneme(){
		nashornDeneme = this;
		OrtakMetotlar.setBilesenBoyutu(nashornDeneme, 250, 250);
		nashornDeneme.setContentPane(yaziYazimPanel());
	}

	private JPanel yaziYazimPanel() {
		if (yaziYazimPanel == null) {
			yaziYazimPanel = new JPanel();
			yaziYazimPanel.add(getYaziYazmaAlani());
		}

		return yaziYazimPanel;
	}

	private JTextArea getYaziYazmaAlani() {
		if (yaziYazmaAlani == null) {
			yaziYazmaAlani = new JTextArea();
			OrtakMetotlar.setBilesenBoyutu(yaziYazmaAlani, 200, 200);
		}

		return yaziYazmaAlani;
	}

	public static void main(String[] args) {

		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
//		try {
//			engine.eval("print('Hello World!');");
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	//GERI AC	
//		try {
//			engine.eval(new FileReader(".\\external\\conf\\script.js"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		try {
//			ScriptObjectMirror  s =(ScriptObjectMirror) engine.eval(new FileReader(".\\external\\conf\\script.js"));
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (ScriptException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		try {
			engine.eval("function objProvider(){return {a:1, b:'2','c': true,'d': {'e':[],'f':['1',{'g':45}]}};}");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Object scriptObj = null;
		try {
			scriptObj = ((Invocable) engine).invokeFunction("objProvider");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Object javaObj = OrtakMetotlar.convertIntoJavaObject(scriptObj);
	  
	    System.out.println(javaObj);
	//	Invocable invocable = (Invocable) engine;

//		Object result = null;
//		try {
//			result = invocable.invokeFunction("fun1", "Peter Parker");
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
//		System.out.println(result.getClass());
	}
	public static void getProduct(ScriptObjectMirror result) {
		  System.out.println(result.get("name") + ": " + result.get("valueOfGoods"));
		}

}
