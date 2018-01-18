package com.trains.metric;

import java.io.PrintStream;
import java.util.List;

import com.trains.route.IRoute;

public class DurationMetric extends AbstractRouteMetric {

	public DurationMetric(String configMetricLine, PrintStream outputStream) {
		super(configMetricLine, outputStream);
	}

	@Override
	protected int callRoute(IRoute route, String start, String end, List<String> intermediate) {
		return route.routeDuration(start, end, intermediate);
	}

}
