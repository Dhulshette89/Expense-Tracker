package components;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class LeftPanel extends JPanel {
	private JLabel dateLabel;
	private JLabel categoryLabel;
	private JLabel amountLabel;
	private JDatePickerImpl datePicker;
	private JTextField amountField;
	private JComboBox categoryBox;
	private JButton submitBtn;
	
	private JLabel addExpenseLabel;
	private JButton addCatBtn;
	private FormPanel formPanel;
	private JLabel addCatLabel;
	private JTextField addCatField;
	private JLabel checkLabel;
	private ArrayList<String> catArrayList;
	private String[] catString;
	private JLabel repLabel;
	private JButton showReportBtn;
	private FormPanelCat formPanelCat;
	private FormPanelReport formPanelReport;
	
	private JRadioButton weeklyRadio;
	private JRadioButton monthlyRadio;
	private JRadioButton yearlyRadio;
	private JRadioButton r4;
	private FormListener formListener;
	private FormListener formListener1;
	private FormListener formListener2;
	ButtonGroup bgRadio;
	
	
	public LeftPanel() throws FileNotFoundException
	{	
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim=getPreferredSize();
		dim.width=screenSize.width/4;
		setPreferredSize(dim);
		//setSize(150,700);
		setLayout(new GridLayout(0,1));
		formPanel=new FormPanel();
		formPanel.setBackground(new Color(204,229,255));
		formPanelCat=new FormPanelCat();
		formPanelCat.setBackground(new Color(204,204,255));
		formPanelReport=new FormPanelReport();
		formPanelReport.setBackground(new Color(229,204,255));
		add(formPanel);
		add(formPanelCat);
		add(formPanelReport);
	}
			
	class FormPanel extends JPanel{
			
			@SuppressWarnings("rawtypes")
			public FormPanel() throws FileNotFoundException
			{
				
				
	// Labels & TextField//
				setBorder(BorderFactory.createTitledBorder("Add expenses"));
				dateLabel=new JLabel("Date ");
				dateLabel.setFont(new Font(dateLabel.getFont().getName(), dateLabel.getFont().getStyle(), 20));
				dateLabel.setPreferredSize(new Dimension(100, 75)); 
				
				categoryLabel=new JLabel("Category ");
				categoryLabel.setFont(new Font(categoryLabel.getFont().getName(), categoryLabel.getFont().getStyle(), 20));
				categoryLabel.setPreferredSize(new Dimension(100, 75));
				
				amountLabel=new JLabel("Amount");
				amountLabel.setFont(new Font(amountLabel.getFont().getName(), amountLabel.getFont().getStyle(), 20));
				amountLabel.setPreferredSize(new Dimension(100, 75));
				
				
				
				
				
				
				
				
				amountField=new JTextField(10);
				Dimension d1=amountField.getPreferredSize();
				d1.height=40;
				amountField.setPreferredSize(d1);
				amountField.setFont(new Font("Arial",Font.BOLD,17));
				
	//Button//
				
				submitBtn=new JButton("Add Expense");
				submitBtn.setFont(new Font("Arial",Font.BOLD,17));
				submitBtn.setPreferredSize(new Dimension(140, 40));
				
				
				
				
	//Reading from file for setting category comboBox//
				loadCategoryInfoFromFile("category.txt");
				
	// ComboBox//
				
				categoryBox=new JComboBox(catString);
				categoryBox.setSelectedIndex(4);
				categoryBox.setFont(new Font("Arial",Font.BOLD,20));
				Dimension d2=categoryBox.getPreferredSize();
				d2.height=40;
				categoryBox.setPreferredSize(d2);	
				
	
	//listener//
				submitBtn.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						if(amountField.getText().isEmpty()==false)
						{
						//String date=dateField.getText();
						String amount=amountField.getText();
						String category=(String) categoryBox.getSelectedItem();
						
						String report=bgRadio.getSelection().getActionCommand();
						
						Date selectedDate = (Date) datePicker.getModel().getValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(selectedDate);
						int month = cal.get(Calendar.MONTH) + 1;
						int day   = cal.get(Calendar.DAY_OF_MONTH);
						int year = cal.get(Calendar.YEAR);
						
						StringBuilder dateString = new StringBuilder();
						if(month < 10)
							dateString.append('0');
						dateString.append("" + month);
						dateString.append('/');
						if(day<10)
							dateString.append('0');
						dateString.append("" + day);
						dateString.append('/');
						dateString.append(year);
						//System.out.println("DatePicker:" + month);
						
						FormEvent ev=new FormEvent(this,dateString.toString(),category,amount);
						JOptionPane.showMessageDialog( submitBtn, "Expenses added");
						amountField.setText("");
						
						
						if(formListener!=null)
							formListener.formEventOccured(ev);
						}
						else{
							JOptionPane.showMessageDialog( submitBtn, "cannot add Expenses,empty fields");
						}
					}
					
				});
				
	//
	//Layout//
				setLayout(new GridBagLayout());
				GridBagConstraints gc= new GridBagConstraints();
				
				///////////////////First Row///////////////
				gc.weightx=1;
				gc.weighty=0.1;
				gc.gridx=0;
				gc.gridy=0;
				gc.fill=GridBagConstraints.NONE;
				gc.anchor=GridBagConstraints.LINE_END;
				gc.insets=new Insets(0,0,0,5);
				add(dateLabel,gc);
				/////////////
				
				
			
				
				
				UtilDateModel model = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				model.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
				model.setSelected(true);
				JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
				datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
				JFormattedTextField textField = datePicker.getJFormattedTextField();
				textField.setFont(new Font("Arial", Font.BOLD, 18));
				gc.weightx=1;
				gc.weighty=0.1;
				gc.anchor=GridBagConstraints.LINE_START;
				/////////////
				gc.gridx=1;
				gc.gridy=0;
				add(datePicker,gc);
				
				
				/////////////////////////2nd row///////////////////
				gc.weightx=1;
				gc.weighty=0.1;
				gc.gridx=0;
				gc.gridy=1;
				gc.fill=GridBagConstraints.NONE;
				gc.anchor=GridBagConstraints.LINE_END;
				gc.insets=new Insets(0,0,0,5);
				add(categoryLabel,gc);
				/////////////
				gc.gridx=1;
				gc.gridy=1;
				gc.anchor=GridBagConstraints.LINE_START;
				add(categoryBox,gc);
				
				/////////////////3rd row////////////////////
				gc.weightx=1;
				gc.weighty=0.1;
				gc.gridx=0;
				gc.gridy=2;
				gc.fill=GridBagConstraints.NONE;
				gc.anchor=GridBagConstraints.LINE_END;
				gc.insets=new Insets(0,0,0,5);
				add(amountLabel,gc);
				/////////////
				gc.gridx=1;
				gc.gridy=2;
				gc.anchor=GridBagConstraints.LINE_START;
				add(amountField,gc);
		/////////////////4th row////////////////////
				
				gc.weightx=1;
				gc.weighty=2;
				/////////////
				gc.gridx=1;
				gc.gridy=3;
				gc.anchor=GridBagConstraints.LINE_START;
				add(submitBtn,gc);
		
				
	
			}		
	}
	class FormPanelCat extends JPanel
	{
		public FormPanelCat(){
			setBorder(BorderFactory.createTitledBorder("Add Category"));
			
		checkLabel=new JLabel("Category not in the list??");
		checkLabel.setFont(new Font(checkLabel.getFont().getName(), checkLabel.getFont().getStyle(), 16));
		checkLabel.setPreferredSize(new Dimension(200, 75));
		

		addCatLabel=new JLabel(" Add new category");
		addCatLabel.setFont(new Font(addCatLabel.getFont().getName(), addCatLabel.getFont().getStyle(), 20));
		addCatLabel.setPreferredSize(new Dimension(190, 75));
		
		addCatField=new JTextField(10);
		Dimension d4=addCatField.getPreferredSize();
		d4.height=40;
		addCatField.setPreferredSize(d4);
		addCatField.setFont(new Font("Arial",Font.BOLD,17));
		
		addCatBtn=new JButton("Add Category");
		addCatBtn.setFont(new Font("Arial",Font.BOLD,17));
		addCatBtn.setPreferredSize(new Dimension(150, 40));
		
	////Listener/////////////	
		addCatBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(addCatField.getText().isEmpty()==false)
				{
				String newCategory=addCatField.getText();
				FormEvent ev=new FormEvent(this,newCategory);
			
				
				if(formListener1!=null)
					formListener1.formEventOccured(ev);
				}
				else{
					
					JOptionPane.showMessageDialog( addCatBtn, "cannot add Category,empty fields");
				}
			}
			
		});
		
		
		
		//Layout//
		setLayout(new GridBagLayout());
		GridBagConstraints gc= new GridBagConstraints();
