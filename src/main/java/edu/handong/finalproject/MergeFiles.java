package edu.handong.finalproject;

import edu.handong.finalproject.UnZip;
import edu.handong.finalproject.ReadFile;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MergeFiles {
	
	String input;
	String output;
	boolean help;
	
	
	public void run(String[] args) {
		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			
			if (help){
				printHelp(options);
				return;
			}
			File data = new File(input);
			
			FileFilter fileFilter = new FileFilter() {
				public boolean accept(File file) {
				return file.isFile();
			    }
			};
			
			// List all the files using the filter
			File[] tab = data.listFiles( fileFilter );
			//ArrayList<String> lists = new ArrayList<String>();
			ArrayList<String>[] reading = new ArrayList[tab.length];
			int i =0;
			for( File current : tab ){
			    //lists.add(current.getName());
				reading[i] = UnZip.readFileInZip(current.getAbsolutePath());
				i++;
			}
			writeFile(output,reading);
			
			
		}
	}
		

	      
	
	
	
	private void writeFile(String output2,ArrayList<String>[] reading2) {
		
		  XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Final Project");
	        /*Object[][] datatypes = {
	                {"Datatype", "Type", "Size(in bytes)"},
	                {"int", "Primitive", 2},
	                {"float", "Primitive", 4},
	                {"double", "Primitive", 8},
	                {"char", "Primitive", 1},
	                {"String", "Non-Primitive", "No fixed size"}
	        };*/

	        int rowNum = 0;
	        System.out.println("Creating excel");
	        for(int j = 0;j<reading2.length;j++) {
	      
	        for (String list : reading2[j]) {
	        	list.substring(1, list.length());
	            Row row = sheet.createRow(rowNum++);
	            int colNum = 0;
	            String[] lists = list.split(",");
	            Cell first = row.createCell(colNum++);
	            first.setCellValue(String.format("%04d",j+1));
	            for (String cells : lists) {
	                Cell cell = row.createCell(colNum++);
	                //if (field instanceof String) {
	                    cell.setCellValue((String) cells);
	                /*} else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }*/
	            }
	            
	        }
	        }

	        try {
	            FileOutputStream outputStream = new FileOutputStream(output2);
	            workbook.write(outputStream);
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.println("Done");
	    }
	
	
	




	/**
	 * This method implements a logic that parse options typed by users in CLI.
	 * @param options
	 * @param args
	 * @return
	 */
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	/**
	 * This method creates an instance of Options class.
	 * This method adds each option in the options instance.
	 * @return
	 */
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		

		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());

		return options;
	}
	
	/**
	 * This method automatically generate the help statement.
	 * @param options
	 */
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "Merging Files";
		String footer ="";
		formatter.printHelp("Merging Files", header, options, footer, true);
	}

}
