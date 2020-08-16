/* Jacob Wagner
 * 500754931
 */
import java.util.*;
public class telephoneComp implements Comparator<Person> 
{
	public telephoneComp()
	{
		
	}
	public int compare(Person p1,Person p2)
	{
		if(p1.getTelephone() == p2.getTelephone())
		{
			return 0;
		} else {
			return 1;
		}
	}
}
