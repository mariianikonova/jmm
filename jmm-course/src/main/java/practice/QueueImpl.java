package practice;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class QueueImpl<Node> implements Queue<Node> {

	private int size = 0;
	private Node head;
	private Node tail;

	public static void main(String[] arg) {
		QueueImpl queue = new QueueImpl();

		for (int i = 0; i < 10; i++) {
			queue.add("Строка:" + i);
		}

		while (queue.size() > 0) {
			System.out.println(queue.poll().toString());
			System.out.println("queue.size:" + queue.size());
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<Node> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return new Object[0];
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(Node node) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Node> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {

	}

	@Override
	public boolean offer(Node node) {
		return false;
	}

	@Override
	public Node remove() {
		return null;
	}

	@Override
	public Node poll() {
		return null;
	}

	@Override
	public Node element() {
		return null;
	}

	@Override
	public Node peek() {
		return null;
	}

	class Node {

		private String data;
		private Node next;
	}
}
