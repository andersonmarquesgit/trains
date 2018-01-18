package com.trains.graph;

public class DefaultEdge<N> implements IEdge<N> {

	private final N fromNode;
	private final N endNode;
	private final int weight;

	public DefaultEdge(N fromNode, N endNode, int weight) {
		super();
		this.fromNode = fromNode;
		this.endNode = endNode;
		this.weight = weight;
	}

	public static <N> IEdge<N> getWeightedEdge(final N fromNode, final N endNode, final int weight) {
		return new DefaultEdge<N>(fromNode, endNode, weight);
	}

	@Override
	public N getStartingNode() {
		return fromNode;
	}

	@Override
	public N getEndingNode() {
		return endNode;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (endNode == null ? 0 : endNode.hashCode());
		result = prime * result + (fromNode == null ? 0 : fromNode.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DefaultEdge other = (DefaultEdge) obj;
		if (endNode == null) {
			if (other.endNode != null) {
				return false;
			}
		} else if (!endNode.equals(other.endNode)) {
			return false;
		}
		if (fromNode == null) {
			if (other.fromNode != null) {
				return false;
			}
		} else if (!fromNode.equals(other.fromNode)) {
			return false;
		}
		return true;
	}

}
