public class SortingAndSearchingAlgorithms {
    public static void main(String[] args) {
        int nums[] = {10, 2, 18, -4, 12, 33, 9};

        selectionSort(nums);
        display(nums);

        int nums1[] = {12, 21, -18, -4, 2, 13, 9};

        insertionSort(nums1);
        display(nums1);

        int arr[] = merge(nums, nums1);
        display(arr);

        int [] arr2 = {1, 1, 3, 2, 6, 5, 4, 7, 8, 9, 11, 20, 14, 17, 13, 10, 12, 15, 16, 19};
        display(arr2);
        System.out.print("\n--------------\n" + sequentialSearch(arr2, 5));

        selectionSort(arr2);
        System.out.print("\n--------------\n" + binarySearch(arr2, 5));
            // Iterative method.

        System.out.print("\n--------------\n" + binarySearch(arr2, 5, 0, arr2.length-1));
            // Recursice method.
    }

    private static int binarySearch(int[] arr, int findMe) {
        int left = 0;
        int right = arr.length-1;
        
        while (left <= right) {
            int middle = (left + right)/ 2;
            if (arr[middle] == findMe) {
                return middle;
            } else if (arr[middle] > findMe) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }

        return -1;
        
    }

    private static int binarySearch(int[] arr, int findMe, int left, int right) {
        if (left > right) {
            return -1;
        } else {
            int mid = (left + right)/2;
            if (arr[mid] == findMe) {
                return mid;
            } else if (arr[mid] > findMe) {
                return binarySearch(arr, findMe, left, mid-1);
            } else {
                return binarySearch(arr, findMe, mid+1, right);
            }
        }
        
    }

    private static int sequentialSearch(int[] arr, int findMe) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == findMe) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 
     * @param nums a sorted array
     * @param nums1 a sorted array
     * @return a sorted array with all of the elements from nums and nums1
     */
    private static int[] merge(int[] nums, int[] nums1) {
        int arr[] = new int[nums.length + nums1.length];
        
        int k = 0;
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            // Check if we have NOT reached the end of both arrays.
            if (k < nums.length && j < nums1.length) {
                if (nums[k] < nums[j]) {
                    arr[i] = nums[k];
                    k++;
                } else {
                    arr[i] = nums1[j];
                    j++;
                }
            // Checks if we have stuff left in the 1st array.
            } else if (k < nums.length) {
                arr[i] = nums[k];
                k++;
            // We have stuff left only in the 2nd array.
            } else {
                arr[i] = nums1[j];
                j++;
            }
        }

        return arr;
    }

    private static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i-1;
            while (j >= 0 && temp < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }

            arr[j+1] = temp;
        }

    }

    private static void display(int[] nums) {
        for (int x : nums) {
            System.out.println(x);
        }
    }

    private static void selectionSort(int[] nums) {
        // Outer loop is responsible for the division between the sorted and unsorted sides.
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            // Inner loop is reponsible for locating the smallest element of the array.
            for (int j = i+1; j <nums.length; j++) {
                if (nums[j] < nums[minIndex])
                    minIndex = j;
            }

            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
    }
}
