/**
 * Trains
 */
package com.trains.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.trains.test.model.Route;

/**
 * @author Christian Gomez
 *
 */
public class Methods {
	
	/**
	 * This method receives a string that contain all the routes, and decode it in a List of objects of class Route
	 * 
	 * @param graph The graph string that represents all the possible routes
	 * @return A List of routes decoded from the input graph string
	 * @throws Exception If there is a problem with the decoding process
	 */
	public static List<Route> decodeGraph(String graph) throws Exception{
		try {
			String[] arrOfRoutes = graph.replace(" ", "").split(",");
			
			List<Route> routes = new ArrayList<Route>();
			for (String route : arrOfRoutes) {
				if(Validation.validateRoute(route)) {
					Route newRoute = new Route(route.substring(0, 1), route.substring(1, 2), Long.valueOf(route.substring(2, 3)), route.substring(0, 1) + route.substring(1, 2), false);
					routes.add(newRoute);
				}
				
			}
			Validation.validateRoutes(routes);
			return routes;
		} catch (Exception e) {
			throw e;
		} 
	}
	
	/**
	 * This method calculates the distance between cities given in an input String
	 * 
	 * @param routes A List of routes that holds the start city, end city and distance between them
	 * @param cities The input that represents the cities of the trip
	 * @return The cities traveled and the total distance traveled
	 */
	public static String getDistanceBetweenCities(List<Route> routes, String cities) {
		String[] arrOfCities = cities.replace(" ", "").split("-");
		
		//validate if the string has at least two cities
		if(arrOfCities.length<=1) {
			return cities+": The string must contain at least two cities";
		}
		
		//iterate over the array of cities to find the distance between each city and sumarize such distance
		List<Route> filteredRoutes = new ArrayList<Route>();
		for (int i = 0; i < arrOfCities.length-1; i++) {
			String start = arrOfCities[i];
			String end = arrOfCities[i+1];
			filteredRoutes.addAll(
					routes.stream().filter(
					rout -> (rout.getStartCity().equals(start) && rout.getEndCity().equals(end))
					).collect(Collectors.toList())
					)
					;
		}
		Long distance = 0l;
		if(arrOfCities.length-1 != filteredRoutes.size()) {
			return cities+": NO SUCH ROUTE";
		}
		for (Route route : filteredRoutes) {
			distance+=route.getDistance();
		}
		
		return cities+": "+distance.toString();
	}
	
	/**
	 * This method calculates the number of trips available between two cities considering a maximum number of stops
	 * 
	 * @param routes A List of routes that holds the start city, end city and distance between them
	 * @param startCity The start city of the trip
	 * @param endCity The end city of the trip
	 * @param maximunStops The maximum number of stops allowed
	 * @return The number of trips available between both cities and given maximum stops
	 */
	public static String getNumberoftripsBetweencitiesWithMaximunstops(List<Route> routes, String startCity, String endCity, Long maximunStops) {
		Long numberOfTrips = 0l;
		
		//Determine the routes that starts with the start city
		List<Route> startingRoutes = new ArrayList<Route>();
		startingRoutes.addAll(
				routes.stream().filter(
				rout -> ( rout.getStartCity().equals(startCity) )
				).collect(Collectors.toList())
				)
				;
		Long numberOfStops = 1l;
		//Determine an auxiliary routes array that will save the routes that can continue the trip untill reaching the 
		//end city or the maximum stops allowed
		List<Route> auxiliaryRoutes = new ArrayList<Route>();
		
		while(numberOfStops<=maximunStops) {
			auxiliaryRoutes.clear();	
			for (Route startingRoute : startingRoutes) {
				if(startingRoute.getEndCity().contentEquals(endCity)) {
					numberOfTrips++;
					//break;
				} else {
					auxiliaryRoutes.addAll(
							routes.stream().filter(
							rout -> ( rout.getStartCity().equals(startingRoute.getEndCity()) )
							).collect(Collectors.toList())
							)
							;
				}
				
			}
			startingRoutes.clear();
			startingRoutes.addAll(auxiliaryRoutes);
			//startingRoutes = auxiliaryRoutes;
			numberOfStops++;
		}
		
		return "starts at " + startCity + " ends at "+ endCity + " maximun stops " + maximunStops.toString() + ": " + numberOfTrips.toString();
	}
	
