package com.trains.metric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Está classe é responsável por criar as métricas. Foi adotado um arquivo de configuração de cada métrica
 * afim de instanciar a sua respectiva classe. Caso seja necessário evoluir essa configuração para ser carregada de outra
 * fonte basta alterar o método constructMetrics().
 * @author anderson.marques
 *
 */
public class MetricBuilder {

	private static final String DISTANCE_REGEX = "DISTANCE:\\s\\D-\\D(-\\D)*";
    private static final String TRIPS_REGEX = "TRIPS:\\s(MAX_STOPS|EXACT_STOPS|MAX_DISTANCE),(\\d)+,\\D-\\D";
    private static final String SHORTEST_REGEX = "SHORTEST:\\s\\D-\\D";
    private static final String DURATION_REGEX = "DURATION:\\s\\w-\\w(-\\w)*";
    
    private final PrintStream outputStream;

    public MetricBuilder(final PrintStream outputStream) {
        this.outputStream = outputStream;
    }
    
    public List<IMetric> constructMetrics(File inputFile) throws IOException {
		final List<IMetric> metrics = new ArrayList<IMetric>();
        final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        while (reader.ready()) {
            final IMetric toAdd = processLine(reader);
            if (toAdd != null) {
            	metrics.add(toAdd);
            }
        }
        reader.close();
        return metrics;
	}
    
    private IMetric processLine(final BufferedReader reader) throws IOException {
    	IMetric processedRoute = null;
        final String currentLine = reader.readLine().toUpperCase();
        if (currentLine.matches(SHORTEST_REGEX)) {
            processedRoute = new ShortesDistanceMetric(currentLine, outputStream);
        } else if (currentLine.matches(TRIPS_REGEX)) {
            processedRoute = new TripsMetric(currentLine, outputStream);
        } else if (currentLine.matches(DISTANCE_REGEX)) {
            processedRoute = new DistanceMetric(currentLine, outputStream);
        } else if (currentLine.matches(DURATION_REGEX)) {
            processedRoute = new DurationMetric(currentLine, outputStream);
        } else {
            System.out.println("Line: " + currentLine + " is invalid");
        }
        return processedRoute;
    }
	
}
