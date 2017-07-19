package Graphs;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;


public class CenterPanel extends JPanel {
	
	private int width;
	private int height;
	
	private JPanel jp;
	private JPanel barPane;
	private GraphAndTable graphAndTable;
	private JScrollPane tablePane;
	GridLayout g;
	final BarChart demo;
	
	public CenterPanel() throws FileNotFoundException, ParseException
	{	
		g=new GridLayout(0,1);
		setLayout(g);
		
	//instantiating graph and table panel to add in CenterPanel.
		graphAndTable=new GraphAndTable();
		
		
		demo = new BarChart("Expenses in each Category");
		barPane=demo.getPanel();
		setBorder(BorderFactory.createTitledBorder("Expenses & Reports"));
	//adding panels to centerPanel
		this.add(barPane,g);
		this.add(graphAndTable,g);
		
		
		
		
	}
//function to change the graphs.
	public void reportChanged()
	{
	//calling function of graphAnnTable to change the reports 
		graphAndTable.reportChanged();
	//removing existing panel.
		remove(barPane);
		barPane=demo.getPanel();
	//adding refreshed panel.
		this.add(barPane,g);
		this.add(graphAndTable,g);
		
	} 
			
	

		
	}

	
		





