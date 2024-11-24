
// Title: Binary Gradebook
// Author: Heet Joshi
// Email: hjoshi6@wisc.edu

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models a grade book for a specific course used to store student
 * records.
 */
public class Gradebook implements Iterable<StudentRecord> {

    /**
     * Name of this course
     */
    public final String course;

    /**
     * Minimum passing grade for this course
     */
    public final double PASSING_GRADE;

    /**
     * Root node of the BST
     */
    private BSTNode<StudentRecord> root;

    /**
     * Total number of StudentRecords stored in this Gradebook
     */
    private int size;

    /**
     * Indicates whether the passing grade iterator is enabled
     */
    private boolean passingGradeIteratorEnabled;

    /**
     * Constructs an empty Gradebook for a given course and define its passing
     * grade.
     *
     * @param course       - name of the course
     * @param passingGrade - passing grade of the course
     */
    public Gradebook(String course, double passingGrade) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }
        if (passingGrade < 0.0 || passingGrade > 100.0) {
            throw new IllegalArgumentException("Passing grade must be in the range [0.0, 100.0].");
        }

        this.course = course;
        this.PASSING_GRADE = passingGrade;
        this.root = null;
        this.size = 0;
        this.passingGradeIteratorEnabled = false;
        passingGradeIteratorEnabled = false;
    }

    /**
     * Enables the passing grade iterator
     */
    public void enablePassingGradeIterator() {
        passingGradeIteratorEnabled = true;
    }

    /**
     * Disables the passing grade iterator
     */
    public void disablePassingGradeIterator() {
        passingGradeIteratorEnabled = false;
    }

    /**
     * Returns an iterator over the student records in this gradebook in the
     * increasing order.
     *
     * @return an Iterator over the elements in this gradebook in proper sequence.
     */
    @Override
    public Iterator<StudentRecord> iterator() {
        if (passingGradeIteratorEnabled) {
            return new PassingGradeIterator(this);
        } else {
            return new GradebookIterator(this);
        }
    }

    /**
     * Checks whether this Gradebook is empty
     *
     * @return true if this Gradebook is empty and false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of this Gradebook
     *
     * @return the total number of StudentRecord objects stored in this Gradebook
     */
    public int size() {
        return size;
    }

    /**
     * Adds a new StudentRecord to this Gradebook. This method tries to add record
     * to this tree and updates its size accordingly.
     *
     * @param record - to be added to this Gradebook
     * @throws IllegalStateException - If a match with record is already in this
     *                               tree
     */
    public void addStudent(StudentRecord record) {
        root = addStudentHelper(record, root);
        size++;
    }

    /**
     * Recursive helper method to add a record to the subtree rooted at node
     *
     * @param record - new Student to add
     * @param node   - root of a subtree
     * @return the new root of this BST after adding the record to this tree
     * @throws IllegalStateException - if the subtree rooted at node contains a
     *                               duplicate record
     */
    protected static BSTNode<StudentRecord> addStudentHelper(StudentRecord record, BSTNode<StudentRecord> node) {
        if (node == null) {
            return new BSTNode<>(record);
        }

        int compareResult = record.compareTo(node.getData());
        if (compareResult < 0) {
            node.setLeft(addStudentHelper(record, node.getLeft()));
        } else if (compareResult > 0) {
            node.setRight(addStudentHelper(record, node.getRight()));
        } else {
            throw new IllegalStateException("Duplicate StudentRecord not allowed.");
        }

        return node;
    }

    /**
     * Returns a String representation of the contents of this Gradebook in
     * increasing order
     *
     * @return an in-order String representation of this Gradebook
     *
     */
    @Override
    public String toString() {
        String result = toStringHelper(root);
        return result;
    }

    /**
     * Returns a String representation of the subtree rooted at node in increasing
     * order
     *
     * @param node - root of a subtree
     * @return an in-order String representation of the subtree rooted at node
     */
    protected static String toStringHelper(BSTNode<StudentRecord> node) {
        StringBuilder result = new StringBuilder();

        if (node != null) {

            result.append(toStringHelper(node.getLeft()));


            result.append(node.getData().toString());
            result.append("\n");


            result.append(toStringHelper(node.getRight()));
        }

        return result.toString();
    }

    /**
     * Returns a decreasing-order String representation of the structure of this
     * subtree, indented by four spaces for each level of depth in the larger tree
     *
     * @param node  - current subtree within the larger tree
     * @param depth - depth of the current node within the larger tree
     * @return a String representation of the structure of this subtree
     */
    protected static String prettyStringHelper(BSTNode<StudentRecord> node, int depth) {
        if (node == null) {
            return "";
        }

        String rightSubtree = prettyStringHelper(node.getRight(), depth + 1);
        String indent = "    ".repeat(depth);
        String currentStudent = indent + node.getData().name + "\n";
        String leftSubtree = prettyStringHelper(node.getLeft(), depth + 1);

        return rightSubtree + currentStudent + leftSubtree;
    }

    /**
     * Returns a String representation of the structure of this BST.
     *
     * @return a String representation of the structure of this BST
     */
    public String prettyString() {
        return prettyStringHelper(root, 0);
    }

    /**
     * Finds a StudentRecord given the associated email address
     *
     * @param email - email address of a student
     * @return the Student associated with the email argument if there is a match,
     *         or null otherwise
     */
    public StudentRecord lookup(String email) {
        if (root == null || email == null) {
            return null;
        }
        return lookupHelper(new StudentRecord("Bob@email.com", email, 0.0), root);
    }

    /**
     * Recursive helper method which looks for a given StudentRecord given in the
     * BST rooted at node
     *
     * @param target - the StudentRecord to search in the subtree rooted at node
     * @param node   - root of a subtree of this BST
     * @return the StudentRecord which matches the one passed as input if a match is
     *         found in the subtree rooted at node, or null if no match found
     */
    protected static StudentRecord lookupHelper(StudentRecord target, BSTNode<StudentRecord> node) {
        if (node == null) {
            return null;
        }

        int compare = target.compareTo(node.getData());
        if (compare == 0) {
            return node.getData();
        } else if (compare < 0) {
            return lookupHelper(target, node.getLeft());
        } else {
            return lookupHelper(target, node.getRight());
        }
    }


    /**
     * Returns the StudentRecord with the lexicographically smallest email in this
     * BST, or null if this Gradebook is empty
     *
     * @return the StudentRecord with the lexicographically smallest email in this
     *         BST
     */
    protected StudentRecord getMin() {
        if (root == null) {
            return null;
        }
        return getMinHelper(root);
    }

    /**
     * Returns the smallest StudentRecord in the subtree rooted at node
     *
     * @param node - root of a subtree of a binary search tree
     * @return the smallest Student in the subtree rooted at node, or null if the
     *         node is null
     */
    protected static StudentRecord getMinHelper(BSTNode<StudentRecord> node) {
        if (node.getLeft() == null) {
            return node.getData();
        }
        return getMinHelper(node.getLeft());
    }

    /**
     * Deletes a StudentRecord from this Gradebook given their email, or throws a
     * NoSuchElementException if there is no StudentRecord with the given email.
     *
     * @param email - the email of the student to delete
     * @throws NoSuchElementException - if there is no matching StudentRecord in
     *                                this Gradebook
     */
    public void removeStudent(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        StudentRecord recordToRemove = lookup(email);
        if (recordToRemove == null) {
            throw new NoSuchElementException("No matching StudentRecord found.");
        }
        root = removeStudentHelper(recordToRemove, root);
        size--;
    }

    /**
     * Deletes the matching StudentRecord with toDrop if it is found within this
     * tree, or otherwise throws a NoSuchElementException.
     *
     * @param toDrop - the StudentRecord to be removed from this tree
     * @param node   - the root of the subtree to remove the student from
     * @return the new root of the subtree after removing the matching StudentRecord
     * @throws NoSuchElementException - if there is no matching StudentRecord in
     *                                this subtree
     */
    protected static BSTNode<StudentRecord> removeStudentHelper(StudentRecord toDrop, BSTNode<StudentRecord> node) {
        if (node == null) {
            throw new NoSuchElementException("No matching StudentRecord found.");
        }

        int compareResult = toDrop.compareTo(node.getData());
        if (compareResult < 0) {
            node.setLeft(removeStudentHelper(toDrop, node.getLeft()));
        } else if (compareResult > 0) {
            node.setRight(removeStudentHelper(toDrop, node.getRight()));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            BSTNode<StudentRecord> successor = getMinNode(node.getRight());
            node.setData(successor.getData());
            node.setRight(removeMinNode(node.getRight()));
        }

        return node;
    }

    /**
     * Retrieves the node with the minimum key value in the given binary search tree
     * (BST).
     *
     * @param node The root node of the BST (or subtree) from which to find the
     *             minimum node.
     * @return The node with the minimum key value in the BST, or null if the BST is
     *         empty.
     */
    private static BSTNode<StudentRecord> getMinNode(BSTNode<StudentRecord> node) {
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Removes the node with the minimum key value in the given binary search tree
     * (BST). If the minimum node has a right subtree, the minimum node itself is
     * removed and replaced with its right child. If it doesn't have a right
     * subtree, the minimum node is simply removed.
     *
     * @param node The root node of the BST (or subtree) from which to remove the
     *             minimum node.
     * @return The root node of the BST (or subtree) after the minimum node has been
     *         removed.
     */
    private static BSTNode<StudentRecord> removeMinNode(BSTNode<StudentRecord> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(removeMinNode(node.getLeft()));
        return node;
    }

    /**
     * Returns the successor of a target StudentRecord or returns null if there is
     * no successor in this Gradebook
     *
     * @param target - the StudentRecord to find the successor of
     * @return the successor of the target in the Gradebook, or null if none exists
     */
    protected StudentRecord successor(StudentRecord target) {
        if (isEmpty()) {
            return null;
        }

        return successorHelper(target, root);
    }

    /**
     * Returns the successor of a target StudentRecord within the subtree or returns
     * null if there is no successor in this subtree.
     *
     * @param target - the StudentRecord to find the successor of
     * @param node   - the subtree to search for a successor to the target
     * @return the successor of the target in the subtree rooted at node or null if
     *         none exists
     */
    protected static StudentRecord successorHelper(StudentRecord target, BSTNode<StudentRecord> node) {
        if (node == null) {
            return null;
        }


        int compareResult = target.compareTo(node.getData());

        if (compareResult >= 0) {
            return successorHelper(target, node.getRight());
        } else {
            StudentRecord successor = successorHelper(target, node.getLeft());
            if (successor == null) {
                if (node.getData().compareTo(target) > 0) {
                    return node.getData();
                }
            }
            return successor;
        }
    }

    /**
     * Returns true if this BST has an identical layout (all subtrees equal) to the
     * given tree.
     *
     * @author Ashley Samuelson
     * @author Heet Joshi
     * @see BSTNode#equals(Object)
     * @param node tree to compare this Gradebook to
     * @return true if the given tree looks identical to the root of this Gradebook
     */
    public boolean equalBST(BSTNode<StudentRecord> node) {
        return root == node || (root != null && root.equals(node));
    }

    /**
     * Searches for the StudentRecord associated with the provided input email in
     * this BST and checks whether it has a passing grade for this course.
     *
     * @param email - the email of the StudentRecord to find
     * @return A String indicating whether the student having the input email has a
     *         passing or failing grade
     */
    public String checkPassingCourse(String email) {

        StudentRecord student = lookup(email);


        if (student == null) {
            return "No match found.";
        }


        if (student.getGrade() >= PASSING_GRADE) {
            return student.toString() + ": PASS";
        } else {
            return student.toString() + ": FAIL";
        }
    }
}
