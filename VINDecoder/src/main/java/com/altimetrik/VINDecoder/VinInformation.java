package com.altimetrik.VINDecoder;

import java.io.Serializable;

public class VinInformation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String make;
	private String modelYear;
	private String plantCountry;
	private String plantState;
	private String plantCity;

	public VinInformation(String make, String modelYear) {
		this.make = make;
		this.modelYear = modelYear;
	}

	public VinInformation() {
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}

	public String getPlantCountry() {
		return plantCountry;
	}

	public void setPlantCountry(String plantCountry) {
		this.plantCountry = plantCountry;
	}

	public String getPlantState() {
		return plantState;
	}

	public void setPlantState(String plantState) {
		this.plantState = plantState;
	}

	public String getPlantCity() {
		return plantCity;
	}

	public void setPlantCity(String city) {
		this.plantCity = city;
	}


	
}
