/* Jacob Wagner
 * 500754931
 */
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.*;
import java.io.*;
//@SuppressWarnings("serial")
public class AppointmentFrame extends JFrame
{
	static Calendar cal;
	SimpleDateFormat format;
	Stack<Appointment> previous = new Stack<Appointment>();
	JLabel date;
	String curDate;
	SimpleDateFormat format2;
	SimpleDateFormat format3;
	String curMonth;
	Contacts contact;
	ArrayList<Appointment> holder;
	JTextArea dayAppoint;
	JScrollPane area;
	JPanel panel;
	JPanel datePanel;
	JPanel panel2;
	JButton leftArrow;
	JButton rightArrow;
	ActionListener listener1;
	ActionListener listener2;
	ActionListener listener3;
	ActionListener listener4;
	ActionListener listener5;
	ActionListener listener6;
	ActionListener listener7;
	ActionListener listener8;
	JButton calendar[][] = new JButton[6][7];
	JPanel calHolder[][] = new JPanel[7][7];
	ActionListener calendarList[] = new ActionListener[31];
	JLabel day;
	JTextField day1;
	JLabel month;
	JTextField month1;
	JLabel year;
	JTextField year1;
	JButton show;
	JPanel actionPanel;
	JLabel hour;
	JTextField hour1;
	JLabel minute;
	JTextField minute1;
	JButton create;
	JButton cancel;
	JButton recall;
	JPanel descPanel;
	JTextArea descArea;
	JPanel contactPanel;
	JLabel lastName;
	JLabel firstName;
	JTextField lastField;
	JTextField firstField;
	JLabel telephone;
	JLabel email;
	JTextField teleField;
	JTextField emailField;
	JLabel address;
	JTextField addressField;
	JTextField pushField;
	JButton find;
	JButton clear;
	JLabel month2;
	JPanel calPanel;
	JLabel sunday;
	JLabel monday;
	JLabel tuesday;
	JLabel wednesday;
	JLabel thursday;
	JLabel friday;
	JLabel saturday;
	public AppointmentFrame() throws IOException
	{
		setSize(1000,800);
		setLayout(new BorderLayout());
		holder = new ArrayList<Appointment>();
		contact = new Contacts();
		cal = new GregorianCalendar(2020, 1, 2, 0, 0);
		listener1 = new dayBackward();
		listener2 = new dayForward();
		listener3 = new dayJump();
		listener4 = new createBut();
		listener5 = new deleteBut();
		listener6 = new clearBut();
		listener7 = new findBut();
		listener8 = new recallWork();
		for(int i = 0;i < 31;i++)
		{
			calendarList[i] = new CalMove(i);
		}
		createScreen();
		contact.readContactsFile(descArea);	
		updateArea();
	}
	public void createScreen()
	{
		topDate();
		controlPanel();
		controlPanel2();
	}
	public void updateArea()
	{
		dayAppoint.setText("");
		String timeShift = "";
		for(int i = 0;i < holder.size();i++)
		{
			if(holder.get(i).getYear() == cal.get(Calendar.YEAR) && holder.get(i).getMonth() == cal.get(Calendar.MONTH) && holder.get(i).getDay() == cal.get(Calendar.DAY_OF_MONTH))
			{
				if(holder.get(i).getMinute() == 0)
				{
					timeShift = "0";
				} else timeShift = "";
				String intercept = "";
				if(holder.get(i).getMinute() < 10)
				{
					intercept = "0" + holder.get(i).getMinute();
				} else {
					intercept = "" + holder.get(i).getMinute();
				}
				dayAppoint.append(holder.get(i).getHour() + ":" + intercept + timeShift + " - " + holder.get(i).getDesc() + " - " + holder.get(i).getFirstName() + " " + holder.get(i).getLastName() + " " + holder.get(i).getTelephone() + " " + holder.get(i).getEmail() + " " + holder.get(i).getAddress() + "   \n\n");
			}
		}
	}
	public void appSort()
	{
		Collections.sort(holder);
	}
	public void topDate()
	{
		String pattern = "EEE, MMM dd, yyyy";
		format = new SimpleDateFormat(pattern);
		format.setCalendar(cal);
		date = new JLabel(curDate);
		date.setSize(295,10);
		updateDate();
		date.setVisible(true);
		add(date, BorderLayout.NORTH);
	}
	public void updateDate()
	{
		curDate = format.format(cal.getTime());
		date.setText(curDate);
	}
	public void topMonth()
	{
		String pattern3 = "MMM";
		format3 = new SimpleDateFormat(pattern3);
		format3.setCalendar(cal);
		month2 = new JLabel(curMonth);
		month2.setPreferredSize(new Dimension(450,20));
		updateMonth();
		month2.setVisible(true);
		panel2.add(month2);
	}
	public void updateMonth()
	{
		curMonth = format3.format(cal.getTime());
		month2.setText(curMonth);
	}
	public void textArea(JPanel panel)
	{
		dayAppoint = new JTextArea();
		dayAppoint.setEditable(false);
		area = new JScrollPane(dayAppoint);
		area.setPreferredSize(new Dimension(475,250));
		area.setVisible(true);
		panel.add(area, BorderLayout.WEST);
	}
	public void controlPanel()
	{
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(500,400));
		panel.setVisible(true);
		textArea(panel);
		subPanel1(panel);
		subPanel2(panel);
		add(panel, BorderLayout.WEST);
	}
	public void controlPanel2()
	{
		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(500,800));
		panel2.setVisible(true);
		topMonth();
		subPanel4(panel2);
		subPanel5(panel2);
		subPanel6(panel2);
		add(panel2,BorderLayout.CENTER);
	}
	public void subPanel1(JPanel panel)
	{
		
		datePanel = new JPanel();
		datePanel.setVisible(true);
		datePanel.setBorder(new TitledBorder(new EtchedBorder(), "Date"));
		datePanel.setLayout(new BorderLayout());
		datePanel.setPreferredSize(new Dimension(475,225));
		miniPanel1(datePanel);
		miniPanel2(datePanel);
		miniPanel3(datePanel);
		panel.add(datePanel);
	}
	public void miniPanel1(JPanel datePanel)
	{
		JPanel minipanel = new JPanel();
		minipanel.setVisible(true);
		leftArrow = new JButton("<");
		leftArrow.setPreferredSize(new Dimension(120,30));
		leftArrow.setVisible(true);
		leftArrow.addActionListener(listener1);
		minipanel.add(leftArrow);
		rightArrow = new JButton(">");
		rightArrow.setPreferredSize(new Dimension(120,30));
		rightArrow.setVisible(true);
		rightArrow.addActionListener(listener2);
		minipanel.add(rightArrow);
		datePanel.add(minipanel, BorderLayout.NORTH);
	}
	public void miniPanel2(JPanel datePanel)
	{
		JPanel miniPanel = new JPanel();
		miniPanel.setVisible(true);
		day = new JLabel("Day:");
		day.setVisible(true);
		miniPanel.add(day);
		day1 = new JTextField();
		day1.setPreferredSize(new Dimension(35,25));
		day1.setVisible(true);
		miniPanel.add(day1);
		month = new JLabel("Month:");
		month.setVisible(true);
		miniPanel.add(month);
		month1 = new JTextField();
		month1.setPreferredSize(new Dimension(35,25));
		month1.setVisible(true);
		miniPanel.add(month1);
		year = new JLabel("Year:");
		year.setVisible(true);
		miniPanel.add(year);
		year1 = new JTextField();
		year1.setPreferredSize(new Dimension(55,25));
		year1.setVisible(true);
		miniPanel.add(year1);
		datePanel.add(miniPanel, BorderLayout.CENTER);
	}
	public void miniPanel3(JPanel datePanel)
	{
		JPanel miniPanel = new JPanel();
		miniPanel.setVisible(true);
		show = new JButton("Show");
		show.setPreferredSize(new Dimension(75,25));
		show.setVisible(true);
		show.addActionListener(listener3);
		miniPanel.add(show);
		datePanel.add(miniPanel, BorderLayout.SOUTH);
	}
	public class dayForward implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			cal.add(Calendar.DAY_OF_MONTH, 1);
			calDraw();
			updateMonth();
			updateDate();
			updateArea();
		}
	}
	public class dayBackward implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
			calDraw();
			updateMonth();
			updateDate();
			updateArea();		
		}
	}
	public class dayJump implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(tryParseInt(year1.getText()) && tryParseInt(month1.getText()) && tryParseInt(day1.getText()) && Integer.parseInt(year1.getText()) < cal.getMaximum(GregorianCalendar.YEAR) && Integer.parseInt(month1.getText()) <= 12 && Integer.parseInt(day1.getText()) <= cal.getMaximum(GregorianCalendar.DAY_OF_MONTH) && Integer.parseInt(day1.getText()) > 0 && Integer.parseInt(month1.getText()) > 0 && Integer.parseInt(year1.getText()) >= cal.getMinimum(GregorianCalendar.YEAR))
			{
				cal.set(Integer.parseInt(year1.getText()), (Integer.parseInt(month1.getText())) - 1, Integer.parseInt(day1.getText()));
				descArea.setText("");
				day1.setText("");
				month1.setText("");
				year1.setText("");
			} else {
				descArea.setText("One of the Show fields is invalid, please fix before continuing.");
			}
			calDraw();
			updateMonth();
			updateArea();
			updateDate();
		}
	}
	public void subPanel2(JPanel panel)
	{
		actionPanel = new JPanel(); 
		actionPanel.setVisible(true);
		actionPanel.setPreferredSize(new Dimension(475,225));
		actionPanel.setBorder(new TitledBorder(new EtchedBorder(),"Action"));
		actionPanel.setLayout(new BorderLayout());
		miniPanel4(actionPanel);
		miniPanel5(actionPanel);
		panel.add(actionPanel);
	}
	public void miniPanel4(JPanel actionPanel)
	{
		JPanel miniPanel = new JPanel();
		miniPanel.setVisible(true);
		hour = new JLabel("Hour:");
		hour.setVisible(true);
		miniPanel.add(hour);
		hour1 = new JTextField();
		hour1.setPreferredSize(new Dimension(65,25));
		hour1.setVisible(true);
		miniPanel.add(hour1);
		minute = new JLabel("Minute:");
		minute.setVisible(true);
		miniPanel.add(minute);
		minute1 = new JTextField();
		minute1.setPreferredSize(new Dimension(65,25));
		minute1.setVisible(true);
		miniPanel.add(minute1);
		actionPanel.add(miniPanel, BorderLayout.NORTH);
	}
	public void miniPanel5(JPanel actionPanel)
	{
		JPanel miniPanel = new JPanel();
		miniPanel.setVisible(true);
		create = new JButton("Create");
		create.setPreferredSize(new Dimension(75,35));
		create.setVisible(true);
		create.addActionListener(listener4);
		miniPanel.add(create);
		cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(75,35));
		cancel.setVisible(true);
		cancel.addActionListener(listener5);
		miniPanel.add(cancel);
		recall = new JButton("Recall");
		recall.setPreferredSize(new Dimension(75,35));
		recall.setVisible(true);
		recall.addActionListener(listener8);
		miniPanel.add(recall);
		actionPanel.add(miniPanel, BorderLayout.SOUTH);
	}
	public class createBut implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dayAppoint.setText("");
			int valid = 1;
			for(int i = 0;i < holder.size();i++)
			{
				if(holder.get(i).getYear() == cal.get(Calendar.YEAR) && holder.get(i).getMonth() == cal.get(Calendar.MONTH) && holder.get(i).getDay() == cal.get(Calendar.DAY_OF_MONTH) && holder.get(i).getHour() == Integer.parseInt(hour1.getText()) && holder.get(i).getMinute() == Integer.parseInt(minute1.getText()))
				{
					valid = 0;
					dayAppoint.setText("Conflicting Appointment!");
				}
			}
			if(valid == 1)
			{
				int hold = 0;
				int hold2 = 0;
				int validInput = 0;
				int validInput2 = 0;
				String hold3[] = new String[6];
				hold3 = new String[]{" "," "," "," "," "," "};
				if(tryParseInt(hour1.getText()))
				{
					if(Integer.parseInt(hour1.getText()) != 0)
					{
						hold = Integer.parseInt(hour1.getText());
						validInput = 1;
					}
				}
				if(tryParseInt(minute1.getText()))
				{
					if(Integer.parseInt(minute1.getText()) != 0)
					{
						hold2 = Integer.parseInt(minute1.getText());
						validInput2 = 1;
					}
				}
				if(!descArea.getText().equals(""))
				{
					hold3[0] =  descArea.getText();
				}
				if(!lastField.getText().equals(""))
				{
					hold3[1] =  lastField.getText();
				}
				if(!firstField.getText().equals(""))
				{
					hold3[2] =  firstField.getText();
				}
				if(!teleField.getText().equals(""))
				{
					hold3[3] =  teleField.getText();
				}
				if(!emailField.getText().equals(""))
				{
					hold3[4] =  emailField.getText();
				}
				if(!addressField.getText().equals(""))
				{
					hold3[5] =  addressField.getText();
				}
				if(validInput == 1 && validInput2 == 1 && Integer.parseInt(hour1.getText()) <= 24 && Integer.parseInt(minute1.getText()) <= 60 && Integer.parseInt(hour1.getText()) >= 0 && Integer.parseInt(minute1.getText()) >= 0)
				{
					holder.add(new Appointment(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hold, hold2, hold3[0], hold3[1], hold3[2], hold3[3], hold3[4], hold3[5]));
					previous.push(new Appointment(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), hold, hold2, hold3[0], hold3[1], hold3[2], hold3[3], hold3[4], hold3[5]));
					descArea.setText("");
					hour1.setText("");
					minute1.setText("");
				} else {
					descArea.setText("One of the fields is invalid, please fix before proceeding.");
				}
				appSort();
				updateArea();
			}
			valid = 1;
		}
	}
	public boolean tryParseInt(String input)
	{
		try
		{
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}
	public class deleteBut implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			deleteCheck();
		}
	}
	public void deleteCheck()
	{
		for(int i = 0;i < holder.size();i++)
		{
			if(holder.get(i).getYear() == cal.get(Calendar.YEAR) && holder.get(i).getMonth() == cal.get(Calendar.MONTH) && holder.get(i).getDay() == cal.get(Calendar.DAY_OF_MONTH) && holder.get(i).getHour() == Integer.parseInt(hour1.getText()) && holder.get(i).getMinute() == Integer.parseInt(minute1.getText()))
			{
				holder.remove(i);
				hour1.setText("");
				minute1.setText("");
				previous.pop();
				updateArea();
			}
		}
	}
	public class recallWork implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dayJump2();
		}
	}
	public void dayJump2()
	{
		if(!previous.isEmpty())
		{
		cal.set(previous.peek().getYear(), previous.peek().getMonth(), previous.peek().getDay());
		hour1.setText(String.valueOf(previous.peek().getHour()));
		minute1.setText(String.valueOf(previous.peek().getMinute()));
		updateArea();
		updateDate();
		calDraw();
		updateMonth();
		} 
	}
	public void subPanel4(JPanel panel2)
	{
		calPanel = new JPanel();
		calPanel.setVisible(true);
		calPanel.setPreferredSize(new Dimension(470,280));
		calPanel.setLayout(new GridLayout(7,7));
		for(int i = 0;i < calHolder.length;i++)
		{
			for(int l = 0;l < calHolder[i].length;l++)
			{
				calHolder[i][l] = new JPanel();
				calHolder[i][l].setVisible(true);
				calHolder[i][l].setPreferredSize(new Dimension(20,60));
				calPanel.add(calHolder[i][l]);
			}
		}
		for(int i = 0;i < calendar.length;i++)
		{
			for(int p = 0;p < calendar[0].length;p++)
			{
				calendar[i][p] = new JButton();
				calendar[i][p].setVisible(true);
				calendar[i][p].setPreferredSize(new Dimension(50,35));
				calHolder[i+1][p].add(calendar[i][p]);
			}
		}
		sunday = new JLabel("Sun");
		sunday.setVisible(true);
		sunday.setPreferredSize(new Dimension(40,40));
		calHolder[0][0].add(sunday);
		monday = new JLabel("Mon");
		monday.setVisible(true);
		monday.setPreferredSize(new Dimension(40,40));
		calHolder[0][1].add(monday);
		tuesday = new JLabel("Tues");
		tuesday.setVisible(true);
		tuesday.setPreferredSize(new Dimension(40,40));
		calHolder[0][2].add(tuesday);
		wednesday = new JLabel("Wed");
		wednesday.setVisible(true);
		wednesday.setPreferredSize(new Dimension(40,40));
		calHolder[0][3].add(wednesday);
		thursday = new JLabel("Thurs");
		thursday.setVisible(true);
		thursday.setPreferredSize(new Dimension(40,40));
		calHolder[0][4].add(thursday);
		friday = new JLabel("Fri");
		friday.setVisible(true);
		friday.setPreferredSize(new Dimension(40,40));
		calHolder[0][5].add(friday);
		saturday = new JLabel("Sat");
		saturday.setVisible(true);
		saturday.setPreferredSize(new Dimension(40,40));
		calHolder[0][6].add(saturday);
		calDraw();
		panel2.add(calPanel);
	}
	public void calDraw()
	{
		Calendar cal2 = (Calendar) cal.clone();
		String pattern2 = "EEE";
		format2 = new SimpleDateFormat(pattern2);
		format2.setCalendar(cal2);
		cal2.set(Calendar.DAY_OF_MONTH,cal2.getMinimum(Calendar.DAY_OF_MONTH));
		String minDayOfWeek = format2.format(cal2.getTime());
		int dayInt = 0;
		switch(minDayOfWeek)
		{
			case "Sun.": dayInt = 1; break;
			case "Mon.": dayInt = 2; break;
			case "Tues.": dayInt = 3; break;
			case "Wed.": dayInt = 4; break;
			case "Thurs.": dayInt = 5; break;
			case "Fri.": dayInt = 6; break;
			case "Sat.": dayInt = 7; break;
		}
		cal2.set(Calendar.DAY_OF_MONTH,cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
		String maxDayOfWeek = format2.format(cal2.getTime());
		int dayInt2 = 0;
		switch(maxDayOfWeek)
		{
			case "Sun.": dayInt2 = 1; break;
			case "Mon.": dayInt2 = 2; break;
			case "Tues.": dayInt2 = 3; break;
			case "Wed.": dayInt2 = 4; break;
			case "Thurs.": dayInt2 = 5; break;
			case "Fri.": dayInt2 = 6; break;
			case "Sat.": dayInt2 = 7; break;
		}
		int hiddenDay = 1;
		int usedDay = 1;
		int curDay = cal.get(Calendar.DAY_OF_MONTH);
		for(int i = 0;i < 6;i++)
		{
			for(int p = 0;p < 7;p++)
			{
				if(hiddenDay >= dayInt && usedDay <= cal2.getActualMaximum(Calendar.DAY_OF_MONTH))
				{
					calendar[i][p].setBackground(new JButton().getBackground());
					calendar[i][p].setText(String.valueOf(usedDay));
					calendar[i][p].setVisible(true);
					calendar[i][p].addActionListener(calendarList[usedDay - 1]);
					if(curDay == usedDay)
					{
						calendar[i][p].setBackground(new Color(255,0,0));
					}
					usedDay++;
				} else {
					calendar[i][p].setVisible(false);
				}
				hiddenDay++;
			}
		}
	}
	public class CalMove implements ActionListener
	{
		int day = 0;
		public CalMove(int day2)
		{
			this.day = day2 + 1;
		}
		public void actionPerformed(ActionEvent e)
		{
			cal.set(Calendar.DAY_OF_MONTH,day);
			calDraw();
			updateDate();
			updateArea();
		}
	}
	public void subPanel5(JPanel panel2)
	{
		contactPanel = new JPanel();
		contactPanel.setVisible(true);
		contactPanel.setPreferredSize(new Dimension(470,230));
		contactPanel.setBorder(new TitledBorder(new EtchedBorder(),"Contact"));
		lastName = new JLabel("Last Name");
		lastName.setVisible(true);
		lastName.setPreferredSize(new Dimension(220,10));
		contactPanel.add(lastName);
		firstName = new JLabel("First Name");
		firstName.setVisible(true);
		firstName.setSize(200,10);
		firstName.setPreferredSize(new Dimension(220,10));
		contactPanel.add(firstName);
		lastField = new JTextField();
		lastField.setVisible(true);
		lastField.setPreferredSize(new Dimension(220,30));
		contactPanel.add(lastField);
		firstField = new JTextField();
		firstField.setVisible(true);
		firstField.setPreferredSize(new Dimension(220,30));
		contactPanel.add(firstField);
		telephone = new JLabel("Telephone");
		telephone.setVisible(true);
		telephone.setPreferredSize(new Dimension(220,10));
		contactPanel.add(telephone);
		email = new JLabel("Email");
		email.setVisible(true);
		email.setSize(200,10);
		email.setPreferredSize(new Dimension(220,10));
		contactPanel.add(email);
		teleField = new JTextField();
		teleField.setVisible(true);
		teleField.setPreferredSize(new Dimension(220,30));
		contactPanel.add(teleField);
		emailField = new JTextField();
		emailField.setVisible(true);
		emailField.setPreferredSize(new Dimension(220,30));
		contactPanel.add(emailField);
		address = new JLabel("Address");
		address.setVisible(true);
		address.setPreferredSize(new Dimension(440,10));
		contactPanel.add(address);
		addressField = new JTextField();
		addressField.setVisible(true);
		addressField.setPreferredSize(new Dimension(440,30));
		contactPanel.add(addressField);
		find = new JButton("Find");
		find.setPreferredSize(new Dimension(75,35));
		find.setVisible(true);
		find.addActionListener(listener7);
		contactPanel.add(find);
		clear = new JButton("Clear");
		clear.setPreferredSize(new Dimension(75,35));
		clear.setVisible(true);
		clear.addActionListener(listener6);
		contactPanel.add(clear);
		panel2.add(contactPanel,BorderLayout.CENTER);
	}
	public class clearBut implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			lastField.setText("");
			firstField.setText("");
			teleField.setText("");
			emailField.setText("");
			addressField.setText("");
			descArea.setText("");
		}
	}
	public class findBut implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Person q = null;
			if(!lastField.getText().equals(""))
			{
				q = contact.findLast(lastField.getText());
			}
			if(!firstField.getText().equals(""))
			{
				q = contact.findFirst(firstField.getText());
			}
			if(!teleField.getText().equals(""))
			{
				q = contact.findTele(teleField.getText());
			}
			if(!emailField.getText().equals(""))
			{
				q = contact.findEmail(emailField.getText());
			}
			if(q != null)
			{
				displayPerson(q);
			} else {
				descArea.setText("Not an available contact");
			}
		}
	}
	public void displayPerson(Person p)
	{
		lastField.setText(p.getLastName());
		firstField.setText(p.getFirstName());
		teleField.setText(p.getTelephone());
		emailField.setText(p.getEmail());
		addressField.setText(p.getAddress());
	}
	public void subPanel6(JPanel panel2)
	{
		descPanel = new JPanel();
		descPanel.setVisible(true);
		descPanel.setPreferredSize(new Dimension(470,165));
		descPanel.setBorder(new TitledBorder(new EtchedBorder(),"Description"));
		descArea = new JTextArea();
		descArea.setVisible(true);
		descArea.setPreferredSize(new Dimension(430,120));
		descPanel.add(descArea);
		panel2.add(descPanel,BorderLayout.SOUTH);
	}
}
