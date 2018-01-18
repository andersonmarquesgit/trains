package com.trains.filters;

import com.trains.graph.IPath;

public interface IPathFilter<N> {

	  boolean passFilter(final IPath<N> path);
}
