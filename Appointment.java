/* Jacob Wagner
 * 500754931
 */
import java.util.*;
public class Appointment implements Comparable<Appointment>
{
	public Calendar date;
	private String description;
	private Person p;
	public Appointment(int year, int month, int day, int hour, int minutes, String description)
	{
		date = new GregorianCalendar(year, month, day, hour, minutes);
		this.description = description;
	}
	public Appointment(int year, int month, int day, int hour, int minutes, String description, String lastName, String firstName, String telephone, String address, String email)
	{
		date = new GregorianCalendar(year, month, day, hour, minutes);
		this.description = description;
		p = new Person(lastName, firstName, telephone, email, address);
	}
	public String getDesc()
	{
		return description;
	}
	public void setDescription(String desc)
	{
		description = desc;
	}
	public int getYear()
	{
		return date.get(Calendar.YEAR);
	}
	public int getMonth()
	{
		return date.get(Calendar.MONTH);
	}
	public int getDay()
	{
		return date.get(Calendar.DAY_OF_MONTH);
	}
	public int getHour()
	{
		return date.get(Calendar.HOUR_OF_DAY);
	}
	public int getMinute()
	{
		return date.get(Calendar.MINUTE);
	}
	public String getLastName()
	{
		return p.getLastName();
	}
	public String getFirstName()
	{
		return p.getFirstName();
	}
	public String getTelephone()
	{
		return p.getTelephone();
	}
	public String getAddress()
	{
		return p.getAddress();
	}
	public String getEmail()
	{
		return p.getEmail();
	}
	public void setLastName(String lastName)
	{
		p.setLastName(lastName);
	}
	public void setFirstName(String firstName)
	{
		p.setFirstName(firstName);
	}
	public void setTelephone(String telephone)
	{
		p.setTelephone(telephone);
	}
	public void setAddress(String address)
	{
		p.setAddress(address);
	}
	public void setEmail(String email)
	{
		p.setEmail(email);
	}
	public boolean occursOn(int year, int month, int day, int hours, int minutes)
	{
		boolean result = false;
		if(date.get(Calendar.DAY_OF_MONTH) == day && date.get(Calendar.HOUR_OF_DAY) == hours)
		{
			result = true;
		}
		return result;
	}
	public int compareTo(Appointment compare)
	{ 
		if(this.getHour() < compare.getHour())
		{
			return -1;
		} else if(this.getHour() > compare.getHour())
		{
			return 1;
		} else {
			return 0;
		}
	}
}	
