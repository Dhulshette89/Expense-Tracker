package components;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ExpenseManager {
	
	private static List<Expense> expenseList;
	private static ExpenseManager expenseManager;
	private static Date startDate;
	private static Date endDate;
	private LeftPanel leftPanel;
	private static String report1="weekly";
 //constructor of Expense Manager loads data from file , instantiating leftPanel to add.
	private ExpenseManager() throws FileNotFoundException
	{
		this.loadExpensesFromFile();
		Date date = new Date();
		endDate=date ;
		leftPanel=new LeftPanel();
		setStartDate();
	}
	
	
	public static Date getStartDate() {
		return startDate;
	}


	public static void setStartDate() {
	//as per the choice given by user setting the start date of the report.
		
		if(report1.equals("weekly"))
		{
			Calendar calendar = Calendar.getInstance(); // this would default to now
			calendar.add(Calendar.DAY_OF_MONTH, -7);
			startDate=calendar.getTime();
			System.out.println("this is start"+startDate);
		}
		else if(report1.equals("monthly"))
		{
			Calendar calendar = Calendar.getInstance(); // this would default to now
			calendar.add(Calendar.MONTH, -1);
			startDate=calendar.getTime();
			System.out.println("this is start"+startDate);
		
		}
		
		else if(report1.equals("yearly"))
		{
			Calendar calendar = Calendar.getInstance(); // this would default to now
			calendar.add(Calendar.YEAR, -1  );
			startDate=calendar.getTime();
			System.out.println("this is start"+startDate);
			
		}
		ExpenseManager.startDate = startDate;
	}


	public static Date getEndDate() {
		return endDate;
	}

	public static ExpenseManager getInstance() throws FileNotFoundException
	{
		if(expenseManager == null)
		{
			expenseManager = new ExpenseManager();
		}
		return expenseManager;
	}
	public static void loadExpensesFromFile() 
	{
		expenseList = new ArrayList<Expense>();
		File file = new File("expenses.txt");
		try 
		{	
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    while(scanner.hasNext())
		    {
		    	String expenseRecord = scanner.nextLine();
		    	String[] tokens=expenseRecord.split(" ");
		    	if(tokens.length == 3)
		    	{
		    		Expense expense = new Expense();
		    		expense.setDate(Expense.getDateFormat().parse(tokens[0]));
		    		expense.setCategory(tokens[1]);
		    		expense.setAmount(Double.valueOf(tokens[2]));
		    		expenseList.add(expense);
		    	}
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void setReport(String report) {
		report1 = report;
	}
//computing and generating map for the data with filtered dates.
	public static HashMap<String,Double> computeCategorySum(Date startDate, Date endDate)
	{	
		loadExpensesFromFile();
		HashMap<String,Double> map = new HashMap<String,Double>(); 
		for(Expense exp:expenseList)
		{
			if(exp.getDate().compareTo(endDate) <=0 && exp.getDate().compareTo(startDate)>=0)
			{
				if(!map.containsKey(exp.getCategory()))
				{
					map.put(exp.getCategory(), exp.getAmount());
					
				}
				else
				{
					Double value = map.get(exp.getCategory());
					value = value + exp.getAmount();
					map.put(exp.getCategory(), value);
				}
			}
		}
		
		return map;
		
	}

}
