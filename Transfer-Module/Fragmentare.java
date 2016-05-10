package transfer_nou;

import java.io.IOException;

public class Fragmentare {
	private Parsare fisier;
	
	public Fragmentare(String cale) throws IOException {
		fisier = new Parsare();
		fisier.Parseaza(cale);
	}
	
	public Parsare getFisier() {
		return fisier;
	}
	
	public Tag[] fragmentare() throws IOException {
		Tag[] output = new Tag[10];
		for(int i=0; i<output.length; i++)
			output[i] = new Tag(null,0,0);
		int contor_linii = 0;
		int contor_tag = -1;
		String[] nume_tag = new String[output.length];
		while(contor_linii<fisier.getContor_linii()) {
			if(fisier.getLinie(contor_linii).startsWith("<") && fisier.getLinie(contor_linii).endsWith(">") && fisier.getLinie(contor_linii).charAt(1)!='/') {
				contor_tag++;
				output[contor_tag].setDenumire(fisier.getLinie(contor_linii));
				output[contor_tag].setPozitieInceput(contor_linii);
				int delimitator;
				boolean gasit=false;
				for(delimitator=0; delimitator<fisier.getLinie(contor_linii).length() && !gasit; delimitator++) {
					if(fisier.getLinie(contor_linii).charAt(delimitator)==' ' || fisier.getLinie(contor_linii).charAt(delimitator)=='>')
						gasit=true;
				}
				if(gasit)
					nume_tag[contor_tag]=fisier.getLinie(contor_linii).substring(1, delimitator-1);
				else nume_tag[contor_tag]=fisier.getLinie(contor_linii);
			}
			else if(fisier.getLinie(contor_linii).startsWith("</") && fisier.getLinie(contor_linii).endsWith(">")) {
				for(int pozitie_tag=contor_tag; pozitie_tag>=0; pozitie_tag--) {
					System.out.println(fisier.getLinie(contor_linii).substring(2, fisier.getLinie(contor_linii).length()-1)+" da");
					System.out.println(nume_tag[pozitie_tag]+" NU");
					if(fisier.getLinie(contor_linii).substring(2, fisier.getLinie(contor_linii).length()-1).equals(nume_tag[pozitie_tag])) {
						output[pozitie_tag].setPozitieFinal(contor_linii);
						System.out.println(contor_linii);
						break;
					}
				}
			}
			System.out.println(contor_linii);
			contor_linii++;
		}
		return output;
	}
	
	public static void main(String[] args) throws IOException {
		Fragmentare rezolvare = new Fragmentare("C:\\sursa.xhtml");
		//for(int i=0; i<rezolvare.getFisier().getContor_linii(); i++)
		//	System.out.println(rezolvare.getFisier().getLinie(i));
		Tag[] output = rezolvare.fragmentare();
		System.out.println();
		for(int i=0; i<output.length; i++)
			System.out.println(output[i].getPozitieInceput()+" "+output[i].getDenumire()+" "+output[i].getPozitieFinal());
	}
}
