package Main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import konfigurasyon.KONFIGURASYON;
import Ortak.OrtakMetotlar;

public final class KonfigurasyonIslemler {
	private static KonfigurasyonIslemler konfigurasyonIslemler;
 private KonfigurasyonIslemler(){
	 
 }
 public static KonfigurasyonIslemler getInstance(){
	 if (konfigurasyonIslemler == null) {
		konfigurasyonIslemler = new KonfigurasyonIslemler();
		
	}
	 return konfigurasyonIslemler;
 }
 public void konfigurasyonIslemleriYap(){
	KONFIGURASYON konfigurasyon = null;
	try {
		konfigurasyon = (KONFIGURASYON) KONFIGURASYON.unmarshal(new BufferedReader(new InputStreamReader(new FileInputStream("./external/conf/Konfigurasyon.xml"),"UTF-8")));
	} catch (MarshalException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ValidationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int en = konfigurasyon.getYazilimGorunum().getYazilimEnBoy(0).getEn();
	OrtakMetotlar.setAnaEkranXBoy(en);
	int boy = konfigurasyon.getYazilimGorunum().getYazilimEnBoy(0).getBoy();
	OrtakMetotlar.setAnaEkranYBoy(boy);
	int gridXsayisi  =konfigurasyon.getYazilimGorunum().getYazilimGrid(0).getXekseniElemanSayisi();
	OrtakMetotlar.setGridXsayisi(gridXsayisi);
	int gridYSayisi = konfigurasyon.getYazilimGorunum().getYazilimGrid(0).getYekseniElemanSayisi();
	OrtakMetotlar.setGridYsayisi(gridYSayisi);

 }
}
