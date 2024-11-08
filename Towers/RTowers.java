import java.util.LinkedList;
import java.util.Stack;

public class RTowers {
    static Stack<Integer> A = new Stack<>();
    static Stack<Integer> B = new Stack<>();
    static Stack<Integer> C = new Stack<>();
    private static void move(int n, Stack<Integer> source, Stack<Integer> dest, Stack<Integer> aux) {
        if(n > 0){

            move(n-1, source, aux, dest);
            dest.push(source.pop());
            System.out.println(A);
            System.out.println(B);
            System.out.println(C);
            System.out.println("--------");
            move(n-1, aux, dest, source);

        }

    }

    public static void main(String[] args) {
        int n = 5;
        for(int i = n; i >= 1; i--) {
            A.push(i);
        }

        move(n, A, C, B);


    }


}