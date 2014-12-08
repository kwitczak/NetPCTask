package NetPC.dto;
/*
 * Contact to klasa, która bêdzie
 * przesy³ana pomiêdzy warstwami aplikacji
 * 
 * Contact zawiera informacje o
 * osobie wybranej z listy kontaktow
 */


public class Contact {
	
	private String name;
	private String surname;
	private String email;
	private String phonenumber;
	private String dateofbirth;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

}
