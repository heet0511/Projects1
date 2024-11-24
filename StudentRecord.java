////////////FILE HEADER(INCLUDE IN EVERY FILE)////////////////
//
// Title: Binary Gradebook
// Course: CS300 Spring 2024
//
// Author: Heet Joshi
// Email: hjoshi6@wisc.edu
// Lecturer: Mouna Kacem
//
////////////////// ASSISTANCE/HELP CITATIONS//////////////
//
// Person: No help taken
// Online sources: No help taken
//
//////////////////////////////////////////////////////////////////


/**
 * Represents a student record with a name, email, and grade.
 * Implements Comparable and equals for comparison operations.
 */
public class StudentRecord extends Object implements Comparable<StudentRecord> {
    public final String name;
    public final String email;
    private double grade;

    /**
     * Constructor to initialize a StudentRecord with name, email, and grade
     *
     * @param name  Name of the person.
     * @param email Email of the person.
     * @param grade Grade the person earned.
     */
    public StudentRecord(String name, String email, double grade) {
        // Check for null or blank name and email
        if (name == null || name.isBlank() || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Name and email must not be null or blank");
        }
        // Check if grade is within the valid range
        if (grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Grade must be within 0.0 to 100.0 (inclusive)");
        }
        this.name = name;
        this.email = email;
        this.grade = grade;
    }

    /**
     * Getter method to retrieve the grade.
     *
     * @return The grade.
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Setter method to set the grade.
     *
     * @param grade The grade to set.
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Method to return a string representation of the StudentRecord.
     *
     * @return String representation of the StudentRecord.
     */
    @Override
    public String toString() {
        return name + " (" + email + "): " + grade;
    }

    /**
     * Method to compare StudentRecords based on email.
     *
     * @param other The StudentRecord to compare to.
     * @return 0 if emails are equal, positive if this email is greater, else negative.
     */
    @Override
    public int compareTo(StudentRecord other) {
        return this.email.compareTo(other.email);
    }

    /**
     * Method to check if two StudentRecords are equal based on email.
     *
     * @param o The object to compare to.
     * @return true if the emails are equal, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentRecord that = (StudentRecord) o;
        return email.equals(that.email);
    }
}
