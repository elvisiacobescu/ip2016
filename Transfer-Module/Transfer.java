package transfer.ip.IP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Transfer {
	
	private static String linii_sursa[]=new String[10000];
	private static String linii_destinatie[]=new String[10000];
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
	
	public static String[] tagare(String[] linii, String[] taguri, String[] proprietati, int pozitie_inceput, int pozitie_sfarsit,
			int pozitie_linie_inceput, int pozitie_linie_sfarsit, int contor) {
		String[] linii_noi = new String[linii.length];
		linii_noi=linii;
		String linie_inceput = new String();
		String linie_sfarsit = new String();
		linie_inceput = linii_noi[pozitie_inceput].substring(0, pozitie_linie_inceput);
		System.out.println("Inceput inainte: " + linii_noi[pozitie_inceput].substring(0, pozitie_linie_inceput));
		System.out.println("Inceput dupa: "+ linii_noi[pozitie_inceput].substring(pozitie_linie_inceput, linii_noi[pozitie_inceput].length()));
		for(int i=0; i<contor; i++)
			if(proprietati[i]!="")
				linie_inceput+="<"+taguri[i]+" "+proprietati[i]+">";
			else linie_inceput+="<"+taguri[i]+">";
		System.out.println("Sfarsit inainte: " + linii_noi[pozitie_sfarsit].substring(0, pozitie_linie_sfarsit));
		System.out.println("Sfarsit dupa: " + linii_noi[pozitie_sfarsit].substring(pozitie_linie_sfarsit, linii_noi[pozitie_sfarsit].length()));
		linie_sfarsit = linii_noi[pozitie_sfarsit].substring(0, pozitie_linie_sfarsit);
		for(int i=contor-1; i>=0; i--)
			linie_sfarsit+="</"+taguri[i]+">";
		linii_noi[pozitie_inceput]=linie_inceput+linii_noi[pozitie_inceput].substring(pozitie_linie_inceput, linii_noi[pozitie_inceput].length());
		linii_noi[pozitie_sfarsit]=linie_sfarsit+linii_noi[pozitie_sfarsit].substring(pozitie_linie_sfarsit, linii_noi[pozitie_sfarsit].length());
		return linii_noi;
	}
	
	public static void main(String[] args) throws IOException {
		sursa="C:\\Users\\Cozmin\\Desktop\\IP\\005-MIRI-34934-40855_gh.html";
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
		
		
		
		
		
		BufferedReader br = new BufferedReader(new FileReader("D:\\file.txt"));
	int count=0;
	setSursa(br.readLine());
	setDestinatie(br.readLine());
	String line=br.readLine();
	while (line != null) {
		String[] parts = line.split(" ");
		pozitie_inceput_sursa[count]=Integer.parseInt(parts[0]);
		pozitie_sfarsit_sursa[count]=Integer.parseInt(parts[1]);
		pozitie_inceput_destinatie[count]=Integer.parseInt(parts[2]);
		pozitie_sfarsit_destinatie[count]=Integer.parseInt(parts[3]);
		pozitie_inceput_linie_sursa[count]=Integer.parseInt(parts[4]);
		pozitie_final_linie_sursa[count]=Integer.parseInt(parts[5]);
		pozitie_inceput_linie_destinatie[count]=Integer.parseInt(parts[6]);
		pozitie_final_linie_destinatie[count]=Integer.parseInt(parts[7]);
		count++;
		line=br.readLine();
	    }
	for(int t=0;t<count;t++){
		 System.out.print(pozitie_inceput_sursa[t]+" ");
		 System.out.print(pozitie_sfarsit_sursa[t]+" ");
		 System.out.print(pozitie_inceput_destinatie[t]+" ");
		 System.out.print(pozitie_sfarsit_destinatie[t]+" ");
		 System.out.print(pozitie_inceput_linie_destinatie[t]+" ");
		 System.out.print(pozitie_final_linie_destinatie[t]+" ");
		 System.out.print(pozitie_inceput_linie_sursa[t]+ " ");
		 System.out.print(pozitie_final_linie_sursa[t]+ " ");
		 System.out.println("");
	 }
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
		
		String[] aux_den = new String[a.getTaguri().length];
		String[] aux_prop = new String[a.getTaguri().length];
		int contor=0;
		for(int i=0; i<a.getTaguri().length; i++)
			if(a.getTaguri(i).getPozitieInceput()<pozitie_inceput_sursa[0] && 
					a.getTaguri(i).getPozitieFinal()>pozitie_sfarsit_sursa[0]) 
				if((a.getTaguri(i).getPozitieInceput()==pozitie_inceput_sursa[0] && a.getTaguri(i).getPozitie_sfarsit_linie_inceput()<pozitie_inceput_linie_sursa[0]) || (
					a.getTaguri(i).getPozitieFinal()==pozitie_sfarsit_sursa[0] && a.getTaguri(i).getPozitie_inceput_linie_sfarsit()>pozitie_final_linie_sursa[0])) {
						aux_den[contor]=a.getTaguri(i).getDenumire();
						aux_prop[contor]=a.getTaguri(i).getProprietate();
						contor++;
						System.out.println("Denumire: "+aux_den[contor-1]+" Proprietate: "+aux_prop[contor-1]);
				}
				else {
					aux_den[contor]=a.getTaguri(i).getDenumire();
					aux_prop[contor]=a.getTaguri(i).getProprietate();
					contor++;
					System.out.println("Denumire: "+aux_den[contor-1]+" Proprietate: "+aux_prop[contor-1]);
				}
		linii_destinatie=tagare(linii_destinatie, aux_den, aux_prop, pozitie_inceput_destinatie[0], pozitie_sfarsit_destinatie[0], pozitie_inceput_linie_sursa[0], pozitie_final_linie_sursa[0], contor);
		for(int i=0; i<b.getTaguri().length; i++){
			String auxiliar;
			if(b.getTaguri(i).getPozitieInceput()>pozitie_inceput_destinatie[0] && b.getTaguri(i).getPozitieInceput() < pozitie_sfarsit_destinatie[0] && b.getTaguri(i).getPozitieFinal() > pozitie_sfarsit_destinatie[0]){
				auxiliar=linii_destinatie[b.getTaguri(i).getPozitieInceput()].substring(0,b.getTaguri(i).getPozitie_inceput_linie_inceput());
				for(int j=a.getTaguri().length-1; j>=0; j--)
					if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
							a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
						auxiliar=auxiliar+ "</"+a.getTaguri(j).getDenumire()+'>';
					}
				
				auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieInceput()].substring(b.getTaguri(i).getPozitie_inceput_linie_inceput(),b.getTaguri(i).getPozitie_sfarsit_linie_inceput()+1);
				
				for(int j=0; j<a.getTaguri().length;j++)
					if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
							a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
						auxiliar=auxiliar+ '<'+a.getTaguri(j).getDenumire()+'>';
					}
			
				auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieInceput()].substring(b.getTaguri(i).getPozitie_sfarsit_linie_inceput()+1);
				
				linii_destinatie[b.getTaguri(i).getPozitieInceput()]=auxiliar;
			}
			else if (b.getTaguri(i).getPozitieInceput()<pozitie_inceput_destinatie[0] && b.getTaguri(i).getPozitieFinal() < pozitie_sfarsit_destinatie[0] &&b.getTaguri(i).getPozitieFinal() > pozitie_inceput_destinatie[0]){
				auxiliar=linii_destinatie[b.getTaguri(i).getPozitieFinal()].substring(0,b.getTaguri(i).getPozitie_inceput_linie_sfarsit());
				for(int j=a.getTaguri().length-1; j>=0; j--)
					if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
							a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
						auxiliar=auxiliar+ "</"+a.getTaguri(j).getDenumire()+'>';
					}
			
			auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieFinal()].substring(b.getTaguri(i).getPozitie_inceput_linie_sfarsit(),b.getTaguri(i).getPozitie_sfarsit_linie_sfarsit()+1);
			for(int j=0; j<a.getTaguri().length;j++)
				if(a.getTaguri(j).getPozitieInceput()<pozitie_inceput_sursa[0] && 
						a.getTaguri(j).getPozitieFinal()>pozitie_sfarsit_sursa[0]) {
					auxiliar=auxiliar+ '<'+a.getTaguri(j).getDenumire()+'>';
				}
			auxiliar=auxiliar+linii_destinatie[b.getTaguri(i).getPozitieFinal()].substring(b.getTaguri(i).getPozitie_sfarsit_linie_sfarsit()+1);
			linii_destinatie[b.getTaguri(i).getPozitieFinal()]=auxiliar;
			}
			
		}
		
		output(linii_destinatie,b.getFisier().getContor_linii(),destinatie);
	}

}
