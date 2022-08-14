public class DvideAndConcer {

    public static void main(String[] args) {

        int[] arr1= {1,2,3,5};
        int[] arr2={1,2,3,5};
        System.out.println(Median(arr1,arr2));

    }


    public static double Median(int[] arr1, int[] arr2)

    {
        int N = arr1.length;

        return Median(arr1, 0, N -1 , arr2, 0, N - 1);

    }

    public static double Median(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2)

    {
        int n=r1-l1+1;
        int mid1 = (int) Math.ceil((double) (r1 + l1 ) / 2);
        int mid2 = (r2 + l2 ) / 2;

        double m1=Median(arr1,n);
        double m2=Median(arr2,n);
        if (n==1)
            return (double) (arr1[0]+arr2[0])/2;

        if (n==2)

            return (double) (Math.max(arr1[l1] , arr2[l2]) + Math.min(arr1[r1] , arr2[r2]))/2;

        if (m1==m2)
            return m2;

        else if (m1 > m2)

            return Median(arr1, l1, mid1 , arr2, mid2 , r2);

        else

            return Median(arr1, mid1, r1, arr2, l2, mid2);

    }

    static double Median(int[] arr, int n)
    {
        if (n % 2 == 0)
            return (double) (arr[n / 2] + arr[n / 2 - 1]) / 2;
        else
            return arr[n / 2];
    }



}
