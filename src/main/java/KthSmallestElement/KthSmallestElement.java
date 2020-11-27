package KthSmallestElement;

import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestElement {
    /**
     * Explication de votre complexité temporelle
     *
     *
     * Explication de votre complexité spatiale
     *
     *
     */
    /**
     * TODO Worst case
     * Time complexity : O ( k log max(m,n) )
     * Space complexity : O ( max(m,n) )
     * <p>
     * Returns the `k`th smallest element in `matrix`
     *
     * @param matrix 2D table of shape M x N respecting the following rules
     *               matrix[i][j] <= matrix[i+1][j]
     *               matrix[i][j] <= matrix[i][j+1]
     * @param k      Index of the smallest element we want
     * @return `K`th smallest element in `matrix`, null if non-existent
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {

        class MatrixElement implements Comparable<MatrixElement> {
            final int rIndex;
            final int cIndex;
            final T value;

            public MatrixElement(T val, int rIdx, int cIdx) {
                this.rIndex = rIdx;
                this.cIndex = cIdx;
                this.value = val;
            }

            @Override
            public int compareTo(MatrixElement o) {
                return value.compareTo(o.value);
            }
        }

        Queue<MatrixElement> queue = new PriorityQueue<>();
        T returnValue;
        if (matrix != null) {
            for (int j = 0; j < matrix[0].length; j++) {
                queue.add(new MatrixElement(matrix[0][j], 0, j));
            }
        } else {
            return null;
        }
        int count = 0;
        while (!queue.isEmpty()) {
            MatrixElement mat = queue.poll();
            if (count == k) {
                return mat.value;
            } else {
                count++;
                if (mat.rIndex + 1 < matrix.length) {
                    queue.add(new MatrixElement(matrix[mat.rIndex + 1][mat.cIndex], mat.rIndex + 1, mat.cIndex));
                }
            }
        }
        return null;
    }
}