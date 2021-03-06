package example.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the user table in the database
 * 
 * @author lpreams
 */

@Entity // marks this class as a database table (database tables are also called entities)
@Table(name="user") // sets the name of the table in the database
public class DBUser {
	
	@Id // specifies that the following field is the primary key for this table, which also means that no two entries in this column may be identical
	@GeneratedValue(strategy=GenerationType.AUTO) // specifies that this field will be automatically generated by the database and should not be specifies manually 
	private long id; // unique id
	
	@Column(unique=true) // specifies that no two entries in this column may be identical (even though this column is *not* part of the primary key)
	private String email; // user's email address
	
	private String name; // user's display name (note that these are not required to be unique)
	
	private long timestamp; // date account created
	
	private String password; // user's password (irl this should be salted and hashed, but for simplicity we'll just store plaintext passwords for now
	
	private int bgColor; // User's preference of background color
	
	/**
	 * Use this class to return objects from the database, classes retried from Hibernate are transient and can become unreliable after session is closed
	 */
	public static class FlatUser {
		public final long id;
		public final String email;
		public final String name;
		public final long timestamp;
		public final String password;
		public final int bgColor;
		public FlatUser(DBUser user) {
			this.id = user.id;
			this.email = user.email;
			this.name = user.name;
			this.timestamp = user.timestamp;
			this.password = user.password;
			this.bgColor = user.bgColor;
		}
	}
	public FlatUser flatten() {
		return new FlatUser(this);
	}

	/*
	 * Hibernate REQUIRES that there exist a public default constructor (which should do nothing) and that all fields have a getter and setter
	 */
	
	public DBUser() {} // required empty constructor
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getBgColor() {
		return bgColor;
	}
	
	public void setBgColor(int bgColor) {
		this.bgColor = bgColor;
	}
	
	
}
