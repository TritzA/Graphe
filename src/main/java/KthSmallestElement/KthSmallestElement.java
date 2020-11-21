package KthSmallestElement;

import java.util.PriorityQueue;

public class KthSmallestElement {
    /**
     * Explication de votre complexité temporelle
     *
     *
     * Explication de votre complexité spatiale
     *
     *
     */
    /** TODO Worst case
     *      Time complexity : O ( k log max(m,n) )
     *      Space complexity : O ( max(m,n) )
     *
     * Returns the `k`th smallest element in `matrix`
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j + 1]
     * @param k Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {
        PriorityQueue<T> pq = new PriorityQueue<T>();
        for (int col = 0; col < matrix.length; col++){

        }
        return null;
    }
}
