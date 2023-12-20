public class MaximumSubMatrix {
    public static void main(String[] args) {
        int[][] arr1 = {{1, 2, -3}, {4, 0, -6}, {-7,8,-9}};
        int R = arr1.length;
        int C = arr1[0].length;
        int ans = Integer.MIN_VALUE;
        for(int st = 0; st<R; st++) {
            int[] A = new int[C];
            for (int i = st; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    A[j] += arr1[i][j];
                }
                ans = Math.max(ans, MaximumSubMatrix.kadanes(A, C));
            }
        }
        System.out.println(ans);

       /*for(int i=0; i<A.length; i++){
           System.out.print(A[i]+" ");
        }*/

    }

    public static int kadanes(int[] A, int C){
        int sum = 0;
        int max_sum = Integer.MIN_VALUE;

        for(int i=0; i<C; i++){
            sum += A[i];
            max_sum = Math.max(sum, max_sum);
            if(sum<0){
                sum = 0;
            }
        }

        return max_sum;
    }
}