import java.io.IOException;

public class Fragmentare {
	private Parsare fisier;
	private Tag[] taguri;
	
	public Fragmentare(String cale) throws IOException {
		this.taguri = new Tag[1000];
		this.fisier = new Parsare();
		this.fisier.Parseaza(cale);
		this.fragmentare();
	}
	
	public Parsare getFisier() {
		return fisier;
	}
	
	public Tag[] getTaguri() {
		return taguri;
	}

	public void setTaguri(Tag[] taguri) {
		this.taguri = taguri;
	}
	
	public void setTaguri(Tag taguri, int pozitie) {
		this.taguri[pozitie] = taguri;
	}
	
	public Tag getTaguri(int pozitie) {
		return this.taguri[pozitie]; 
	}

	public void setFisier(Parsare fisier) {
		this.fisier = fisier;
	}

	public void fragmentare() {
		for(int i=0; i<taguri.length; i++)
			taguri[i] = new Tag();
		String[] deschidere = new String[taguri.length];
		int[] pozitie_inceput_deschis = new int[taguri.length]; 
		int[] pozitie_linie_deschis = new int[taguri.length];
		String[] inchidere = new String[taguri.length];
		int[] pozitie_inceput_inchis = new int[taguri.length];
		int[] pozitie_linie_inchis = new int[taguri.length];
		boolean tag_deschis = false, tag_inchis = false;
		int tag_start_deschidere = 0, tag_start_inchidere = 0, contor_tag_deschis = 0, contor_tag_inchis = 0;
		try {
		for(int i=0; i<fisier.getContor_linii(); i++)
			for(int j=0; j<fisier.getLinie(i).length(); j++)
				if(fisier.getLinie(i).charAt(j)=='<' && fisier.getLinie(i).charAt(j+1)!='/') {
					tag_deschis = true;
					tag_start_deschidere = j+1;
				}
				else if(fisier.getLinie(i).charAt(j)=='>' && tag_deschis) {
					tag_deschis = false;
					pozitie_inceput_deschis[contor_tag_deschis]=tag_start_deschidere;
					pozitie_linie_deschis[contor_tag_deschis]=i;
					deschidere[contor_tag_deschis++]=fisier.getLinie(i).substring(tag_start_deschidere, j);
				}
				else if(fisier.getLinie(i).charAt(j)=='<' && fisier.getLinie(i).charAt(j+1)=='/') {
					tag_inchis = true;
					tag_start_inchidere = j+2;
				}
				else if(fisier.getLinie(i).charAt(j)=='>' && tag_inchis) {
					tag_inchis = false;
					pozitie_inceput_inchis[contor_tag_inchis]=tag_start_inchidere;
					pozitie_linie_inchis[contor_tag_inchis]=i;
					inchidere[contor_tag_inchis++]=fisier.getLinie(i).substring(tag_start_inchidere, j);
				}
		}
		catch(IndexOutOfBoundsException e) {}
		Tag[] tag_aux = new Tag[taguri.length];
		for(int i=0; i<tag_aux.length; i++)
			tag_aux[i]=new Tag("","",0,0,0,0);
		int contor_tag = 0;
		int[] aux_deschidere = new int[taguri.length];
		int[] aux_inchidere = new int[taguri.length];
		for(int i=0; i<aux_deschidere.length; i++) 
			aux_deschidere[i]=0;
		for(int i=0; i<aux_inchidere.length; i++) 
			aux_inchidere[i]=0;
		for(int i=0; i<contor_tag_deschis; i++)
			for(int j=i+1; j<contor_tag_deschis-1; j++)
				if(deschidere[i].equals(deschidere[j]))
					aux_deschidere[i]++;
		for(int i=contor_tag_deschis-2; i>=0; i--)
			if(deschidere[i]==deschidere[contor_tag_deschis-1]) {
				aux_deschidere[contor_tag_deschis-1]=aux_deschidere[i]+1;
				break;
			}
		for(int i=0; i<contor_tag_inchis; i++)
			for(int j=i+1; j<contor_tag_inchis-1; j++)
				if(inchidere[i].equals(inchidere[j]))
						aux_inchidere[i]++;
		for(int i=0; i<contor_tag_deschis; i++)
			if(!"body".equals(deschidere[i]) && !"xhtml".equals(deschidere[i]) && !"xml".equals(deschidere[i]) && !"head".equals(deschidere[i]) && !"title".equals(deschidere[i]))
				for(int j=0; j<contor_tag_inchis; j++)
					if(!"body".equals(inchidere[j]) && !"xhtml".equals(inchidere[j]) && !"xml".equals(inchidere[j]) && !"head".equals(inchidere[j]) && !"title".equals(inchidere[j])) {
						String[] aux_spargere = new String[2];
						aux_spargere=deschidere[i].split(" ",2);
						if(aux_spargere[0].equals(inchidere[j]) && aux_deschidere[i]==aux_inchidere[j] ) {
							tag_aux[contor_tag].setDenumire(inchidere[j]);
							if(aux_spargere.length==2)
								tag_aux[contor_tag].setProprietate(aux_spargere[1]);
							tag_aux[contor_tag].setPozitie_linie_inceput(pozitie_inceput_deschis[i]-1);
							tag_aux[contor_tag].setPozitie_linie_inceput1(pozitie_inceput_deschis[i]+deschidere[i].length()+1);
							tag_aux[contor_tag].setPozitie_linie_sfarsit(pozitie_inceput_inchis[j]-2);
							tag_aux[contor_tag].setPozitie_linie_sfarsit1(pozitie_inceput_inchis[j]+inchidere[j].length()+2);
							tag_aux[contor_tag].setPozitieInceput(pozitie_linie_deschis[i]);
							tag_aux[contor_tag].setPozitieFinal(pozitie_linie_inchis[j]);
							contor_tag++;
							break;
						}
					}
		setTaguri(tag_aux);
	}
}