package com.rajeshkawali.dsa.linkedlist;

public class MyLinkedList {

	static class MyNode {
		public int data;
		public MyNode next;

		public MyNode(int data) {
			this.data = data;
			this.next = null;
		}
	}

	private MyNode head;
	private MyNode tail;
	private int size;

	public MyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	// Insert at head
	public void insertAtHead(int data) {
		MyNode newNode = new MyNode(data);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		size++;
	}

	// Insert at tail
	public void insertAtTail(int data) {
		MyNode newNode = new MyNode(data);
		if (isEmpty()) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	// Insert at position (1-based index)
	public void insertAtPosition(int index, int data) {
		if (index < 1 || index > size + 1) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 1) {
			insertAtHead(data);
			return;
		}
		if (index == size + 1) {
			insertAtTail(data);
			return;
		}

		MyNode prev = head;
		for (int i = 1; i < index - 1; i++) {
			prev = prev.next;
		}

		MyNode newNode = new MyNode(data);
		newNode.next = prev.next;
		prev.next = newNode;
		size++;
	}

	// Remove head
	public int removeHead() {
		if (isEmpty()) {
			return -1;
		}
		int val = head.data;
		head = head.next;
		size--;
		if (head == null) {
			tail = null;
		}
		return val;
	}

	// Remove tail
	public int removeTail() {
		if (isEmpty()) {
			return -1;
		}
		if (head == tail) { // single node
			int val = head.data;
			head = tail = null;
			size = 0;
			return val;
		}
		MyNode cur = head;
		while (cur.next != tail) {
			cur = cur.next;
		}
		int val = tail.data;
		tail = cur;
		tail.next = null;
		size--;
		return val;
	}

	// Remove at position
	public int removeAtPosition(int index) {
		if (index < 1 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 1) {
			return removeHead();
		}
		if (index == size) {
			return removeTail();
		}
		MyNode prev = head;
		for (int i = 1; i < index - 1; i++) {
			prev = prev.next;
		} 
		int val = prev.next.data;
		prev.next = prev.next.next;
		size--;
		return val;
	}

	// Search for value
	public boolean search(int data) {
		MyNode cur = head;
		while (cur != null) {
			if (cur.data == data) {
				return true;
			}
			cur = cur.next;
		}
		return false;
	}

	public int findIndex(int data) {
		MyNode temp = head;
		int index = 1;
		while(temp != null) {
			if(temp.data == data) {
				return index;
			} else {
				temp = temp.next;
				index++;
			}
		}
		return -1;
	}
	
	@SuppressWarnings("unused")
	public void updateAtIndex(int index, int newData) {
		MyNode temp = head;
		int iTemp = 1;
		if(index < 1 && index > size+1) {
			System.out.println("Index not found");
			return;
		}
		while(temp != null) {
			if(temp.data == newData) {
				temp.data = newData;
				System.out.println("Updated at index: "+ index);
			} else {
				temp = temp.next;
				iTemp++;
			}
		}
	}
	
	public boolean updateValue(int oldData, int newData) {
		MyNode temp = head;
		while (temp != null) {
			if (temp.data == oldData) {
				temp.data = newData;
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	// Reverse list (iterative)
	public void reverse() {
		MyNode prev = null, cur = head, next;
		tail = head;
		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head = prev;
	}

	// Print list
	public void printData() {
		MyNode temp = head;
		while (temp != null) {
			System.out.print(temp.data);
			temp = temp.next;
			if (temp != null)
				System.out.print(" -> ");
		}
		System.out.println();
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getHead() {
		return head != null ? head.data : -1;
	}

	public int getTail() {
		return tail != null ? tail.data : -1;
	}

	public void clearList() {
		head = tail = null;
		size = 0;
	}

	// Demo
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(3);
		list.insertAtHead(4);
		list.printData(); // 4 -> 3 -> 2 -> 1

		list.insertAtTail(5);
		list.printData(); // 4 -> 3 -> 2 -> 1 -> 5

		list.insertAtPosition(3, 47);
		list.printData(); // 4 -> 3 -> 47 -> 2 -> 1 -> 5

		list.removeAtPosition(3);
		list.printData(); // 4 -> 3 -> 2 -> 1 -> 5

		list.reverse();
		list.printData(); // 5 -> 1 -> 2 -> 3 -> 4

		System.out.println("Search 2: " + list.search(2));
		System.out.println("Head: " + list.getHead() + ", Tail: " + list.getTail());
		System.out.println("Size: " + list.getSize());
	}
}
