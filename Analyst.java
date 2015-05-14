
import java.awt.*;
import java.awt.event.*; 
import java.awt.Canvas.*;
import java.awt.image.*;

/*@(#) Analyst.java 1.0 09 Jan 2012
 *
 *Copyright (c) School of Geography.
 *University of Leeds, Leeds, West Yorkshire, UK. LS2 9JT.
 *All rights reserved.
 *
 *This code is provided under the Academic Academic Free License v. 3.0.
 *For details, please see the site http://www.opensource.org/licenses/AFL-3.0.
 */ 

/**
 *The Analyst class can be used to display geographical data within a Graphical User Interface (GUI). 
 *
 *The Analyst class extends the 'Frame' class and implements the 'ActionListener' class. The Analyst class creates a canvas and Graphical User Interface, 
 *and calls upon data and methods in other classes to display geographical data within the GUI. 
 *
 *@author Nicholas Addis <gy11nja@leeds.ac.uk>
 *@version 1.0 09 Jan 2012
 */
public class Analyst extends Frame implements ActionListener { 

	Canvas canvas = new Canvas();		//This creates a new Canvas object named 'canvas'.

	Storage store = new Storage();		//This creates a new Storage object named 'store'. 
	
	Panel panel = new Panel();		//This creates a new Panel object.  
	
	IO io = new IO();		//This creates a new IO object. 
	
	boolean runbutton = false;
		
	public void paint (Graphics g) {
			
			
		if (runbutton == false) {
			
			
		
		Image image = store.getDataAsImage();		//This calls the store objects getDataAsImage method from Storage class. 
			
		if (image != null) {
			
			Graphics gc = canvas.getGraphics();
			
			//int width = image.getWidth(this);
			//int height = image.getHeight(this);
			
			gc.drawImage(image, 15, 10, panel);
		}
			
			
		} else {
			
			
			Image imaged = store.getDataAsImageDrunks();
			
			if (imaged != null) {
				
				Graphics gc = canvas.getGraphics();
				
				//int width = image.getWidth(this);
				//int height = image.getHeight(this);
				
				gc.drawImage(imaged, 15, 10, panel);
				
				
			}
		}
			
	}
		
		
	/**
	*The Analyst method creates the MenuBars for the GUI. 
	*
	*@param void no input parameters. 
	*@return no return.
	*/
	public Analyst () {		
						
				
		setSize(350,380);		//Sets size for the frame. 
			
			//This code sets up and adds an 'Open' function to a menu.
			MenuBar menuBar = new MenuBar();		//This creates a new MenuBar object.
			Menu fileMenu = new Menu("File");		//This creates a new Menu object. 
			menuBar.add(fileMenu);		//This adds a new Menu to the MenuBar on the GUI.
			MenuItem openMenuItem = new MenuItem("Open...");		//This creates a new MenuItem object 'Open...' for the GUI.
			fileMenu.add(openMenuItem);		//This adds the 'Open' MenuItem to the fileMenu for the GUI. 
			
			MenuItem runProcess = new MenuItem ("Run...");		//This creates a new MenuItem object 'Run Modelled Drunks Application...' to the GUI.
			fileMenu.add(runProcess);		//This adds the newly made MenuItem to the GUI.
			
			setMenuBar(menuBar); 
			add(canvas);		
								
			//This code sets up and adds a 'Save' function to a menu. 		
			menuBar.add(fileMenu);
			MenuItem saveMenuItem = new MenuItem("Save...");
			fileMenu.add(saveMenuItem);
			setMenuBar(menuBar); 
			
			
			openMenuItem.addActionListener(this);		//Makes a listener for the menu (Open).
			
			runProcess.addActionListener(this);			//Makes a listener for the menu (Run).
			
			
			saveMenuItem.addActionListener(this);		//Makes a listener for the menu (Save).		
			
			setVisible(true);		//sets the window as visible.
			
			
			addWindowListener(new WindowAdapter(){		//Adds WindowListener. 
				public void windowClosing(WindowEvent e){
					((Frame)e.getSource()).dispose();
				
								
				}
				
			} );	
				
	}
		
		
		/**
		*The actionPerformed method makes the link between the buttons pressed by the user on the GUI, and the subsequent methods that 
		*open, display and then save a data file. 
		*
		*@param variables (ActionEvent e).
		*@return no return.
		*/
		public void actionPerformed(ActionEvent e) {
			
			MenuItem clickedMenuItem = (MenuItem)e.getSource();		//Sets the functions for the open option. 
			if (clickedMenuItem.getLabel().equals("Open..."))  { 
				store.setData(io.readData());
			
			}
			
			
			if (clickedMenuItem.getLabel().equals("Save..."))  { 	//Sets the functions for the save option. 
				io.writeData(store.getData()); 
			
			}
			
			
			if (clickedMenuItem.getLabel().equals("Run..."))  { 	//Sets the functions for the run option. 
				
				store.modellingDrunks(store.getData());
			
				runbutton = true;		//If the 'Run' button is selected then runbutton = true, which will then initiate the paint method for the drunkdensitymap array. 
				
			}
				
			repaint();
			
			
			//System.out.println(store.drunkdensitymap);
			
		} 

		
		/**
		 *This is the main method.
		 *
		 *The main method enables the Java program to run. 
		 *
		 *@param an array of String objects. 
		 *@return no return values.
		 */ 
		public static void main (String args[]) {
			new Analyst ();		
		
		}			
}			
