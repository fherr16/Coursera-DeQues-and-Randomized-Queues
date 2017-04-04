import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  
  private Node first, last;
  private int count = 0;
  
  private class Node {
    Item item;
    Node next;
    Node previous;
  }
  
  public class ListIterator implements Iterator<Item> {
    private Node current = first;
    
    public boolean hasNext() { return current != null; }
    
    public Item next() {
      if (current == null)
        throw new NoSuchElementException();
      else {
        Item item = current.item;
        current = current.next;
        return item; 
      }
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  public Deque() {
    this.first = null;
    this.last = null;
  }
  
  public boolean isEmpty() {
    return this.first == null;
  }
  
  public int size() {
    return count;
  }
  
  public void addFirst(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    else {
      if (isEmpty()) {
        this.first = new Node();
        this.first.item = item;
        this.first.next = null;
        this.first.previous = null;
      }
      else if (count == 1) {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        this.first.previous = null;
        this.last = oldFirst;
        this.last.previous = this.first;
      }
      else {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        this.first.previous = null;
        oldFirst.previous = this.first;
      }
      this.count++; 
    }
  }
  
  public void addLast(Item item) {
    if (item == null) {
      throw new NullPointerException();
    }
    else {
      if (isEmpty()) {
        this.first = new Node();
        this.first.item = item;
        this.first.next = null;
        this.first.previous = null;
      }
      else if (count == 1) {
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        this.last.previous = this.first;
        this.first.next = this.last;
      }
      else {
        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        this.last.previous = oldLast;
        oldLast.next = this.last;
      }
      this.count++; 
    }
  }
  
  @SuppressWarnings("unused")
  public Item removeFirst() {
    
    if (count == 0) {
      throw new NoSuchElementException();
    }
    else {
      if (count == 1) {
        Item item = this.first.item;
        this.first = null;
        count--;
        return item;
      }
      else if (count == 2) {
        Item item = this.first.item;
        Node oldFirst = this.first;
        this.first = this.last;
        this.first.previous = null;
        this.last = null;
        oldFirst = null;
        count--;
        return item;
      }
      else {
        Item item = this.first.item;
        Node oldFirst = this.first;
        this.first = this.first.next;
        this.first.previous = null;
        oldFirst = null;
        this.count--;
        return item; 
      } 
    }
  }
  
  @SuppressWarnings("unused")
  public Item removeLast() {
    if (count == 0) {
      throw new NoSuchElementException();
    }
    else {
      if (count == 1) {
        Item item = this.first.item;
        this.first = null;
        this.count--;
        return item;
      }
      else if (count == 2) {
        Item item = this.last.item;
        this.last = null;
        this.first.next = null;
        this.count--;
        return item;
      }
      else {
        Item item = this.last.item;
        Node oldLast = this.last;
        this.last = this.last.previous;
        this.last.next = null;
        oldLast = null; 
        this.count--;
        return item;
      } 
    }
  }
  
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  
}
