package edu.handong.finalproject;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.common.io.Files;

public class CustomizedException extends Exception{
	
	
	
	public CustomizedException(String fileName) {
		String err = "error.csv" ;{
			
			try{
			FileWriter fw = new FileWriter(err);
			BufferedWriter writer = new BufferedWriter(fw);
			
			writer.write(fileName);
			}
			catch(IOException e) {
				e.printStackTrace();}
			}

	}
}
