package com.trains;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.trains.graph.GraphBuilder;
import com.trains.metric.IMetric;
import com.trains.metric.MetricBuilder;
import com.trains.route.IRoute;
import com.trains.route.RouterImpl;

public class Main {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		final IRoute route = new RouterImpl(GraphBuilder.getEmptyGraph());
		
		File inputFile = new File(Main.class.getResource("/configMetrics.txt").toURI());
		final List<IMetric> metricConfigList = new MetricBuilder(System.out).constructMetrics(inputFile);
		
		String routesString;
		routesString = scanner.nextLine();
		
		GraphBuilder.createGraphicRoutes(route, routesString);
		
		for (final IMetric metric : metricConfigList) {
            metric.execute(route);
        }

	}
}
