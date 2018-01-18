package com.trains.route;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.trains.exception.NoSuchRouteException;
import com.trains.graph.DirectedGraph;
import com.trains.graph.IGraph;
import com.trains.route.IRoute;
import com.trains.route.RouteImpl;

import junit.framework.TestCase;

public class RouteTest extends TestCase {

	private final IRoute route = new RouteImpl(this.getDefaultRoutes());

	@Test
	public void distanceForABCMustBe9() {
		assertEquals("Distance of the route A-B-C must be 9", 9,
				route.routeDistance("A", "C", getIntermediateList("B")));
	}

	@Test
	public void distanceForADMustBe5() {
		assertEquals("Distance of the route A-D must be 5", 5, route.routeDistance("A", "D", null));
	}

	@Test
	public void distanceForADCMustBe13() {
		assertEquals("The distance for the route A-D-C must be 13", 13,
				route.routeDistance("A", "C", getIntermediateList("D")));
	}

	@Test
	public void distanceForAEBCDMustBe22() {
		assertEquals("The distance for the route A-B-C must be 22", 22,
				route.routeDistance("A", "D", getIntermediateList("E", "B", "C")));
	}

	@Test(expected = NoSuchRouteException.class)
	public void distanceForAEDMustThrowNoSuchRouteException() {
		route.routeDistance("A", "D", getIntermediateList("E"));
	}

	@Test
	public void numberOfTripsBetweenCandCWithMax3StopsMustBe2() {
		assertEquals("There must be 2 trips between C and C with a maximum of 3 stops", 2,
				route.numberOfTripsWithMaxStops("C", "C", 3));
	}

	@Test
	public void numberOfTripsBetweenAandCWith4StopsMustBe3() {
		assertEquals("There must be 3 trips between A and C with exactly 4 stops", 3,
				route.numberOfTripsWithExactStops("A", "C", 4));
	}

	@Test
	public void shortestDistanceFromAtoCMustBe9() {
		assertEquals("The shortest distance from A to C must be 9", 9, route.legthOfTheShortestRoute("A", "C"));
	}

	@Test
	public void shortestDistanceFromBtoBMustBe9() {
		assertEquals("The shortest distance from B to B must be 9", 9, route.legthOfTheShortestRoute("B", "B"));
	}

	@Test
	public void numberOfRoutesBetweenCandCmustBe7() {
		assertEquals("There must be 7 routes between C and C with a distance less than 30", 7,

				route.numberOfTripsWithMaxWeight("C", "C", 30));
	}

	@Test
	public void durationOfTheTripABCMustBe11() throws Exception {
		assertEquals(11, route.routeDuration("A", "C", getIntermediateList("B")));
	}

	private List<String> getIntermediateList(final String... nodes) {
		final List<String> intermediateList = new ArrayList<String>();
		for (final String each : nodes) {
			intermediateList.add(each);
		}
		return intermediateList;
	}

	public static IGraph<String> getDefaultRoutes() {
		final IGraph<String> routeGraph = new DirectedGraph<String>();
		final String nodeA = "A";
		final String nodeB = "B";
		final String nodeC = "C";
		final String nodeD = "D";
		final String nodeE = "E";

		routeGraph.addNode(nodeA);
		routeGraph.addNode(nodeB);
		routeGraph.addNode(nodeC);
		routeGraph.addNode(nodeD);
		routeGraph.addNode(nodeE);

		routeGraph.addEdge(nodeA, nodeB, 5);
		routeGraph.addEdge(nodeB, nodeC, 4);
		routeGraph.addEdge(nodeC, nodeD, 8);
		routeGraph.addEdge(nodeD, nodeC, 8);
		routeGraph.addEdge(nodeD, nodeE, 6);
		routeGraph.addEdge(nodeA, nodeD, 5);
		routeGraph.addEdge(nodeC, nodeE, 2);
		routeGraph.addEdge(nodeE, nodeB, 3);
		routeGraph.addEdge(nodeA, nodeE, 7);
		return routeGraph;
	}
}
