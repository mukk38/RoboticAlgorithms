package dwa2;

import Mapping.Engel;
import Mapping.GridEngel;
import Mapping.Nokta;
import Ortak.GlobalDegiskenler;
import Ortak.OrtakSabitler;

public class GlobalDegisken {
public static 	int obstacle[][] = { { 0, 2 },
			{ 4, 2 },
			{ 4, 4 },
			{ 5, 4 },
			{ 5, 5 },
			{ 5, 6 },
			{ 5, 9 },
			{ 8, 8 },
			{ 8, 9 },
			{ 7, 9 },
			{ 6, 5 },
			{ 6, 3 },
			{ 6, 8 },
			{ 6, 7 },
			{ 7, 4 },
			{ 9, 8 },
			{ 9, 11 },
			{ 9, 6 } };

public static void obstacleDegisti(){
	engellerDegisti(GlobalDegiskenler.GetGridPanel().getHareketliEngelHedefleri());
//	int sayi = 0;
//	for(GridEngel engel :GlobalDegiskenler.getHareketliEngelNoktalari()){
//		for(Nokta nokta : engel.getNoktalar()){
//			sayi ++;
//		}
//	}
//	obstacle = new int[sayi][2];
//	sayi = 0;
//	for(GridEngel engel :GlobalDegiskenler.getHareketliEngelNoktalari()){
//		for(Nokta nokta : engel.getNoktalar()){
//			int a[] = {nokta.getX(),nokta.getY()};
//			obstacle[sayi] =a;
//			sayi ++;
//		}
//	}
	
}

public static void engellerDegisti(Engel[] hareketliEngelHedefleri) {

	int ofset=15;
	int sayi = 0;
	for(Engel engel :hareketliEngelHedefleri){
		if(engel!=null &&engel.getBaslamaNoktasi()!=null&& engel.getBitisNoktasi()!=null){
		Nokta baslamaNoktasi = engel.getBaslamaNoktasi();
		Nokta bitisNoktasi = engel.getBitisNoktasi();
		int baslamaNoktasiX,bitisNoktasiX,baslamaNoktasiY,bitisNoktasiY;
		if(baslamaNoktasi.getX()<=bitisNoktasi.getX()) {
			baslamaNoktasiX = baslamaNoktasi.getX()-ofset;
			bitisNoktasiX = bitisNoktasi.getX()+ofset;
		}else {
			baslamaNoktasiX=bitisNoktasi.getX()-ofset;
			bitisNoktasiX=baslamaNoktasi.getX()+ofset;
		}
		if(baslamaNoktasi.getY()<=bitisNoktasi.getY()) {
			baslamaNoktasiY = baslamaNoktasi.getY()-ofset;
			bitisNoktasiY = bitisNoktasi.getY()+ofset;
		}else {
			baslamaNoktasiY = bitisNoktasi.getY()-ofset;
			bitisNoktasiY = baslamaNoktasi.getY()+ofset;
		}
		sayi=sayi+ 2*(bitisNoktasiY-baslamaNoktasiY)+2*(bitisNoktasiX-baslamaNoktasiX);
		}
	}
	obstacle = new int[sayi][2];
	sayi = 0;
	for(Engel engel :hareketliEngelHedefleri){
		if(engel!=null &&engel.getBaslamaNoktasi()!=null&& engel.getBitisNoktasi()!=null){
			int baslamaNoktasiX = engel.getBaslamaNoktasi().getX()-ofset;
			int baslamaNoktasiY = engel.getBaslamaNoktasi().getY()-ofset;
			int bitisNoktasiX = engel.getBitisNoktasi().getX()+ofset;
			int bitisNoktasiY = engel.getBitisNoktasi().getY()+ofset;
			for(int i =baslamaNoktasiX;i<bitisNoktasiX;i++) {
				int a[] = {i,baslamaNoktasiY};
				obstacle[sayi] =a;
				sayi ++;
				int a1[] = {i,bitisNoktasiY};
				obstacle[sayi] =a1;
				sayi ++;
				
			}
			for(int i =baslamaNoktasiY;i<bitisNoktasiY;i++) {
				int a[] = {baslamaNoktasiX,i};
				obstacle[sayi] =a;
				sayi ++;
				int a1[] = {bitisNoktasiX,i};
				obstacle[sayi] =a1;
				sayi ++;
				
			}
			
		
	}
	}
}
}
