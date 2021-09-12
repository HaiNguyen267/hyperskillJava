import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int s = sc.nextInt();

        Random random = new Random(s);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                String state = random.nextBoolean() == true ? "O" : " ";
                System.out.print(state);

            }
            System.out.println();
        }
    }
}
