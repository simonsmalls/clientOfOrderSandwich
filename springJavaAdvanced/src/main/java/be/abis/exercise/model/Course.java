package be.abis.exercise.model;


public enum Course implements CourseInterface {
	   JAVA_PROGRAMMING ("Java programming", 4, 375.0),
	   JAVA_ADVANCED ("Java SE advanced programming", 3, 400.0),
	   J2EE_WEBSPHERE ("J2EE: building enterprise Java with WebSphere", 3, 400.0),
	   MAINFRAME_CASE ("Mainframe case study", 5, 350),
	   UNIX_SHELL ("UNIX/Linux shell programming", 2, 375),
	   SQL_FUNDAMENTALS ("SQL fundamentals", 2, 350),
	   INTERNET_ENABLING ("Internet web-enabling techniques", 1, 400);

	private String title;
	private int days;
	private double dailyPrice;

	private Course(String title, int days, double dailyPrice) {
		this.title = title;
		this.days = days;
		this.dailyPrice = dailyPrice;
	}

	public String getTitle() {
		return title;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	
	@Override
	public String toString() {
		return this.getTitle();
	}
}