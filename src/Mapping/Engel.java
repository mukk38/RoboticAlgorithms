package Mapping;

import java.awt.Point;

public class Engel {
	
	private Nokta baslamaNokta;
	private Nokta	bitisNoktasi;
	private int weight;
	private int height;
	private Point ekrandakiBaslamaNoktasi;
	public Engel(){
		
	}
	public Engel(Nokta baslamaNoktasi_,int weight_,int height_,Nokta bitisNoktasi_){
		baslamaNokta=baslamaNoktasi_;
		weight=weight_;
		height=height_;
		bitisNoktasi=bitisNoktasi_;
	}
	public Point getEkrandakiBaslamaNoktasi() {
		return ekrandakiBaslamaNoktasi;
	}
	public void setEkrandakiBaslamaNoktasi(Point ekrandakiBaslamaNoktasi) {
		this.ekrandakiBaslamaNoktasi = ekrandakiBaslamaNoktasi;
	}
	public Point getEkrandakiBitisNoktasi() {
		return ekrandakiBitisNoktasi;
	}
	public void setEkrandakiBitisNoktasi(Point ekrandakiBitisNoktasi) {
		this.ekrandakiBitisNoktasi = ekrandakiBitisNoktasi;
	}
	private Point ekrandakiBitisNoktasi;
	public Nokta getBitisNoktasi() {
		return bitisNoktasi;
	}
	public void setBitisNoktasi(Nokta bitisNoktasi) {
		this.bitisNoktasi = bitisNoktasi;
	}

	public Nokta getBaslamaNoktasi() {
		return baslamaNokta;
	}
	public void setBaslamaNoktasi(Nokta nokta) {
		this.baslamaNokta = nokta;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}


}
