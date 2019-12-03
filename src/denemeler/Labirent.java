package denemeler;

import Ortak.GlobalDegiskenler;

public class Labirent {
	private static Labirent labirent;
	static int mazeWidth,mazeHeight,startH,startW,endH,endW;
	static LabirentEleman[][] labirentler;
	
	private Labirent(){
		
	}
	
	public static Labirent getInstance(){
		if (labirent == null) {
			labirent = new Labirent();
			
		}
		return labirent;
	}
	
	public static void labirentiOlustur(int mazeWidth_,int mazeHeight_,int startH_,int startW_,int endH_,int endW_){
		mazeWidth = mazeWidth_;
		mazeHeight =mazeHeight_;
		startH = startH_;
		startW = startW_;
		endH =endH_;
		endW = endW_;
		labirentler = new LabirentEleman[mazeHeight][mazeWidth];
		for(int i=0;i<mazeHeight;i++){
			for(int j=0;j<mazeWidth;j++){
				LabirentEleman eleman = new LabirentEleman();
				labirentler[i][j]= eleman;
			}
		}
		for(int i=0;i<GlobalDegiskenler.getEngelNoktalari().size();i++){
			for(int j=0;j<GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().size();j++){
			labirentler[GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(j).getX()][GlobalDegiskenler.getEngelNoktalari().get(i).getNoktalar().get(j).getY()].setDeger(1);
			}
		}
	}
	public int getStartHeight(){
		return startH;
	}
	public int getStartWeight(){
		return startW;
	}
 public boolean	isValidLocation(int row,int col){
	 if(row<0 || row>mazeHeight-1 || col<0 ||col>mazeWidth-1){
		 return false;
	 }
	 return true;
 }
 public void setVisited(int row,int col){
	 labirentler[row][col].setVisited(1);
 }
public boolean isExplored(int row,int column){
	if(labirentler[row][column].getVisited()==1){
		return true;
	}
	return false;
}
public boolean isWall(int row,int column){
	if(labirentler[row][column].getDeger()==1){
		return true;
	}
	return false;
}
public boolean isExit(int row,int column){
	if(endH==row&&endW==column){
		return true;
	}
	return false;
}
 
}
