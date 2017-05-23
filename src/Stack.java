import java.util.Scanner;
//класс стек
public class Stack {
    private int mSize;     //максимальное количество элементов в стеке
    private int[] stackArr;//создание массива
    private int top;       //вершина стека

    //Конструктор для создания стека максимальной размерности l
    Stack(int l){
        this.mSize = l;
        stackArr = new int[l];
        top = -1;
    }

    //Метод поместить элемент (он же push метод)
    public void setElement(int newElement){
        if(!isFull()){
            stackArr[++top]=newElement;
        }else {
            System.out.println("Стек полный!!! Больше элементов не добавить!!!");
        }
    }

    //Метод убрать элемент (он же pop метод)
    public int delElement(){
        if (isEmpty()) {
            System.out.println("Стек пустой!!!");
            return 0;
        }else {
            return stackArr[top--];
        }
    }
    //Метод возвращающий колличество элементов в стеке
    public int getLenght(){
        int count = 1;
        return count = count+top;
    }

    //Метод возвращающий все элементы в стеке
    public void getElements(){
        for (int i = 0; i<top+1; i++){
            System.out.println(stackArr[i]);
        }
    }

    //Метод возвращающий элемент на вершине стека
    public int readTop(){
        return stackArr[top];
    }

    //Метод проверки (пустой ли стек)
    public boolean isEmpty(){
        return (top == -1);
    }

    //Метод проверки (заполнен ли стек)
    public boolean isFull(){
        return (top == mSize-1);
    }

}
//Класс для вызова метода main
class Work{

    public static void main(String[] args) {

        int size;                                  //длина стека
        int element;                               //переменная для нового элемента
        System.out.println("Введите длину стека");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();               //чтение вводимой строки

        //обработка возможных исключений
        try {
            size = Integer.parseInt(s); //преобразование введенной строки в тип Integer
        } catch (Exception e) {
            System.out.println("Длина стека должна быть натуральным числом");
            return;
        }

        if (size <= 0) {
            System.out.println("Длина стека должна быть больше 0!");
            return;
        }
        Stack stack = new Stack(size); //Вызов конструктора для создания стека размерности size
        char ch;//Переменная для обработки действий пользователя
        do {
            System.out.println("\nОперации со стеком\n");
            System.out.println("1. Вставить новый элемент");
            System.out.println("2. Удалить элемент");
            System.out.println("3. Вернуть количество элементов в стеке");
            System.out.println("4. Вернуть содержимое стека");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите элемент");
                    element = input.nextInt();
                    stack.setElement(element);
                    break;
                case 2:
                    System.out.println("Удаление элемента в стеке");
                    stack.delElement();
                    break;
                case 3:
                System.out.println("Колличество элементов стека: " + stack.getLenght());
                break;
                case 4:
                System.out.print("Элементы: \n");
                stack.getElements();
                break;
            }
            System.out.println("\nПродолжить операции? (y - Да, n - Нет) \n");
            ch = input.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}
