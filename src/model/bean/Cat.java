package model.bean;

public class Cat {
	private int idCat;
	private String nameCat;
	private int idParent;
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	public int getIdParent() {
		return idParent;
	}
	public void setIdParent(int idParent) {
		this.idParent = idParent;
	}
	public Cat(int idCat, String nameCat, int idParent) {
		super();
		this.idCat = idCat;
		this.nameCat = nameCat;
		this.idParent = idParent;
	}
	public Cat() {
		super();
	}
	
}
