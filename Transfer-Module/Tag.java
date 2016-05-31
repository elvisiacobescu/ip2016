package transfer.ip.IP;

public class Tag {
	private String denumire;
	private String proprietate;
	private int pozitie_inceput;
	private int pozitie_final;
	private int pozitie_inceput_linie_inceput;
	private int pozitie_sfarsit_linie_sfarsit;
	private int pozitie_sfarsit_linie_inceput;
	private int pozitie_inceput_linie_sfarsit;

	public Tag(String denumire, String proprietate, int pozitie_inceput, int pozitie_final, int pozitie_inceput_linie_inceput, int pozitie_sfarsit_linie_sfarsit, int pozitie_sfarsit_linie_inceput, int pozitie_inceput_linie_sfarsit) {
		super();
		this.denumire = denumire;
		this.proprietate = proprietate;
		this.pozitie_inceput = pozitie_inceput;
		this.pozitie_final = pozitie_final;
		this.pozitie_inceput_linie_inceput = pozitie_inceput_linie_inceput;
		this.pozitie_sfarsit_linie_sfarsit = pozitie_sfarsit_linie_sfarsit;
		this.pozitie_sfarsit_linie_inceput = pozitie_sfarsit_linie_inceput;
		this.pozitie_inceput_linie_sfarsit = pozitie_inceput_linie_sfarsit;
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
		this.pozitie_inceput_linie_inceput = -1;
		this.pozitie_sfarsit_linie_sfarsit = -1;
		this.pozitie_sfarsit_linie_inceput = -1;
		this.pozitie_inceput_linie_sfarsit = -1;
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

	public int getPozitie_inceput_linie_inceput() {
		return pozitie_inceput_linie_inceput;
	}

	public void setPozitie_inceput_linie_inceput(int pozitie_inceput_linie_inceput) {
		this.pozitie_inceput_linie_inceput = pozitie_inceput_linie_inceput;
	}

	public int getPozitie_sfarsit_linie_sfarsit() {
		return pozitie_sfarsit_linie_sfarsit;
	}

	public void setPozitie_sfarsit_linie_sfarsit(int pozitie_sfarsit_linie_sfarsit) {
		this.pozitie_sfarsit_linie_sfarsit = pozitie_sfarsit_linie_sfarsit;
	}
	
	public int getPozitie_sfarsit_linie_inceput() {
		return pozitie_sfarsit_linie_inceput;
	}

	public void setPozitie_sfarsit_linie_inceput(int pozitie_sfarsit_linie_inceput) {
		this.pozitie_sfarsit_linie_inceput = pozitie_sfarsit_linie_inceput;
	}

	public int getPozitie_inceput_linie_sfarsit() {
		return pozitie_inceput_linie_sfarsit;
	}

	public void setPozitie_inceput_linie_sfarsit(int pozitie_inceput_linie_sfarsit) {
		this.pozitie_inceput_linie_sfarsit = pozitie_inceput_linie_sfarsit;
	}
}
