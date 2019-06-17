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
		        
		       /* for(String value:myReader.getData(stream)) {
		        	System.out.println(value);
		        }*/
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reading;
	}
	
	/*public static InputStream zipReader(String path) throws IOException {
		
		
		
		ZipFile zipFile;
		InputStream is = null;
		
		zipFile = new ZipFile(path);
		Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
 
		ZipArchiveEntry entry = entries.nextElement();
		is = zipFile.getInputStream(entry);
		
	    return is;
		
	}*/
}


/*public class ZipReader {

	public static void main(String[] args) {
		ZipReader zipReader = new ZipReader();
		zipReader.run(args);
	}

	private void run(String[] args) {
		//String path = args[0];
		
		readFileInZip("0001.zip");
		
	}

	public static void readFileInZip(String path) {
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			
				

		   while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		    
		        ExcelReader myReader = new ExcelReader();
		        
		        for(String value:myReader.getData(stream)) {
		        	System.out.println(value);
		        }
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/