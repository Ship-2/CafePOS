package org.ship2.model.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5441486290977264087L;
	
	private int cat_code;
	private String cat_name;
	public CategoryDTO(int cat_code, String cat_name) {
		this.cat_code = cat_code;
		this.cat_name = cat_name;
	}
	public CategoryDTO() {
	}
	public int getCat_code() {
		return cat_code;
	}
	public void setCat_code(int cat_code) {
		this.cat_code = cat_code;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	@Override
	public String toString() {
		return "CategoryDTO [cat_code=" + cat_code + ", cat_name=" + cat_name + "]";
	}

}
