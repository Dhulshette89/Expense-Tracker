package Graphs;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.text.TextBlockAnchor;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;

import components.ExpenseManager;


public class BarChart extends ApplicationFrame  {
	private ChartPanel chartPanel;
//instantiating chart & creating chart panel.
	public BarChart(String title) throws ParseException, FileNotFoundException {
		super(title);
		JFreeChart chart = createChart(createDataset( ));
		chartPanel = new ChartPanel(chart);

	}
	//creating data-set for bar chart.
	public DefaultCategoryDataset createDataset( ) throws ParseException, FileNotFoundException
	{
		 ExpenseManager.getInstance();
		 Date startDate = ExpenseManager.getStartDate();
		 Date endDate   = ExpenseManager.getEndDate();
	//computing data-set with start date & end date filter.
		  HashMap<String,Double> dataMap =  ExpenseManager.computeCategorySum(startDate, endDate);
		  System.out.print(dataMap);
		  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
			for(String category:dataMap.keySet())
		      {
		    	  dataset.setValue(dataMap.get(category),category, category);
		      }
		      
		      return dataset;
		
	}
//chart generation with the help of data-set.
	public JFreeChart createChart(CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart3D("Expenses in each Category", // chart
																						// title
				"Category", // domain axis label
				"Value", // range axis label
				dataset, // data
				PlotOrientation.HORIZONTAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);
//plotting the chart
		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setForegroundAlpha(1.0f);
		 ValueAxis axis1 = plot.getRangeAxis();
		 Font font1 = new Font("Dialog", Font.PLAIN, 15);
		 axis1.setTickLabelFont(font1);

		final CategoryAxis axis = plot.getDomainAxis();
		Font font = new Font("Dialog", Font.BOLD, 18);
		axis.setTickLabelFont(font);
		final CategoryLabelPositions p = axis.getCategoryLabelPositions();

		final CategoryLabelPosition left = new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT,
				TextAnchor.CENTER_LEFT, 0.0, CategoryLabelWidthType.RANGE, 0.30f);
		axis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));
		
		 CategoryPlot cplot = (CategoryPlot)chart.getPlot();
		    cplot.setBackgroundPaint(SystemColor.inactiveCaption);//change background color

		    //set  bar chart color

		    ((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());

		    BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
		    
		    chart.getPlot().setBackgroundPaint( new Color(204,255,229) );
		    r.setSeriesPaint(0, Color.blue);
		    r.setSeriesPaint(1,new Color(204,0,102));
		    r.setSeriesPaint(2, Color.orange);
		    r.setSeriesPaint(3, new Color(0,102,0));
		    r.setSeriesPaint(4, Color.magenta);
		    r.setSeriesPaint(5, new Color(255,153,153));
		    r.setSeriesPaint(6, Color.red);
		    r.setSeriesPaint(7,new Color(102,51,0));
		    r.setSeriesPaint(8, new Color(153,76,0));
		    r.setSeriesPaint(9, Color.DARK_GRAY);
		    
				
		return chart;

	}
	//get panel method.
	public ChartPanel getPanel()
	{
		JFreeChart chart = null;
		try {
			chart = createChart(createDataset( ));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chartPanel = new ChartPanel(chart);
		return chartPanel ;
	}
	
}
