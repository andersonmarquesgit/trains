package com.trains.filters;

import com.trains.graph.IPath;

public class ContainsPathFilter<N> implements IPathFilter<N> {

	private final IPath<N> objectivePath;

	public ContainsPathFilter(final IPath<N> objectivePath) {
		this.objectivePath = objectivePath;
	}

	@Override
	public boolean passFilter(IPath<N> path) {
		return objectivePath.startsWith(path);
	}

}
