package com.trains.graph;

import java.util.List;


public interface IPath<N> extends Comparable<IPath<N>> {
    void addEdge(IEdge<N> edge);

    int getPathTotalWeight();
    int getNumberOfStops();
    N getLastNode();
    void removeLastEdge();
    List<IEdge<N>> getEdgeList();
    boolean hasRepeatedEdges();
    boolean startsWith(IPath<N> otherPath);
}
