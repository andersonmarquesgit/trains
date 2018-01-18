package com.trains.metric;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.trains.exception.NoSuchRouteException;
import com.trains.route.IRoute;

public abstract class AbstractRouteMetric extends AbstractMetric {

	protected static final String NO_ROUTE_MSG = "NO SUCH ROUTE";
	protected final PrintStream outputStream;

	public AbstractRouteMetric(String configMetricLine, PrintStream outputStream) {
		super(configMetricLine);
		this.outputStream = outputStream;
	}

	protected abstract int callRoute(IRoute route, String start, String end, List<String> intermediate);

	@Override
	public void execute(IRoute route) {
		final String routeLine = getConfigMetricLine().substring(10);
        final String[] nodes = routeLine.split("-");
        try {
            outputStream.println(callRoute(route,nodes[0], nodes[nodes.length - 1], getIntermediateList(nodes)));
        } catch (final NoSuchRouteException e) {
            outputStream.println(NO_ROUTE_MSG);
        }
	}

	private List<String> getIntermediateList(final String[] nodes) {
        final List<String> intermediateList = new ArrayList<String>();
        for (int i = 1; i < nodes.length - 1; i++) {
            intermediateList.add(nodes[i]);
        }
        return intermediateList;
    }
}
