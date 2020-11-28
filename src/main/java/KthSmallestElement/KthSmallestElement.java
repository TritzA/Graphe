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

    /**
     * Explication des complexités de la résolution du problème
     * <p>
     * Explication de la complexité temporelle
     * On devra itérer sur k éléments. Et pour chaque élément itéré, on devra
     * l'ajouter dans un Heap (grâce au add) et le sortir d'un Heap (grâce au poll).
     * Ces deux opérations s'effectuent en O( log ( max(m,n) ) ). La complexité
     * sera donc de O( k*log( max(m, n) ) ).
     * <p>
     * Explication de la complexité spatiale
     * Au début on ajoute tous les éléments de la première ligne dans le Heap.
     * Ensuite, à chaque fois qu'on sort un élément du heap, on le compare,
     * puis on en ajoute un autre. Ce qui fait que la taille du Heap reste constante
     * à une valeur près. Elle reste d'une taille O ( max(m,n) ).
     * <p>
     * Note, les complexités sont explicitées dans le code en commentaires.
     */
    static public <T extends Comparable<T>> T findKthSmallestElement(final T[][] matrix, final int k) {

        class MatrixElement implements Comparable<MatrixElement> {
            final int rowIndex;
            final int colIndex;
            final T value;

            public MatrixElement(T val, int rIdx, int cIdx) {
                this.rowIndex = rIdx;
                this.colIndex = cIdx;
                this.value = val;
            }

            @Override
            public int compareTo(MatrixElement o) {
                return value.compareTo(o.value);
            }
        }

        PriorityQueue<MatrixElement> queue = new PriorityQueue<>();// Max size : max(m, n) -> O ( max(m,n) ) Space complexity

        if (matrix != null) {
            for (int j = 0; j < matrix[0].length; j++) {
                queue.add(new MatrixElement(matrix[0][j], 0, j));
            }
        } else {
            return null;
        }

        int count = 0;
        while (!queue.isEmpty()) {// O(k) * ( O( log( max(m, n) ) ) + O( log( max(m, n) ) ) ) -> O( k * log( max(m, n) ) ) Time complexity
            MatrixElement mat = queue.poll();// O( log( max(m, n) ) ) Time complexity
            if (count == k) {
                return mat.value;
            } else {
                count++;
                if (mat.rowIndex + 1 < matrix.length) {
                    queue.add(new MatrixElement(matrix[mat.rowIndex + 1][mat.colIndex], mat.rowIndex + 1, mat.colIndex));// O( log( max(m, n) ) ) Time complexity
                }
            }
        }
        return null;
    }
}