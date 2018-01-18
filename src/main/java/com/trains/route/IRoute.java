package com.trains.route;

import java.util.List;

import com.trains.graph.IGraph;


/**
 * Interface responsável pelo cálculo de cada métrica de rotas.
 * @author anderson.marques
 *
 */
public interface IRoute {
	
	int routeDistance(String fromCity, String endCity, List<String> intermediateCities);
	int numberOfTripsWithMaxStops(String fromCity, String endCity, int stops);
	int numberOfTripsWithMaxWeight(String fromCity, String endCity, int weight);
	int numberOfTripsWithExactStops(String fromCity, String endCity, int stops);
	int legthOfTheShortestRoute(String fromCity, String endCity);
	
	int routeDuration(String startingCity, String endCity, List<String> intermediateCities);
	IGraph<String> getAllRoutes();
}
