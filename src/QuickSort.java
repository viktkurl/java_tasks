import java.util.Random;
import java.util.Scanner;

public class QuickSort  {
    private int[] numbers;
    private int number;

    public void sort(int[] values) {
        // проверка массива
        if (values ==null || values.length==0){
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Получаем элемент для сравнения из середины
        int pivot = numbers[low + (high-low)/2];

        while (i <= j) {
            while (numbers[i] < pivot) {
                i++;
            }
            while (numbers[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Рекурсия
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    //Перестановка
    private void exchange(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    //метод инициализирует массив случайными числами
    public static void RandomInit(int[] mas, int n) {
        Random rand = new Random(); // создание объекта для генерации случайных чисел
        for (int i=0; i<n; i++) {
            mas[i] = rand.nextInt(1000000); // присваивание i-му элементу массива случайного числа в диапазоне от 0 до 999999
        }
    }

    //метод выводит массив на экран
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

    public static void main(String[] args)  {

        int n; // размерность массива
        // отсортированный массив

        //Ввод размерности массива с клавиатуры
        Scanner input=new Scanner(System.in); // создание сканера, считыающего данные из консоли
        System.out.println("Input the array length:"); //вывод сообщения на консоль
        String  s=input.nextLine(); //считываем количество элементов, введенное с клавиатуры

        //проверяем, является ли строка числом
        try {
            n = Integer.parseInt(s);//попытаться преобразовать s в integer и присвоить n
        } catch (Exception e) { //если произошла ошибка
            System.out.println("The array length should be integer!"); // Вывод сообщения об ошибке
            return;//выход из программы
        }
        //проверяем, чтобы длина была больше 0
        if (n<=0) {
            System.out.println("The array length should be more than 0!"); // Вывод сообщения об ошибке
            return;//выход из программы
        }
        int[] mas = new int[n]; // создаем исходный массив требуемой длины
        int[] resultMas = new int[n];
        //инициализация массива случаными числами
        RandomInit(mas,n);
        System.out.println("");System.out.println("The initial array:"); //Вывод исходного массива на экран
        ShowArray(mas);

        long t1Quick = System.nanoTime();//замеряем время до InsertionSort
        QuickSort sorter = new QuickSort();
        sorter.sort(mas);
        long t2Quick = System.nanoTime();//замеряем время после InsertionSort
        System.out.println("");System.out.println("The Quick sorted array:"); //проверяем, отсортировался ли массив
        ShowArray(mas);

        //Вывод результатов
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Массив размерности "+n+":");
        System.out.println("InsertionSort отсортировал за: "+String.format("%,12d",t2Quick-t1Quick) + " ns");
    }
}