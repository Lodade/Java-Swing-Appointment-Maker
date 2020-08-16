/* Jacob Wagner
 * 500754931
 */
public class Person implements Comparable<Person> {
	String lastName;
	String firstName;
	String telephone;
	String address;
	String email;

	public Person(String lastName, String firstName, String telephone, String address, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.telephone = telephone;
		this.address = address;
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return (lastName + " " + firstName + " " + telephone + " " + address + " " + email);
	}

	public int compareTo(Person p)
	{
		char hold1 = ' ';
		char hold2 = ' ';
		char hold3 = ' ';
		char hold4 = ' ';
		hold1 = getLastName().charAt(0);
		hold2 = p.getLastName().charAt(0);
		hold3 = getFirstName().charAt(0);
		hold4 = p.getFirstName().charAt(0);
		if(getLastName() == p.getLastName())
		{
			if(getFirstName() == p.getFirstName())
			{
				return 0;
			} 
			if(hold3 > hold4)
			{
				return 1;
			}
			return -1;
		}
		if(hold1 > hold2)
		{
			return 1;
		}
		return -1;
	}
}
