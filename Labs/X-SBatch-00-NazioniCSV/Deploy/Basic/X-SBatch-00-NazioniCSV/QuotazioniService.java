package corso.ajax.demo.quotazioni.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.csvreader.CsvReader;
import corso.ajax.demo.quotazioni.service.beans.QuotazioneBean;

public class QuotazioniService {

	private static final int [] RANGE_NUMERI_PESO={
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,
			2,2,2,2,2,2,2,2,2,2,2,
			3,3,3,3,3,3,3,3,3,
			4,4,4,4,4,4,4,
			5,5,5,5,5,5,
			6,6,6,6,6,
			7,7,7,7,
			8,8,8,8,
			9,9,9,
			10,10,
			11,12,13,14,15};
	
	private static final int QUANTITA_SCAMBIATA=300000;
	
	
	public List<QuotazioneBean> initQuotazioni(Reader streamCsv){
		
		List<QuotazioneBean> listaQuotazioni = new ArrayList<QuotazioneBean>();
		CsvReader quotazioniCsv = null;
		
		
		try {
			quotazioniCsv =new CsvReader(streamCsv);
			quotazioniCsv.setDelimiter(';');
			quotazioniCsv.readHeaders();
			
			while (quotazioniCsv.readRecord())
			{
				String descrizione = quotazioniCsv.get("Descrizione");
				String trendTitolo = quotazioniCsv.get("Trend titolo");
				
				String ultimoPrezzoStr = quotazioniCsv.get("Ultimo prezzo");
				ultimoPrezzoStr=ultimoPrezzoStr.replaceAll(",",".");
				double ultimoPrezzo = Double.parseDouble(ultimoPrezzoStr);
				
				String variazionePercentualeStr = quotazioniCsv.get("Var. %");
				variazionePercentualeStr=variazionePercentualeStr.replaceAll(",",".");
				double variazionePercentuale = Double.parseDouble(variazionePercentualeStr);
				
				String oraUltimoPrezzo = quotazioniCsv.get("Ora ultimo prezzo");
				
				String volumeProgStr = quotazioniCsv.get("Volume progr.");
				volumeProgStr=volumeProgStr.replaceAll("[.]","");				
				int volumeProg= Integer.parseInt(volumeProgStr);
				
				String miglioreDenaroStr= quotazioniCsv.get("Migliore Denaro");
				miglioreDenaroStr=miglioreDenaroStr.replaceAll(",",".");
				double miglioreDenaro = Double.parseDouble(miglioreDenaroStr);
				
				String miglioreLetteraStr = quotazioniCsv.get("Migliore Lettera");
				miglioreLetteraStr=miglioreLetteraStr.replaceAll(",",".");
				double miglioreLettera = Double.parseDouble(miglioreLetteraStr);
				
				String prezzoDiRiferimentoStr = quotazioniCsv.get("Prezzo di rif.");
				prezzoDiRiferimentoStr=prezzoDiRiferimentoStr.replaceAll(",",".");
				double prezzoDiRiferimento = Double.parseDouble(prezzoDiRiferimentoStr);
				
				String aperturaStr = quotazioniCsv.get("Apertura");
				aperturaStr=aperturaStr.replaceAll(",",".");
				double apertura = Double.parseDouble(aperturaStr);
				
				String codiceIsin = quotazioniCsv.get("Codice ISIN");
				String tic = quotazioniCsv.get("TIC");
				
				QuotazioneBean quotazioneBean= new QuotazioneBean();
				quotazioneBean.setDescrizione(descrizione);
				quotazioneBean.setTrendTitolo(trendTitolo);
				quotazioneBean.setUltimoPrezzo(ultimoPrezzo);
				quotazioneBean.setVariazionePercentuale(variazionePercentuale);
				quotazioneBean.setOraUltimoPrezzo(oraUltimoPrezzo);
				quotazioneBean.setVolumeProg(volumeProg);
				quotazioneBean.setMiglioreDenaro(miglioreDenaro);
				quotazioneBean.setMiglioreLettera(miglioreLettera);
				quotazioneBean.setPrezzoDiRiferimento(prezzoDiRiferimento);
				quotazioneBean.setApertura(apertura);
				quotazioneBean.setCodiceIsin(codiceIsin);
				quotazioneBean.setTic(tic);
				
				listaQuotazioni.add(quotazioneBean);
				
				//System.out.println(quotazioneBean);
			}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		quotazioniCsv.close();
		return listaQuotazioni;
	}
	
	
	public List<QuotazioneBean> getUpdatedQuotazioni(List<QuotazioneBean> vecchieQuotazioni){
		
		//List<QuotazioneBean> newQuotazioni= new ArrayList<QuotazioneBean>();
		
		for (QuotazioneBean quotazioneOld : vecchieQuotazioni){
			SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date now = new Date();
			String dataString =dataFormat.format(now);
			String variazioneSimbolo="+";
			
			double variazione= generateVariazione();
			int nuovaQuantita=generateQuantitaScambiata();			
			if (variazione<0){
				variazioneSimbolo="-";
				
			}
			
			//System.out.println("variazione "+ variazione+"%");
			variazione=variazione/100; //valore percentuale per calcolo
			double nuovoPrezzoRiferimento=(variazione*quotazioneOld.getPrezzoDiRiferimento())+quotazioneOld.getPrezzoDiRiferimento();
			
			quotazioneOld.setPrezzoDiRiferimento(nuovoPrezzoRiferimento);
			quotazioneOld.setVariazionePercentuale(variazione);
			quotazioneOld.setVolumeProg(nuovaQuantita);
			quotazioneOld.setOraUltimoPrezzo(dataString);
			quotazioneOld.setTrendTitolo(variazioneSimbolo);
			
			
			/*
			QuotazioneBean quotazioneBean= new QuotazioneBean();
			quotazioneBean.setDescrizione(quotazioneOld.getDescrizione());
			quotazioneBean.setTrendTitolo(variazioneSimbolo);
			quotazioneBean.setUltimoPrezzo(quotazioneOld.getUltimoPrezzo());
			quotazioneBean.setVariazionePercentuale(variazione);
			quotazioneBean.setOraUltimoPrezzo(dataString);
			quotazioneBean.setVolumeProg(nuovaQuantita);
			quotazioneBean.setMiglioreDenaro(quotazioneOld.getMiglioreDenaro());
			quotazioneBean.setMiglioreLettera(quotazioneOld.getMiglioreLettera());
			quotazioneBean.setPrezzoDiRiferimento(nuovoPrezzoRiferimento);
			quotazioneBean.setApertura(quotazioneOld.getApertura());
			quotazioneBean.setCodiceIsin(quotazioneOld.getCodiceIsin());
			quotazioneBean.setTic(quotazioneOld.getTic());
			
			newQuotazioni.add(quotazioneBean);
			*/
			
			
			//System.out.println("nuovaQuantita "+ nuovaQuantita);			
		}
			
		//return newQuotazioni;
		return vecchieQuotazioni;
		
	}
	
	
	public double generateVariazione(){
		Random rnd= new Random();		
		
		int indiceEstratto= rnd.nextInt(RANGE_NUMERI_PESO.length);
		int interoEstratto=RANGE_NUMERI_PESO[indiceEstratto];
		double doubleEstratto =rnd.nextDouble();
		double variazione=interoEstratto+doubleEstratto;
		int segno = rnd.nextInt(2);
				
		if (segno==1){
			variazione=variazione*(-1);
		}
		
		return variazione;
		
	}
	
	
	public int generateQuantitaScambiata(){
		Random rnd= new Random();
		int costante=20000;
		int indiceEstratto= rnd.nextInt(RANGE_NUMERI_PESO.length);
		int interoEstratto=RANGE_NUMERI_PESO[indiceEstratto];
		int nuovaQuantita= interoEstratto*costante;
		return nuovaQuantita;
	}
}
