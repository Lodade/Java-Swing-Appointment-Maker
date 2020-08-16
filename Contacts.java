/* Jacob Wagner
 * 500754931
 */
import java.util.*;
import java.io.*;
import javax.swing.*;
public class Contacts 
{
	LinkedList<Person> holder = new LinkedList<Person>();
	ListIterator iterator = null;
	public Contacts()
	{
		
	}
	public void readContactsFile(JTextArea area) throws IOException
	{
			String lastName = " ";
			String firstName = " ";
			String telephone = " ";
			String address = " ";
			String email = " ";
			int contactNum = 0;
			BufferedReader reader;
			try 
			{
			reader = new BufferedReader(new FileReader("contacts.txt"));
			contactNum = Integer.parseInt(reader.readLine());
			for(int i = 1;i <= contactNum;i++)
			{
				lastName = reader.readLine();
				firstName = reader.readLine();
				address = reader.readLine();
				telephone = reader.readLine();
				email = reader.readLine();
				holder.add(new Person(lastName, firstName, telephone, address, email));
			}
			Collections.sort(holder);
			reader.close();
			}
			catch(IOException | NumberFormatException | NullPointerException | StringIndexOutOfBoundsException e)
			{
				area.setText("There was an issue reading the contacts file. No contacts are available.");
			}
	}
	public Person findLast(String lastName)
	{
		Person temp;
		iterator = holder.listIterator();
		while(iterator.hasNext())
		{
			temp = (Person) iterator.next();
			if(temp.getLastName().equals(lastName))
			{
				return temp;
			}
		}
		return null;
	}
	public Person findFirst(String firstName)
	{
		Person temp;
		iterator = holder.listIterator();
		while(iterator.hasNext())
		{
			temp = (Person) iterator.next();
			if(temp.getFirstName().equals(firstName))
			{
				return temp;
			}
		}
		return null;
	}
	public Person findTele(String telephone)
	{
		Person temp;
		iterator = holder.listIterator();
		while(iterator.hasNext())
		{
			temp = (Person) iterator.next();
			if(temp.getTelephone().equals(telephone))
			{
				return temp;
			}
		}
		return null;
	}
	public Person findEmail(String email)
	{
		Person temp;
		iterator = holder.listIterator();
		while(iterator.hasNext())
		{
			temp = (Person) iterator.next();
			if(temp.getEmail().equals(email))
			{
				return temp;
			}
		}
		return null;
	}
}
