
import java.io.*;
import java.util.*;
import java.awt.*;

/*@(#) IO.java 1.0 09 Jan 2012
 *
 *Copyright (c) School of Geography.
 *University of Leeds, Leeds, West Yorkshire, UK. LS2 9JT.
 *All rights reserved.
 *
 *This code is provided under the Academic Academic Free License v. 3.0.
 *For details, please see the site http://www.opensource.org/licenses/AFL-3.0.
 */ 

/**
 *The IO class can be used to read and write out geographical data. 
 *
 *The class includes methods that read files and then can write data out to a file. 
 *
 *@author Nicholas Addis <gy11nja@leeds.ac.uk>
 *@version 1.0 09 Jan 2012
 */
public class IO {		//The IO class is a  class for reading and writing. 
	
	/**
	*This is the readData method, which instructs the JVM which file to read and then reads the data. 
	*
	*@param void no input parameters.
	*@return data
	*/
    public double[][] readData() {		//This is the readData method, which instructs the method which file to read. 
	
		
		FileDialog fd = new FileDialog(new Frame(), "Open File", FileDialog.LOAD);		//Opens up the file dialog window when you open the GUI. 
		fd.setVisible(true);
	
	
		File f = new File("m:/java/src/unpackaged/Assignment2_Final_Completed_Submission/drunk.txt");		//This sets the file location to open the drunk.text file.
		
		FileReader fr = null;	
			
		
		try {		//This opens a try catch block.
			fr = new FileReader (f);		//This opens up a new FileReader within the try catch block.
		} 	catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
				
			}
		

			BufferedReader br = new BufferedReader(fr);		//This filereader is then 'wrapped' in a BufferedReader, and the variables are declared. This also declares a new BufferedReader object named 'br'.  
			
			int lines = -1; 	//The variables are declared. 
			String textIn = " ";
			String[] file = null; 
		
			
			try {
				
				while (textIn !=null) {		//This reads through the file, and counts the lines in the file.
						textIn = br.readLine();
						lines++; 
				}
				
				file = new String[lines];		//This creates a new 1D String array. 
				
				br.close();		//This closes the file. 
					
					
			} 	catch (IOException ioe) {} 	//This catches any exceptions that emerge. 
				
				try {
					fr = new FileReader (f);		//This creates a new FileReader. 
				
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
					
				}
				
				br = new BufferedReader(fr);		//This wraps the new filereader in a new bufferedreader. 
		
				try {	
					
					for (int i = 0; i < lines; i++) {		//This loops through the lines in the array. 
						file[i] = br.readLine();
					}
					
					br.close();
					
				} 	catch (IOException ioe) {
						ioe.printStackTrace();		//This catches the exceptions. 
					}
   
   
					/**
					 *This array stores geographical data.
					 */
   					double[][]data = new double [lines] [];
					
					for (int i =0; i < lines; i++) {		//This runs through the array, using a StringTokenizer, parsing the 1D String array into a a 2D double array.
						StringTokenizer st = new StringTokenizer(file[i], ", ");
						data[i] = new double[st.countTokens()];
						int j = 0;
						while (st.hasMoreTokens()) {
							data[i][j] = Double.parseDouble(st.nextToken());
							j++;
						}
					}
					
					return data;
	
	}

		
	/**
	*This is the writeData method, which writes data out to a file. 
	*
	*@param variables (double[][] dataIn).  
	*@return no return values. 
	*/		
   	public void writeData (double[][] dataIn) {		//The writeData method writes the data out to a file. 
						
		FileDialog fd = new FileDialog(new Frame(), "Save File", FileDialog.SAVE);		//This creates a new 'Save' FileDialog. 
		fd.setVisible(true);
						
						
		File f = new File("m:/java/src/unpackaged/Assignment2_Final_Completed_Submission/drunkoutput.txt");		//This sets the location to save the output file. 
	  
		FileWriter fw = null;
		
		
	
		try {		//This makes a FileWriter in a try catch block.   

			fw = new FileWriter (f, true);

		} catch (IOException ioe) { 	//This is the catch exception. 
			ioe.printStackTrace();
		}

		
		BufferedWriter bw = new BufferedWriter (fw);		//This makes a BufferedWriter object, to wrap the FileWriter inside the BufferedWriter. 
		
		String tempStr = "";
		try { 
		
			for (int i = 0; i < dataIn.length; i++) {		//This loops through the rows of the array.
				for (int j = 0; j < dataIn[i].length; j++) {		//This loops through the columns of the array.
					 
					tempStr = String.valueOf(dataIn[i][j]);			//This changes the type double to a String. 
					
					bw.write(tempStr);		//This writes the string to the file. 

				}
				bw.newLine();	
			}
			bw.close(); 

		} catch (IOException ioe) {} //This closes the try block. 

					
	} 
}
