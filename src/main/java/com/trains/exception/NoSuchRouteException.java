package com.trains.exception;

/**
 * Implementação da exceção de ausência de rota
 * @author anderson.marques
 *
 */
public class NoSuchRouteException extends RuntimeException {

	private static final long serialVersionUID = -3002885204210474431L;

	public NoSuchRouteException(final String from, final String end) {
        super("No such route: " + from + " and " + end);
    }
}
