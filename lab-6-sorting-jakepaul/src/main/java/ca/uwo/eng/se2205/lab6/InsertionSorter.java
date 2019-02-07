package ca.uwo.eng.se2205.lab6;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by jacob on 2017-03-19.
 */
public class InsertionSorter implements Sorter {
    
    @Override
    public <E> void sort(DelayedList<E> sort, DelayedComparator<E> comparator) {
        
        for (int index = 1; index < sort.size(); index++){
            E key = sort.get(index);
            int position = index;
            
            while (position > 0 && (comparator.compare(sort.get(position-1), key) >0)){
                //sort.get(position) = sort.get(position-1);
                sort.set(position, sort.get(position-1));
                position--;
            }
            sort.set(position,key);
        }
        
    }
    
    public static void main(String[] args){
        
        DelayedList<Integer> myList = DelayedList.create(Delayed.Time.Fast, new ArrayList<Integer>());
        
        myList.add(8);
        myList.add(11);
        myList.add(3);
        myList.add(27);
        
        myList.add(2);
        
        myList.add(40);
        myList.add(33);
        myList.add(5);
        
        System.out.println(myList.toString());
        System.out.println("now sorted");
        
        InsertionSorter sorter = new InsertionSorter();
        sorter.sort(myList, DelayedComparator.create(comparator, Delayed.Time.Fast));
        
        System.out.println(myList.toString());
    }
    
    public static Comparator<Integer> comparator = new Comparator<Integer>() {
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };
    
    
    
    
}
