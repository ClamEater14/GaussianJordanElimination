import java.util.Scanner;

/**
 * Main
 * A program that takes in an augmented matrix and solves it by putting it into RREF (Reduced Row-Echelon Form).
 * It tells whether the matrix can be solved or not and the solutions if possible.
 */
enum SolutionType {
    UNIQUE,
    INFINITE,
    NONE,
    TO_BE_DETERMINED
}

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static SolutionType checkConsistency(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            double sum = 0;
            double[] row = matrix[i];
            for (int j = 0; j < row.length - 1; j++) {
                sum += row[j];
            }
            if (sum == 0 && row[row.length - 1] != 0) {
                return SolutionType.NONE;
            }
        }

        return SolutionType.INFINITE;
    }

    public static void swapRows(double[][] matrix, int rowA, int rowB) {
        double[] tempRow = matrix[rowA];
        matrix[rowA] = matrix[rowB];
        matrix[rowB] = tempRow;
    }

    public static void multiplyRowByNumber(double[][] matrix, int targetRowIndex, double multiplier) {
        double[] targetRow = matrix[targetRowIndex];
        for (int i = 0; i < targetRow.length; i++) {
            targetRow[i] *= multiplier;
        }
    }

    public static void addRowByMultipleOfAddendRow(double[][] matrix, int targetRowIndex, int addendRowIndex, double addendMultiplier) {
        double[] targetRow = matrix[targetRowIndex];
        double[] addendRow = matrix[addendRowIndex];
        for (int i = 0; i < targetRow.length; i++) {
            targetRow[i] += addendMultiplier * addendRow[i];
        }
    }

    public static SolutionType performOperation(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == 0) {
                // Find a row to swap so that the pivot is non-zero.
                int swapWith = i + 1;
                while (swapWith < matrix.length && matrix[swapWith][i] == 0) {
                    swapWith++;
                }

                // No rows to swap for pivot to be non-zero, the system may have no or infinite solutions.
                if (swapWith == matrix.length) {
                    return SolutionType.TO_BE_DETERMINED;
                }

                swapRows(matrix, i, swapWith);
            }

            for (int j = 0; j < matrix.length; j++) {
                if (i != j) {
                    double mult = -matrix[j][i] / matrix[i][i];
                    addRowByMultipleOfAddendRow(matrix, j, i, mult);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            // Make the leading entries one after re-arrangement.
            double rowMult = 1.0 / matrix[i][i];
            multiplyRowByNumber(matrix, i, rowMult);
        }
        
        if (matrix.length != matrix[0].length - 1) {
            return SolutionType.TO_BE_DETERMINED;
        }
        else {
            return SolutionType.UNIQUE;
        }
    }

    private static void printMatrix(double[][] matrix, boolean augmented) {
        String result = "";
        StringBuilder builder = new StringBuilder();
        for (double[] currentRow : matrix) {
            for (int i = 0; i < currentRow.length; i++) {
                double currentElement = currentRow[i];
                builder.append(Double.toString(currentElement) + ((i == currentRow.length - 2 && augmented) ? "\t|" : "") + "\t");
            }
            builder.append("\n");
        }
        result = builder.toString().trim();
        System.out.println(result);
    }

    private static void printSolution(double[][] matrix, SolutionType solType) {
        if (solType == SolutionType.TO_BE_DETERMINED) {
            System.out.println("Checking consistency...");
            solType = checkConsistency(matrix);
            if (solType == SolutionType.NONE) {
                System.out.println("There are no solutions. The system is inconsistent.");
            }
            else if (solType == SolutionType.INFINITE) {
                System.out.println("There are infinite solutions. Refer to Resultant Matrix and perform back substitution.");
            }
        }
        else {
            System.out.println("The solution is");
            for (int i = 0; i < matrix.length; i++) {
                double[] row = matrix[i];
                System.out.println("x" + (i + 1) + " = " + row[row.length - 1]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("This is made for programming practice only. No cheating with this program will be allowed.");
        int m, n;
        System.out.println("Dimensions ([Rows] [Columns]):");
        m = in.nextInt();
        n = in.nextInt();
        double[][] augmentedMatrix = new double[m][n + 1];

        // Matrix setup
        System.out.println("Insert each element.\n([a1 b1 c1 ...] [k1] per row)\n(The last element per row is always part of the constant column.)");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n + 1; j++) {
                augmentedMatrix[i][j] = in.nextDouble();
            }
        }

        System.out.println("Initial Matrix:");
        printMatrix(augmentedMatrix, true);

        System.out.println("Converting matrix to RREF form...");
        SolutionType solType = performOperation(augmentedMatrix);
        
        System.out.println("Resultant Matrix:");
        printMatrix(augmentedMatrix, true);
        printSolution(augmentedMatrix, solType);
    }
}