//////////////////1st row/////////////
gc.weightx=1;
gc.weighty=0.1;
gc.gridx=0;
gc.gridy=0;
gc.fill=GridBagConstraints.NONE;
gc.anchor=GridBagConstraints.LINE_END;
gc.insets=new Insets(0,0,0,5);
add(checkLabel,gc);
/////////////

///////////////////2nd row//
gc.weightx=1;
gc.weighty=0.1;
gc.gridx=0;
gc.gridy=1;
gc.fill=GridBagConstraints.NONE;
gc.anchor=GridBagConstraints.LINE_END;
gc.insets=new Insets(0,0,0,5);
add(addCatLabel,gc);
/////////////
gc.gridx=1;
gc.gridy=1;
gc.anchor=GridBagConstraints.LINE_START;
add(addCatField,gc);
/////////////////3rd row////////////////
gc.weightx=1;
gc.weighty=2;

/////////////
gc.gridx=1;
gc.gridy=2;
gc.anchor=GridBagConstraints.LINE_START;
add(addCatBtn,gc);


		
	}
	}
	class FormPanelReport extends JPanel{
		
		public FormPanelReport(){
			
			setBorder(BorderFactory.createTitledBorder("select reports"));
			repLabel=new JLabel("Select expense report ");
		repLabel.setFont(new Font(repLabel.getFont().getName(), repLabel.getFont().getStyle(), 20));
		repLabel.setPreferredSize(new Dimension(220, 75));
		
		//Radio Buttons//
		JRadioButton weeklyRadio=new JRadioButton("Weekly Report");
		JRadioButton monthlyRadio=new JRadioButton("Monthly Report");    
		JRadioButton yearlyRadio=new JRadioButton("Yearly Report"); 
		JRadioButton r4=new JRadioButton("Customized Report"); 
		
		weeklyRadio.setBackground(new Color(229,204,255));
		monthlyRadio.setBackground(new Color(229,204,255));
		yearlyRadio.setBackground(new Color(229,204,255));
		
		weeklyRadio.setActionCommand("weekly");
		monthlyRadio.setActionCommand("monthly");
		yearlyRadio.setActionCommand("yearly");
		
		
		
		weeklyRadio.setPreferredSize(new Dimension(200, 75));
		monthlyRadio.setPreferredSize(new Dimension(200, 75));
		yearlyRadio.setPreferredSize(new Dimension(200, 75));
		r4.setPreferredSize(new Dimension(200, 75));
		
		weeklyRadio.setFont(new Font(weeklyRadio.getFont().getName(), weeklyRadio.getFont().getStyle(), 20));
		monthlyRadio.setFont(new Font(monthlyRadio.getFont().getName(), monthlyRadio.getFont().getStyle(), 20));
		yearlyRadio.setFont(new Font(yearlyRadio.getFont().getName(), yearlyRadio.getFont().getStyle(), 20));
		r4.setFont(new Font(r4.getFont().getName(), r4.getFont().getStyle(), 20));
		bgRadio=new ButtonGroup();
		bgRadio.add(weeklyRadio);
		bgRadio.add(monthlyRadio);
		bgRadio.add(yearlyRadio);
		weeklyRadio.setSelected(true);
		

		showReportBtn=new JButton("Show Report");
		showReportBtn.setFont(new Font("Arial",Font.BOLD,17));
		showReportBtn.setPreferredSize(new Dimension(150, 40));
		
	////Listener/////////////	
			showReportBtn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					String report=bgRadio.getSelection().getActionCommand();
					FormEvent ev=new FormEvent(this,report,1);
					
					if(formListener2!=null)
						formListener2.formEventOccured(ev);
				}
				
			});
		
		//Layout//
		setLayout(new GridBagLayout());
		GridBagConstraints gc= new GridBagConstraints();
		
