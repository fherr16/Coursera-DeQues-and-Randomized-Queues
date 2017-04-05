import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args) {
   
    int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> rando = new RandomizedQueue<String>();
    
    while (!StdIn.isEmpty()) {
      String s = StdIn.readString();
      rando.enqueue(s);
    }
    for (int i = 0; i < k; i++) StdOut.println(rando.dequeue());
  }
}