package com.trains.filters;

import com.trains.graph.IPath;

/**
 * Filtro de verificação do total da rota
 * @author anderson.marques
 *
 * @param <N>
 * Nó da rota
 */
public class WeightPathFilter<N> implements IPathFilter<N> {

	private final int maxWeight;

    public WeightPathFilter(final int maxWeight) {
        super();
        this.maxWeight = maxWeight;
    }

	@Override
	public boolean passFilter(IPath<N> path) {
		return path.getPathTotalWeight() < maxWeight;
	}

}
