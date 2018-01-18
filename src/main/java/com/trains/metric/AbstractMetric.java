package com.trains.metric;

public abstract class AbstractMetric implements IMetric {

	private final String configMetricLine;

	public AbstractMetric(final String configMetricLine) {
		this.configMetricLine = configMetricLine;
	}

	protected final String getConfigMetricLine() {
		return configMetricLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (configMetricLine == null ? 0 : configMetricLine.hashCode());
		return result;
	}

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
		final AbstractMetric other = (AbstractMetric) obj;
		if (configMetricLine == null) {
			if (other.configMetricLine != null) {
				return false;
			}
		} else if (!configMetricLine.equals(other.configMetricLine)) {
			return false;
		}
		return true;
	}

}
