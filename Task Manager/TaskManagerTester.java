
// Title:    Task Manager Tester
// Author:   Heet Divyesh Joshi
// Email:    hjoshi6@wisc.edu

import java.util.NoSuchElementException;


/**
 * This class tests the TaskManager, TaskList, and SortedTaskList classes.
 */
public class TaskManagerTester {

	/**
	 * Checks the correctness of the implementation of the method compareTo()
	 * defined in the Task class. Consider different test scenarios including each
	 * of the SortingOrder values: TITLE and PRIORITY
	 * 
	 * Test scenarios: <BR>
	 * aTask and anotherTask references any Task objects you can create.<BR>
	 * 1. aTask.compareTo(anotherTask) is expected to return 0 if they are equal
	 * with respect to the comparison criteria. <BR>
	 * 2. aTask.compareTo(aTask) is expected to return 0 <BR>
	 * 3. aTask.compareTo(anotherTask) is expected to return a negative integer
	 * (less than zero) if aTask is less than another Task with respect to the
	 * comparison criteria. <BR>
	 * 4.aTask.compareTo(anotherTask) is expected to return a positive integer
	 * (greater than zero) if aTask is greater than another Task with respect to the
	 * comparison criteria.
	 * 
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testTaskCompareTo() {
		// TODO: Implement test scenarios
		Task.setSortingOrderByTitle();
		Task aTask = new Task("Task 1", "1", false);
		Task anotherTask = new Task("Task 2", "2", false);
		Task aTaskCopy = new Task("Task 1", "3", false);
		Task higherTask = new Task("Task 3", "4", true);

		if (aTask.compareTo(aTaskCopy) != 0)
			return false;

		if (aTask.compareTo(aTask) != 0)
			return false;

		if (aTask.compareTo(anotherTask) >= 0)
			return false;

		if (anotherTask.compareTo(aTask) <= 0)
			return false;

		Task.setSortingOrderByPriorityLevel();

		if (higherTask.compareTo(aTask) >= 0)
			return false;

		return aTask.compareTo(higherTask) > 0;
	}

	/**
	 * Checks the correctness of the implementation of the equals() method defined
	 * in the Task clas.
	 * 
	 * @return true when this test verifies a correct functionality, and false
	 *         otherwise
	 */
	public static boolean testTaskEquals() {
		// TODO: Implement test scenarios

		Task aTask = new Task("Task 1", "1", false);
		Task aTaskCopy = new Task("Task 1", "3", false);
		Task anotherTask = new Task("Task 2", "2", false);

		if (aTask.equals(anotherTask))
			return false;

		if (!aTask.equals(aTaskCopy))
			return false;

		Task diffTask = new Task("task 1", "4", false);
		return aTask.equals(diffTask);
	}

