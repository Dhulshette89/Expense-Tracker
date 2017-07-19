package Graphs;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import components.ExpenseManager;
   
public class MyTable extends JPanel {    
    JTable jt;
  //adding scrollPane helps to make table much flexible for any number of rows that goes beyond the frame space.
    JScrollPane tablePane;
    JScrollPane sp1;
    
    MyTable() throws ParseException, FileNotFoundException{    
    //f=new JFrame();
    	setLayout(new GridBagLayout());
    	setBorder(BorderFactory.createTitledBorder("Expenses Table"));
    	createDataAndTable();
    }
    
    public void createDataAndTable() throws FileNotFoundException{
    	ExpenseManager.getInstance();
    	 Date startDate = ExpenseManager.getStartDate();
		 Date endDate   = ExpenseManager.getEndDate();
//Generating data and copying it into data & column format.
		  HashMap<String,Double> dataMap =  ExpenseManager.computeCategorySum(startDate, endDate);
		  System.out.print(dataMap);
		  String[][] data = new String[dataMap.keySet().size()][2];
		  int i =0;
		  for(String category:dataMap.keySet())
	      {
			  data[i][0] = category;
			  data[i][1] = dataMap.get(category).toString();
			  i++;
	      }
		  String column[]={"Category","Expenses( $ )"};  
		  DefaultTableModel model1 = new DefaultTableModel(data, column);
		  JTable jTable1 = new JTable(model1);
		  jTable1.setBackground(new Color(204,255,229));
  //setting title font-1st row of table
		  jTable1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 25));
  //setting other row fonts and row heights.
		  jTable1.setRowHeight(60);
		  jTable1.setFont(new Font("Arial", Font.BOLD, 22));
		  sp1 = new JScrollPane();
    
		  sp1.setViewportView(jTable1);
		  JTable jt = new JTable(model1);
    }
    //return the pane containing table
   public JScrollPane getScrollPane()
   {
	   return sp1;
   }
}     
