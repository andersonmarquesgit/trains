package com.trains.metric;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
		final List<IMetric> commands = new ArrayList<IMetric>();
        final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        while (reader.ready()) {
            final IMetric toAdd = processLine(reader);
            if (toAdd != null) {
                commands.add(toAdd);
            }
        }
        reader.close();
        return commands;
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
