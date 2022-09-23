import java.util.ArrayList;

public class TupleSort {
   class Tuple implements Comparable<Tuple>{
    int[] tuple;
public Tuple(int[] this_tuple) {
this.tuple = this_tuple;
}
/** you need to implement this function */
@Override
public int compareTo(Tuple other_tuple) {
         return -1;
  }
}
   
    public TupleSort() {
    }
    
public int test_compareTo_gs(int[] arr1, int[] arr2) {
   Tuple t1 = new Tuple(arr1);
   Tuple t2 = new Tuple(arr2);
   return t1.compareTo(t2);
}
public ArrayList<int[]> test_tuplesort_gs(ArrayList<int[]> list) {
   int len = list.size();
   Tuple[] tuple = new Tuple[len];
   for (int i = 0; i < len; i++) {
      tuple[i] = new Tuple(list.get(i));
}    
      Tuple[] sorted_tuple = tuple_sort (tuple);
      ArrayList<int[]> sorted_list = new ArrayList<int[]>();   
      for (int i = 0; i < len; i++) {
         if (sorted_tuple[i] != null)
            sorted_list.add(sorted_tuple[i].tuple);
      else
         sorted_list.add(new int[]{-1});
}
      return sorted_list;
}
   
   /** you need to implement this function */
   
   public Tuple[] tuple_sort(Tuple[] array) {
      int round = 1;
      int swap_count = 0;
      int compare_count = 0;
      Tuple[]a = array.clone();
      boolean itSorted = false;
      do{ itSorted = false;
         for (int i = 0; i<a.length - round; i++){
            Tuple temp = a[i+1];
            a[i] = a[i+1];
            a[i + 1] = temp;
            swap_count += 1;
            itSorted = true;
         }
         compare_count += 1;
      } while (itSorted);
      return a;
   }
   
   public void print_tuple_array(Tuple[] array) {
   
   String actual_output = "";
   
   for(int i = 0; i < array.length; i++) {
   
   String output = "(";
   for(int x = 0; x < array[i].tuple.length; x++)
   output += array[i].tuple[x] + ", ";
   output = output.substring(0, output.length()-2);
   
   output += ")";
   
   actual_output += output + ", ";
   
   }
   
   System.out.println(actual_output.substring(0, actual_output.length()-2));
   
   }
   
   public void test_tuple_sort () {
   Tuple [] test_tuple = new Tuple [5];
   test_tuple [0] = new Tuple (new int[] {1 , 2});
   test_tuple [1] = new Tuple (new int[] {2});
   test_tuple [2] = new Tuple (new int[] {1 , 1 , 1});
   test_tuple [3] = new Tuple (new int[] {1 , 5 , 0 , 5});
   test_tuple [4] = new Tuple (new int[] {1 , 5 , -1});
   System.out.println("Before sorting: ");
   this.print_tuple_array(test_tuple);
   Tuple [] sorted_tuple = this. tuple_sort ( test_tuple );
   System.out.println("After sorting: ");
   this.print_tuple_array(test_tuple);
   }
   
   public static void main(String[] args) {
   TupleSort app = new TupleSort();
   }
}