/**
 * Created by Viktor on 13.02.2017.
 */
import java.util.Scanner; //подключение библиотеки Java для ввода данных с клавиатуры
import java.util.Random; //подключение библиотеки Java для генерации случайных чисел
class SortLab {
    //главная функция, тестирующая работоспособность методов сортировки
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

        long t1Bubble = System.nanoTime();//замеряем время до BubbleSort
        int[] sortedMas = BubbleSort(mas, n);
        long t2Bubble = System.nanoTime();//замеряем время после BubbleSort
        System.out.println("");System.out.println("The bubble sorted array:"); //проверяем, отсортировался ли массив
        ShowArray(sortedMas);

        long t1Selection = System.nanoTime();//замеряем время до SelectionSort
        sortedMas = SelectionSort(mas, n);
        long t2Selection = System.nanoTime();//замеряем время после SelectionSort
        System.out.println("");System.out.println("The selection sorted array:"); //проверяем, отсортировался ли массив
        ShowArray(sortedMas);

        long t1InsertionSort = System.nanoTime();//замеряем время до InsertionSort
        sortedMas = InsertionSort(mas, n);
        long t2InsertionSort = System.nanoTime();//замеряем время после InsertionSort
        System.out.println("");System.out.println("The insertion sorted array:"); //проверяем, отсортировался ли массив
        ShowArray(sortedMas);

        //Вывод результатов
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Массив размерности "+n+":");
        System.out.println("BubbleSort отсортировал за: "+String.format("%,12d",t2Bubble-t1Bubble) + " ns");
        System.out.println("SelectionSort отсортировал за: "+String.format("%,12d",t2Selection-t1Selection) + " ns");
        System.out.println("InsertionSort отсортировал за: "+String.format("%,12d",t2InsertionSort-t1InsertionSort) + " ns");
    }

    //Метод пузырьковой сортировки
    public static int[] BubbleSort(int[] mas, int n) {
        int j;
        boolean flag = true;
        int temp;
        int[] resultMas = (int[])mas.clone();

        while (flag){
            flag = false;
            for (j=0;j<resultMas.length-1;j++){
                if(resultMas[j]<resultMas[j+1]){
                    temp = resultMas[j];
                    resultMas[j]=resultMas[j+1];
                    resultMas[j+1]=temp;
                    flag=true;
                }
            }
        }
        return resultMas;
    }

    //Метод сортировки выборки
    public static int[] SelectionSort(int[] mas, int n) {
        int[] resultMas = (int[])mas.clone();
        for(int i=0; i<resultMas.length;i++){
            int min = resultMas[i];
            int min_i = i;
            for(int j=i+1;j<resultMas.length;j++){
                if(resultMas[j]>min){
                    min = resultMas[j];
                    min_i=j;
                }
            }
            if(i!=resultMas[min_i]){
                int tmp = resultMas[i];
                resultMas[i]=resultMas[min_i];
                resultMas[min_i]=tmp;
            }
        }
        return resultMas;
    }

    //Метод сортировки выборки
    public static int[] InsertionSort(int[] mas, int n) {
        //ваша реализация сортировки вставкой
        int[] resultMas = (int[])mas.clone();
        for (int i=1; i < resultMas.length; i++) {
            int valueToSort = resultMas[i];
            int j = i;
            while (j > 0 && resultMas[j - 1] < valueToSort) {
                resultMas[j] = resultMas[j - 1];
                j--;
            }
            resultMas[j] = valueToSort;
        }
        return  resultMas;
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

}

