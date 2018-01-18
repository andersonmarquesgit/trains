package com.trains.graph;

public interface IEdge<N> {
	N getStartingNode();
	N getEndingNode();
	int getWeight();
}
