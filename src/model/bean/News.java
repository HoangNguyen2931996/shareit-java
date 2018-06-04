package model.bean;

import java.sql.Timestamp;

public class News {
	private int idNews;
	private String nameNews;
	private String preview;
	private String detail;
	private Timestamp dateCreate;
	private String createBy;
	private String picture;
	private String nameCat;
	private int idCat;
	private int idParent;
	private int isSlide;
	private int status;
	private int readNews;
	public int getIdNews() {
		return idNews;
	}
	public void setIdNews(int idNews) {
		this.idNews = idNews;
	}
	public String getNameNews() {
		return nameNews;
	}
	public void setNameNews(String nameNews) {
		this.nameNews = nameNews;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public int getIdParent() {
		return idParent;
	}
	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}
	public int getIsSlide() {
		return isSlide;
	}
	public void setIsSlide(int isSlide) {
		this.isSlide = isSlide;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReadNews() {
		return readNews;
	}
	public void setReadNews(int readNews) {
		this.readNews = readNews;
	}
	public News(int idNews, String nameNews, String preview, String detail, Timestamp dateCreate, String createBy,
			String picture, String nameCat, int idCat, int idParent, int isSlide, int status, int readNews) {
		super();
		this.idNews = idNews;
		this.nameNews = nameNews;
		this.preview = preview;
		this.detail = detail;
		this.dateCreate = dateCreate;
		this.createBy = createBy;
		this.picture = picture;
		this.nameCat = nameCat;
		this.idCat = idCat;
		this.idParent = idParent;
		this.isSlide = isSlide;
		this.status = status;
		this.readNews = readNews;
	}
	public News() {
		super();
	}


}
