 import java.util.*;

public class Quick{

  public static void main(String[] args) {
  int[] ary = {999,999,100,4,1,0,3,2,999,999,999};
  //System.out.println("ANSWER(999): " + quickselect(ary, 10));
  partition(ary,0,ary.length);
  System.out.println(toString(ary));
}

  public static int partition (int [] data, int start, int end) {

  int pivot = (int)(Math.random()*(start-end) + start);
  int holder = pivot;
  data[pivot] = data[start];
  data[start] = holder;
  start++;

  //System.out.println("Pivot is currently: " + data[0]);

  while (start != end) {
    if (data[start + 1] > data[start]) {
      holder = data[start + 1];
      data[start + 1] = data[end];
      data[end] = holder;
      end --;
    }
    else {
      holder = data[start];
      data[start] = data[start + 1];
      data[start + 1] = holder;
      start ++;
    }
  }
  return start;
}

public static String toString(int[] sorted) {
   String result = "";
   for (int x = 0; x < sorted.length; x++) {
     result += x + " ";
   }
   return result;
 }

public static int quickselect(int []data, int max){
   int partitioned = partition(data, 0, data.length);
   if(partitioned == max - 1){
     return data[partitioned];
   }
   else{
     return quickselect(data, max);
   }
 }

} 
