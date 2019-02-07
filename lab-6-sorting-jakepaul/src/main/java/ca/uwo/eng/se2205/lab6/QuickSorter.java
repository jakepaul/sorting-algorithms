package ca.uwo.eng.se2205.lab6;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by jacob on 2017-03-19.
 */
public class QuickSorter implements Sorter {
    
    @Override
    public <E> void sort(DelayedList<E> sort, DelayedComparator<E> comparator) {
        System.out.println("in");
        quickSortHelper(sort, comparator,0 , sort.size() - 1);
    }
    
    private <E> void quickSortHelper(DelayedList<E> list, DelayedComparator<E> comp, int min, int max){
        if (min < max){
            int indexofpartition = partition(list, comp, min, max);
            
            quickSortHelper(list, comp, min,indexofpartition-1);
            quickSortHelper(list, comp, indexofpartition+1,max);
        }
        
    }
    
    private static <E> int partition(DelayedList<E> list, DelayedComparator<E> comp, int min, int max){
        E partElement;
        int left, right;
        int middle = (min + max)/2;
        
        partElement = list.get(middle);
        swap(list,middle,min);
        left = min;
        right = max;
        
        while (left < right){
            while (left < right && comp.compare(list.get(left), partElement) <= 0){
                left++ ;
            }
            while (comp.compare(list.get(right), partElement) > 0){
                right-- ;
            }
            
            if (left < right){
                swap(list, left, right);
            }
            
        }
        swap(list, min, right);
        return right;
    }
    
    private static <E> void swap (DelayedList<E> list, int index1, int index2){
        
        E temp = list.get(index2);
        list.set(index2, list.get(index1));
        list.set(index1, temp);
    }
    
    public static void main(String[] args){
        
        DelayedList<Integer> myList = DelayedList.create(Delayed.Time.Fast, new ArrayList<Integer>());
        
        myList.add(7);
        myList.add(11);
        myList.add(3);
        myList.add(27);
        
        myList.add(2);
        
        myList.add(40);
        myList.add(33);
        myList.add(5);
        
        System.out.println(myList.toString());
        System.out.println("now sorted with quicksort");
        
        QuickSorter sorter = new QuickSorter();
        sorter.sort(myList, DelayedComparator.create(comparator, Delayed.Time.Fast));
        
        System.out.println(myList.toString());
    }
    
    public static Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };
    
    
    
}

