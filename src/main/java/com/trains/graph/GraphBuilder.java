package com.trains.graph;

import com.trains.route.IRoute;

public class GraphBuilder {

    public static IGraph<String> getEmptyGraph() {
        return new DirectedGraph<String>();
    }
    
    public static void createGraphicRoutes(final IRoute route, String routes) {
        final String[] nodes = routes.split(",");
        for (final String node : nodes) {
            addNodes(route.getAllRoutes(), node);
        }

    }

    private static void addNodes(final IGraph<String> graphic, final String node) {
        final String trimmedPair =  node.trim();
        final String from = String.valueOf(trimmedPair.charAt(0));
        final String to = String.valueOf(trimmedPair.charAt(1));
        final int weight = Integer.valueOf(trimmedPair.substring(2));
        graphic.addNode(from);
        graphic.addNode(to);
        graphic.addEdge(from, to, weight);
    }
}
