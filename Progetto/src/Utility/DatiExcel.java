package Utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DatiExcel {

	public  List<String> ottieniProvince() throws IOException {
		
		List<String> Province = new ArrayList<String>();
		
		//Aggiungo un primo valore vuoto per non far visualizzare nessun valore nella JComboBox
		Province.add("");
		String path = "Elenco-comuni-italiani.csv";
		String riga  = "";
		FileInputStream file = new FileInputStream(path);
		
		//Utilizzo di ISO_8859_1 per ottenere correttamente le accento sulle lettere
		BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.ISO_8859_1));
		int i=0;
		while((riga = br.readLine()) != null) {
			if(i>2) {
				String[] dati = riga .split(";");
				if(!Province.contains(dati[1])) {
					Province.add(dati[1]);
				}
			}
				i++;
		}
		br.close();
		
		//Ordinare in ordine alfabeticop
		
		
		return Province;

	}
	
	public  List<String> ottieniComuniProvincia(String Provincia) throws IOException{
		
		List<String> comuniProvincia = new ArrayList<String>();
		
		//Aggiungo un primo valore vuoto per non far visualizzare nessun valore nella JComboBox
		comuniProvincia.add("");
		
		String path = "Elenco-comuni-italiani.csv";
		String riga  = "";
		FileInputStream file;
		file = new FileInputStream(path);
		
		//Utilizzo di ISO_8859_1 per ottenere correttamente le accento sulle lettere
		BufferedReader br = new BufferedReader(new InputStreamReader(file, StandardCharsets.ISO_8859_1));
		int i=0;
			while((riga  = br.readLine()) != null) {
				if(i>2) {
					String[] dati = riga .split(";");
					if(Provincia.equals(dati[1])) {
						comuniProvincia.add(dati[0]);
					}
				}
					i++;
			}
		br.close();
		
		return comuniProvincia;

	}
	
}
