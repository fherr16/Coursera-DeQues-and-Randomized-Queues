import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private Node first;
  private int count;
 
  private class Node {
    private Item item; 
    private Node next; 
    private Node previous;
  }
  
  private class RandomListIterator implements Iterator<Item> {
    
    private Node current;
    private Node cloneOldFirst, cloneFirst;
    private int cloneCount;
    
    public RandomListIterator()  {
      current = first;
      cloneCount = 0;
      cloneFirst = new Node();
      cloneFirst.item = current.item;
      cloneFirst.next = null;
      cloneFirst.previous = null;
      current = current.next;
      cloneCount++;
      
      while (current != null) {
        cloneOldFirst = cloneFirst;
        cloneFirst = new Node();
        cloneFirst.item = current.item;
        cloneFirst.next = cloneOldFirst;
        cloneFirst.previous = null;
        cloneOldFirst.previous = cloneFirst;
        current = current.next;
        cloneCount++;
      }
    }
    
    public boolean hasNext() { 
      return cloneFirst != null; 
    }
    
    public Item next() {
      if (cloneFirst == null) throw new NoSuchElementException();
      else {
        int index = StdRandom.uniform(cloneCount);
        if (index == 0) {
          if (cloneCount == 1) {
            Item item = cloneFirst.item; 
            cloneFirst = null;
            cloneCount--; 
            return item;
          }
          else {
            Item item = cloneFirst.item; 
            cloneFirst = cloneFirst.next;
            cloneFirst.previous.next = null;
            cloneFirst.previous = null;
            cloneCount--;
            return item; 
          } 
        }
        else {
          Node cloneCurrent = cloneFirst;
          for (int i = 0; i < index; i++)
            cloneCurrent = cloneCurrent.next;
          if (index == cloneCount-1) { 
            Item item = cloneCurrent.item;
            cloneCurrent.previous.next = null; 
            cloneCurrent.previous = null; 
            cloneCurrent = null;
            cloneCount--;
            return item;
          }
          else {
            Item item = cloneCurrent.item;
            cloneCurrent.previous.next = cloneCurrent.next;
            cloneCurrent.next.previous = cloneCurrent.previous;
            cloneCurrent.next = null;
            cloneCurrent.previous = null;
            cloneCurrent = null;
            cloneCount--;
            return item; 
          }
        }
      }
    }
    
    public void remove() { 
      throw new UnsupportedOperationException(); 
    }
  }
  
  public RandomizedQueue() { 
    this.count = 0; 
  }
  
  public boolean isEmpty() { 
    return this.first == null; 
  }
  
  public int size() { 
    return this.count; 
  }
  
  public void enqueue(Item item) {
    if (item == null) 
      throw new NullPointerException();
    else {
      if (isEmpty()) {
        this.first = new Node();
        this.first.item = item;
        this.first.previous = null; 
        this.first.next = null;
        this.count++;
      }
      else {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.previous = null;
        this.first.next = oldFirst;
        oldFirst.previous = this.first;
        this.count++; 
      } 
    }
  }
  
  public Item dequeue() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      int index = StdRandom.uniform(this.count);
      Node current = this.first;
      if (index == 0) { 
        if (this.count == 1) { 
          Item item = first.item; 
          this.first = null; 
          current = null; 
          this.count--; 
          return item;
        }
        Item item = first.item;
        this.first = first.next;
        this.first.previous = null; 
        current = null;
        this.count--; 
        return item; 
      }
      for (int i = 0; i < index; i++)
        current = current.next;
      if (index == count-1) { 
        Item item = current.item;
        current.previous.next = null; 
        current = null; 
        this.count--;
        return item;
      }
      else {
        Item item = current.item;
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current = null;
        this.count--;
        return item;
      }
    }
  }
  
  public Item sample() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      int index = StdRandom.uniform(this.count);
      Node current = this.first;
      for (int i = 0; i < index; i++)
        current = current.next;
      return current.item; 
    }
  }
  
  public Iterator<Item> iterator() {
    return new RandomListIterator(); 
  }
}
