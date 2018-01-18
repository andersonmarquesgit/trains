package com.trains.metric;

import java.io.PrintStream;
import java.util.List;

import com.trains.route.IRoute;

/**
 * Implementação da métrica de distância
 * @author anderson.marques
 *
 */
public class DistanceMetric extends AbstractRouteMetric {

	public DistanceMetric(String configMetricLine, PrintStream outputStream) {
		super(configMetricLine, outputStream);
	}

	@Override
	protected int callRoute(IRoute route, String start, String end, List<String> intermediate) {
		return route.routeDistance(start, end, intermediate);
	}

}
