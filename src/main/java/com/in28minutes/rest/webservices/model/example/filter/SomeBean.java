package com.in28minutes.rest.webservices.model.example.filter;

import com.fasterxml.jackson.annotation.JsonFilter;


//== static filtering
//@JsonIgnoreProperties(value = {"string2", "string3"})

//== needed for dynamic filtering
@JsonFilter("SomeBeanFilter")
public class SomeBean {

	private String string;
	private String string2;
	
	// == static filtering
	//@JsonIgnore
	private String string3;

	public SomeBean(String string, String string2, String string3) {
		super();
		this.string = string;
		this.string2 = string2;
		this.string3 = string3;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getString2() {
		return string2;
	}

	public void setString2(String string2) {
		this.string2 = string2;
	}

	public String getString3() {
		return string3;
	}

	public void setString3(String string3) {
		this.string3 = string3;
	}

}
