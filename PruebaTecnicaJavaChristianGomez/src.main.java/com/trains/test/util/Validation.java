/**
 * Trains
 */
package com.trains.test.util;

import java.util.List;

import com.trains.test.model.Route;

/**
 * @author Christian Gomez
 *
 */
public class Validation {
	
	/**
	 * This method validates if the given route inside the input graph is correct.
	 * 
	 * @param route Represents one route inside the input graph
	 * @return true If the route is OK
	 * @throws Exception If the route is wrong
	 */
	public static boolean validateRoute(String route) throws Exception {
		if(route.length() != 3) {
			throw new Exception("The length of the routes must be of 3 charactes, please check the input");
		} else if (!route.matches("[A-Z]{2}[1-9]{1}")) {
			throw new Exception("The format of the routes must be [CAPITAL_LETTER][CAPITAL_LETTER][NUMBER], please check the input");
		} else if (route.substring(0, 1).equals(route.substring(1, 2)) ) {
			throw new Exception("The start city and the end city cannot be the same, please check the input");
		} else {
			return true;
		}
	}
	
	/**
	 * This method validates if the List of given routes is correct.
	 * 
	 * @param routes Represents the List of routes to be validated
	 * @return true If the routes are OK
	 * @throws Exception If there is something wrong in the array
	 */
	public static boolean validateRoutes(List<Route> routes) throws Exception {
		
		for (Route route : routes) {
			int matchCounter = 0;
			for (Route route2 : routes) {
				if(route.getStartCity().equals(route2.getStartCity()) && route.getEndCity().equals(route2.getEndCity())) {
					matchCounter++;
				}
			}
			if(matchCounter>1)
				throw new Exception("A given route can only appear once, please check the input");
		}
		return true;
	}
	
}
