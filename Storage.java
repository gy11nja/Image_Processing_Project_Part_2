import java.awt.*;
import java.awt.image.*;

/*@(#) Storage.java 1.0 09 Jan 2012
 *
 *Copyright (c) School of Geography.
 *University of Leeds, Leeds, West Yorkshire, UK. LS2 9JT.
 *All rights reserved.
 *
 *This code is provided under the Academic Academic Free License v. 3.0.
 *For details, please see the site http://www.opensource.org/licenses/AFL-3.0.
 */ 

/**
 *The Storage class can be used to store and process geographical image data. 
 *
 *The Storage class stores and geographical image data and is called upon by methods in other classes 
 *to process this data so that it can be displayed as an image.
 *
 *@author Nicholas Addis <gy11nja@leeds.ac.uk>
 *@version 1.0 09 Jan 2012
 */
 public class Storage {		//Declares the public class Storage

	/**This is the setData method which sets the data for the array. 
	 *
	 *The data array is a 300 by 300 array of type double of landscape or other geographical data.
	 *
	 *@param variables (double [][] dataIn) 
	 *@return no return
	 */
	void setData (double [][] dataIn)  {
				data = dataIn;			

	}	
			
	double[][] data = null; 
	double[][] drunkdensitymap = null;
	
		
	/**
	 *This is the getData method which returns the data for the current 2D Array.
     *
     *@param void no input parameters.
     *@return data for 2D Array.
     */
	double[][] getData ()  {
		
		return data;
								
	}	
	
	
	/**
	 *This is the 'get1DArray' method which returns the current data array. 
	 *
	 *@param variables (double[][] dataIn)
	 *@return tempArray
	 */	
	double[] get1DArray (double[][] dataIn) {
			
		double tempArray[] = new double [dataIn.length * dataIn[0].length];		  
			
		for (int i = 0; i < dataIn.length; i++)  {		//This loops through the rows.
				
			for (int j = 0; j < dataIn[i].length; j++)  {		//This loops through the columns. 
		
				tempArray [(i * dataIn[0].length) + j] = dataIn[i][j];
						
			}
		}
					
		return tempArray;
					
	}
			
			
	/**
	 *The 'getDataAsImage' method gets the data from the array and displays this as an image.
	 *
	 *The data is put into a 1D array re-ranged between 0 and 255. 
	 *
	 *@param void no input parameters.
	 *@return image.
	 */	
	public Image getDataAsImage() {
			
		if (data !=null) {
			
			//gets the 1D array reranged between 0 and 255. 
			double[] array1Dreranged = get1DArray(data);
			//declares int array named 'pixels'. 
			int pixels[] = new int [data.length * data[0].length];		
								
			//loops through the array. 
			for (int i = 0; i < pixels.length; i++)  {
									
				int value = (int) array1Dreranged[i]; 
				//sets the	R G B values for the pixel object. 
				Color pixel = new Color (value, value, value); 
						
				//This implements the color displayed in the pixel (it gets the R G and B values from inside the pixel object). 
				pixels [i] = pixel.getRGB();
				
			} 
			
			MemoryImageSource memImage = new MemoryImageSource(data.length, data[0].length, pixels, 0, data.length);		//Makes a MemoryImageSource object.
			Panel panel = new Panel(); 
					
			
			Image image = panel.createImage(memImage);		//Makes an Image object.
						
			return image; 
				
		} 	else {
				
				return null;
				
			}
				
	}		
	
	
	/**
	 *This is the modellingDrunks method, which models the journeys taken by 25 'drunk people' from a public house to their respective homes. 
	 *
	 *The method models the journeys taken by the 'drunk individuals', who move randomly left, right up or down, until they reach their house. A density 
	 *map of the number of individuals who pass through each point on the map is taken and then displayed as a density map. 
	 *
	 *@param variables (double[][] data)
	 *@return drunksDensityMap.
	 */
	void modellingDrunks (double[][] data) {  
								 
		drunkdensitymap = new double[data.length][data[0].length];		//The drunkdensitymap is the array that will store the journeys of the drunken individuals. 
					
		int drunkValue = 10;		
					
					
		int xp = 0;
		int yp = 0;
				
			
		for (int i = 0; i < data.length; i++) {		//This loops through the rows.    
				
			for (int j = 0; j < data.length; j++) {		//This loops across the columns.  		 
				
				if (data[i][j] == 1) {
								
					xp = i;		//This sets the co-ordinates for the public house if [i][j] hits a cell with a value of 1 (the public house). 
					yp = j;
							
				} 
								
			}		

		}
						
							
		for (int a = 0; a < 24; a++) {		//Opens a new counting loop across 25 drunken individuals.  
								
			int x = xp; 	//Integer x is equal to the position of 'xp'.    
								
			int y = yp;		//Integer y is equal to the position of 'yp'.    				
																		
			boolean drunkfindshouse = false;		//This sets the boolean variable 'drunkfindshouse' to false.  
										
			while (drunkfindshouse == false) {		//Opens a 'while' loop for drunk to move randomly as long as 'drunkfindshouse' remains false. 
								
				int randomnumber = (int) (Math.random() * 4);		//This generates an integer between 0 and 3 (inclusive). 
											
				
				if ((randomnumber == 0) && (x < 299)) {		//If the random number is 0 and the drunken individual meets the boundary exception criteria, then the person moves one place to the right. 
				
					x = x + 1;
					y = y;
					drunkdensitymap[x][y] = drunkdensitymap[x][y] + 1;		//Adds one to the drunkdensitymap. 
				}
									
					
				if ((randomnumber == 1) && (x > 0)) {		//If the random number is 1 and the drunken individual meets the boundary exception criteria, then the person moves one place to the left. 
			
					x = x - 1;
					y = y;
					drunkdensitymap[x][y] = drunkdensitymap[x][y] + 1;
				}
											
						
				if ((randomnumber == 2) && (y < 299)) {		//If the random number is 2 and the drunken individual meets the boundary exception criteria, then the person moves one place down.  		
				
					x = x;
					y = y + 1;
					drunkdensitymap[x][y] = drunkdensitymap[x][y] + 1;					
				}
						
					
				if ((randomnumber == 3) && (y > 0)) {		//If the random number is 3 and the drunken individual meets the boundary exception criteria, then the person moves one place up.  				
					
					x = x;
					y = y - 1;
					drunkdensitymap[x][y] = drunkdensitymap[x][y] + 1;			
				}
											
							
					
				if (drunkValue == data[x][y]) {		//If the value of the drunk equals the current cell they are in, then they have reached home and therefore 'drunkfindshouse' = true, and the individual has a value of 10 added and it goes on to the next individual in the loop. 
									
					drunkfindshouse = true;
															
				}  
					
			}
									
			drunkValue = drunkValue + 10;		//Adds a value of 10 to the drunk individual. 			
				    																
		} 
				
	}					
		
		
	/**
	 *The 'getDataAsImageDrunks' method gets the data from the array and displays this as an image.
	 *
	 *The data is put into a 1D array re-ranged between 0 and 255. 
	 *
	 *@param void no input parameters.
	 *@return image.
	 */
	public Image getDataAsImageDrunks() {
			
		if  (drunkdensitymap !=null) {
		 
			double[][] a = rerangeArray(0.0, 255.0);
				
			double[] array1Dreranged = get1DArray(a);		//This gets the 1D array reranged between 0 and 255. 
				
			int newdrunkmap[] = new int [drunkdensitymap.length * drunkdensitymap[0].length];		//Declares int array named 'pixels'. 		
								
			for (int i = 0; i < newdrunkmap.length; i++)  {		//Loops through the array. 
									
				int value = (int) array1Dreranged[i]; 
					
				Color pixel = new Color (value, value, value);		//This sets the	R G B values for the pixel object.  
						
				newdrunkmap [i] = pixel.getRGB();		//This implements the color displayed in the pixel (it gets the R G and B values from inside the pixel object). 
				
			} 
			
				
			MemoryImageSource memImage = new MemoryImageSource(drunkdensitymap.length, drunkdensitymap[0].length, newdrunkmap, 0, drunkdensitymap.length);		//Makes a MemoryImageSource object.
				
			Panel panel = new Panel();		//This makes a Panel object.  
					
			Image image = panel.createImage(memImage);		//This makes an Image object.
					
			return image; 
				
		} 	else {
				
				return null;
				
			}
				
	}		
		
		
	/**
	 *This is the rerangeArray method which reranges the current data array.
	 *
	 *The array holds geographical data.
	 *
	 *@param (double newMaximum, double newMinimum).
	 *@return tempArray.
	 */
	double[][] rerangeArray (double newMaximum, double newMinimum)  {
		
		double currentMaximum = getMaximum();		//setting up double variable called containing currentMaximum.
		
		double currentMinimum = getMinimum();		//setting up double variable called containing currentMinimum.
				
			
		double tempArray[][] = new double [drunkdensitymap.length][drunkdensitymap[0].length];		//making double tempArray[data.length][data[0].length] . 
		
		for (int i = 0; i < drunkdensitymap.length; i++)  {		//Opens loop with 'i' across data rows.
				
			for (int j = 0; j < drunkdensitymap[i].length; j++)  {		//Opens loop with 'j' across a row. 

				tempArray[i][j] = drunkdensitymap[i][j];
								
				tempArray[i][j] = tempArray[i][j] - currentMinimum;
					
				if (tempArray[i][j] != 0)  {		//If tempArray is not zero, then tempArray[i][j] = tempArray[i][j] / (currentMaximum - current Minimum) - gives data between 0 and 1. 
						tempArray[i][j] = tempArray[i][j] / (currentMaximum - currentMinimum);
				}  
								
				tempArray[i][j] = tempArray[i][j] * (newMaximum - newMinimum);  
									
									
				tempArray[i][j] = tempArray[i][j] + newMinimum;
																
			}
								
		}
			
		return tempArray; 
			
	}
		
		
	/**
     *This is the getMaximum method which gets the Maximum value for the data array. 
     *
	 *@param void no input parameters.
     *@return Maximum value. 
	 */	 
		
	double getMaximum()  {
		
		double maximum = -1.0;		//Setting double maximum value as -1.0
			
			for (int i = 0; i < drunkdensitymap.length; i++)  {		//Open loop with 'i' across data rows. 
				
				for (int j = 0; j < drunkdensitymap[i].length; j++)  {		//Open loop with 'j' across data rows. 
							
					if (drunkdensitymap [i][j] > maximum) {
	
						maximum = drunkdensitymap[i][j];
	
					}
	
				}
	
			}
				
			return maximum; 
	}
		
		
	/**
     *This is the getMinimum method which gets the Minimum value for the data array. 
     *
	 *@param void no input parameters.
     *@return Minimum value. 
	 */	 
	double getMinimum()   {
			
		double minimum = getMaximum(); 
		
		for (int i = 0; i < drunkdensitymap.length; i++)   {		//Open loop with 'i' across rows. 
			
			for (int j = 0; j < drunkdensitymap.length; j++)  {		//Open loop with 'j' across columns. 
				
				if (drunkdensitymap[i][j] < minimum)   {
					minimum = drunkdensitymap[i][j]; 
				
				}
			}
		}
		
		return minimum;	
	}
										
}		

