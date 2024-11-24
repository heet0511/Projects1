
// Title: Binary Gradebook Tester
// Author: Heet Joshi
// Email: hjoshi6@wisc.edu

import java.util.Iterator;

/**
 * A tester class for testing the methods and constructors in Gradebook,
 * GradebookIterator and PassingGradeIterator class.
 */
public class GradebookTester {

    /**
     * Tests the constructor of the Gradebook class with various input parameters to
     * ensure proper behavior. Checks for valid constructor parameters, invalid
     * course names (null or empty), and invalid passing grades.
     *
     * @return true if all tests pass successfully, false otherwise.
     */
    public static boolean constructorTester() {

        try {
            Gradebook validGradebook = new Gradebook("Calculus", 65.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Valid constructor test failed: " + e.getMessage());
            return false;
        }


        try {
            Gradebook invalidCourseGradebook = new Gradebook(null, 65.0);
            System.out.println("Invalid course name test failed.");
            return false;
        } catch (IllegalArgumentException e) {
        }


        try {
            Gradebook emptyCourseGradebook = new Gradebook("", 65.0);
            System.out.println("Empty course name test failed.");
            return false;
        } catch (IllegalArgumentException e) {
        }

        try {
            Gradebook invalidGradebook = new Gradebook("Invalid Course", -20.0);
            System.out.println("Invalid passing grade test failed.");
            return false;
        } catch (IllegalArgumentException e) {
        }


        try {
            Gradebook invalidGradebook = new Gradebook("Invalid Course", 110.0);
            System.out.println("Invalid passing grade test failed.");
            return false;
        } catch (IllegalArgumentException e) {
        }

        return true;
    }

    /**
     * Tests the isEmpty, size, addStudent, and removeStudent methods of the
     * Gradebook class with various scenarios to ensure proper behavior and
     * exception handling.
     *
     * @return true if all tests pass successfully, false otherwise.
     */
    public static boolean isEmptySizeAddTester() {
        Gradebook gradebook1 = new Gradebook("Calculus", 65.0);

        boolean isEmpty1 = gradebook1.isEmpty();


        StudentRecord student1 = new StudentRecord("Emma", "emma@example.com", 80.0);
        gradebook1.addStudent(student1);
        boolean isEmpty2 = gradebook1.isEmpty();


        boolean hasIllegalStateException = false;
        try {
            gradebook1.addStudent(student1);
        } catch (IllegalStateException e) {
            hasIllegalStateException = true;
        }


        gradebook1.removeStudent("emma@example.com");
        boolean isEmpty3 = gradebook1.isEmpty();


        StudentRecord student2 = new StudentRecord("Felix", "felix@example.com", 90.0);
        gradebook1.addStudent(student2);
        gradebook1.removeStudent("felix@example.com");
        boolean isEmpty4 = gradebook1.isEmpty();


        return isEmpty1 && !isEmpty2 && hasIllegalStateException && isEmpty3 && isEmpty4;
    }

    public static boolean toStringTester() {
        Gradebook gradebook = new Gradebook("Calculus", 65.0);


        StudentRecord student1 = new StudentRecord("Grace", "grace@example.com", 75.0);
        StudentRecord student2 = new StudentRecord("Henry", "henry@example.com", 85.0);
        StudentRecord student3 = new StudentRecord("Ivy", "ivy@example.com", 95.0);



        gradebook.addStudent(student2);
        gradebook.addStudent(student1);
        gradebook.addStudent(student3);


        String expected = "Grace (grace@example.com): 75.0\n" + "Henry (henry@example.com): 85.0\n"
                + "Ivy (ivy@example.com): 95.0\n";


        String actual = gradebook.toString();

        return expected.equals(actual);
    }

    /**
     * Tests the toString method of the Gradebook class to ensure it correctly
     * represents the contents of the gradebook in ascending order based on student
     * names.
     *
     * @return true if the string representation matches the expected format, false
     *         otherwise.
     */
    public static boolean prettyStringTester() {
        Gradebook gradebook = new Gradebook("Calculus", 65.0);


        StudentRecord student1 = new StudentRecord("Grace", "grace@example.com", 75.0);
        StudentRecord student2 = new StudentRecord("Henry", "henry@example.com", 85.0);
        StudentRecord student3 = new StudentRecord("Ivy", "ivy@example.com", 95.0);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);


        String expected = "    Ivy\n" + "Henry\n" + "Grace\n";


        String actual = gradebook.prettyString();


        return expected.trim().equals(actual.trim());
    }

