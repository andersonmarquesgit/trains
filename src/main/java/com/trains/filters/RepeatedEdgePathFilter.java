package com.trains.filters;

import com.trains.graph.IPath;

public class RepeatedEdgePathFilter<N> implements IPathFilter<N> {

	@Override
	public boolean passFilter(IPath<N> path) {
		return !path.hasRepeatedEdges();
	}

}
