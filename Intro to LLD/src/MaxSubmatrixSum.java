public class MaxSubmatrixSum {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int maxSubmatrixSum = findMaxSubmatrixSum(matrix);
        System.out.println("Maximum submatrix sum: " + maxSubmatrixSum);
    }

    public static int findMaxSubmatrixSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Create a prefix sum matrix
        int[][] prefixSum = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefixSum[i][j] = matrix[i][j];
                if (j > 0) {
                    prefixSum[i][j] += prefixSum[i][j - 1];
                }
            }
        }

        int maxSubmatrixSum = Integer.MIN_VALUE;

        for (int topRow = 0; topRow < rows; topRow++) {
            for (int bottomRow = topRow; bottomRow < rows; bottomRow++) {
                int currentSum = 0;

                for (int col = 0; col < cols; col++) {
                    int colSum = prefixSum[bottomRow][col];
                    if (topRow > 0) {
                        colSum -= prefixSum[topRow - 1][col];
                    }

                    currentSum += colSum;

                    // If the current sum is greater than the maximum sum, update it
                    maxSubmatrixSum = Math.max(maxSubmatrixSum, currentSum);

                    // If the current sum is negative, reset it to 0
                    if (currentSum < 0) {
                        currentSum = 0;
                    }
                }
            }
        }

        return maxSubmatrixSum;
    }
}
