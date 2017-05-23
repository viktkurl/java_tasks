import java.util.Random;
import java.util.Scanner;

public class TestBST {
    public static void main(String[] args) {
        //Создание объекта класса BST
        BST<Integer> OBst = new BST<Integer>();

        int N;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the numbers of tree items:");
        N=sc.nextInt();
        Random rn = new Random();

        for (int i=0; i<N;i++){
            OBst.put(rn.nextInt(1000000));
        }

        long t1InOrd = System.nanoTime();
        OBst.traverseTree();
        long t2InOrd = System.nanoTime();

        System.out.println("Высота дерева: "+OBst.height());
        System.out.println("Наименьший ключ: "+OBst.min());
        System.out.println("Максимальный ключ: "+OBst.max());

        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Дерево размерности "+N+":");
        System.out.println("InOrder обошел за: "+String.format("%,12d",t2InOrd-t1InOrd) + " ns");

    }
}
