package com.sample.test.common;

public class Bird {
	private String id;
	private String name;
	private String family;
	private String[] continents;
	private String added;
	private Boolean visible;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * @param family the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}
	/**
	 * @return the continents
	 */
	public String[] getContinents() {
		return continents;
	}
	/**
	 * @param continents the continents to set
	 */
	public void setContinents(String[] continents) {
		this.continents = continents;
	}
	/**
	 * @return the added
	 */
	public String getAdded() {
		return added;
	}
	/**
	 * @param added the added to set
	 */
	public void setAdded(String added) {
		this.added = added;
	}
	/**
	 * @return the visible
	 */
	public Boolean getVisible() {
		return visible;
	}
	/**
	 * @param visible the visible to set
	 */
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	
}
