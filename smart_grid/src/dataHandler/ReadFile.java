/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author amit
 */
public class ReadFile {
	
	BufferedReader file;
	
    public ReadFile(String filePath)
    {
    	try {
			file = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public BufferedReader getFile()
    {
    	return file;
    }
    
}
