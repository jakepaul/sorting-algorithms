package ca.uwo.eng.se2205.lab6;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by jacob on 2017-03-19.
 */
public class MergeSorter implements Sorter {
    
    
    
    @Override
    public <E> void sort(DelayedList<E> sort, DelayedComparator<E> comparator) {
        System.out.println("Sort");
        mergeSortHelper(sort, comparator, 0, sort.size() - 1);
    }
    
    
    private static <E> void mergeSortHelper(DelayedList<E> list, DelayedComparator<E> comp, int min, int max) {
      
        
        if (min < max){
            int mid = (min + max)/2;
            mergeSortHelper(list, comp, min, mid);
            mergeSortHelper(list, comp, mid+1, max);
            merge(list, comp, min, mid, max);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static <E> void merge(DelayedList<E> list, DelayedComparator<E> comp, int first, int mid, int last){
    
        
        
        //DelayedList<E> temp = DelayedList.create(Delayed.Time.Fast, new ArrayList<E>());
        //for (int i = 0; i < list.size(); i++) {temp.add(null);}
        
        E[] tempArray = (E[])(new Object[list.size()]);
        
        int first1 = first;
        int last1 = mid;
        int first2 = mid +1;
        int last2 = last;
        
        int index = first1;
        
        while (first1 <= last1 && first2 <= last2){
            if (comp.compare(list.get(first1),list.get(first2)) < 0){
                //temp.set(index, list.get(first1));
                tempArray[index] = list.get(first1);
                first1++;
            }
            else{
                //temp.set(index, list.get(first2));
                tempArray[index] = list.get(first2);
                first2++;
            }
            index++;
        }
        
        while (first1 <= last1){
            //temp.set(index, list.get(first1));
            tempArray[index] = list.get(first1);
            first1++;
            index++;
        }
        while (first2 <= last2){
            //temp.set(index, list.get(first2));
            tempArray[index] = list.get(first2);
            first2++;
            index++;
        }
        
        for (index = first; index <= last; index++){
            list.set(index, tempArray[index]/*temp.get(index)*/);
        }
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
        System.out.println("now sorted");
        
        MergeSorter sorter = new MergeSorter();
        sorter.sort(myList, DelayedComparator.create(comparator, Delayed.Time.Fast));
        
        System.out.println(myList.toString());
    }
    
    public static Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };
    
    
    
}