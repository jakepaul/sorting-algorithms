package ca.uwo.eng.se2205.lab6;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by jacob on 2017-03-19.
 */
public class SelectionSorter implements Sorter{
    
    @Override
    public <E> void sort(DelayedList<E> sort, DelayedComparator<E> comparator) {
        int min ;
        E temp;
        
        for (int index = 0; index < sort.size()-1; index++ ){
            min = index;
            for (int scan = index +1; scan < sort.size(); scan++){
                if (comparator.compare((sort.get(scan)), (sort.get(min))) < 0){
                    min = scan;
                }
            }
            swap(sort, min, index);
        }
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
        System.out.println("now sorted");
        
        SelectionSorter sorter = new SelectionSorter();
        sorter.sort(myList, DelayedComparator.create(comparator, Delayed.Time.Fast));
        
        System.out.println(myList.toString());
    }
    
    public static Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };
    
    
}

