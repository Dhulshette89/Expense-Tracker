package components;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//creating expense object with 3 attributes-date , category, amount.
public class Expense {
	private static String DATE_FORMAT = "MM/dd/yyyy"; 
	private Date date;
	private String category;
	private Double amount;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public static DateFormat getDateFormat()
	{
		return new SimpleDateFormat(DATE_FORMAT); 
	}

}
