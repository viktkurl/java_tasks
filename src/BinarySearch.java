import java.lang.*;

class BST<Integer extends Comparable<Integer>> {
    private Node root; //корень
    private class Node{
        private Integer key; //ключ
        private Node left, right; //ссылки на поддеревья
        private int N; //количество узлов в поддереве с данным корнем
        public Node(Integer key,  int N) {
            this.key = key; this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }


    public void put(Integer key) {
        //Поиск ключа. Если найден, изменяется значение, если нет - увеличивается дерево
        root = put(root, key);
    }

    public Node put(Node x, Integer key) {
        //Если ключ key присутствут в поддереве с корнем х,
        //его значение меняется на val
        //Иначе в поддерево добавляется новый узел с ключом key и значением val
        if (x == null) return new Node(key,1);
        int cmp = key.compareTo(x.key);
        if (cmp<0) x.left = put(x.left, key);
        else if (cmp>0) x.right = put(x.right, key);
        else x.N = size(x.left) + size(x.right) +1;
        return x;
    }


    public Integer min() {
        return min(root).key;
    }

    public Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    //Рекурсивный метод
    public Integer max(){
        return max(root).key;
    }

    //Добавленный метод max()
    public Node max(Node x) {
        if (x.right==null) return x;
        return max(x.right);
    }

    public Integer floor(Integer key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;

    }

    private Node floor(Node x, Integer key) {
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Integer ceiling(Integer key){
        Node x = ceiling(root, key);
        if (x == null) return  null;
        return x.key;
    }

    //Метод возвращающий наибольший ключ из BST, который больше или равен ключу
    private Node ceiling(Node x, Integer key) {
        if (x==null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Integer select (int k) {
        return select(root,k).key;
    }

    private Node select(Node x, int k) {
        //Возвращает узел, содержащий ключ ранга к
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Integer key) {
        return rank(key, root);
    }

    private int rank(Integer key, Node x) {
        //Возвращает количество ключей, меньших x.key
        if ( x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1+ size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) +1;
        return x;
    }

    public void deleteMax() { root = deleteMax(root); }

    //Метод, который удаляет наибольший ключ и связанное с ним значение
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.right)+size(x.left)+1;
        return x;
    }

    public void delete(Integer key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Integer key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp>0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;

        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }

    public void traverseTree() {
        traverseTree(root);
        System.out.println();
    }

    private void traverseTree(Node x) {
        int iter = 0;
        if (x == null)
            return;
                traverseTree(x.left);
                System.out.println("[" + x.key + "]");
                traverseTree(x.right);
    }

    public Iterable<Integer> keys() {
        return keys(min(), max());
    }

    public Iterable<Integer> keys(Integer lo, Integer hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Integer> queue = new Queue<Integer>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Integer> queue, Integer lo, Integer hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int height() {
        return height(root);
    }
    //Метод для вычисления высоты дерева
    private int height(Node x) {
        if (x == null) return -1;
        if (height(x.left)>height(x.right)){
            return 1+height(x.left);
        } else {
            return 1+height(x.right);
        }
    }
}
