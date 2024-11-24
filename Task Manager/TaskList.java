
// Title:    Task Manager
// Author:   Heet Divyesh Joshi
// Email:    hjoshi6@wisc.edu

import java.util.NoSuchElementException;

/**
 * This class models a list of Tasks. It implements the ListADT as a doubly
 * linked list that stores only elements of type Task.
 */
public class TaskList implements ListADT<Task> {

	/**
	 * Reference to the first node in this list
	 */
	private LinkedNode<Task> head;

	/**
	 * Reference to the last node in this list
	 */
	private LinkedNode<Task> tail;

	/**
	 * Total number of Task objects stored in this list
	 */
	private int size;

	// TODO
	public TaskList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Checks if the list is empty
	 * 
	 * @return true if the list is empty or false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Returns the size of this list
	 * 
	 * @return the number of items in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds newElement to the end (tail) of this list
	 * 
	 * @throws NullPointerException - if newElement is null
	 */
	@Override
	public void add(Task newElement) {
		if (newElement == null)
			throw new NullPointerException("Task shouldn't be null");

		LinkedNode<Task> node = new LinkedNode<>(newElement);

		if (isEmpty()) {
			head = node;
			tail = node;
		} else {
			tail.setNext(node);
			node.setPrev(tail);
			tail = node;
		}

		size += 1;
	}

	/**
	 * Adds new Element to the head of this list
	 * 
	 * @throws NullPointerException - if newElement is null
	 */
	@Override
	public void addFirst(Task newElement) {

		if (newElement == null)
			throw new NullPointerException("Task shouldn't be null");

		LinkedNode<Task> node = new LinkedNode<>(newElement);

		if (isEmpty()) {
			head = node;
			tail = node;
		} else {
			head.setPrev(node);
			node.setNext(head);
			head = node;
		}

		size += 1;
	}

	/**
	 * Adds newElement at the given position index within this list
	 * 
	 */
	@Override
	public void add(int index, Task newElement) {

		if (newElement == null)
			throw new NullPointerException("Task shouldn't be null");

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Invalid index(out of bounds)");

		LinkedNode<Task> node = new LinkedNode<>(newElement);
		if (index == 0) {
			addFirst(newElement);

		} else if (index == size) {

			if (isEmpty()) {
				head = node;
				tail = node;
			} else {
				tail.setNext(node);
				node.setPrev(tail);
				tail = node;
			}
			size += 1;

		} else {
			LinkedNode<Task> iterNode = head;

			int i = 0;
			do {
				iterNode = iterNode.getNext();
				i++;
			} while (i < index);

			node.setNext(iterNode);
			node.setPrev(iterNode.getPrev());
			iterNode.getPrev().setNext(node);
			iterNode.setPrev(node);
			size += 1;
		}

	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException - if the index is out of range (index < 0
	 *                                   || index ≥ size())
	 */
	@Override
	public Task get(int index) {

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index(out of bounds)");

		LinkedNode<Task> iterNode = head;
		for (int i = 0; i < index; i++) {
			iterNode = iterNode.getNext();
		}
		return iterNode.getData();
	}

	/**
	 * Returns true if this list contains the specified element toFind.
	 * 
	 * @return true if this list contains at least one match with toFind
	 */
	@Override
	public boolean contains(Task toFind) {

		LinkedNode<Task> iterNode = head;

		if (iterNode != null) {
			if (iterNode.getData().equals(toFind)) {
				return true;
			}

			iterNode = iterNode.getNext();
		}

		return false;
	}

	/**
	 * Removes the element at the specified position in this list.
	 * 
	 * @return the element that was removed from the list
	 * @throws IndexOutOfBoundsException - if the 
	 * index is out of range (index < 0 || index ≥ size())
	 */
	@Override
	public Task remove(int index) {

		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("Invalid index(out of bonds)");

		LinkedNode<Task> iterNode = head;

		if (index == 0) {
			head = head.getNext();
			if (head != null)
				head.setPrev(null);
			else
				tail = null;

		} else if (index == size - 1) {

			iterNode = tail;
			tail = tail.getPrev();
			tail.setNext(null);
		} else {
			int i = 0;
			while (i < index && iterNode != null) {
				iterNode = iterNode.getNext();
				i++;
			}

			iterNode.getNext().setPrev(iterNode.getPrev());
			iterNode.getPrev().setNext(iterNode.getNext());
		}
		size -= 1;
		return iterNode.getData();
	}

	/**
	 * Removes all of the elements from this list. 
	 * The list will be empty after this call returns.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns a String representation of the contents of this list
	 * traversed in the forward direction separated by a newline.
	 * @return a String representation of the connected nodes making this linked
	 *  list traversed starting from the head of the list
	 */
	protected String traverseForward() {

		StringBuilder builder = new StringBuilder();
		LinkedNode<Task> iterNode = head;
		while (iterNode != null) {
			builder.append(iterNode.getData()).append("\n");
			iterNode = iterNode.getNext();
		}
		return builder.toString();

	}

	/**
	 * Returns a String representation of the contents of this list
	 * traversed in the backward direction separated by a newline.
	 * 
	 * @return a String representation of the connected nodes making
	 *  this linked list traversed starting from the tail of the list
	 */
	protected String traverseBackward() {

		StringBuilder builder = new StringBuilder();
		LinkedNode<Task> iterNode = tail;
		while (iterNode != null) {
			builder.append(iterNode.getData()).append("\n");
			iterNode = iterNode.getPrev();
		}
		return builder.toString();
	}

	/**
	 * Returns a String representation of this task list traversed in the forward 
	 * direction from the head to the tail of the list if forward is true. 
	 * 
	 * @param forward  indicates the traversal direction of 
	 * this task list: true if the traversal direction is forward, false otherwise.
	 * 
	 * @return a String representation of the tasks stored in this task list. 
	 */
	public String traverse(boolean forward) {

		if (forward) {
			return traverseForward();
		} else {
			return traverseBackward();
		}

	}

}
