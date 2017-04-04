import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
   
    int k = Integer.parseInt(args[0]);
    @SuppressWarnings("rawtypes")
    RandomizedQueue rando = new RandomizedQueue();
    
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      rando.enqueue(s);
    }
    for(int i = 0; i < k; i++)
      StdOut.println( rando.dequeue() );
  }
}