	/**
	 * This method calculates the number of trips available between two cities considering an exactly number of stops
	 * 
	 * @param routes A List of routes that holds the start city, end city and distance between them
	 * @param startCity The start city of the trip
	 * @param endCity The end city of the trip
	 * @param exactlyStops The exactly number of stops to accomplish
	 * @return The number of trips available between both cities and given exactly number of stops
	 */
	public static String getNumberoftripsBetweencitiesWithExactlystops(List<Route> routes, String startCity, String endCity, Long exactlyStops) {

		Long numberOfTrips = 0l;
		
		//Determine de routes that starts with the start city
		List<Route> startingRoutes = new ArrayList<Route>();
		startingRoutes.addAll(
				routes.stream().filter(
				rout -> ( rout.getStartCity().equals(startCity) )
				).collect(Collectors.toList())
				)
				;
		Long numberOfStops = 1l;
		//Determine an auxiliary routes array that will save the routes that can continue the trip untill reaching the 
		//end city and the exactly stops allowed
		List<Route> auxiliaryRoutes = new ArrayList<Route>();
		
		while(numberOfStops<=exactlyStops) {
			auxiliaryRoutes.clear();	
			for (Route startingRoute : startingRoutes) {
				if(startingRoute.getEndCity().contentEquals(endCity) && numberOfStops == exactlyStops) {
					numberOfTrips++;
					//break;
				}
				auxiliaryRoutes.addAll(
						routes.stream().filter(
						rout -> ( rout.getStartCity().equals(startingRoute.getEndCity()) )
						).collect(Collectors.toList())
						)
						;
				
				
			}
			startingRoutes.clear();
			startingRoutes.addAll(auxiliaryRoutes);
			numberOfStops++;
		}
		
		return "starts at " + startCity + " ends at "+ endCity + " exactly stops " + exactlyStops.toString() + ": " + numberOfTrips.toString();
	}
	
	/**
	 * This method calculates the length of the total trip between two cities
	 * 
	 * @param routes A List of routes that holds the start city, end city and distance between them
	 * @param startCity The start city of the trip
	 * @param endCity The end city of the trip
	 * @return The length between both cities
	 */
	public static String getLengthofshortesrouteBetweentwocities(List<Route> routes, String startCity, String endCity) {
		
		//Determine the routes that starts with the start city
		List<Route> startingRoutes = new ArrayList<Route>();
		startingRoutes.addAll(
				routes.stream().filter(
				rout -> ( rout.getStartCity().equals(startCity) )
				).collect(Collectors.toList())
				)
				;
		
		//Determine an auxiliary routes array that will save the routes that can continue the trip untill reaching the 
		//end city or the maximum stops allowed
		List<Route> auxiliaryRoutes = new ArrayList<Route>();
		
		//iterate over the staringRoutes array to save the distance traveling and validating if the route reaches the end city
		boolean foundRoute = false;
		while(!foundRoute) {
			
			auxiliaryRoutes.clear();
			while(startingRoutes.size()>0) {
				
				Route actualRoute = startingRoutes.get(0);
				auxiliaryRoutes.addAll(
						routes.stream().filter(
						rout -> ( rout.getStartCity().equals(actualRoute.getEndCity()) )
						).collect(Collectors.toList())
						)
						;
				
				for (Route route : auxiliaryRoutes) {
					if(route.getStartCity().equals(actualRoute.getEndCity()))
							route.setDistance(route.getDistance()+actualRoute.getDistance());
				}
				startingRoutes.remove(0);
			}
			
			//validate if y have reached the end city and the other routes lenghts are superior to the one
			//that have already reached  the final city
			Long shortestLength = null;
			for (Route route : auxiliaryRoutes) {
				if(route.getEndCity().equals(endCity)) {
					if(null==shortestLength) {
						shortestLength = route.getDistance();
					}
					if(route.getDistance() < shortestLength) {
						shortestLength = route.getDistance();
					}
				}
			}
			if(shortestLength!=null) {//it means there is a route that have reached the final city and we have calculated the total lenght of the trip
				foundRoute = true;
				for (Route route : auxiliaryRoutes) {
					if(route.getDistance() <  shortestLength) {
						foundRoute = false;
					}
				}
			}
			if(foundRoute) {
				return "starts at " + startCity + " ends at " + endCity + ": " + shortestLength.toString();
			}
			
			//asignate the auxiliary routes to the starting routes to run the loop again
			startingRoutes.addAll(auxiliaryRoutes);
		}
		return "";
	}
	
