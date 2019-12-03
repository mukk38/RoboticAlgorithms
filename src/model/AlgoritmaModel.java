package model;

public class AlgoritmaModel {

		private int katEdilenYol;
		private long harcananZaman;
		private long hesaplananZaman;
		private boolean basariliMi;
		private double acisalHiz;
		private String rrtDeger;
		
		public AlgoritmaModel(String rrtDeger_){
			rrtDeger =rrtDeger_;
		}
		
		public AlgoritmaModel(long l,int katEdilenYol_){
			hesaplananZaman=l;
			katEdilenYol=katEdilenYol_;
		}
		public AlgoritmaModel(long l,int katEdilenYol_,double acisalHiz_){
			hesaplananZaman=l;
			harcananZaman = l;
			katEdilenYol=katEdilenYol_;
			acisalHiz = acisalHiz_;
		}
		
		
		public int getKatEdilenYol() {
			return katEdilenYol;
		}
		public void setKatEdilenYol(int katEdilenYol) {
			this.katEdilenYol = katEdilenYol;
		}
		public long getHarcananZaman() {
			return harcananZaman;
		}
		public void setHarcananZaman(long harcananZaman) {
			this.harcananZaman = harcananZaman;
		}
		public boolean isBasariOrani() {
			return basariliMi;
		}
		public void setBasariOrani(boolean basariOrani) {
			this.basariliMi = basariOrani;
		}
		public double getAcisalHiz() {
			return acisalHiz;
		}
		public void setAcisalHiz(double acisalHiz) {
			this.acisalHiz = acisalHiz;
		}
		public long getHesaplananZaman() {
			return hesaplananZaman;
		}
		public void setHesaplananZaman(long hesaplananZaman) {
			this.hesaplananZaman = hesaplananZaman;
		}
		public String getRrtDeger() {
			return rrtDeger;
		}
		public void setRrtDeger(String rrtDeger) {
			this.rrtDeger = rrtDeger;
		}
}
