package Graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.orsoncharts.util.json.parser.ParseException;

import components.ExpenseManager;
 
public class PieChart extends ApplicationFrame {
	
   public PieChart( String title ) throws FileNotFoundException, java.text.ParseException {
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   public PieDataset createDataset( ) throws FileNotFoundException, java.text.ParseException {
	 
	  ExpenseManager.getInstance();
	  Date startDate = ExpenseManager.getStartDate();
	  Date endDate   = ExpenseManager.getEndDate();
	  HashMap<String,Double> dataMap =  ExpenseManager.computeCategorySum(startDate, endDate);
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      for(String category:dataMap.keySet())
      {
    	  dataset.setValue(category, dataMap.get(category));
      }
      
      return dataset;         
   }
   
 
   	public JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Expenses",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);
     //plotting the chart.
      PiePlot plot2 = (PiePlot) chart.getPlot();
      //creating labels.
      plot2.setLabelGenerator((PieSectionLabelGenerator) new StandardPieItemLabelGenerator(
               "{0} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()
           ));
     //setting label fonts
      plot2.setLabelFont(new Font("Arial Unicode MS", 0, 20));
      plot2.setSectionOutlinesVisible(true);
      //adding stroke to graph.
      plot2.setBaseSectionOutlinePaint(new Color(0xFFFFFF));
      plot2.setBaseSectionOutlineStroke(new BasicStroke(2F));
      chart.getPlot().setBackgroundPaint( new Color(204,255,229) );
    
      return chart;
      
   }
   
   public JPanel createDemoPanel( ) throws FileNotFoundException, java.text.ParseException {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }

}
