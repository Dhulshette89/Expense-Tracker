package components;

import java.util.Date;
import java.util.EventObject;
//creating a formevent class to pass it in listener with useful attributes.
public class FormEvent extends EventObject{
	
	private String date;
	private String category;
	private String amount;
	private String report;
	private String newCategory;
	//type of constructor which matches Add expense button event.
	public FormEvent(Object arg0,String date,String category,String amount) {
		super(arg0);
		this.date=date;
		this.category=category;
		this.amount=amount;
		
	
	}
	//type of constructor which matches add category button event.
	public FormEvent(Object arg0,String newCategory)
	{
		super(arg0);
		this.newCategory=newCategory;
	}
	public String getReport() {
		return report;
	}
	//type of constructor which matches show report button event
	public FormEvent(Object arg0,String report,int i)
	{
		super(arg0);
		this.report=report;
	}

	public String getNewCategory() {
		return newCategory;
	}
	
	public FormEvent(Object arg0) {
		super(arg0);
		
	}

	public String getDate() {
		return date;
	}

	public String getCategory() {
		return category;
	}
	public String getAmount() {
		return amount;
	}

}
