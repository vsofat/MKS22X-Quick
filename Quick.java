 import java.util.*;

public class Quick{

  public static void main(String[] args) {
  int[] ary = {999,999,100,4,1,0,3,2,999,999,999};
  //System.out.println("ANSWER(999): " + quickselect(ary, 10));
  partition(ary,0,ary.length);
  System.out.println(toString(ary));
//  System.out.println(quickselect(ary,3));
}

  public static int partition (int [] data, int start, int end) {

  Random rand =  new Random();
  int pivot = Math.abs(rand.nextInt(end-start)) + start;

  swapValues(data,start,pivot);

  pivot = 0;
  start++;

  //debugging
  //System.out.println("Pivot is currently: " + data[0]);

  while (start != end) {
    if (data[start] > data[pivot]) {
      swapValues(data, start, end);
      end --;
    }
    else {
      start ++;
    }
  }
  // compare start and pivot and move based off values
  if (data[start] > data[pivot]){
    swapValues(data,start-1,pivot);
  }
  else{
    swapValues(data,start,pivot);
    pivot = start;
  }
  return pivot;
}

public static void swapValues(int[] data, int val1, int val2){
  int holder = data[val1];
  data[val1] = data[val2];
  data[val2] = holder;
}

public static String toString(int[] sorted) {
   String result = "";
   for (int x = 0; x < sorted.length; x++) {
     result += x + " ";
   }
   return result;
 }

public static int quickselect(int []data, int max){

   int partitioned = partition(data, 0, data.length - 1);
   int high = data.length - 1;
   int low = 0;

   while (max != partitioned){
    //System.out.println("Pivot is currently: " + partitioned);
    //System.out.println("Given max is: " + max);
   if(partitioned > max){
     high =  partitioned - 1;
   }
   else if (partitioned < max){
     low = partitioned - 1;
   }

   partitioned = partition(data,high,low);

   }

   return data[max];
 }
}
