/* Jacob Wagner
 * 500754931
 */
import java.util.*;
public class emailComp implements Comparator<Person>
{
	public emailComp()
	{
		
	}
	public int compare(Person p1,Person p2)
	{
		if(p1.getEmail() == p2.getEmail())
		{
			return 0;
		} else {
			return 1;
		}
	}
}
