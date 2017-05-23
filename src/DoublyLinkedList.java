import java.util.Scanner;

//Класс узел (или звено) списка
class Node
{
    protected int data; //Переменная для хранения данных
    protected Node next, prev; //Ссылки на следующий и предыдущий элемент

    /*
    //Конструктор пустого списка
    public Node()
    {
        next = null;
        prev = null;
        data = 0;
    }
    */
    //Конструктор
    public Node(int d, Node n, Node p)
    {
        data = d;
        next = n;
        prev = p;
    }
    //Функция размещения ссылки на следующий элемент
    public void setLinkNext(Node n)
    {
        next = n;
    }
    //Функция размещения ссылки на предыдущий элемент
    public void setLinkPrev(Node p)
    {
        prev = p;
    }
    //Функция получения ссылки на следующий элемент
    public Node getLinkNext()
    {
        return next;
    }
    //Функция получения ссылки на предыдущий элемент
    public Node getLinkPrev()
    {
        return prev;
    }
    //Функция поместить значение в узел
    public void setData(int d)
    {
        data = d;
    }
    //Функция получить значение из узла
    public int getData()
    {
        return data;
    }
}

//Класс списка
class MyLinkedList
{
    protected Node start;
    protected Node end ;
    public int size;

    //пустой конструктор
    public MyLinkedList()
    {
        start = null;
        end = null;
        size = 0;
    }
    //Функция проверки списка на пустоту
    public boolean isEmpty()
    {
        return start == null;
    }
    //Функция получения размера листа
    public int getSize()
    {
        return size;
    }
    //Функция вставки элемента в начало
    public void insertAtStart(int val)
    {
        Node nptr = new Node(val, null, null);
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;
        }
        size++;
    }
    //Функция вставки элемента в конец
    public void insertAtEnd(int val)
    {
        Node nptr = new Node(val, null, null);
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            end = nptr;
        }
        size++;
    }
    //Функция вставки элемента в позицию
    public void insertAtPos(int val , int pos)
    {
        Node nptr = new Node(val, null, null);
        if (pos == 1)
        {
            insertAtStart(val);
            return;
        }
        Node ptr = start;
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();
        }
        size++ ;
    }
    //Функция удаления узла в позиции
    public void deleteAtPos(int pos)
    {
        if (pos == 1)
        {
            if (size == 1)
            {
                start = null;
                end = null;
                size = 0;
                return;
            }
            start = start.getLinkNext();
            start.setLinkPrev(null);
            size--;
            return ;
        }
        if (pos == size)
        {
            end = end.getLinkPrev();
            end.setLinkNext(null);
            size-- ;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();

                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }
    //Функция поиска по ключу
    public int search(int key){
        int count = 0;
        System.out.print("Под идентификатором находится элемент со значением ");
        if (size == 0)
        {
            System.out.print("лист пустой, элемента с таким идентификатором нет\n");
            return 0;
        }
        Node ptr = start;
        int i = 1;
        while (ptr.getLinkNext()!=null) {
            if (i == key) {
                count = ptr.getData();
                break;
            } else {
                ptr=ptr.getLinkNext();
                i++;
            }
        }
        if (key == size){
            count = ptr.getData();

        }
        return count;
    }

    //Функция вывода значений в списке
    public void display()
    {
        System.out.print("\nДвухсвязный список содержит = ");
        if (size == 0)
        {
            System.out.print("пусто\n");
            return;
        }
        if (start.getLinkNext() == null)
        {
            System.out.println(start.getData() );
            return;
        }
        Node ptr = start;
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != null)
        {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ "\n");
    }
}

//Класс для вызова методов из main
public class DoublyLinkedList
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);

        //Создание объекта класса linkedList
        MyLinkedList list = new MyLinkedList();
        System.out.println("Программа двусвязный список\n");
        char ch;//Переменная для обработки действий пользователя
        do
        {
            //Возможные операции с листом
            System.out.println("\nОперации с двухсвязным списком\n");
            System.out.println("1. вставка в начало");
            System.out.println("2. вставка в конец");
            System.out.println("3. вставка в позицию");
            System.out.println("4. удаление в позиции");
            System.out.println("5. проверка на пустоту");
            System.out.println("6. получение количества элементов");
            System.out.println("7. поиск по идентификатору");

            int choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    System.out.println("Введите элемент типа integer для вставки");
                    list.insertAtStart( scan.nextInt() );
                    break;
                case 2 :
                    System.out.println("Введите элемент типа integer для вставки");
                    list.insertAtEnd( scan.nextInt() );
                    break;
                case 3 :
                    System.out.println("Введите элемент типа integer для вставки");
                    int num = scan.nextInt() ;
                    System.out.println("Введите позицию");
                    int pos = scan.nextInt() ;
                    if (pos < 1 || pos > list.getSize() )
                        System.out.println("Неверная позиция\n");
                    else
                        list.insertAtPos(num, pos);
                    break;
                case 4 :
                    System.out.println("Введите позицию");
                    int p = scan.nextInt() ;
                    if (p < 1 || p > list.getSize() )
                        System.out.println("Неверная позиция\n");
                    else
                        list.deleteAtPos(p);
                    break;
                case 5 :
                    System.out.println("Проверка на пустоту = "+ list.isEmpty());
                    break;
                case 6 :
                    System.out.println("Размер = "+ list.getSize() +" \n");
                    break;
                case 7 :
                    System.out.println("Необходимо найти элемент по идентификатору: \n ");
                    int find = scan.nextInt();
                    System.out.println("Поиск выдал значение: "+ list.search(find));
                default :
                    System.out.println("Неверный ввод \n ");
                    break;
            }    
            //Отображение листа при каждой операции с ним
            list.display();
            System.out.println("\nХотите продолжить (Выберите y - Да или N - нет) \n");
            ch = scan.next().charAt(0);

        } while (ch == 'Y'|| ch == 'y');
    }
}