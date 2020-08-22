package concurrency.others;

public class ThreadInstantiation {

    public static void main(String[] args) {
        viaAnonymousClass();
        viaLambdaExpression();
        viaLambdaBlock();
    }

    private static void viaAnonymousClass() {
        new Thread() {
            public void run() {
                System.out.println("wwww");
            }
        }.start();
    }

    private static void viaLambdaExpression() {
        new Thread(() -> System.out.println("wwww")).start();
    }

    private static void viaLambdaBlock() {
        new Thread(
                () -> {
                    System.out.println("wwww");// Notice the semicolon ';'
                    return;//unnecessary
                }).start();
    }
}
