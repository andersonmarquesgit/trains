package com.trains.route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.trains.filters.ContainsPathFilter;
import com.trains.filters.ExactStopsPathFilter;
import com.trains.filters.IPathFilter;
import com.trains.filters.MaxStopsPathFilter;
import com.trains.filters.RepeatedEdgePathFilter;
import com.trains.filters.WeightPathFilter;
import com.trains.graph.DefaultEdge;
import com.trains.graph.GraphPath;
import com.trains.graph.IGraph;
import com.trains.graph.IPath;

public class RouterImpl implements IRoute {

	private final IGraph<String> routes;

	public RouterImpl(final IGraph<String> routes) {
		super();
		this.routes = routes;
	}

	@Override
	public int routeDistance(String fromCity, String endCity, List<String> intermediateCities) {
		final IPath<String> objectivePath = createPath(fromCity, endCity, intermediateCities);
		final List<IPath<String>> allPaths = routes.getAllPaths(fromCity, endCity,
				new ContainsPathFilter<String>(objectivePath));

		return allPaths.get(0).getPathTotalWeight();
	}

	@Override
	public int numberOfTripsWithMaxStops(String fromCity, String endCity, int stops) {
		 return routes.getAllPaths(fromCity, endCity, new MaxStopsPathFilter<String>(stops)).size();
	}
	
	@Override
	public int numberOfTripsWithMaxWeight(String fromCity, String endCity, int weight) {
		 return routes.getAllPaths(fromCity, endCity, new WeightPathFilter<String>(weight)).size();
	}

	@Override
	public int numberOfTripsWithExactStops(String fromCity, String endCity, int stops) {
		final List<IPath<String>> paths = routes.getAllPaths(fromCity, endCity,
				new MaxStopsPathFilter<String>(stops));
		final List<IPath<String>> exactPaths = new ArrayList<IPath<String>>();
		final IPathFilter<String> exactFilter = new ExactStopsPathFilter<String>(stops);
		for (final IPath<String> each : paths) {
			if (exactFilter.passFilter(each)) {
				exactPaths.add(each);
			}
		}
		return exactPaths.size();
	}

	@Override
	public int legthOfTheShortestRoute(String fromCity, String endCity) {
		final List<IPath<String>> allPaths = routes.getAllPaths(fromCity, endCity,
				new RepeatedEdgePathFilter<String>());
		return Collections.min(allPaths).getPathTotalWeight();
	}

	@Override
	public int routeDuration(String fromCity, String endCity, List<String> intermediateCities) {
		return routeDistance(fromCity, endCity, intermediateCities) + 2 * intermediateCities.size();
	}

	@Override
	public IGraph<String> getAllRoutes() {
		return routes;
	}

	private IPath<String> createPath(final String from, final String to, final List<String> intermediateNodes) {
		final IPath<String> resultingPath = GraphPath.emptyPath();
		String currentNode = from;
		if (intermediateNodes != null) {
			for (final String eachIntermediate : intermediateNodes) {
				resultingPath.addEdge(DefaultEdge.getWeightedEdge(currentNode, eachIntermediate, 0));
				currentNode = eachIntermediate;
			}
		}
		resultingPath.addEdge(DefaultEdge.getWeightedEdge(currentNode, to, 0));
		return resultingPath;
	}

}
