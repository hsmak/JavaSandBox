package exercise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by hsmak on 2/23/16.
 */
public class MainApp {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1) Find all transactions in 2011 and sort by value (small to high)
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println();

        //2) What are all the unique cities where the traders work?
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        //3) Find all traders from Cambridge and sort them by name
        transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        System.out.println();

        //4) Return a string of all traders’ names sorted alphabetically
        String names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(names);
        //Alternatively, an efficient way
        String names_efficient = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(names_efficient);
        System.out.println();

        //5) Are any traders based in Milan?
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milan);

        //6) Print all transactions’ values from the traders living in Cambridge
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Combridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println();

        //7) What’s the highest value of all the transactions?
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(max.get());
        System.out.println();

        //8) Find the transaction with the smallest value
        Optional<Transaction> minTrans = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(minTrans.get());
        //alternatively
        Optional<Transaction> minTrans_v2 =
                transactions.stream()
                        .min(Comparator.comparing(Transaction::getValue));
        System.out.println(minTrans_v2.get());
        System.out.println();


        //Pythagorian Triples
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
//                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)// avoid sqrt twice
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2]%1 == 0)
                        );
        pythagoreanTriples.forEach((trip) -> System.out.println("{" + trip[0] + "," + trip[1] + "," + trip[2] + "}"));

    }


}
