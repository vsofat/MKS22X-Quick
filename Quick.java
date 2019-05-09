import java.util.*;

public class Quick{

  public static void main(String[] args) {

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

  public static int partition (int [] data, int low, int high) {

    Random rand = new Random();

    int middle = (low + high) / 2;

    int index = middle;

    if(data[low] > data[high] && data[low] < data[middle] || data[low] < data[high] && data[low] > data[middle]){
      index = low;
    }

    if(data[high] > data[low] && data[high] < data[middle] || data[high] < data[low] && data[high] > data[middle]){
      index = high;
    }

    int pivot = data[index];
    int temp = data[low];
    data[low] = pivot;
    data[index] = temp;
    index = low;
    low++;

    while (low != high) {
      if (data[low] > pivot || (data[low] == pivot && Math.random() > 0.50)){
        int temp2 = data[low];
        data[low] = data[high];
        data[high] = temp2;
        high--;
      }
      else {
        low++;
      }
    }

    if (data[index] < data[low]){
      low--;
    }

    int min = data[low];
    data[index] = min;
    data[low] = pivot;
    int num = -1;

    for(int i = low; i <= high; i++){
      if(data[i] == pivot){
        num = i;
      }
    }
    //System.out.println(pivot);
    return num;
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
    return quickSelectHelper(data, max, 0, high);
  }

  public static int quickSelectHelper(int[] data, int max, int low, int high){
    int[] partitioned = dutchPartition (data, low, high);

    if(max <= partitioned[1] && max >= partitioned[0]){
      return data[partitioned[1]];
    }
    if(high - low < 5){
      insertionsort(data, low, high);
      return data[max];
    }

    if(partitioned[0] > max){
      return quickSelectHelper(data, max, low, partitioned[0]);
    }
    return quickSelectHelper(data, max, partitioned[1], high);
  }


  public static void quicksort(int[] data) {
    quickHelper(data,0,data.length-1);
  }

  public static void insertionsort(int [] data, int low, int high) {
    for (int x = low + 1; x <= high; x++) {
      for (int y = x; y > low; y--) {
        if (data[y] < data[y - 1]) {
          int placeholder = data[y];
          data[y] = data[y - 1];
          data[y - 1] = placeholder;
        }
      }
    }
  }

  public static int[] dutchPartition(int[] data, int low, int high){

    int mid = (low + high) / 2;
    int index = mid;

    if(data[low] > data[high] && data[low] < data[mid] || data[low] < data[high] && data[low] > data[mid]){
      index = low;
    }
    if(data[high] > data[low] && data[high] < data[mid] || data[high] < data[low] && data[high] > data[mid]){
      index = high;
    }

    int lt = low;
    int gt = high;
    int ind = lt;
    int pivot = data[index];
    int temp = data[low];
    data[lt] = pivot;
    data[index] = temp;
    index = lt;
    lt++;

    while (index <= gt) {

      if (data[ind] > pivot){
        int placeholder = data[ind];
        data[ind] = data[gt];
        data[gt] = placeholder;
        gt--;
      }

      else if(data[ind] == pivot){
        ind++;
      }

      else{

        int placeholder2 = data[ind];
        data[ind] = data[lt];
        data[lt] = placeholder2;
        lt++;
        index++;
      }
    }
    lt--;

    int placeholder3 = data[index];
    data[index] = data[lt];
    data[lt] = placeholder3;
    int[] result = new int[]{
      lt, gt
    };

    return result;
  }

  private static void quickHelper(int[] data, int low, int high) {
    if (low < high) {
      int pivot = partition(data,low,high);
      quickHelper(data, low, pivot -1);
      quickHelper(data, pivot + 1, high);
    }
  }

}
