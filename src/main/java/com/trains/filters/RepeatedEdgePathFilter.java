package com.trains.filters;

import com.trains.graph.IPath;


/**
 * Filtro de verificação de nós repetidos
 * @author anderson.marques
 *
 * @param <N>
 * Nó da rota
 */
public class RepeatedEdgePathFilter<N> implements IPathFilter<N> {

	@Override
	public boolean passFilter(IPath<N> path) {
		return !path.hasRepeatedEdges();
	}

}
