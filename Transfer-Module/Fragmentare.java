package transfer.ip.IP;

import java.io.IOException;

public class Fragmentare {
	private Parsare fisier;
	private Tag[] taguri;
	
	public Fragmentare(String cale) throws IOException {
		this.taguri = new Tag[100];
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
		int contor_tag = -1;
		int contor_linii = 0;
		while(contor_linii<fisier.getContor_linii()) {
			if("<body>".equals(fisier.getLinie(contor_linii))) 
				break;
			contor_linii++;
		}
		contor_linii++;
		for(int i=contor_linii; i<fisier.getContor_linii(); i++)
			for(int j=0; j<fisier.getLinie(i).length()-1; j++)
				if(fisier.getLinie(i).charAt(j)=='<' && fisier.getLinie(i).charAt(j+1)!='/')
					for(int k=j+1; k<fisier.getLinie(i).length(); k++)
						if(fisier.getLinie(i).charAt(k)=='>') {
							String[] nume_tag = new String[2];
							if(fisier.getLinie(i).indexOf(' ')>=0) {
								nume_tag = fisier.getLinie(i).substring(j, k).split(" ", 2);
								nume_tag[0]=nume_tag[0].substring(1, nume_tag[0].length());
								if(nume_tag.length>1)
									if(nume_tag[1].indexOf('>')>=-0)
										nume_tag[1]=nume_tag[1].substring(0, nume_tag[1].indexOf('>'));
							}
							else {
								nume_tag[0]=fisier.getLinie(i).substring(j+1, k);
								nume_tag[1]="";
							}
							for(int a=i; a<fisier.getContor_linii(); a++) {
								int b=0;
								if(a==0 && k+1<fisier.getLinie(i).length())
									b=k+1;
								for(; b<fisier.getLinie(a).length()-1; b++)
									if(fisier.getLinie(a).charAt(b)=='<' && fisier.getLinie(a).charAt(b+1)=='/')
										for(int c=b+1; c<fisier.getLinie(a).length(); c++)
											if(fisier.getLinie(a).charAt(c)=='>') 
												if(nume_tag[0].trim().equals(fisier.getLinie(a).substring(b+2, c).trim()))
														if(!"body".equals(nume_tag[0]) || !"head".equals(nume_tag[0])) {
															contor_tag++;
															taguri[contor_tag].setPozitieInceput(i);
															taguri[contor_tag].setPozitie_linie_inceput(j);
															taguri[contor_tag].setDenumire(nume_tag[0]);
															if(nume_tag.length>1)
																taguri[contor_tag].setProprietate(nume_tag[1]);
															taguri[contor_tag].setPozitie_linie_sfarsit(k);
															taguri[contor_tag].setPozitieFinal(a);
															taguri[contor_tag].setPozitie_linie_sfarsit1(c);
															taguri[contor_tag].setPozitie_linie_inceput1(b);
														}
							}
						}
		/*
		for(int i=0; i<contor_tag-1; i++)
			for(int j=i+1; j<contor_tag; j++)
				if(taguri[i].getDenumire().equals(taguri[j].getDenumire())) {
					if(taguri[i].getPozitieInceput()==taguri[j].getPozitieInceput())
						if(taguri[i].getPozitie_linie_inceput()==taguri[j].getPozitie_linie_inceput()) {
							contor_tag=stergeTag(j);
							j--;
						}
				}
				else if(taguri[i].getPozitieFinal()==taguri[j].getPozitieFinal())
						if(taguri[i].getPozitie_linie_sfarsit()==taguri[j].getPozitie_linie_sfarsit()) {
							contor_tag=stergeTag(j);
							j--;
						}*/
	}
}