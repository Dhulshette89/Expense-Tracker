package Graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class GraphAndTable extends JPanel {
	
	private JScrollPane tablePane;
	private JPanel jp;
	private PieChart pc;
	private MyTable myTable;
	//constructor to initialize & instantiate the required panels.
	public GraphAndTable() throws ParseException, FileNotFoundException
	{	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim=getPreferredSize();
		dim.width=screenSize.width/4;
		setPreferredSize(dim);
	//the 0,2 in gridLayout signifies 2 columns in a layout.
		setLayout(new GridLayout(0,2));
		myTable=new MyTable();
		tablePane=myTable.getScrollPane();
		pc = new PieChart("Expenses");
		jp=new JPanel();
		jp=pc.createDemoPanel();
		add(tablePane);
		add(jp);
	}
	public void reportChanged()
	{
		try {
			
	//implementing refresh graph function to generate newly requested report type.
			remove(tablePane);
			myTable.createDataAndTable();
			tablePane=myTable.getScrollPane();
			add(tablePane);
			pc.createChart(pc.createDataset());
			remove(jp);
			jp=pc.createDemoPanel();
			add(jp);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
