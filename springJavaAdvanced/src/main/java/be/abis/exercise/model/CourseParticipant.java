package be.abis.exercise.model;


public interface CourseParticipant extends Comparable<CourseParticipant> {
	void attendCourse(Course course);
}
