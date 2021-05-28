/**
 * Trains
 */
package com.trains.test.main;

import java.util.List;

import com.trains.test.model.Route;
import com.trains.test.util.Methods;

/**
 * @author Christian Gomez
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {
			String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
			//String graph = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
			//input = input.replace(" ", "");
			System.out.println("input: " + graph);
			List<Route> routes = Methods.decodeGraph(graph);
			
			//System.out.println(routes);
			System.out.println("Output #1: " + Methods.getDistanceBetweenCities(routes, "A-B-C"));
			System.out.println("Output #2: " + Methods.getDistanceBetweenCities(routes, "A-D"));
			System.out.println("Output #3: " + Methods.getDistanceBetweenCities(routes, "A-D-C"));
			System.out.println("Output #4: " + Methods.getDistanceBetweenCities(routes, "A-E-B-C-D"));
			System.out.println("Output #5: " + Methods.getDistanceBetweenCities(routes, "A-E-D"));
			System.out.println("Output #6: " + Methods.getNumberoftripsBetweencitiesWithMaximunstops(routes, "C", "C", 3l));
			System.out.println("Output #7: " + Methods.getNumberoftripsBetweencitiesWithExactlystops(routes, "A", "C", 4l));
			System.out.println("Output #8: " + Methods.getLengthofshortesrouteBetweentwocities(routes, "A", "C"));
			routes = Methods.decodeGraph(graph);
			System.out.println("Output #9: " + Methods.getLengthofshortesrouteBetweentwocities(routes, "B", "B"));
			routes = Methods.decodeGraph(graph);
			System.out.println("Output #10: " + Methods.getNumberofroutesBetweentwocitiesWithlimitedistance(routes, "C", "C", 30l));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
	}

}
