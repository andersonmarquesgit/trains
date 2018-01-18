package com.trains.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.trains.exception.NoSuchRouteException;
import com.trains.filters.IPathFilter;

public class DirectedGraph<N> implements IGraph<N> {

	private final Map<N, Set<IEdge<N>>> edges = new HashMap<N, Set<IEdge<N>>>();

	@Override
	public boolean addEdge(N from, N to, int weight) {
		assertNodeExists(from);
		assertNodeExists(to);

		final IEdge<N> newEdge = DefaultEdge.getWeightedEdge(from, to, weight);

		final Set<IEdge<N>> sourceEdges = edges.get(from);
		if (sourceEdges.contains(newEdge)) {
			sourceEdges.remove(newEdge);
		}
		return edges.get(from).add(newEdge);
	}

	private void assertNodeExists(final N node) {
		if (!edges.containsKey(node)) {
			throw new IllegalArgumentException("Node " + node.toString() + " does not exist");
		}
	}

	@Override
	public boolean addNode(N node) {
		assertNodeNotNull(node);
		if (!edges.containsKey(node)) {
			edges.put(node, new LinkedHashSet<IEdge<N>>());
			return true;
		}
		return false;
	}

	private void assertNodeNotNull(final N node) {
		if (node == null) {
			throw new IllegalArgumentException("Node can not be null");
		}
	}

	@Override
	public IEdge<N> getEdge(N from, N to) {
		assertNodeExists(from);
		assertNodeExists(to);

		final Set<IEdge<N>> startingNodeEdges = edges.get(from);
		for (final IEdge<N> eachEdge : startingNodeEdges) {
			if (eachEdge.getEndingNode().equals(to)) {
				return eachEdge;
			}
		}
		return null;
	}

	@Override
	public Set<N> getAllVertex() {
		return Collections.unmodifiableSet(edges.keySet());
	}

	@Override
	public List<IPath<N>> getAllPaths(N startingNode, N endingNode, IPathFilter<N> filter) {
		assertNodeExists(startingNode);
		assertNodeExists(endingNode);

		final List<IPath<N>> paths = new ArrayList<IPath<N>>();
		for (final IEdge<N> each : edges.get(startingNode)) {
			final IPath<N> path = GraphPath.emptyPath();
			path.addEdge(each);
			paths.addAll(search(path, filter, endingNode));
		}

		if (paths.isEmpty()) {
			throw new NoSuchRouteException(startingNode.toString(), endingNode.toString());
		}
		return paths;
	}

	private List<IPath<N>> search(final IPath<N> path, final IPathFilter<N> filter, final N end) {
		final List<IPath<N>> paths = new ArrayList<IPath<N>>();
		if (filter.passFilter(path)) {
			if (hasReachedGoal(path, end)) {
				paths.add(GraphPath.copyPath(path));
			}
			for (final IEdge<N> each : edges.get(path.getLastNode())) {
				path.addEdge(each);
				paths.addAll(search(path, filter, end));
			}

		}
		path.removeLastEdge();
		return paths;
	}

	private boolean hasReachedGoal(final IPath<N> path, final N end) {
		return path.getLastNode().equals(end);
	}

}
