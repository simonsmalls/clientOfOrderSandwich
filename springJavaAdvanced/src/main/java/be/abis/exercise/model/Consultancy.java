package be.abis.exercise.model;
public class Consultancy implements Service {

	public double invoice() {
		return 800;
	}

	@Override
	public String toString() {
		return "Consultancy";
	}

}
