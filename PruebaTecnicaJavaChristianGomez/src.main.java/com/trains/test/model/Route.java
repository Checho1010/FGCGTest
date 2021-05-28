/**
 * Trains
 */
package com.trains.test.model;

/**
 * @author Christian Gomez
 * 
 * This model represents the start and end cities of a route and the
 * distance between them
 */
public class Route {
	String startCity;
	String endCity;
	Long distance;
	String passedCities;
	Boolean limitReached;
	
	public Route(String startCity, String endCity, Long distance, String passedCities, Boolean limitReached) {
		super();
		this.startCity = startCity;
		this.endCity = endCity;
		this.distance = distance;
		this.passedCities = passedCities;
		this.limitReached = limitReached;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getPassedCities() {
		return passedCities;
	}

	public void setPassedCities(String passedCities) {
		this.passedCities = passedCities;
	}

	public Boolean getLimitReached() {
		return limitReached;
	}

	public void setLimitReached(Boolean limitReached) {
		this.limitReached = limitReached;
	}
	
}