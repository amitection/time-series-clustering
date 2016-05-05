package ssvToTsv;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;


public class ssvToTsv {


	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	//private static final String FILE_HEADER = "METER_ID,DATE_TIME,kW/30";
	
	public static void main(String args[])
	{

		BufferedReader file = (new dataHandler.ReadFile("/home/amit/BE_PROJECT/ElecData/Files/File6.txt")).getFile();
		System.out.println("Reading INPUT FILE");
		
		FileWriter fileWriter;
		String line;
		String []arr;
		long counter =0;
		
		try {
			
			fileWriter = new FileWriter("/home/amit/BE_PROJECT/ElecData/CSVFiles/File6.csv");
			System.out.println("OPENING OUTPUT FILE");
			
			//fileWriter.append(FILE_HEADER.toString());
			//fileWriter.append(NEW_LINE_SEPARATOR);
			
			while((line = file.readLine()) != null)
			{
				counter++;
				
				if(counter%10000 == 0)
					System.out.println("Done Processing "+counter+" lines...");
			
				arr = line.split(" ");
            	
				fileWriter.append(arr[0]);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(arr[1]);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(arr[2]);
				fileWriter.append(NEW_LINE_SEPARATOR);
			
			}
			
			System.out.println("OPERATION COMPLETE");
			
			fileWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}



}
