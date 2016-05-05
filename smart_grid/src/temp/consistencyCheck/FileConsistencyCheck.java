package temp.consistencyCheck;
//program to count the number of lines in file1.csv

import java.io.BufferedReader;
import java.io.IOException;

public class FileConsistencyCheck {

	
	public static void main(String[] args)
	{
		BufferedReader file = (new dataHandler.ReadFile("/home/amit/BE_PROJECT/ElecData/CSVFiles/File5.csv")).getFile();
		System.out.println("Reading INPUT FILE");
		long counter = 0;
		String line;
		try {
			while((line = file.readLine()) != null)
			{
				counter++;
				
				if(counter%10000 == 0)
					System.out.println("Done Processing "+counter+" lines...");
			}
			System.out.println("---------------FINISED-------------");
			System.out.println("No of Lines:: "+counter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
