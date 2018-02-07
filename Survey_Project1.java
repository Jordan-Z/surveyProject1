/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Survey_Project1;

import java.util.*;

/**
 * Class: 44-242 Data Structures Author: (Jordan Zech) Description: (Lab 10)
 * Due: (4/11/2017) I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or any source. I
 * have not given my code to any other student. I have not given my code to any
 * other student and will not share this code with anyone under any
 * circumstances.
 */
public class Survey_Project1 {

    private static void swapInds(ArrayList<Integer> L, int i, int j) {
        Integer t = L.get(i);
        L.set(i, L.get(j));
        L.set(j, t);
    }

    public static ArrayList<Integer> insertionSort(ArrayList<Integer> L) {
        for (int i = 0; i < L.size(); i++) {
            int j = i;
            while (j > 0 && L.get(j - 1) > L.get(j)) {
                swapInds(L, j - 1, j);
                j--;
            }

        }

        return L;
    }

    public static ArrayList<Integer> insertValueSort(ArrayList<Integer> L, int n) {
        for (int i = 0; i < L.size(); i++) {
            int j = i;
            while (j > 0 && L.get(j - 1) > L.get(j)) {
                swapInds(L, j - 1, j);
                j--;
            }

        }
        System.out.println(L.get(n));
        return L;
    }

    public static ArrayList<Integer> selectionSort(ArrayList<Integer> L) {
        for (int i = 0; i < L.size(); i++) {
            int smallest = i;
            for (int j = i; j < L.size(); j++) {
                if (L.get(j) < L.get(smallest)) {
                    smallest = j;
                }
            }
            swapInds(L, smallest, i);
        }
        return L;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> L) {

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();
        if (L.size() <= 1) {
            return L;

        }
        int n = L.size() / 2;

        A = mergeSort(new ArrayList<Integer>(L.subList(0, n)));
        B = mergeSort(new ArrayList<Integer>(L.subList(n, L.size())));
        return merge(A, B);
    }

    private static ArrayList<Integer> merge(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> C = new ArrayList<>();
        int iA = 0;
        int iB = 0;
        C = new ArrayList<>();
        while (iA < A.size() && iB < B.size()) {
            if (A.get(iA) < B.get(iB)) {
                C.add(A.get(iA));
                iA++;

            } else {
                C.add(B.get(iB));
                iB++;
            }
        }
        while (iA < A.size()) {
            C.add(A.get(iA));
            iA++;
        }
        while (iB < B.size()) {
            C.add(B.get(iB));
            iB++;
        }
        //System.out.println(C.get(2));
        //mergeKvalue(C, 1);
        return C;

    }

    private static int mergeKvalue(ArrayList<Integer> A, int n) {
        System.out.println(A.get(n));
        return A.get(n);
    }
    
    private static ArrayList<Integer> selectAlg(ArrayList<Integer> S)
    {
        Random rand = new Random();
        int e = rand.nextInt(20);
        ArrayList<Integer> Al = new ArrayList<Integer>();
        ArrayList<Integer> Ae = new ArrayList<Integer>();
        ArrayList<Integer> Ar = new ArrayList<Integer>();
        for (int i : S) {
            if (i < e) {
                Al.add(i);
            }
            if (i == e) {
                Ae.add(i);
            }
            if (i > e) {
                Ar.add(i);
            }
        }
        selectionSort(Al);
        selectionSort(Ae);
        selectionSort(Ar);
        
        ArrayList<Integer> sortedA = new ArrayList<Integer>();
        sortedA.addAll(Al);
        sortedA.addAll(Ae);
        sortedA.addAll(Ar);
        return sortedA;
    }

    public static ArrayList<Integer> randoList(int size) {
        ArrayList<Integer> C = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            C.add((int) (Math.random() * size * 2));
        }
        return C;
    }

    public static double timeInsertion(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            insertionSort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeSelection(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            selectAlg(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeMerge(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            mergeSort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeBuiltinSort(int size) {
        double avg = 0;
        for (int i = 0; i < 30; i++) {
            ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            Collections.sort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number (0-9) for k(index in the arraylist): ");
        int n = reader.nextInt();
        //reader.close();

        System.out.print("k value for insertion algorithm ");
        insertValueSort(randoList(10), n);

        ArrayList<Integer> unsortedList = randoList(10);
        ArrayList<Integer> mergeList = mergeSort(unsortedList);
        System.out.print("k value for merge algorithm ");
        System.out.println(mergeList.get(n));

        ArrayList<Integer> builtinSort = randoList(10);
        Collections.sort(builtinSort);
        System.out.print("k value for builtin sorting algorithm ");
        System.out.println(builtinSort.get(n));
        
        ArrayList<Integer> selectA = randoList(10);
        ArrayList<Integer> sortedAlg = new ArrayList<>(selectAlg(selectA));
        System.out.print("k value for selection algorithm is ");
        System.out.println(sortedAlg.get(n));
        
        int sizes[] = {1, 500, 1000, 5000, 10000, 25000};
        System.out.printf("%-10s %-20s %-20s %-20s %-20s\n", "Size", "Insertion",
                "Merge", "BuiltinSort", "Selection");
        for (int size : sizes) {
            System.out.printf("%-10d %-20f %-20f %-20f %-20f\n", size, 
                    timeInsertion(size), timeMerge(size), timeBuiltinSort(size), 
                    timeSelection(size));
        }
    }

}
