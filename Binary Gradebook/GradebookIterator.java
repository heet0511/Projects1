
// Title: Binary Gradebook Iterator
// Author: Heet Joshi
// Email: hjoshi6@wisc.edu

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This iterator class is designed to traverse the student records in a Gradebook in ascending order,
 * ensuring that no records are skipped during the iteration process.
 */
public class GradebookIterator implements Iterator<StudentRecord> {
    /**
     * Reference to the current StudentRecord.
     */
    private StudentRecord current;
    /**
     * The Gradebook instance to iterate over.
     */
    private Gradebook gradebook;

    /**
     * Constructs a GradebookIterator object with a reference to the Gradebook it iterates over.
     *
     * @param gradebook The Gradebook instance to iterate over.
     */
    public GradebookIterator(Gradebook gradebook) {
        this.gradebook = gradebook;
        this.current = gradebook.getMin();
    }

    /**
     * Checks if there exists a next StudentRecord in the iteration.
     *
     * @return true if the currentRecord is not null, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Retrieves the next StudentRecord in the iteration.
     *
     * @return The next StudentRecord.
     * @throws NoSuchElementException if there are no more elements in the iteration.
     */
    @Override
    public StudentRecord next() {
        // Throws NoSuchElementException if there are no more elements in the iteration
        if (current == null) {
            throw new NoSuchElementException("No more elements in the iteration");
        }
        // Retrieves the next StudentRecord
        StudentRecord nextRecord = current;
        // Moves to the next StudentRecord using the successor method of the associated Gradebook
        current = gradebook.successor(current);
        // Returns the next StudentRecord
        return nextRecord;
    }
}
