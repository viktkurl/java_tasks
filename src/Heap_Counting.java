import java.util.*;
public class Heap_Counting {
    Heap_Counting left, right;
    public static void main(String[] args) {
        int n,arr[];
        Scanner in = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        n = in.nextInt();
        arr = new int [n];
        RandomInit(arr,n);

        ShowArray(arr);
        int [] res= Arrays.copyOf(arr, arr.length);
        long t1heapSort = System.nanoTime();
        heapSort(res);
        long t2heapSort = System.nanoTime();
        //countingSort(arr,arr.length);
        System.out.println("HeapSort");
        ShowArray(res);

        res= Arrays.copyOf(arr, arr.length);
        long t1countingSort = System.nanoTime();
        countingSort(res);
        long t2countingSort = System.nanoTime();
        System.out.println("СountingSort");
        ShowArray(res);

        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Массив размерности "+n);
        System.out.println("HeapSort отсортировал за: "+String.format("%,12d",t2heapSort-t1heapSort) + " ns");
        System.out.println("СountingSort отсортировал за: "+String.format("%,12d",t2countingSort-t1countingSort) + " ns");

    }


    public static void heapSort(int[] res) {
        for(int i = res.length; i > 1; i--){
            buildMaxHeap(res, i - 1);
        }
        for(int i = res.length; i > 1; i--){
            buildMaxHeap(res, i - 1);
        }

    }

    public static void buildMaxHeap(int[] res, int max_index){
        int i, o;
        int leftChild, rightChild, maxChild, root, temp;
        root = (max_index -1)/2;

        //обходим дерево в поисках максимального члена (ранее отсортированные максимальные игнорируем)
        for(i = root; i >= 0; i--){
            leftChild = (2*i) + 1;
            rightChild = (2*i) + 2;

            if((leftChild <= max_index) && (rightChild <= max_index)){
                if(res[rightChild] >= res[leftChild])
                    maxChild = rightChild;
                else
                    maxChild = leftChild;
            } else{
                if(rightChild > max_index)
                    maxChild = leftChild;
                else
                    maxChild = rightChild;
            }

            //передвигаем  максимальный член вверх по пирамиде (начало массива)
            if(res[i] < res[maxChild]){
                temp = res[i];
                res[i] = res[maxChild];
                res[maxChild] = temp;
            }
        }

        //убираем максимальный член в конец массива
        temp = res[0];
        res[0] = res[max_index];
        res[max_index] = temp;
    }
    public static void countingSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }

    public static void ShowArray(int[] res) {
        int i=0;
        while (i<res.length){
            System.out.print(res[i]);
            for (int j=i+1;j<i+16; j++) {
                if(j<res.length) {
                    System.out.print("\t"+res[j]);
                }
            }
            System.out.println("");
            i+=11;
        }
    }

    public static void RandomInit(int[] mas, int n) {
        Random rand = new Random(); // создание объекта для генерации случайных чисел
        for (int i=0; i<n; i++) {
            mas[i] = rand.nextInt(1000000); // присваивание i-му элементу массива случайного числа в диапазоне от 0 до 999999
        }
    }
}
