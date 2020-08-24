package _ocp8.ch03_assert_exception;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

class DeclareOrHandleRunner {
    class OneException extends Exception {
    }

    class AnotherException extends Exception {
    }

    void doM() throws DeclareOrHandleRunner.OneException, DeclareOrHandleRunner.AnotherException {
        throw new DeclareOrHandleRunner.AnotherException();
    }

    public static void main(String[] args) throws DeclareOrHandleRunner.AnotherException {

        DeclareOrHandleRunner declareOrHandle = new DeclareOrHandleRunner();
        try {
            declareOrHandle.doM();
        } catch (DeclareOrHandleRunner.OneException e) {

        }
    }

}

class SuppressedExRunner {
    class Lamb implements Closeable {

        @Override
        public void close() throws IOException {
            System.out.println("close - in");
            throw new IOException("close - exception");
        }
    }

    public void rethrowEx() throws IOException {
        try (SuppressedExRunner.Lamb l = new SuppressedExRunner.Lamb()) {
            throw new IOException("try - rethrowEx");
        } catch (Exception e) {
            for (Throwable s : e.getSuppressed()) {
                System.out.println("Manually printing suppressed: " + s);
            }
//            throw new RuntimeException("catch");
            throw e; // Rethrowing the same exception causes the suppress to be part of the stack trace
        }
    }

    public void throwNewEx() throws IOException {
        try (SuppressedExRunner.Lamb l = new SuppressedExRunner.Lamb()) {
            throw new IOException("try - throwNewEx");
        } catch (Exception e) {
            for (Throwable s : e.getSuppressed()) {
                System.out.println("Manually printing suppressed: " + s);
            }
            throw new RuntimeException("catch - throwNewEx"); // The new Exception takes over
//            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                new SuppressedExRunner().rethrowEx();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                new SuppressedExRunner().throwNewEx();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class ThrowingExRunner {
    public static void main(String[] args) {

        try {
            throwEx();
        } catch (IOException | SQLException e) {

        } catch (Exception e) {
            throw e;
        }
    }

    static void throwEx() throws IOException, SQLException {
        throw new SQLException();
    }
}
