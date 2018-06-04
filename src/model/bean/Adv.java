package model.bean;

public class Adv {
	private int idAdv;
	private String nameAdv;
	private String picture;
	private String link;
	public int getIdAdv() {
		return idAdv;
	}
	public void setIdAdv(int idAdv) {
		this.idAdv = idAdv;
	}
	public String getNameAdv() {
		return nameAdv;
	}
	public void setNameAdv(String nameAdv) {
		this.nameAdv = nameAdv;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Adv(int idAdv, String nameAdv, String picture, String link) {
		super();
		this.idAdv = idAdv;
		this.nameAdv = nameAdv;
		this.picture = picture;
		this.link = link;
	}
	public Adv() {
		super();
	}
	
}
