import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;//первоначальная размерность

    private int n;        // число пар ключ-значение
    private int m;        // число цепочек
    private Node[] st;    // массив символьных таблиц

    //Класс для создания узла
    private static class Node {
        private final Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    //Инициализация пустой таблицы символов
    public HashST() {
        this(INIT_CAPACITY);
    }

    //Конструктор класса HashST
    public HashST(int m) {
        this.m = m;
        st = new Node[m];
    }

    // изменяем размер хэш-таблицы, чтобы иметь заданное количество цепей
    // переназначаем все ключи
    private void resize(int chains) {
        HashST<Key, Value> temp = new HashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                temp.put((Key) x.key, (Value) x.val);
            }
        }

        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }

    //Хеш-значение между 0 и m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    //Возвращение количества пар ключ-значение в символьной таблице
    public int size() {
        return n;
    }

    //Метод возвращает true, если эта таблица символов пуста
    public boolean isEmpty() {
        return size() == 0;
    }

    //Метод возвращающий true, если таблица символов содержит указанный ключ
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("аргумент в contains() это null");
        return get(key) != null;
    }

    //метод возвращает значение, связанное с указанным ключом в этой таблице символов
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("аргумент в get() это null");
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }

    //метод помещения в Hash таблицу
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("первый аргумент в put() это null");
        if (val == null) {
            remove(key);
            return;
        }

        // увеличение размера таблицы в 2 раза, если длина списка> = 10
        if (n >= 10*m) resize(2*m);


        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        n++;
        st[i] = new Node(key, val, st[i]);
    }

    //Метод удаляет указанный ключ и связанное с ним значение из таблицы символов
    public void remove(Key key) {
        if (key == null) throw new IllegalArgumentException("аргумент в remove() это null");

        int i = hash(key);
        st[i] = remove(st[i], key);

        // Метод уменьшение размера таблицы наполовину, если длина списка <= 2
        if (m > INIT_CAPACITY && n <= 2*m) resize(m/2);
    }

    // Метод удалить ключ в связанном списке, начиная с узла X
    private Node remove(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = remove(x.next, key);
        return x;
    }

    //Вернуть все ключи в символьной таблице
    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                queue.enqueue((Key) x.key);
            }
        }
        return queue;
    }


    //Метод main
    public static void main(String[] args) throws IOException{
        String filePath = "C:\\Users\\Viktor\\IdeaProjects\\labs\\src\\words.txt";
        //Создание объекта HashST
        HashST<String, String> st = new HashST<String, String>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split("-", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                st.put(key, value);
            } else {
                System.out.println("Строка в неправильном формате: " + line);
            }
        }

        /*
        *Вывод всех слов в словаре
         */
        /*
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
        reader.close();
        */

        char ch;//Переменная для обработки действий пользователя
        do {
            System.out.println("Введите слово:");
            Scanner input = new Scanner(System.in);
            String s = input.nextLine();//чтение вводимой строки
            String result = "Такого слова нет в словаре";
            if (st.contains(s)) {
                result = s + "-" + st.get(s);
            }
            System.out.println(result);
            System.out.println("\nПродолжить переводить? (y - продолжить)");
            ch = input.next().charAt(0);
        } while (ch == 'Y'|| ch == 'y');
    }
}