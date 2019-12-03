package Mapping;

public class Robot {
	private Nokta baslamaNoktasi;
	private Nokta bitisNoktasi;
	private Integer robotIndeks;
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
	public Integer getRobotIndeks() {
		return robotIndeks;
	}
	public void setRobotIndeks(Integer robotIndeks) {
		this.robotIndeks = robotIndeks;
	}

	public Robot(int indeks){
		robotIndeks = indeks;
	}
	

}