	/**
	 * This method calculates how many routes are available between two cities considering a maximum length
	 * 
	 * @param routes A List of routes that holds the start city, end city and distance between them
	 * @param startCity The start city of the trip
	 * @param endCity The end city of the trip
	 * @param maxDistance The maximum distance allowed
	 * @return The number of trips available between both cities with a maximum distance restriction
	 */
	public static String getNumberofroutesBetweentwocitiesWithlimitedistance(List<Route> routes, String startCity, String endCity, Long maxDistance) {
		
		//Determine the routes that starts with the start city
		List<Route> startingRoutes = new ArrayList<Route>();
		startingRoutes.addAll(
				routes.stream().filter(
				rout -> ( rout.getStartCity().equals(startCity) )
				).collect(Collectors.toList())
				)
				;
		
		//Determine an auxiliary routes array that will save the routes that can continue the trip untill reaching the 
		//maximum distance allowed
		List<Route> auxiliaryRoutes = new ArrayList<Route>();
		
		//iterate over the staringRoutes array to save the distance traveling and validating if the route reaches the end city
		boolean limitReached = false;
		while(!limitReached) {
			
			//auxiliaryRoutes.clear();
			auxiliaryRoutes = new ArrayList<Route>();
			while(startingRoutes.size()>0) {
				
				Route actualRoute = startingRoutes.get(0);
				
				/*auxiliaryRoutes.addAll(
						routes.stream().filter(
						rout -> ( rout.getStartCity().equals(actualRoute.getEndCity()) )
						).collect(Collectors.toList())
						)
						;*/
				for (Route route : routes) {
					if(route.getStartCity().equals(actualRoute.getEndCity())) {
						auxiliaryRoutes.add( new Route(route.getStartCity(), route.getEndCity(),route.getDistance(), route.getPassedCities(), route.getLimitReached()) );
					}
				}
				
				
				for (Route route : auxiliaryRoutes) {
					Long newDistance = actualRoute.getDistance() + route.getDistance(); 
					if(newDistance < maxDistance) {
						if(route.getStartCity().equals(actualRoute.getEndCity())) {
							route.setDistance(newDistance);
							route.setPassedCities(actualRoute.getPassedCities() + route.getEndCity());
						//	route.setPassedCities(route.getPassedCities() + actualRoute.getEndCity());
						}
							
					}else {
						route.setLimitReached(true);
					}
							
				}
				startingRoutes.remove(0);
			}
			
			limitReached = true;
			for (Route route : auxiliaryRoutes) {
				if(!route.getLimitReached()) {
					limitReached = false;
					break;
				}
					
			}
			
			if(limitReached) {
				startingRoutes.addAll(auxiliaryRoutes);
				break;
			}
			
			//asignate the auxiliary routes to the starting routes to run the loop again
			startingRoutes.addAll(auxiliaryRoutes);
			startingRoutes = new ArrayList<Route>(auxiliaryRoutes);
		}
		
		
		return "under development";
	}
	
}
