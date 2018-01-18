package com.trains.graph;

import java.util.List;
import java.util.Set;

import com.trains.filters.IPathFilter;


public interface IGraph<N> {
    boolean addEdge(N from, N to, int weight);
    boolean addNode(N node);
    IEdge<N> getEdge(N from, N to);
    Set<N> getAllVertex();
    List<IPath<N>> getAllPaths(N startingNode, N endingNode, IPathFilter<N> filter);
}
