import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parsare {
	
	private String path;
	private String linie[]=new String[1000];
	private int contor_linii=0;
	
	public int getContor_linii() {
		return contor_linii;
	}

	public void setContor_linii(int contor_linii) {
		this.contor_linii = contor_linii;
	}

	public void setPath(String cale){
		path=cale;
	}
	
	public String getPath(){
		return path;
	}
	
	public String[] getLinie() {
		return linie;
	}
	
	public String getLinie(int pozitie) {
		return linie[pozitie];
	}

	public void setLinie(String[] linie) {
		this.linie = linie;
	}
	
	public void setLinie(String linie,int pozitie) {
		this.linie[pozitie] = linie;
	}

	public Parsare(){
		path="";
		linie=new String[1000];
	}
	
	public void Parseaza(String cale) throws IOException{
		path=cale;
		
		BufferedReader br = new BufferedReader(new FileReader(cale));
		try {
		    String line = br.readLine();

		    while (line != null) {
		    	linie[contor_linii++] = line;
		    	//System.out.println(line);
		        line = br.readLine();
		    }
		} finally {
		    br.close();
		}
		
	}
  
}
