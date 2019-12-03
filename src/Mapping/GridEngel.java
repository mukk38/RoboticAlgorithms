package Mapping;

import java.util.ArrayList;

import Enum.Yonler;
import Ortak.OrtakSabitler;

public class GridEngel {
	
	private ArrayList<Nokta> noktalar = new ArrayList<Nokta>();
	private int engelIndeks;
	private int xGenislik;
	private int yGenislik;
	private Nokta baslamaNoktasi;
	private Nokta bitisNoktasi;
	private Yonler yon=OrtakSabitler.VARSAYILAN_YON;

	public int getEngelIndeks() {
		return engelIndeks;
	}
	public void setEngelIndeks(int engelIndeks) {
		this.engelIndeks = engelIndeks;
	}
	public ArrayList<Nokta> getNoktalar() {
		return noktalar;
	}
	public void setNoktalar(ArrayList<Nokta> noktalar) {
		this.noktalar = noktalar;
	}
	public int getyGenislik() {
		return yGenislik;
	}
	public void setyGenislik(int yGenislik) {
		this.yGenislik = yGenislik;
	}
	public int getxGenislik() {
		return xGenislik;
	}
	public void setxGenislik(int xGenislik) {
		this.xGenislik = xGenislik;
	}
	public Nokta getBaslamaNoktasi() {
		return baslamaNoktasi;
	}
	public void setBaslamaNoktasi(Nokta baslamaNoktasi) {
		this.baslamaNoktasi = baslamaNoktasi;
	}
	public Nokta getBitisNoktasi() {
		return bitisNoktasi;
	}
	public void setBitisNoktasi(Nokta bitisNoktasi) {
		this.bitisNoktasi = bitisNoktasi;
	}
	public Yonler getYon() {
		return yon;
	}
	public void setYon(Yonler yon) {
		this.yon = yon;
	}

}
