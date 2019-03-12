 import java.util.*;

public class Quick{

  public static int partition (int [] arr, int start, int end) {
  Random rand = new Random();
  int pivot = rand.nextInt(arr.length);

  int holder = pivot;
  arr[pivot] = arr[0];
  arr[0] = holder;

  System.out.println("Pivot: " + arr[0]);

  while (start != end) {
    if (arr[start + 1] > arr[start]) {
      holder = arr[start + 1];
      arr[start + 1] = arr[end];
      arr[end] = holder;
      end --;
    }
    else {
      holder = arr[start];
      arr[start] = arr[start + 1];
      arr[start + 1] = holder;
      start ++;
    }
  }
  return start;
}
}
