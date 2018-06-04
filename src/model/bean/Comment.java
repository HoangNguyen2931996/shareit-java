package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id_cmt;
	private String content;
	private String username;
	private String email;
	private String website;
	private Timestamp date_cmt;
	private int id_parent;
	private int id_news;
	private String name_news;
	private int status_cmt;
	public int getId_cmt() {
		return id_cmt;
	}
	public void setId_cmt(int id_cmt) {
		this.id_cmt = id_cmt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Timestamp getDate_cmt() {
		return date_cmt;
	}
	public void setDate_cmt(Timestamp date_cmt) {
		this.date_cmt = date_cmt;
	}
	public int getId_parent() {
		return id_parent;
	}
	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}
	public int getId_news() {
		return id_news;
	}
	public void setId_news(int id_news) {
		this.id_news = id_news;
	}
	public String getName_news() {
		return name_news;
	}
	public void setName_news(String name_news) {
		this.name_news = name_news;
	}
	public int getStatus_cmt() {
		return status_cmt;
	}
	public void setStatus_cmt(int status_cmt) {
		this.status_cmt = status_cmt;
	}
	public Comment(int id_cmt, String content, String username, String email, String website, Timestamp date_cmt,
			int id_parent, int id_news, String name_news, int status_cmt) {
		super();
		this.id_cmt = id_cmt;
		this.content = content;
		this.username = username;
		this.email = email;
		this.website = website;
		this.date_cmt = date_cmt;
		this.id_parent = id_parent;
		this.id_news = id_news;
		this.name_news = name_news;
		this.status_cmt = status_cmt;
	}
	public Comment() {
		super();
	}

	
}
