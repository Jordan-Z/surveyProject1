/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Survey_Project1;

import java.util.*;

/**
 * Class: Survey of Algorithms Author: (Jordan Zech and Jesse Brandt)
 * Description: (Project 1)
 *
 */
public class Survey_Project1 {

    static int ITERS = 0;
    static int Kvalue = 0;

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

    public static int insertValueSort(ArrayList<Integer> L, int n) {
        for (int i = 0; i < L.size(); i++) {
            int j = i;
            while (j > 0 && L.get(j - 1) > L.get(j)) {
                swapInds(L, j - 1, j);
                j--;
            }

        }
        // System.out.println(L.get(n));
        return L.get(n);
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
        // System.out.println(A.get(n));
        return A.get(n);
    }

    private static int selectAlg(ArrayList<Integer> S, int k) {
        Random rand = new Random();
        int e = S.get(rand.nextInt(S.size()));
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
        if (k < Al.size()) {
            return selectAlg(Al, k);
        } else if (k < (Al.size() + Ae.size())) {
            return e;
        } else {
            return selectAlg(Ar, (k - Al.size() - Ae.size()));
        }
        //selectionSort(Al);
        //selectionSort(Ae);
        //selectionSort(Ar);

        //ArrayList<Integer> sortedA = new ArrayList<Integer>();
        //sortedA.addAll(Al);
        //sortedA.addAll(Ae);
        //sortedA.addAll(Ar);
        //return sortedA;
    }

    public static int KvalueBuiltinSort(ArrayList<Integer> a, int n) {
        return a.get(n);
    }

    public static ArrayList<Integer> randoList(int size) {
        ArrayList<Integer> C = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            C.add((int) (Math.random() * size * 2));
        }
        return C;
    }

    public static double timeInsertion(int size, ArrayList<Integer> l) {
        double avg = 0;
        for (int i = 0; i < ITERS; i++) {
            //ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            insertionSort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeSelection(int size, int k, ArrayList<Integer> l) {
        double avg = 0;
        for (int i = 0; i < ITERS; i++) {
            //ArrayList<Integer> l = randoList(size);
            long start = System.nanoTime();
            selectAlg(l, k);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeMerge(int size, ArrayList<Integer> l) {
        double avg = 0;
        for (int i = 0; i < ITERS; i++) {

            long start = System.nanoTime();
            mergeSort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static double timeBuiltinSort(int size, ArrayList<Integer> l) {
        double avg = 0;
        for (int i = 0; i < ITERS; i++) {

            long start = System.nanoTime();
            Collections.sort(l);
            long end = System.nanoTime();
            avg += (end - start) / 1000000000.0;
        }
        return avg;
    }

    public static int testKValue(int insertK, int mergeK, int builtinK, int selectK) {
        if (builtinK == mergeK && builtinK == insertK && builtinK == selectK) {
            Kvalue++;
        }
        return Kvalue;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number (0-9) for k(index in the arraylist): ");
        int n = reader.nextInt();
        //reader.close();
        System.out.println("Enter a number for ITERS:how many times you want to run loop: ");
        ITERS = reader.nextInt();
        

        int sizes[] = {100, 500, 1000, 5000, 10000};
        System.out.printf("%-10s %-20s %-20s %-20s %-20s\n", "Size", "Insertion",
                "Merge", "BuiltinSort", "Selection");
        for (int size : sizes) {
            ArrayList<Integer> l = randoList(size);
            System.out.printf("%-10d %-20f %-20f %-20f %-20f\n", size,
                    timeInsertion(size, l), timeMerge(size, l), timeBuiltinSort(size, l),
                    timeSelection(size, n, l));
            for (int i = 0; i < ITERS; i++) {

                testKValue(insertValueSort(l, n), mergeKvalue(l, n), KvalueBuiltinSort(l, n), selectAlg(l, n));
            }

        }
        System.out.println("The K value was correct " + Kvalue + " times out of " + ITERS*5);
    }

}
