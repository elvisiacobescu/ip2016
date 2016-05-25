package transfer.ip.IP;



public class Tag {
	private String denumire;
	private String proprietate;
	private int pozitie_inceput;
	private int pozitie_final;
	private int pozitie_linie_inceput;
	private int pozitie_linie_sfarsit;
	private int pozitie_linie_inceput1;
	private int pozitie_linie_sfarsit1;
	private int pozitie_linie_inceput_1;
	private int pozitie_linie_sfarsit_1;

	public Tag(String denumire, String proprietate, int pozitie_inceput, int pozitie_final, int pozitie_linie_inceput, int pozitie_linie_sfarsit) {
		super();
		this.denumire = denumire;
		this.proprietate = proprietate;
		this.pozitie_inceput = pozitie_inceput;
		this.pozitie_final = pozitie_final;
		this.pozitie_linie_inceput = pozitie_linie_inceput;
		this.pozitie_linie_sfarsit = pozitie_linie_sfarsit;
	}
	
	public String getProprietate() {
		return proprietate;
	}

	public void setProprietate(String proprietate) {
		this.proprietate = proprietate;
	}
	
	public Tag() {
		this.denumire = new String();
		this.proprietate = new String();
		this.pozitie_inceput = -1;
		this.pozitie_final = -1;
		this.pozitie_linie_inceput = -1;
		this.pozitie_linie_sfarsit = -1;
	}

	public void setPozitieInceput(int pozitie) {
		pozitie_inceput = pozitie;
	}

	public int getPozitieInceput() {
		return pozitie_inceput;
	}

	public void setPozitieFinal(int pozitie) {
		pozitie_final = pozitie;
	}

	public int getPozitieFinal(){
		return pozitie_final;
	}

	public void setDenumire(String sir){
		denumire = sir;
	}

	public String getDenumire(){
		return denumire;
	}

	public int getPozitie_linie_inceput() {
		return pozitie_linie_inceput;
	}

	public void setPozitie_linie_inceput(int pozitie_linie_inceput) {
		this.pozitie_linie_inceput = pozitie_linie_inceput;
	}

	public int getPozitie_linie_sfarsit() {
		return pozitie_linie_sfarsit;
	}

	public void setPozitie_linie_sfarsit(int pozitie_linie_sfarsit) {
		this.pozitie_linie_sfarsit = pozitie_linie_sfarsit;
	}
	
	public int getPozitie_linie_inceput1() {
		return pozitie_linie_inceput1;
	}

	public void setPozitie_linie_inceput1(int pozitie_linie_inceput1) {
		this.pozitie_linie_inceput1 = pozitie_linie_inceput1;
	}

	public int getPozitie_linie_sfarsit1() {
		return pozitie_linie_sfarsit1;
	}

	public void setPozitie_linie_sfarsit1(int pozitie_linie_sfarsit1) {
		this.pozitie_linie_sfarsit1 = pozitie_linie_sfarsit1;
	}
	public void setPozitie_linie_inceput_1(int pozitie_linie_inceput) {
		this.pozitie_linie_inceput_1 = pozitie_linie_inceput;
	}
	public void setPozitie_linie_sfarsit_1(int pozitie_linie_sfarsit) {
		this.pozitie_linie_sfarsit_1 = pozitie_linie_sfarsit;
	}
		public int getPozitie_linie_sfarsit_1() {
			return pozitie_linie_sfarsit_1;
		}
		public int getPozitie_linie_inceput_1() {
			return pozitie_linie_inceput_1;
		}
}
