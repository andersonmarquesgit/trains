package com.trains.filters;

import com.trains.graph.IPath;

/**
 * Filtro de verificação de paradas exatas.
 * @author anderson.marques
 *
 * @param <N>
 */
public class ExactStopsPathFilter<N> implements IPathFilter<N> {

	private final int stopsNumber;

	public ExactStopsPathFilter(final int stopsNumber) {
	        super();
	        this.stopsNumber = stopsNumber;
	    }

	@Override
	public boolean passFilter(IPath<N> path) {
		return path.getNumberOfStops() == stopsNumber;
	}

}
