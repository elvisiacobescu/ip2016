
public class Tag {
	/* private String continut; */
	private String denumire;
	private int pozitieInceput;
	private int pozitieFinal;
	private int ierarhie;

	public Tag(String denumire, int pozitieInceput, int pozitieFinal, int ierarhie) {
		super();
		this.denumire = denumire;
		this.pozitieInceput = pozitieInceput;
		this.pozitieFinal = pozitieFinal;
		this.ierarhie = ierarhie;
	}

	public void setPozitieInceput(int pozitie) {
		pozitieInceput = pozitie;
	}

	public int getPozitieInceput() {
		return pozitieInceput;
	}

	public void setPozitieFinal(int pozitie) {
		pozitieFinal = pozitie;
	}

	public int getPozitieFinal(){
		return pozitieFinal;
	}

	public void setDenumire(String sir){
		denumire = sir;
	}

	public String getDenumire(){
		return denumire;
	}
	
	public int getIerarhie() {
		return ierarhie;
	}

	public void setIerarhie(int ierarhie) {
		this.ierarhie = ierarhie;
	}
}
