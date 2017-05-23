import java.util.*;
class SortLabTwo {

    private static int[] mas;
    private static int n;
    private static int left;
    private static int right;
    private static int largest;

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("Введите длину массива:");
        String  s=input.nextLine(); //чтение строки

        try {
            n = Integer.parseInt(s); //преобразование введенной строки в тип Integer
        } catch (Exception e) {
            System.out.println("Длина массива должна быть натуральным числом");
            return;
        }

        if (n<=0) {
            System.out.println("Длина массива должна быть больше 0!");
            return;
        }
        int[] mas = new int[n]; //создание пустого массива длинны n
        RandomInit(mas,n); //заполнение случайными числами массива

        int l = n;
        System.out.println("Массив до сортировки:\n");
        ShowArray(mas);
        int[] resMas = (int[])mas.clone();
        long t1heap = System.nanoTime();//время запуска сортировки
        heapSort(resMas);
        long t2heap = System.nanoTime();//время конца сортировки
        System.out.println("heapSort после сортировки: ");ShowArray(resMas);
        resMas = (int[])mas.clone();
        long t1count = System.nanoTime();//время запуска сортировки
        resMas = countingSort(resMas);
        long t2count = System.nanoTime();//время конца сортировки
        System.out.println("countSort после сортировки: ");ShowArray(resMas);
        resMas = (int[])mas.clone();
        long t1bucket = System.nanoTime();//время запуска сортировки
        bucketSort(resMas);
        long t2bucket = System.nanoTime();//время конца сортировки
        System.out.println("bucketSort после сортировки: ");ShowArray(resMas);

        //Вывод результатов
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Массив размерности "+l+":");
        System.out.println("HeapSort отсортировал за: "+String.format("%,12d",t2heap-t1heap) + " ns");
        System.out.println("CountSort отсортировал за: "+String.format("%,12d",t2count-t1count) + " ns");
        System.out.println("BucketSort отсортировал за: "+String.format("%,12d",t2bucket-t1bucket) + " ns");
    }

    public static void buildheap(int []mas) {
        n = mas.length-1;
        for(int i=n/2; i>=0; i--){
            maxheap(mas,i);
        }
    }

    public static void maxheap(int[] a, int i) {
        left = 2*i;
        right = 2*i+1;

        if(left <= n && a[left] > a[i]){
            largest=left;
        } else {
            largest=i;
        }

        if(right <= n && a[right] > a[largest]) {
            largest=right;
        }
        if(largest!=i) {
            exchange(i, largest);
            maxheap(a, largest);
        }
    }

    public static void exchange(int i, int j) {
        int t = mas[i];
        mas[i] = mas[j];
        mas[j] = t;
    }

    public static void heapSort(int[] myarray) {
        mas = myarray;
        buildheap(mas);
        for(int i=n; i>0; i--) {
            exchange(0, i);
            n=n-1;
            maxheap(mas, 0);
        }
    }

    public static int[] countingSort(int[] array) {
        int[] aux = new int[array.length];

        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }


        int[] counts = new int[max - min + 1];


        for (int i = 0;  i < array.length; i++) {
            counts[array[i] - min]++;
        }

        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i-1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            aux[counts[array[i] - min]--] = array[i];
        }

        return aux;
    }


    public static void bucketSort(int[] a) {
        int maxVal = a[0];

        for (int k = 1; k < a.length; k++) {
            if (a[k] > maxVal)
                maxVal = a[k];
        }
        //создадим вспомогательный массив
        int [] bucket=new int[maxVal+1];

        for (int i=0; i<bucket.length; i++) {
            bucket[i]=0;
        }



        //распределим числа по карманам
        for (int i=0; i<a.length; i++) {
            bucket[a[i]]++;
        }

        int outPos=0;
        //отсортируем числа в карманах используя сортировку подсчетом
        for (int i=0; i<bucket.length; i++) {
            for (int j=0; j<bucket[i]; j++) {
                a[outPos++]=i;
            }
        }
    }

    public static void RandomInit(int[] mas, int n) {
        Random rand = new Random(); // создание объекта для генерации случайных чисел
        for (int i=0; i<n; i++) {
            mas[i] = rand.nextInt(1000000); // присваивание i-му элементу массива случайного числа в диапазоне от 0 до 999999
        }
    }

    public static void ShowArray(int[] mas) {
        int i=0;

        while (i<mas.length){
            System.out.print(mas[i]);
            for (int j=i+1;j<i+16; j++) {
                if(j<mas.length) {
                    System.out.print("\t"+mas[j]);
                }
            }
            System.out.println("");
            i+=11;
        }
    }
}
