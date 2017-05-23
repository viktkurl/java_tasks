import java.util.*;

class mergeSort {
        public static void main(String[] args){
            int n; //размер массива
            int summ; //размер суммы

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
            System.out.println("Массив до сортировки:\n");
            ShowArray(mas);
            long t1merge = System.nanoTime();//время запуска сортировки
            mergeSort(mas);
            long t2merge = System.nanoTime();//время конца сортировки
            System.out.println("\nМассив после сортировки:\n");
            ShowArray(mas);
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("Массив размерности "+n+":");
            System.out.println("MergeSort отсортировал за: "+String.format("%,12d",t2merge-t1merge) + " ns");
            Scanner inp = new Scanner(System.in);
            System.out.println("\nХотите проверить массив на сумму двух чисел?(д/н)");
            String a = inp.nextLine();

            if (a.equals("д")){
                boolean flag = false; //создание ключа
                Scanner inpt = new Scanner(System.in);
                System.out.println("\nВведите чему должна ровняться сумма: "+(mas[0]+mas[4])+" (например сумму 5 и 1 элемента)");
                summ = inpt.nextInt();
                long t1search = System.nanoTime(); //время запуска поиска
                for (int i = 0; i < mas.length; i++) {
                    if (rank(summ - mas[i], mas)) {
                        flag = true;
                    }
                }
                long t2search = System.nanoTime(); //время конца поиска
                System.out.println(flag); //вывести значение ключа
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.println("Упорядоченный массив размерности "+n+":");
                System.out.println("Поиск был выполнен за: "+String.format("%,12d",t2search-t1search) + " ns");
            } else {
                System.out.println("Вы решили не производить поиск!");
            }
        }

        public static void mergeSort(int[] arr) {
            if(arr.length>1) {
                int n = arr.length;
                int[] left = leftHalf(arr);
                int[] right = rightHalf(arr);
                mergeSort(left);
                mergeSort(right);
                merge(arr, left, right);
            }
        }

    //создание левой половины массива
        public static int[] leftHalf(int[] arr){
            int size1 = arr.length/2;
            int[] left = new int[size1];
            for(int i=0;i<size1;i++){
                left[i]=arr[i];
            }
            return left;
        }

    //создание правой половины
        public static int[] rightHalf(int[] arr){
            int size1 = arr.length/2;
            int size2 = arr.length-size1;
            int[] right = new int[size2];
            for(int i=0; i<size2;i++){
                right[i]=arr[i+size1];
            }
            return right;
        }

        //метод сортировки принимающий на вход массив исходных чисел, массив левой и правой половин
        public static void merge(int[] resultArr, int[] left, int[] right) {
            int i1 = 0;
            int i2 = 0;
            for (int i = 0; i < resultArr.length; i++) {
                if (i2 >= right.length || (i1 < left.length && left[i1] <= right[i2])) {
                    resultArr[i] = left[i1];
                    i1++;
                } else {
                    resultArr[i] = right[i2];
                    i2++;
                }
            }
        }

        //метод для заполнения массива случайными числами
    public static void RandomInit(int[] mas, int n) {
        Random rand = new Random();
        for (int i=0; i<n; i++) {
            mas[i] = rand.nextInt(1000000);
        }
    }

    //метод для вывода массива принимаемого на вход
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

    public static boolean rank(int val, int[] arr) {
        return rank(arr,val, 0, arr.length-1);
    }

    //метод бинарного поиска
    private static boolean rank(int[] arr, int val, int left, int right) {
        if (left > right) return false;

        int mid = left+((right - left) / 2);

        if (val < arr[mid]) {
            return rank(arr, val, left, mid - 1);
        } else if (val > arr[mid]) {
            return rank(arr, val, mid + 1, right);
        } else {
            return true;
        }
    }
}
