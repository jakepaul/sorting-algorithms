package ca.uwo.eng.se2205.lab6;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

/**
 * Created by jacob on 2017-03-20.
 */
public class Driver {
    
    enum Algorithm {
        InsertionSort,
        MergeSort,
        QuickSort,
        SelectionSort
    }
    private static final String FILENAME =  "C:\\Users\\jacob\\lab-6-sorting-jakepaul\\src\\main\\java\\ca\\uwo\\eng\\se2205\\lab6\\results\\results_time2.csv";
    
    public static Runnable test(Algorithm alg, Delayed.Time listSpeed, Delayed.Time compSpeed, FileWriter fw) {
    
        Runnable run = new Runnable(){
            @Override
            public void run() {
                
                Sorter sorter;
                String algorithm;
                String listSpeedString;
                String compSpeedString;
                
                int n = 40;
                
                switch (listSpeed){
                    case Fast:
                        listSpeedString = "Fast";
                        break;
                    case Normal:
                        listSpeedString = "Normal";
                        break;
                    case Slow:
                        listSpeedString = "Slow";
                        break;
                    default:
                        listSpeedString = "Fast";
                        break;
                }
    
                switch (compSpeed){
                    case Fast:
                        compSpeedString = "Fast";
                        break;
                    case Normal:
                        compSpeedString = "Normal";
                        break;
                    case Slow:
                        compSpeedString = "Slow";
                        break;
                    default:
                        compSpeedString = "Fast";
                        break;
                }
                
                switch (alg) {
                    case InsertionSort:
                        sorter = new InsertionSorter();
                        algorithm = "InsertionSorter";
                        break;
                    case MergeSort:
                        sorter = new MergeSorter();
                        algorithm = "MergeSorter";
                        break;
                    case QuickSort:
                        sorter = new QuickSorter();
                        algorithm = "QuickSorter";
                        break;
                    case SelectionSort:
                        sorter = new SelectionSorter();
                        algorithm = "SelectionSorter";
                        break;
                    default:
                        sorter = new InsertionSorter();
                        algorithm = "InsertionSorter";
                        break;
                }
            
                Comparator<Integer> comparator = new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                };
            
                DelayedList<Integer> list = DelayedList.create(listSpeed, RandomNumbers.get(40));
                DelayedComparator<Integer> comp = DelayedComparator.create(comparator, compSpeed);
            
                //sort
                long starttime1 = System.nanoTime();
                sorter.sort(list, comp);
                long endtime1 = System.nanoTime();
                
                long insertiontime = endtime1 - starttime1;
                //System.out.println(insertiontime);
    
                try {
                    String writeData = algorithm + "," + n + "," + listSpeedString + "," + list.getOperationsPerformed() + "," + compSpeedString + "," + comp.getComparisonsPerformed() + "," + insertiontime + "\n";
                    fw.write(writeData);
                    System.out.println(writeData);
                }catch (IOException e){
                    System.out.println("IOException");
                }
            }
        };
        
        return run;
    }
    
    
    
    
    public static void main(String[] args){
    
      
      
      BatchRunner runner = new BatchRunner ();
      
      try {
          FileWriter fw = new FileWriter(FILENAME, true);
           
          
          // Insertion Sort
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Fast, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Fast, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Fast, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Normal, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Normal, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Normal, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Slow, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Slow, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.InsertionSort, Delayed.Time.Slow, Delayed.Time.Slow, fw));
    
          // Selection Sort
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Fast, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Fast, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Fast, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Normal, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Normal, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Normal, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Slow, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Slow, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.SelectionSort, Delayed.Time.Slow, Delayed.Time.Slow, fw));
    
          // Merge Sort
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Fast, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Fast, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Fast, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Normal, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Normal, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Normal, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Slow, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Slow, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.MergeSort, Delayed.Time.Slow, Delayed.Time.Slow, fw));
    
          // Quick Sort
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Fast, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Fast, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Fast, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Normal, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Normal, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Normal, Delayed.Time.Slow, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Slow, Delayed.Time.Fast, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Slow, Delayed.Time.Normal, fw));
          runner.enqueue(test(Algorithm.QuickSort, Delayed.Time.Slow, Delayed.Time.Slow, fw));
          
            
          runner.run();
          
          fw.close();
            
      } catch (IOException e){
           System.out.println("IOexception here");
      }
        
        System.exit(0);
        
    }
    
}
