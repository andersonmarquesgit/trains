package com.trains.metric;

import com.trains.route.IRoute;

/**
 * Interface geral que permite a criação das métricas de cada cálculo.
 * @author anderson.marques
 *
 */
public interface IMetric {
	void execute(IRoute route, String outNumber);
}