//////////////////1st row////////////////////////
		gc.weightx=1;
		gc.weighty=0.1;
		gc.gridx=0;
		gc.gridy=0;
		gc.fill=GridBagConstraints.NONE;
		gc.anchor=GridBagConstraints.LINE_END;
		gc.insets=new Insets(0,0,0,5);
		add(repLabel,gc);
		/////////////
///////////////////2nd row////////////////////
		gc.weightx=1;
		gc.weighty=0.1;
		/////////////
		gc.gridx=1;
		gc.gridy=1;
		gc.anchor=GridBagConstraints.LINE_START;
		add(weeklyRadio,gc);
//////////////////3rd row///////////////////////
		gc.weightx=1;
		gc.weighty=0.1;
		gc.gridx=1;
		gc.gridy=2;
		gc.anchor=GridBagConstraints.LINE_START;
		add(monthlyRadio,gc);
//////////////////4th row/////////////////////
		gc.weightx=1;
		gc.weighty=0.1;
		gc.gridx=1;
		gc.gridy=3;
		gc.anchor=GridBagConstraints.LINE_START;
		add(yearlyRadio,gc);
/////////////////5th row///////////////////////////////			
		gc.weightx=1;
		gc.weighty=2;
		gc.weightx=1;
		gc.weighty=1;
		gc.gridx=1;
		gc.gridy=4;
		gc.anchor=GridBagConstraints.LINE_START;
		add(showReportBtn,gc);	
		

	}
		
		
	}
		public void setFormListener1(FormListener formListener)
		{
			this.formListener1=formListener;
		}
//set listener for add report		
		public void setFormListener2(FormListener formListener)
		{
			this.formListener2=formListener;
		}
		
	//set listener method for add expense button.	
		public void setFormListener(FormListener formListener)
		{
		this.formListener=formListener;	
		}
		
		
		public void loadCategoryInfoFromFile(String FileName) throws FileNotFoundException
		{
			catArrayList = new ArrayList<String>();
			
			File file = new File(FileName);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
//copying data in the arrayList of type string			
		    while(scanner.hasNext())
		    {
		    	String category = scanner.nextLine();
		    	catArrayList.add(category);
		    }
		    catString = new String[catArrayList.size()];
//saving ArrayList to normal string Array.
		    catArrayList.toArray(catString);
		    
		   
		    
		    
		    
		}
		public void addComboItem(){
			//dynamic addition of new category , box pop-up as soon as new category gets added
		    DefaultComboBoxModel cboNewModel = new DefaultComboBoxModel(catString);
		    categoryBox.setModel(cboNewModel);
		    categoryBox.showPopup();
		}
	}



