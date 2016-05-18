

public class Tag {
	private String denumire;
	private int pozitie_inceput;
	private int pozitie_final;
	private int pozitie_linie_inceput;
	private int pozitie_linie_sfarsit;

	public Tag(String denumire, int pozitie_inceput, int pozitie_final, int pozitie_linie_inceput, int pozitie_linie_sfarsit) {
		super();
		this.denumire = denumire;
		this.pozitie_inceput = pozitie_inceput;
		this.pozitie_final = pozitie_final;
		this.pozitie_linie_inceput = pozitie_linie_inceput;
		this.pozitie_linie_sfarsit = pozitie_linie_sfarsit;
	}
	
	public Tag() {
		denumire = new String();
		pozitie_inceput = 0;
		pozitie_final = 0;
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
	
}
