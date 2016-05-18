

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Transfer {
	
	private static String linii_sursa[]=new String[1000];
	private static String linii_destinatie[]=new String[1000];
	private static String sursa=new String();
    private static String destinatie=new String();

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
	
	public static void Mutare(int pozitie_inceput_sursa,int pozitie_sfarsit_sursa,int pozitie_inceput_destinatie,int pozitie_sfarsit_destinatie,int pozitie_inceput_linie_destinatie,int pozitie_final_linie_destinatie,int pozitie_inceput_linie_sursa,int pozitie_final_linie_sursa){
		int i,j;
		String linie1,linie2;
		if(pozitie_inceput_sursa==pozitie_sfarsit_sursa){ // fragmentul comun este pe aceeasi linie
		
		linie1=linii_destinatie[pozitie_inceput_destinatie].substring(0,pozitie_inceput_linie_destinatie)+linii_sursa[pozitie_inceput_sursa].substring(pozitie_inceput_linie_sursa,pozitie_final_linie_sursa+1)+linii_destinatie[pozitie_inceput_destinatie].substring(pozitie_final_linie_destinatie+1,linii_destinatie[pozitie_inceput_destinatie].length());
		linii_destinatie[pozitie_inceput_destinatie]=linie1;
		return;
		}
		
		linie1=linii_destinatie[pozitie_inceput_destinatie].substring(0,pozitie_inceput_linie_destinatie)+linii_sursa[pozitie_inceput_sursa].substring(pozitie_inceput_linie_sursa,linii_sursa[pozitie_inceput_sursa].length());
		linii_destinatie[pozitie_inceput_destinatie]=linie1;
		
		linie2=linii_sursa[pozitie_sfarsit_sursa].substring(0,pozitie_final_linie_sursa)+linii_destinatie[pozitie_sfarsit_destinatie].substring(pozitie_final_linie_destinatie,linii_destinatie[pozitie_sfarsit_destinatie].length());
		linii_destinatie[pozitie_sfarsit_destinatie]=linie2;
		
		for(i=pozitie_inceput_sursa+1,j=pozitie_inceput_destinatie+1;i<pozitie_sfarsit_sursa;i++,j++)
		{
			linii_destinatie[j]=linii_sursa[i];
		}
	}
	
	public static void output(String[] linie,int contor_linii,String fisier) throws FileNotFoundException, UnsupportedEncodingException{
		int i;
		PrintWriter writer = new PrintWriter(fisier, "UTF-8");	
		for(i=0;i<contor_linii;i++)
		{
			writer.println(linie[i]);
		}
		writer.close();
	}
	
	public static String[] tagare(String[] linii, String[] taguri, int pozitie_inceput, int pozitie_sfarsit, int contor) {
		String[] linii_noi = new String[linii.length+2*contor];
		for(int i=0; i<=pozitie_inceput; i++) 
			linii_noi[i]=linii[i];
		for(int i=pozitie_inceput+1; i<linii.length; i++) 
			linii_noi[i+contor]=linii[i];
		int contor_umplere=0;
		for(int i=pozitie_inceput+1; i<=pozitie_inceput+contor; i++)
			linii_noi[i]=taguri[contor_umplere++];
		for(int i=pozitie_sfarsit; i<linii.length; i++)
			linii_noi[i+2*contor]=linii[i];
		contor_umplere=0;
		String aux = new String();
		for(int i=contor; i>0; i--) {
			aux=taguri[contor_umplere].charAt(0)+'/'+taguri[contor_umplere].substring(1, taguri[contor_umplere].length());
			contor_umplere++;
			linii_noi[i+pozitie_sfarsit+contor]=aux;
		}
		return linii_noi;
	}
	
	public static void main(String[] args) throws IOException {
		sursa="C:\\Users\\home\\Desktop\\New folder\\sursa.xhtml";
		destinatie="C:\\Users\\home\\Desktop\\New folder\\destinatie.xml";
		Fragmentare a=new Fragmentare(sursa);
		Fragmentare b=new Fragmentare(destinatie);
		linii_sursa=a.getFisier().getLinie();
		linii_destinatie=b.getFisier().getLinie();
		
		int[] pozitie_inceput_sursa=new int[100];
		int[] pozitie_sfarsit_sursa=new int[100];
		int[] pozitie_inceput_destinatie=new int[100];
		int[] pozitie_sfarsit_destinatie=new int[100];
		int[] pozitie_inceput_linie_destinatie=new int[100];
		int[] pozitie_final_linie_destinatie=new int[100];
		int[] pozitie_inceput_linie_sursa=new int[100];
		int[] pozitie_final_linie_sursa=new int[100];
		
		/*
		pozitie_inceput_sursa[0]=6;
		pozitie_sfarsit_sursa[0]=6;
		pozitie_inceput_destinatie[0]=9;
		pozitie_sfarsit_destinatie[0]=9;
		pozitie_inceput_linie_destinatie[0]=3;
		pozitie_final_linie_destinatie[0]=5;
		pozitie_inceput_linie_sursa[0]=5;
		pozitie_final_linie_sursa[0]=7;
		
		pozitie_inceput_sursa[1]=7;
		pozitie_sfarsit_sursa[1]=7;
		pozitie_inceput_destinatie[1]=10;
		pozitie_sfarsit_destinatie[1]=10;
		pozitie_inceput_linie_destinatie[1]=4;
		pozitie_final_linie_destinatie[1]=6;
		pozitie_inceput_linie_sursa[1]=6;
		pozitie_final_linie_sursa[1]=8;
		*/
		pozitie_inceput_sursa[0]=9;
		pozitie_sfarsit_sursa[0]=12;
		pozitie_inceput_destinatie[0]=9;
		pozitie_sfarsit_destinatie[0]=12;
		pozitie_inceput_linie_destinatie[0]=5;
		pozitie_final_linie_destinatie[0]=7;
		pozitie_inceput_linie_sursa[0]=3;
		pozitie_final_linie_sursa[0]=5;
		
		for(int k=0;k<pozitie_inceput_sursa.length;k++)
			Mutare(pozitie_inceput_sursa[k],pozitie_sfarsit_sursa[k],pozitie_inceput_destinatie[k],pozitie_sfarsit_destinatie[k],pozitie_inceput_linie_destinatie[k],pozitie_final_linie_destinatie[k],pozitie_inceput_linie_sursa[k],pozitie_final_linie_sursa[k]);
		
		
		System.out.println("Taguri sursa:");
		for(int i=0; i<a.getTaguri().length; i++)
			System.out.println("Pozitii pe linie: ("+a.getTaguri(i).getPozitie_linie_inceput()+","
					+a.getTaguri(i).getPozitie_linie_sfarsit()+") Linie inceput: "+a.getTaguri(i).getPozitieInceput()
					+" Denumire: <"+a.getTaguri(i).getDenumire()+"> Linie sfarsit: "+a.getTaguri(i).getPozitieFinal());
		System.out.println();
		String[] aux = new String[10];
		int contor=0;
		for(int i=0; i<a.getTaguri().length; i++)
			if(a.getTaguri(i).getPozitieInceput()<pozitie_inceput_sursa[0] && 
					a.getTaguri(i).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
				aux[contor]="+<"+a.getTaguri(i).getDenumire()+'>';
				contor++;
			}
		linii_destinatie=tagare(linii_destinatie, aux, pozitie_inceput_destinatie[0]-1, pozitie_sfarsit_destinatie[0],contor);
		output(linii_destinatie,b.getFisier().getContor_linii(),destinatie);
	}

}
