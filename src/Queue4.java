import java.util.Scanner;
//Класс очередь
class Queue2 {
    private int[] queue;
    private int maxSize; // максимальное количество элементов в очереди
    private int nElem;  // текущее количество элементов в очереди
    private int front;
    private int rear;

    //Конструктор для создания очереди размера максимального размера maxSize
    public Queue2(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        rear = -1;
        front = 0;
        nElem = 0;
    }

    //Метод вставки значения
    public void insert(int elem) {
        if(!isFull()) {
            if (rear == maxSize - 1) {
                rear = -1;
            }

            queue[++rear] = elem;  //увеличение Rear и вставка
            nElem++;  // увеличение количества элементов в очереди
        } else {
            System.out.println("Очередь полная");
        }
    }

    //Метод удаления элемента
    public int[] remove() {
        if(isEmpty()){
            System.out.println("Стек пустой");
            return queue;
        } else {
            queue[0] = 0;
            moveLeft(queue, 1); //Вызвать сдвиг влево на одну позицию
            nElem--;    //Уменьшить количество элементов
            rear--;     //Уменьшение хвоста и первого элемента
            front--;
        } return queue;
    }

    //Метод сдвига очереди на позицию
    public static void moveLeft(int[] array, int positions) {
        int size = array.length;
        for (int i = size; i > positions; i--) {
            int temp = array[size - 1];
            for (int j = size - 1; j > 0; j--) {
                array[j] = array[j - 1];
            }
            array[0] = temp;
        }
    }

    //Метод пирамидальной сортировки
    public void heapSort() {
        for(int i = nElem; i > 1; i--){
            buildMaxHeap(queue, i - 1);
        }
    }

    //Метод построения дерева
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

    //Получение первого элемента
    public int getFront() {return queue[front];}
    //Получение последнего элемента
    public int getRear() {
        return queue[rear];
    }
    //Проверка на переполненность
    public boolean isFull() {
        return (nElem == maxSize);
    }
    //Проверка на пустоту
    public boolean isEmpty() {
        return (nElem == 0);
    }
    //Метод возвращающий размер
    public int getSize() {
        return nElem;
    }
    //Метод возвращающий все элементы в очереди
    public void getElements(){
        for (int i = 0; i<nElem; i++){
            System.out.println(queue[i]);
        }
    }
}

//Класс для вызова метода main и работы с очередью
class MyQueue {
    public static void main(String[] args) {
        int size;//длинна очереди
        int element;//переменная для хранения содержимого добавляемого элемента

        System.out.println("Введите максимальную длину очереди");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();//чтение вводимой строки

        //обработка возможных исключений
        try {
            size = Integer.parseInt(s); //преобразование введенной строки в тип Integer
        } catch (Exception e) {
            System.out.println("Длина очереди должна быть натуральным числом");
            return;
        }

        if (size <= 0) {
            System.out.println("Длина очереди должна быть больше 0!");
            return;
        }

        Queue2 myQueue = new Queue2(size); //Вызов конструктора для создания объекта myQueue
        char ch;//Переменная для обработки действий пользователя
        do{
            //Возможные операции с очередью
            System.out.println("\nОперации с очередью\n");
            System.out.println("1. вставка элемента");
            System.out.println("2. удаление элемента");
            System.out.println("3. сортировка элементов в списке");
            System.out.println("4. получение размера");
            System.out.println("5. вывод элементов");

            //Ввод действия
            int choice = input.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Введите элемент типа integer для вставки");
                    element = input.nextInt();
                    //Обработка возможных исключений
                    try {
                        myQueue.insert(element);
                    } catch (Exception e) {
                        System.out.println("В очередь добавляются только натуральные числа");
                        return;
                    }
                    if (element <= 0) {
                        System.out.println("Число должно быть больше 0");
                        return;
                    }
                    break;
                case 2 :
                    System.out.println("Удаление элемента");
                    myQueue.remove();
                    break;
                case 3 :
                    System.out.println("Сортировка элементов очереди");
                    myQueue.heapSort();
                    break;
                case 4 :
                    System.out.println("Колличество элементов = "+ myQueue.getSize());
                    break;
                case 5 :
                    System.out.println("Элементы: ");
                    myQueue.getElements();
                    break;
                default :
                    System.out.println("Неверный ввод \n ");
                    break;
            }
            System.out.println("\nХотите продолжить (Выберите y - Да или N - нет) \n");
            ch = input.next().charAt(0);
        } while (ch=='Y'||ch=='y');
    }

}