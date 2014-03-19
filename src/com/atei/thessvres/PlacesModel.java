package com.atei.thessvres;

import com.atei.thessvres.interfaces.ObjectToTree;

public class PlacesModel implements ObjectToTree {

	private String menu = "", Name = "", Category = "", Description = "",
			Tell = "", Link = "", Email = "", Ch = "", Name_en = "", Ch_en = "", Description_en = ""  ;
	private int ID = 0;
	private double Longtitude = 0.00;
	private double Latitude = 0.00;
	private float km = 0;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;

	}
	public String getCh() {
		return Ch;
	}

	public void setCh(String Ch) {
		this.Ch = Ch;

	}
	public String getName_en() {
		return Name_en;
	}

	public void setName_en(String Name_en) {
		this.Name_en = Name_en;

	}
	public String getCh_en() {
		return Ch_en;
	}

	public void setCh_en(String Ch_en) {
		this.Ch_en = Ch_en;

	}
	public String getDescription_en() {
		return Description_en;
	}

	public void setDescription_en(String Description_en) {
		this.Description_en = Description_en;

	}
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;

	}

	public String getLink() {
		return Link;
	}

	public void setLink(String Link) {
		this.Link = Link;

	}

	public String getTell() {
		return Tell;
	}

	public void setTell(String Tell) {
		this.Tell = Tell;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String Category) {
		this.Category = Category;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public double getLongtitude() {
		return Longtitude;
	}

	public void setLongtitude(double Longtitude) {
		this.Longtitude = Longtitude;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double Latitude) {
		this.Latitude = Latitude;
	}

	public float getkm() {
		return km;
	}

	public void setkm(float km) {
		this.km = km;
	}

	@Override
	public String toString() {
		return this.ID + ". " + this.Name;
	}

	@Override
	public double getCompareItem() {
		return km;
	}
}
