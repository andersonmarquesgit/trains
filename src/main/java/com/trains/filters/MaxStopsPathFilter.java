package com.trains.filters;

import com.trains.graph.IPath;

/**
 * Filtro de verificação do máximo de paradas.
 * @author anderson.marques
 *
 * @param <N>
 * Nó da rota
 */
public class MaxStopsPathFilter<N> implements IPathFilter<N> {

	private final int maxStops;

    public MaxStopsPathFilter(final int maxHops) {
        super();
        this.maxStops = maxHops;
    }

    @Override
    public boolean passFilter(final IPath<N> path) {
        return path.getNumberOfStops() <= maxStops;
    }

}
