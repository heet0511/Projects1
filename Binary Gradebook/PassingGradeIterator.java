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

import java.util.NoSuchElementException;

/**
 * Iterator for traversing the records in a Gradebook in increasing order, while also skipping
 * over StudentRecords who do not have a passing grade.
 * This iterator iterates through the StudentRecord objects with passing grades, only.
 */
public class PassingGradeIterator extends GradebookIterator {
    private double passingGrade;
    private StudentRecord next;

    /**
     * Constructor to initialize PassingGradeIterator with a Gradebook
     * @param gradebook
     */
    public PassingGradeIterator(Gradebook gradebook) {
        super(gradebook); // Calls the constructor of GradebookIterator
        this.passingGrade = gradebook.PASSING_GRADE;
        advanceToNextPassingGrade(); // Move to the next passing grade StudentRecord
    }


    /**
     * Move to the next passing grade StudentRecord
     */
    private void advanceToNextPassingGrade() {
        while (super.hasNext()) {
            StudentRecord nextRecord = super.next();
            if (nextRecord.getGrade() >= passingGrade) {
                next = nextRecord;
                return;
            }
        }
        next = null; // Set next to null if no passing grades are found
    }

    /**
     * Check if there is a next passing grade StudentRecord
     * @return true if next is not null, else false
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }

    /**
     * Get the next passing grade StudentRecord
     * @return passingRecord
     */
    @Override
    public StudentRecord next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more passing grades in the iteration");
        }
        StudentRecord passingRecord = next;
        advanceToNextPassingGrade(); // Move to the next passing grade StudentRecord
        return passingRecord;
    }
}
