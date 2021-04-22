package maze;


public class DoublyLinkedList<E> {
	
	
	private class Node {
		private E value;
		private Node prev;
		private Node next;
		
		public Node(E value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	protected Node head;
	protected Node tail;
	private int size;
	
	public DoublyLinkedList() {
		head = new Node(null, null, null);
		tail = new Node(null, head, null);
		head.next = tail;
		size = 0;
	}
	
	private void insertBetween(E newValue, Node node1, Node node2) {
		Node newNode = new Node(newValue, node1, node2);
		node1.next = newNode;
		node2.prev = newNode;
		size++;
	}

	private E removeBetween(Node node1, Node node2) {
		if (this.isEmpty()) {
			throw new IllegalStateException("Cannot remove from empty list");
		}
		E result = node1.next.value;
		
		node1.next = node2;
		node2.prev = node1;
		size--;
		
		return result;
	}
	
	public void insertAtHead(E newValue) {
		insertBetween(newValue, head, head.next);
	}
	
	public void addLast(E newValue) {
		insertBetween(newValue, tail.prev, tail);
	}
	
	public boolean remove(E target) {
		Node current = head.next;
		while (current != tail) {
			if (current.value.equals(target)) {
				removeBetween(current.prev, current.next);
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public E removeHead() {
		return removeBetween(head, head.next.next);
	}
	
	public E removeTail() {
		return removeBetween(tail.prev.prev, tail);
	}
	
	public E first() {
		return head.value;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return head.next == tail;
	}
	
	public String toString() {
		if (this.isEmpty()) {
			return "List is empty";
		}
		String result = "";
		Node current = head.next;
		while (current != tail) {
			result += current.value.toString() + " ";
			current = current.next;
		}
		return result;
	}
	
	public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
    }
	
	public String reverseToString() {
		if (this.isEmpty()) {
			return "List is empty";
		}
		String result = "";
		Node current = tail.prev;
		while (current != head) {
			result += current.value.toString() + " ";
			current = current.prev;
		}
		return result;
	}
	
}
