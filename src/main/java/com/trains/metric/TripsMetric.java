package com.trains.metric;

import java.io.PrintStream;

import com.trains.enums.FilterCriteriaEnum;
import com.trains.route.IRoute;

/**
 * Implementação da métrica de número de viagens
 * @author anderson.marques
 *
 */
public class TripsMetric extends AbstractMetric {

	private final PrintStream outputStream;

	public TripsMetric(String configMetricLine, final PrintStream stream) {
		super(configMetricLine);
		this.outputStream = stream;
	}

	@Override
	public void execute(IRoute route, String outNumber) {
		final String routeLine = getConfigMetricLine().substring(7);
		final String[] commandParts = routeLine.split(",");

		final String filterCriteria = commandParts[0];
		final int filterValue = Integer.valueOf(commandParts[1]);
		final String startNode = String.valueOf(commandParts[2].charAt(0));
		final String endNode = String.valueOf(commandParts[2].charAt(2));

		int numberOfTrips = 0;

		try {
			if (filterCriteria.equalsIgnoreCase(FilterCriteriaEnum.MAX_STOPS.getValue())) {
				numberOfTrips = route.numberOfTripsWithMaxStops(startNode, endNode, filterValue);
			} else if (filterCriteria.equalsIgnoreCase(FilterCriteriaEnum.EXACT_STOPS.getValue())) {
				numberOfTrips = route.numberOfTripsWithExactStops(startNode, endNode, filterValue);
			} else if (filterCriteria.equalsIgnoreCase(FilterCriteriaEnum.MAX_DISTANCE.getValue())) {
				numberOfTrips = route.numberOfTripsWithMaxWeight(startNode, endNode, filterValue);
			}
			outputStream.println(outNumber + numberOfTrips);
		} catch (final Exception e) {
			outputStream.println(outNumber + AbstractRouteMetric.NO_ROUTE_MSG);
		}

	}

}
