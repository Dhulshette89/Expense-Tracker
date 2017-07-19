package console;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import Graphs.CenterPanel;
import components.ExpenseManager;
import components.FormEvent;
import components.FormListener;
import components.LeftPanel;

import java.awt.Color;
import java.awt.Component;

public class MainFrames extends JFrame {
	private CenterPanel formPanelCenter;
	private LeftPanel formPanelLeft;
	
	
	
	public MainFrames() throws FileNotFoundException, ParseException
	{
		super("My Expense Tracker");
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width,screenSize.height);
		//creating instances of panels to be added in frame.
		formPanelCenter=new CenterPanel();
		formPanelLeft=new LeftPanel();
		//ensuring program is stopped as soon as frame is closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//implementing method in interface &  passing interface type anonymous class 
		formPanelLeft.setFormListener(new FormListener(){
			public void formEventOccured(FormEvent e){
				String date=e.getDate();
				String category=e.getCategory();
				String amount=e.getAmount();
	//writing the user entered data in file
				
				BufferedWriter bw = null;
				FileWriter fw = null;
				try
				{

					File file = new File("expenses.txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					// true = append file
					fw = new FileWriter(file.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);

					bw.write(date + " " + category + " " + amount + "\n");
					
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
				finally {

					try {

						if (bw != null)
							bw.close();

						if (fw != null)
							fw.close();

					} catch (IOException ex) {

						ex.printStackTrace();

					}
				}
				
				
			}
		});
	//implementation of interface method for add category button event.
		formPanelLeft.setFormListener1(new FormListener(){
			public void formEventOccured(FormEvent e){
				String newCategory=e.getNewCategory();
	//writing user entered category to file.
				BufferedWriter bw = null;
				FileWriter fw = null;
				try
				{

					File file = new File("category.txt");

					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					// true = append file
					fw = new FileWriter(file.getAbsoluteFile(), true);
					bw = new BufferedWriter(fw);

					bw.write("\n"+newCategory);
					
					
					
					
					
				}
				catch(IOException ex)
				{
					ex.printStackTrace();
				}
				finally {

					try {

						if (bw != null)
							bw.close();

						if (fw != null)
							fw.close();
						formPanelLeft.loadCategoryInfoFromFile("category.txt");
						formPanelLeft.addComboItem();

					} catch (IOException ex) {

						ex.printStackTrace();

					}
				}
			
				
				
			}
			
		});
	////implementation of interface method for show report button event.
		formPanelLeft.setFormListener2(new FormListener(){
			public void formEventOccured(FormEvent e){
				String report=e.getReport();
	//removing graph-panel in order to get the graphs refreshed.			
				remove(formPanelCenter);
				ExpenseManager.setReport(report);
				ExpenseManager.setStartDate();
				
				formPanelCenter.reportChanged();
			    add(formPanelCenter,BorderLayout.CENTER);
	//repainting , validating and adding refreshed graphs.
			    formPanelCenter.revalidate();
			    formPanelCenter.repaint();
			    add(formPanelCenter);
			}
		});
		
	   
	   
	     add(formPanelLeft,BorderLayout.WEST);
	    add(formPanelCenter,BorderLayout.CENTER);
	   
//setting frame visibility to true, in order to make it visible.	    
	    setVisible(true);
		
	}

	
}
