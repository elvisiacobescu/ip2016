import java.io.IOException;

public class Fragmentare {
	private Parsare fisier;
	private Tag[] taguri;
	
	public Fragmentare(String cale) throws IOException {
		this.taguri = new Tag[4];
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
	
	public Tag getTaguri(int pozitie) {
		return this.taguri[pozitie]; 
	}

	public void setFisier(Parsare fisier) {
		this.fisier = fisier;
	}

	public void fragmentare() throws IOException {
		for(int i=0; i<taguri.length; i++)
			taguri[i] = new Tag("",0,0,0,0);
		int contor_linii = 0;
		int contor_tag = -1;
		while(contor_linii<fisier.getContor_linii()) {
			if(fisier.getLinie(contor_linii).equals("<body>")) 
				break;
			contor_linii++;
		}
		contor_linii++;
		for(int i=contor_linii; i<fisier.getContor_linii() && !fisier.getLinie(i).trim().equals("</body>"); i++)
			for(int j=0; j<fisier.getLinie(i).length()-1; j++)
				if(fisier.getLinie(i).charAt(j)=='<' && fisier.getLinie(i).charAt(j+1)!='/')
					for(int k=0; k<fisier.getLinie(i).length(); k++)
						if(fisier.getLinie(i).charAt(k)=='>') {
							int delimitare=k;
							for(int z=k; z<fisier.getLinie(i).length(); z++)
								if(fisier.getLinie(i).charAt(z)==' ' || fisier.getLinie(i).charAt(z)=='=') {
									delimitare=z-1;
									break;
								}
							for(int a=i; a<fisier.getContor_linii() && !fisier.getLinie(a).trim().equals("</body>"); a++)
								for(int b=0; b<fisier.getLinie(a).length()-1; b++)
									if(fisier.getLinie(a).charAt(b)=='<' && fisier.getLinie(a).charAt(b+1)=='/')
										for(int c=b+1; c<fisier.getLinie(a).length(); c++)
											if(fisier.getLinie(a).charAt(c)=='>' && delimitare>=j)
												if(fisier.getLinie(i).substring(j+1, delimitare).equals(fisier.getLinie(a).substring(b+2, c))) {
													contor_tag++;
													taguri[contor_tag].setPozitieInceput(i);
													taguri[contor_tag].setPozitie_linie_inceput(j);
													taguri[contor_tag].setDenumire(fisier.getLinie(i).substring(j+1, delimitare));
													taguri[contor_tag].setPozitie_linie_sfarsit(delimitare);
													taguri[contor_tag].setPozitieFinal(a);
													taguri[contor_tag].setPozitie_linie_sfarsit_1(c);
													taguri[contor_tag].setPozitie_linie_inceput_1(b);
												}
						}
	}
}