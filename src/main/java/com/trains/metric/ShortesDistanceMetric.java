package com.trains.metric;

import java.io.PrintStream;

import com.trains.exception.NoSuchRouteException;
import com.trains.route.IRoute;

public class ShortesDistanceMetric extends AbstractMetric {

	private final PrintStream outputStream;

	public ShortesDistanceMetric(String configMetricLine, final PrintStream stream) {
		super(configMetricLine);
		this.outputStream = stream;
	}

	@Override
	public void execute(IRoute route) {
		final String routeLine = getConfigMetricLine().substring(10);
		final String from = String.valueOf(routeLine.charAt(0));
		final String to = String.valueOf(routeLine.charAt(2));
		try {
			outputStream.println(route.legthOfTheShortestRoute(from, to));
		} catch (final NoSuchRouteException e) {
			outputStream.println(AbstractRouteMetric.NO_ROUTE_MSG);
		}
	}

}