    /**
     * Tests the lookup method of the Gradebook class to verify its ability to find
     * students by their email addresses, including cases where the email exists at
     * the root level and requires traversal to find.
     *
     * @return true if all lookup tests pass successfully, false otherwise.
     */
    public static boolean lookupTester() {
        // Create a Gradebook instance
        Gradebook gradebook = new Gradebook("Test", 70.0);

        // Insert some student records
        StudentRecord student1 = new StudentRecord("Bob", "bob@example.com", 86);
        StudentRecord student2 = new StudentRecord("Alice", "alice@example.com", 76);
        StudentRecord student3 = new StudentRecord("Charlie", "charlie@example.com", 90);
        StudentRecord student4 = new StudentRecord("Drake", "drake@example.com", 81);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);
        gradebook.addStudent(student4);

        // Lookup for existing students
        StudentRecord root = gradebook.lookup("bob@example.com");
        if (!root.email.equals(student1.email))
            return false;

        StudentRecord root2 = gradebook.lookup("alice@example.com");
        if (!root2.email.equals(student2.email))
            return false;

        StudentRecord root3 = gradebook.lookup("charlie@example.com");
        if (!root3.email.equals(student3.email))
            return false;

        // Lookup for non-existing student
        StudentRecord root4 = gradebook.lookup("random@example.com");
        if (root4 != null)
            return false;

