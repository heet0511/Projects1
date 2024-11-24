//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Task Manager
// Course:   CS 300 Spring 2024
//
// Author:   Samyak Jain
// Email:    sjain252@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Heet Divyesh Joshi
// Partner Email:   hjoshi6@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __X_ Write-up states that pair programming is allowed for this assignment.
//   __X_ We have both read and understand the course Pair Programming Policy.
//   __X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         Aman Jain: helped me optimize the addFirst method
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class models a sorted task list data 
 * structure that extends the doubly linked list of tasks TaskList.
 */

public class SortedTaskList extends TaskList {
	
	/**
	 * Adds a specific task to this sorted list of tasks
	 * @param aTask - the task to be added to this sorted list
	 */
	
	@Override
	public void add(Task aTask) throws NullPointerException {
		
	        if (aTask == null) {
	            throw new NullPointerException("Task cannot be null");
	        }

	        if (isEmpty() || aTask.compareTo(get(size() - 1)) >= 0) {
	            super.add(aTask);
	            return;
	        }

	        int index = 0;
	        while (index < size() && aTask.compareTo(get(index)) >= 0) {
	            index++;
	        }
	        super.add(index, aTask);
	    }
	
	
	/**
	 * Adds a task to the front of the list 
	 * IF AND ONLY IF it is less than any other task in the list
	 * 
	 * @param aTask - task to be added to the head of this sorted list
	 */
	
	@Override
	  public void addFirst(Task aTask) throws java.lang.NullPointerException {
	    if (aTask == null) {
	      throw new NullPointerException("Task shouldn't be null");
	    }
	    if (aTask.compareTo(get(0)) > 0 && !isEmpty()) {
	      throw new IllegalStateException("Existing task are less than tasks");
	    }
	    super.addFirst(aTask);
	  }
	
	
	
	/**
	 * Adds aTask to the given index position within this sorted list IF AND ONLY IF 
	 * index the correct position to aTask to be inserted in this sorted list.
	 * 
	 * @param index - index at which the specified task is to be inserted
     * @param aTask - task to be added to this list
     * 
     * @throws NullPointerException - if aTask is null
     * @throws IndexOutOfBoundsException - if the index is out of range
     *  (index < 0 || index > size())
	 */
	
	@Override
	  public void add(int index, Task aTask) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException {
		
		if (index < 0 ||index > super.size()  ) {
		      throw new IndexOutOfBoundsException("Invalid index(out of range)");
		    }
	    if (aTask == null) {
	      throw new NullPointerException("Task shouldn't be null.");
	    }

	    if (aTask.compareTo(get(0)) > 0 && index == 0 && (!isEmpty())) {
            throw new IllegalStateException("Title greater than head");
        }

	    super.add(index, aTask);
	  }
	
	/**
	 * Returns the task at index zero in this sorted task list
	 * 
	 * @return the task at index zero in this sorted task list
	 * @throws NoSuchElementException - with a descriptive error message if this list is empty
	 */
	public Task peekBest() {
		
	    if (isEmpty() == true)
	      throw new NoSuchElementException("Empty list");
	    return get(0);
	    
	  }
	
	/**
	 * Removes and returns the task at index zero in this sorted task list
	 * 
	 * @return the task that was at index zero within this sorted task list
	 * @throws NoSuchElementException - with a descriptive error message if this list is empty
	 */
	 public Task removeBest() {
		 
		    if (isEmpty() == true)
		      throw new NoSuchElementException("Empty list");
		    
		    return remove(0);
		  }
	
}

