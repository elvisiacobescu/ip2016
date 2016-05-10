import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Transfer {
	
	public static String linii_sursa[]=new String[1000];
	public static String linii_destinatie[]=new String[1000];
	public static String sursa=new String();
    public static String destinatie=new String();

	public static String getSursa() {
		return sursa;
	}

	public static void setSursa(String sursa) {
		Transfer.sursa = sursa;
	}

	public static String getDestinatie() {
		return destinatie;
	}

	public static void setDestinatie(String destinatie) {
		Transfer.destinatie = destinatie;
	}
	
	public static void Mutare(int pozitie_inceput_sursa,int pozitie_sfarsit_sursa,int pozitie_inceput_destinatie,int pozitie_sfarsit_destinatie){
		int i,j;
		for(i=pozitie_inceput_sursa,j=pozitie_inceput_destinatie;i<=pozitie_sfarsit_sursa;i++,j++)
		{
			linii_destinatie[j]=linii_sursa[i];
		}
	}
	
	public static void output(String[] linie,int contor_linii,String fisier) throws FileNotFoundException, UnsupportedEncodingException{
		int i;
		PrintWriter writer = new PrintWriter("E:\\destinatie.xml", "UTF-8");	
		for(i=0;i<contor_linii;i++)
		{
			writer.println(linie[i]);
		}
		writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		sursa="E:\\sursa.xhtml";
		destinatie="E:\\destinatie.xml";
		Parsare a=new Parsare();
		a.Parseaza(sursa);
		
		Parsare b=new Parsare();
		b.Parseaza(destinatie);
		
		linii_sursa=a.getLinie();
		linii_destinatie=b.getLinie();
		
		Mutare(6,8,9,11);
		
		output(linii_destinatie,b.getContor_linii(),destinatie);
		
		int i;
		for (i=0;i<a.getContor_linii();i++){
			System.out.println(linii_sursa[i]);
		}
		
		for (i=0;i<b.getContor_linii();i++){
			System.out.println(linii_destinatie[i]);
		}
	}

}