	/**
	 * Tests the add(), isEmpty(), and size() methods of the TaskList class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Create a new empty TaskList and verify that isEmpty() returns true.<BR>
	 * 2. Add a few tasks to the end of the TaskList and verify that isEmpty()
	 * returns false.<BR>
	 * 3. Verify that size() returns the expected size after adding each Task.
	 *
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testAddIsEmptySize() {
		// TODO: Implement test scenarios
		TaskList list = new TaskList();

		if (!list.isEmpty())
			return false;

		Task task = new Task("Task 1", "1");
		Task task1 = new Task("Task 2", "2");

		list.add(task);

		if (list.isEmpty())
			return false;

		if (list.size() != 1)
			return false;

		list.add(task1);

		return list.size() == 2;

	}

	/**
	 * Tests the addFirst(), and add(index, element) methods of the TaskList class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Test adding elements to an empty TaskList <BR>
	 * 2. Test adding elements to the beginning, middle, and end of a non-empty
	 * TaskList.
	 *
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testAddToTaskList() {
		// TODO: Implement test scenarios
		TaskList list = new TaskList();

		Task task = new Task("Task 1", "1");
		list.addFirst(task);
		if (!list.get(0).equals(task) || list.isEmpty())
			return false;

		Task task1 = new Task("Task 2", "2");
		Task task2 = new Task("Task 3", "3");
		Task task3 = new Task("Task 4", "4");

		list.addFirst(task1);
		if (!list.get(0).equals(task1))
			return false;

		list.add(1, task2);
		if (!list.get(1).equals(task2))
			return false;

		list.add(task3);
		return list.get(list.size() - 1).equals(task3);

	}

	/**
	 * Tests and remove(index) and clear() methods of the TaskList class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Test removing elements from various positions in the TaskList using
	 * remove(index). <BR>
	 * 2. Test removing elements from an empty TaskList or at invalid indices. <BR>
	 * 3. Test clear() method and verify that the TaskList is empty after calling
	 * it.
	 *
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testRemoveClear() {
		// TODO: Implement test scenarios
		TaskList list = new TaskList();

		Task task = new Task("Task 1", "1");
		Task task1 = new Task("Task 2", "2");
		Task task2 = new Task("Task 3", "3");
		list.add(task);
		list.add(task1);
		list.add(task2);

		list.remove(1);
		if (list.get(1).equals(task1))
			return false;

		list.remove(0);
		if (list.get(0).equals(task))
			return false;

		list.remove(list.size() - 1);
		if (!list.isEmpty())
			return false;

		try {
			list.remove(0);
			return false;
		} catch (IndexOutOfBoundsException e) {
		}

		list.add(task);
		list.clear();
		return list.isEmpty();

	}

	/**
	 * Tests the add() method of the SortedTaskList class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Test adding a task to an empty TaskList <BR>
	 * 2. Test adding tasks to a non-mepty sorted task list such that the task
	 * should be added to the beginning, middle, and end of a non-empty TaskList.
	 *
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testAddToSortedTaskList() {
		// TODO: Implement test scenarios
		SortedTaskList sortedList = new SortedTaskList();

		
		Task task1 = new Task("Task 1", "1", true);
        Task task2 = new Task("Task 2", "2", true);
        Task task3 = new Task("Task 3", "3", false);
        
        sortedList.add(task2); 
        sortedList.add(task3);
        sortedList.add(task1); 
	    
        
        if (sortedList.size() != 3) {
            System.out.println("Tasks incorrectly added to SortedTaskList");
            return false;
        }
	   
        if (!sortedList.get(0).equals(task1) ||
                !sortedList.get(1).equals(task2) ||
                !sortedList.get(2).equals(task3)) {
            System.out.println("Tasks sorted incorrectly in SortedTaskList");
            return false;
        }
	    

	    return true;
	    
	    
	    
	    

	}
	
	
	 private static SortedTaskList getSortedList() {
	        SortedTaskList sortedList = new SortedTaskList();

	        
	        Task task1 = new Task("Task 1", "1", true);
	        Task task2 = new Task("Task 2", "2", false);
	        Task task3 = new Task("Task 3", "3", true);

	        
	        sortedList.add(task2); 
	        sortedList.add(task3);  
	        sortedList.add(task1); 
	        return sortedList
	        		;
	    }

	/**
	 * Tests the appendTask() method of the TaskManager class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Test appending a task to an empty TaskManager. <BR>
	 * 2. Test appending multiple tasks to the TaskManager.
	 *
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testAppendTask() {
		
		TaskManager manager = new TaskManager();
		
		Task task1 = new Task("Task 1", "1", false);
		
	    manager.appendTask(task1);
	    if (!manager.toDoList.get(0).equals(task1) || manager.toDoList.isEmpty())
	      return false;
	    
	    Task task2 = new Task("Task 2", "Description 2", false);
	    manager.appendTask(task2);
	    return manager.toDoList.size() == 2 && manager.toDoList.get(1).equals(task2);
	    
	}

	/**
	 * Tests the moveToTop(), moveToBottom(), moveBeforeOtherTask(), and
	 * moveAfterOtherTask() methods of the TaskManager class.
	 *
	 * Test scenarios: <BR>
	 * 1. Test moving tasks to various positions in the toDoList and completedTasks
	 * lists. <BR>
	 * 2. Test moving tasks relative to other tasks (before/after).
	 * 
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testMoveTasks() {
		// TODO: Implement test scenarios
		 TaskManager manager = new TaskManager();

		    // Set up tasks
		    Task task1 = new Task("Task 1", "1", false);
		    Task task2 = new Task("Task 2", "2", false);
		    Task task3 = new Task("Task 3", "3", false);

		  
		    manager.appendTask(task1);
		    manager.appendTask(task2);
		    manager.appendTask(task3);
		    
		    manager.movetoTop(2); 
		    if (!manager.toDoList.get(0).equals(task3))
		      return false;
		    
		    manager.moveToBottom(0); 
		    if (!manager.toDoList.get(2).equals(task3))
		      return false; 
		    
		    manager.moveBeforeOtherTask(2, 1); 
		    if (!manager.toDoList.get(1).equals(task3))
		      return false;
		    
		    manager.moveAfterOtherTask(1, 0); 
		    if (!manager.toDoList.get(1).equals(task3))
		      return false;
		    
		    int compSize = manager.completedTasks.size();
		    manager.completeTask(0); 
		    return manager.completedTasks.size() == compSize + 1 && 
		    		!manager.toDoList.contains(task1);

	}

	/**
	 * Tests the removeTask() method of the TaskManager class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Test removing tasks from various positions in the toDoList and
	 * completedTasks lists. <BR>
	 * 2 Test removing tasks at invalid indices return false.
	 *
	 * 
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testRemoveTask() {
		// TODO: Implement test scenarios
		TaskManager manager = new TaskManager();
		Task task1 = new Task("Task 1", "1", false);
		Task task2 = new Task("Task 2", "2", false);
		manager.appendTask(task1);
		manager.appendTask(task2);
		
		 boolean t = manager.removeTask(0);
		    if (manager.toDoList.contains(task1)||!t)
		      return false;
		    
		    t = manager.removeTask(5); 
		    return !t;   
	}

	/**
	 * Tests the completeTask() method of the TaskManager class.
	 * 
	 * Test scenarios: <BR>
	 * 1. Test completing tasks from various positions in the toDoList. <BR>
	 * 2. Test completing tasks at invalid indices.
	 *
	 * @return true if all test scenarios pass, false otherwise.
	 *
	 */
	public static boolean testCompleteTask() {
		// TODO: Implement test scenarios
		TaskManager manager = new TaskManager();
	    Task task1 = new Task("Task 1", "1", false);
	    manager.appendTask(task1);
	    
	    boolean comp = manager.completeTask(0); 
	    if (!manager.completedTasks.contains(task1) ||manager.toDoList.contains(task1)|| !comp)
	      return false; 
	    
	    comp = manager.completeTask(5);
	    return !comp;
	}

	/**
	 * Main method
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println("testTaskCompareTo(): " + testTaskCompareTo());
		System.out.println("testTaskEquals(): " + testTaskEquals());
		System.out.println("testAddIsEmptySize(): " + testAddIsEmptySize());
		System.out.println("testAddToTaskList(): " + testAddToTaskList());
		System.out.println("testRemoveClear(): " + testRemoveClear());
		System.out.println("testAddToSortedTaskList(): " + testAddToSortedTaskList());
		System.out.println("testAppendTask(): " + testAppendTask());
		System.out.println("testMoveTasks(): " + testMoveTasks());
		System.out.println("testRemoveTask(): " + testRemoveTask());
		System.out.println("testCompleteTask(): " + testCompleteTask());
	}
}
