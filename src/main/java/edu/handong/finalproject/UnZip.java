package edu.handong.finalproject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;


public class UnZip {
	
	public static ArrayList<String> readFileInZip(String path) {
		ZipFile zipFile;
		ArrayList<String> reading = null;
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			
				

		   while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		    
		        ReadFile myReader = new ReadFile();
		        reading = myReader.getData(stream);
		        
		       
		    }
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return reading;
	}
	
	
}