        return true;
    }


    public static boolean getMinTester() {

        Gradebook gradebook = new Gradebook("Calculus", 65.0);


        StudentRecord minStudentEmpty = gradebook.getMin();
        boolean isEmptyMin = minStudentEmpty == null;


        StudentRecord student1 = new StudentRecord("Grace", "grace@example.com", 75.0);
        StudentRecord student2 = new StudentRecord("Henry", "henry@example.com", 85.0);
        StudentRecord student3 = new StudentRecord("Ivy", "ivy@example.com", 95.0);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);


        StudentRecord minStudent = gradebook.getMin();
        boolean isMinCorrect = minStudent != null && minStudent.equals(student1);


        StudentRecord student4 = new StudentRecord("Jane", "jane@example.com", 80.0);
        gradebook.addStudent(student4);

        StudentRecord minStudentUpdated = gradebook.getMin();
        boolean isMinUpdated = minStudentUpdated != null && minStudentUpdated.equals(student4);

        return isEmptyMin && isMinCorrect && isMinUpdated;
    }

    /**
     * Tests the getMin method of the Gradebook class to ensure it correctly
     * retrieves the student record with the minimum email address in the gradebook.
     *
     * @return true if all getMin tests pass successfully, false otherwise.
     */
    public static boolean removeStudentTester() {


        Gradebook gradebook = new Gradebook("Calculus", 65.0);
        Gradebook gradebook2 = new Gradebook("Calculus", 65.0);

        StudentRecord student1 = new StudentRecord("Jack", "jack@example.com", 73);
        StudentRecord student2 = new StudentRecord("Katie", "katie@example.com", 63);
        StudentRecord student3 = new StudentRecord("Liam", "liam@example.com", 77);
        StudentRecord student4 = new StudentRecord("Mia", "mia@example.com", 67);
        StudentRecord student5 = new StudentRecord("Noah", "noah@example.com", 79);
        StudentRecord student6 = new StudentRecord("Olivia", "olivia@example.com", 87);

        gradebook.addStudent(student4);
        gradebook.addStudent(student1);
        gradebook.addStudent(student6);
        gradebook.addStudent(student2);
        gradebook.addStudent(student5);
        gradebook.addStudent(student3);

        gradebook2.addStudent(student5);
        gradebook2.addStudent(student1);
        gradebook2.addStudent(student6);
        gradebook2.addStudent(student2);
        gradebook2.addStudent(student3);

        gradebook.removeStudent("mia@example.com");
        if (gradebook.size() != 5) {
            return false;}

        if (!gradebook2.prettyString().equals(gradebook.prettyString())) {
            return false;
        }


        try {
            gradebook.removeStudent("random@example.com");
            if (gradebook.size() != 5)
                return false;
        } catch (Exception e) {

        }
        return true;
    }

    /**
     * Tests the successor method of the Gradebook class to ensure it correctly
     * retrieves the successor student record based on a given target student,
     * including cases where the target has a successor and where the target has no
     * successor.
     *
     * @return true if all successor tests pass successfully, false otherwise.
     */
    public static boolean successorTester() {

        Gradebook gradebook = new Gradebook("Calculus", 65.0);


        StudentRecord student1 = new StudentRecord("Grace", "grace@example.com", 75.0);
        StudentRecord student2 = new StudentRecord("Henry", "henry@example.com", 85.0);
        StudentRecord student3 = new StudentRecord("Ivy", "ivy@example.com", 95.0);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);


        StudentRecord targetStudent = student1;
        StudentRecord successorStudent = gradebook.successor(targetStudent);
        boolean hasSuccessor = successorStudent != null && successorStudent.compareTo(targetStudent) > 0;


        StudentRecord noSuccessorStudent = student3;
        StudentRecord noSuccessorResult = gradebook.successor(noSuccessorStudent);
        boolean hasNoSuccessor = noSuccessorResult == null;

        return hasSuccessor && hasNoSuccessor;
    }

    /**
     * Tests the GradebookIterator class to ensure it properly iterates through the
     * student records in a Gradebook, starting from the first student added.
     *
     * @return true if the iterator correctly initializes and iterates through
     *         students, false otherwise.
     */
    public static boolean iteratorTester() {

        Gradebook gradebook = new Gradebook("Calculus", 65.0);

        StudentRecord student1 = new StudentRecord("Grace", "grace@example.com", 75.0);
        StudentRecord student2 = new StudentRecord("Henry", "henry@example.com", 85.0);
        StudentRecord student3 = new StudentRecord("Ivy", "ivy@example.com", 95.0);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);

        GradebookIterator iterator = new GradebookIterator(gradebook);

        return iterator.hasNext() && iterator.next().equals(student1);
    }

    /**
     * Tests the PassingGradeIterator functionality in the Gradebook class to ensure
     * it properly iterates through student records with passing grades, according
     * to the passing grade threshold set in the Gradebook.
     *
     * @return true if the PassingGradeIterator correctly initializes and iterates
     *         through passing grade records, false otherwise.
     */
    public static boolean passingIteratorTester() {

        Gradebook gradebook = new Gradebook("Calculus", 65.0);


        StudentRecord student1 = new StudentRecord("Grace", "grace@example.com", 75.0);
        StudentRecord student2 = new StudentRecord("Henry", "henry@example.com", 85.0);
        StudentRecord student3 = new StudentRecord("Ivy", "ivy@example.com", 95.0);
        StudentRecord student4 = new StudentRecord("Jane", "jane@example.com", 55.0);

        gradebook.addStudent(student1);
        gradebook.addStudent(student2);
        gradebook.addStudent(student3);
        gradebook.addStudent(student4);


        gradebook.enablePassingGradeIterator();


        Iterator<StudentRecord> iterator = gradebook.iterator();


        boolean hasFirstPassingRecord = iterator.hasNext() && iterator.next() == student1;
        boolean hasSecondPassingRecord = iterator.hasNext() && iterator.next() == student2;
        boolean hasNextPassingRecord = iterator.hasNext();


        gradebook.disablePassingGradeIterator();


        return hasFirstPassingRecord && hasSecondPassingRecord && hasNextPassingRecord;
    }

    /**
     * Main method to run a series of test methods for the Gradebook class and print
     * the results.
     *
     * @param args Command-line arguments (not used in this method).
     */
    public static void main(String[] args) {

        boolean constructorTest = constructorTester();
        boolean isEmptySizeAddTest = isEmptySizeAddTester();
        boolean toStringTest = toStringTester();
        boolean prettyStringTest = prettyStringTester();
        boolean lookupTest = lookupTester();

        boolean getMinTest = getMinTester();

        boolean removeStudentTest = removeStudentTester();
        boolean successorTest = successorTester();
        boolean iteratorTest = iteratorTester();

        boolean passingIteratorTest = passingIteratorTester();


        System.out.println("Constructor Test: " + (constructorTest ? "Passed" : "Failed"));
        System.out.println("isEmpty, Size, Add Test: " + (isEmptySizeAddTest ? "Passed" : "Failed"));
        System.out.println("toString Test: " + (toStringTest ? "Passed" : "Failed"));
        System.out.println("prettyString Test: " + (prettyStringTest ? "Passed" : "Failed"));
        System.out.println("lookup Test: " + (lookupTest ? "Passed" : "Failed"));
        System.out.println("getMin Test: " + (getMinTest ? "Passed" : "Failed"));
        System.out.println("removeStudent Test: " + (removeStudentTest ? "Passed" : "Failed"));
        System.out.println("successor Test: " + (successorTest ? "Passed" : "Failed"));
        System.out.println("iteratorTest Test: " + (iteratorTest ? "Passed" : "Failed"));
        System.out.println("passingIterator Test: " + (passingIteratorTest ? "Passed" : "Failed"));

    }

}
