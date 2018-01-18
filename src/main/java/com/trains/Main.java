package com.trains;

import java.io.File;
import java.util.List;

import com.trains.graph.GraphBuilder;
import com.trains.metric.IMetric;
import com.trains.metric.MetricBuilder;
import com.trains.route.IRoute;
import com.trains.route.RouterImpl;

public class Main {

	public static void main(String[] args) throws Exception {
		final IRoute route = new RouterImpl(GraphBuilder.getEmptyGraph());
		
		File inputFile = new File(Main.class.getResource("/configMetrics.txt").toURI());
		final List<IMetric> metricConfigList = new MetricBuilder(System.out).constructMetrics(inputFile);
		GraphBuilder.createGraphicRoutes(route, "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		
		for (final IMetric metric : metricConfigList) {
            metric.execute(route);
        }

	}
}
