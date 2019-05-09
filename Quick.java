 import java.util.*;

public class Quick{

  public static void main(String[] args) {
  /*int[] ary = {1000, 999,999,999,0,0,1000,1000,0};;
  //System.out.println("ANSWER(999): " + quickselect(ary, 10));
  partition(ary,0,ary.length);
  System.out.println(toString(ary));
  //System.out.println(quickselect(ary,3));*/

  // Mr. K's driver

  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Quick.quicksort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }
}

  public static int partition (int [] data, int start, int end) {

  int middle = (start + end) / 2;
  int low = start;
  int holder = start + 1;
  int high = middle;


  if (low == high){
    return low;
  }

  //Random rand =  new Random();
  int pivotIndex = (int)(Math.random() * ((end-start)) + start);
  int pivot = data[pivotIndex];

  swapValues(data, pivotIndex, start);

  //debugging
  //System.out.println("Pivot is currently: " + data[0]);

  while (holder <= high) {
    boolean first = true;
    boolean second = false;

    /*if (data[holder] == data[pivot]){
      if (first){
        swapValues(data, holder, low);
        first = false;
        second = true;
        holder++;
      }
      if(second){
        swapValues(data, holder, high);
        second = false;
        first = true;
        holder++;
      }
    }*/

    if (data[holder] < pivot) {
      holder++;
    }
    else if (data[holder] == pivot && pivotIndex <= end){
      swapValues(data, holder, pivotIndex);
      pivotIndex++;
    }
    else{
      swapValues(data, high, holder);
      high--;
    }
  }

  swapValues(data, low, high);

  return high;
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

   int high = data.length - 1;
   int low = 0;
   int partitioned = -100;

   while (partitioned != max){
     partitioned = partition(data,low,high);

    //System.out.println("Pivot is currently: " + partitioned);
    //System.out.println("Given max is: " + max);

   if(partitioned > max){
     high =  partitioned - 1;
   }
   else {
     low = partitioned + 1;
   }

   }

   return data[partitioned];
 }

 public static void quicksort(int[] data) {
		quickHelper(data,0,data.length-1);
	}

  //using class notest

  private static void quickHelper(int[] data, int start, int end) {
		if (start <= end) {
			int pivot = partition(data,start,end);
			quickHelper(data, start, pivot -1);
			quickHelper(data, pivot + 1, end);
		}
	}

}
