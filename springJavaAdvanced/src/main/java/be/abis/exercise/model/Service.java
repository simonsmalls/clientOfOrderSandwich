package be.abis.exercise.model;

import be.abis.exercise.exception.InvoiceException;

public interface Service {
	public abstract double invoice() throws InvoiceException;
}
