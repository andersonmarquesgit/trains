package com.trains.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphPath<N> implements IPath<N> {

	private final List<IEdge<N>> edgeList = new ArrayList<IEdge<N>>();
	private int totalWeight = 0;

	private GraphPath() {
	}

	private GraphPath(final IPath<N> otherPath) {
		edgeList.addAll(otherPath.getEdgeList());
		this.totalWeight = otherPath.getPathTotalWeight();
	}

	public static <N> IPath<N> emptyPath() {
		return new GraphPath<N>();
	}

	public static <N> IPath<N> copyPath(final IPath<N> otherPath) {
		return new GraphPath<N>(otherPath);
	}

	@Override
	public void addEdge(IEdge<N> edge) {
		if (!edgeIsConsecutive(edge)) {
			throw new IllegalArgumentException("The edge " + edge + " is not consecutive to the existing path");
		}
		edgeList.add(edge);
		totalWeight += edge.getWeight();
	}

	private boolean edgeIsConsecutive(final IEdge<N> edge) {
		final N lastNode = getLastNode();
		if (lastNode != null && !lastNode.equals(edge.getStartingNode())) {
			return false;
		}
		return true;
	}

	@Override
	public int getPathTotalWeight() {
		return totalWeight;
	}

	@Override
	public int getNumberOfStops() {
		return edgeList.size();
	}

	@Override
	public N getLastNode() {
		N node = null;
		if (!edgeList.isEmpty()) {
			node = edgeList.get(edgeList.size() - 1).getEndingNode();
		}
		return node;
	}

	@Override
	public void removeLastEdge() {
		if (!edgeList.isEmpty()) {
			final IEdge<N> lastEdge = edgeList.get(edgeList.size() - 1);
			this.totalWeight -= lastEdge.getWeight();
			edgeList.remove(edgeList.size() - 1);
		}

	}

	@Override
	public List<IEdge<N>> getEdgeList() {
		return Collections.unmodifiableList(edgeList);
	}

	@Override
	public boolean hasRepeatedEdges() {
		for (int i = 0; i < edgeList.size(); i++) {
			for (int j = i + 1; j < edgeList.size(); j++) {
				if (edgeList.get(i).equals(edgeList.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean startsWith(IPath<N> otherPath) {
		final List<IEdge<N>> partialPath = otherPath.getEdgeList();
		final List<IEdge<N>> completePath = getEdgeList();
		for (int i = 0; i < partialPath.size(); i++) {
			if (i >= completePath.size() || !partialPath.get(i).equals(completePath.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int compareTo(IPath<N> arg0) {
		return this.getPathTotalWeight() - arg0.getPathTotalWeight();
	}

}
