package transfer.ip.IP;



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
		String aux2;
		for(i=pozitie_inceput_sursa+1,j=pozitie_inceput_destinatie+1;i<pozitie_sfarsit_sursa;i++,j++)
		{   int ok=-1;
			for(int t=0;t<linii_destinatie[j].length();t++){
				if(linii_destinatie[j].charAt(t)=='<')
					for(int z=t;z<linii_destinatie[j].length();z++){
						if(linii_destinatie[j].charAt(z)=='>')
							{aux2=linii_sursa[i].substring(0,t)+linii_destinatie[j].substring(t,z+1)+linii_sursa[i].substring(t,linii_sursa[i].length());
						   // System.out.println(aux2);
							linii_destinatie[j]=aux2;
							ok=j;}
					}
			}	
			if(ok!=j)
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
			aux=taguri[contor_umplere].charAt(0)+"/"+taguri[contor_umplere].substring(1, taguri[contor_umplere].length());
			contor_umplere++;
			linii_noi[i+pozitie_sfarsit+contor]=aux;
		}
		return linii_noi;
	}
	
	public static void main(String[] args) throws IOException {
		sursa="C:\\Users\\Cozmin\\Desktop\\IP\\sursa.xhtml";
		destinatie="C:\\Users\\Cozmin\\Desktop\\IP\\destinatie.xml";
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
		
	for(int k=0;k<pozitie_inceput_sursa.length;k++){
			if((pozitie_sfarsit_sursa[k]-pozitie_inceput_sursa[k])==(pozitie_sfarsit_destinatie[k]-pozitie_inceput_destinatie[k])){
			Mutare(pozitie_inceput_sursa[k],pozitie_sfarsit_sursa[k],pozitie_inceput_destinatie[k],pozitie_sfarsit_destinatie[k],pozitie_inceput_linie_destinatie[k],pozitie_final_linie_destinatie[k],pozitie_inceput_linie_sursa[k],pozitie_final_linie_sursa[k]);
			}
			else if((pozitie_sfarsit_sursa[k]-pozitie_inceput_sursa[k])<(pozitie_sfarsit_destinatie[k]-pozitie_inceput_destinatie[k])){
				int diferenta=(pozitie_sfarsit_destinatie[k]-pozitie_inceput_destinatie[k])-(pozitie_sfarsit_sursa[k]-pozitie_inceput_sursa[k]);
			    for(int j=pozitie_sfarsit_destinatie[k];j>pozitie_sfarsit_destinatie[k]-diferenta;j--){
			    	linii_destinatie[j]="";
			    }
			    Mutare(pozitie_inceput_sursa[k],pozitie_sfarsit_sursa[k],pozitie_inceput_destinatie[k],pozitie_sfarsit_destinatie[k]-diferenta,pozitie_inceput_linie_destinatie[k],pozitie_final_linie_destinatie[k],pozitie_inceput_linie_sursa[k],pozitie_final_linie_sursa[k]);
			}
			else{
				int diferenta=(pozitie_sfarsit_sursa[k]-pozitie_inceput_sursa[k])-(pozitie_sfarsit_destinatie[k]-pozitie_inceput_destinatie[k]);
			    for(int j=linii_destinatie.length-1;j>pozitie_sfarsit_destinatie[k]+diferenta;j--){
			    	linii_destinatie[j]=linii_destinatie[j-diferenta];
			    }
			    Mutare(pozitie_inceput_sursa[k],pozitie_sfarsit_sursa[k],pozitie_inceput_destinatie[k],pozitie_sfarsit_destinatie[k]+diferenta,pozitie_inceput_linie_destinatie[k],pozitie_final_linie_destinatie[k],pozitie_inceput_linie_sursa[k],pozitie_final_linie_sursa[k]);
			}
	    }
		
		

		
		
		String[] aux = new String[10];
		int contor=0;
		for(int i=0; i<a.getTaguri().length; i++)
			if(a.getTaguri(i).getPozitieInceput()<pozitie_inceput_sursa[0] && 
					a.getTaguri(i).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
				aux[contor]='<'+a.getTaguri(i).getDenumire()+'>';
				contor++;
			}
		linii_destinatie=tagare(linii_destinatie, aux, pozitie_inceput_destinatie[0]-1, pozitie_sfarsit_destinatie[0],contor);
		for(int i=0; i<b.getTaguri().length; i++){
			String auxiliar;
			if(b.getTaguri(i).getPozitieInceput()>pozitie_inceput_destinatie[0] && b.getTaguri(i).getPozitieInceput() < pozitie_sfarsit_destinatie[0] && b.getTaguri(i).getPozitieFinal() > pozitie_sfarsit_destinatie[0]){
				auxiliar=linii_destinatie[b.getTaguri(i).getPozitieInceput()+contor].substring(0,b.getTaguri(i).getPozitie_linie_inceput());
				for(int j=a.getTaguri().length-1; j>=0; j--)
					if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
							a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
						auxiliar=auxiliar+ "</"+a.getTaguri(j).getDenumire()+'>';
					}
				auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieInceput()+contor].substring(b.getTaguri(i).getPozitie_linie_inceput(),b.getTaguri(i).getPozitie_linie_sfarsit()+1);
				for(int j=0; j<a.getTaguri().length;j++)
					if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
							a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
						auxiliar=auxiliar+ '<'+a.getTaguri(j).getDenumire()+'>';
					}
				auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieInceput()+contor].substring(b.getTaguri(i).getPozitie_linie_sfarsit()+1);
				
				linii_destinatie[b.getTaguri(i).getPozitieInceput()+contor]=auxiliar;
			}
			else if (b.getTaguri(i).getPozitieInceput()<pozitie_inceput_destinatie[0] && b.getTaguri(i).getPozitieFinal() < pozitie_sfarsit_destinatie[0] &&b.getTaguri(i).getPozitieFinal() > pozitie_inceput_destinatie[0]){
				auxiliar=linii_destinatie[b.getTaguri(i).getPozitieFinal()+contor].substring(0,b.getTaguri(i).getPozitie_linie_inceput_1());
				for(int j=a.getTaguri().length-1; j>=0; j--)
					if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
							a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
						auxiliar=auxiliar+ "</"+a.getTaguri(j).getDenumire()+'>';
					}
			auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieFinal()+contor].substring(b.getTaguri(i).getPozitie_linie_inceput_1(),b.getTaguri(i).getPozitie_linie_sfarsit_1()+1);
			for(int j=0; j<a.getTaguri().length;j++)
				if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
						a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
					auxiliar=auxiliar+ '<'+a.getTaguri(j).getDenumire()+'>';
				}
			auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieFinal()+contor].substring(b.getTaguri(i).getPozitie_linie_sfarsit_1()+1);
			linii_destinatie[b.getTaguri(i).getPozitieFinal()+contor]=auxiliar;
			}
			
		}
		
		
		output(linii_destinatie,b.getFisier().getContor_linii(),destinatie);
	}

}
