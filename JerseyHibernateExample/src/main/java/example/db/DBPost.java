package example.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import example.db.DBUser.FlatUser;
/**
 * This class represents a single blog post
 * 
 * @author lpreams
 */
@Entity
@Table(name="post")
public class DBPost {
	
	/****************************************** database columns ***************************/
	
	@Id // specifies that the following field is the primary key for this table, which also means that no two entries in this column may be identical
	@GeneratedValue(strategy=GenerationType.AUTO) // specifies that this field will be automatically generated by the database and should not be specifies manually 
	private long id; // unique id
	
	@ManyToOne // specifies that this field represents a many-to-one relationship, meaning that a single user may own many posts 
	           // (in the database this means that the user's id will be stored in a column called author_id, but Hibernate will automatically convert that into a full DBUser object for us)
	private DBUser author; // user who wrote this post
	
	private String title; // post title
	
	private String body; // post body
	
	private long date; // timestamp of post
	
	
	/****************************************** utility ***************************/
	
	/**
	 * Use this class to return objects from the database, classes retried from Hibernate are transient and can become unreliable after session is closed
	 */
	public static class FlatPost {
		public final long id;
		public final String title;
		public final String body;
		public final long date;
		public final FlatUser author;
		public FlatPost(DBPost user) {
			this.id = user.id;
			this.title = user.title;
			this.body = user.body;
			this.date = user.date;
			this.author = new FlatUser(user.getAuthor());
		}
	}
	public FlatPost flatten() {
		return new FlatPost(this);
	}
	
	
	/****************************************** boilerplate required by Hibernate ***************************/
	/*
	 * Hibernate REQUIRES that there exist a public default constructor (which should do nothing) and that all fields have a getter and setter
	 */
	
	public DBPost() {} // required empty constructor

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DBUser getAuthor() {
		return author;
	}

	public void setAuthor(DBUser author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
	
	
}
