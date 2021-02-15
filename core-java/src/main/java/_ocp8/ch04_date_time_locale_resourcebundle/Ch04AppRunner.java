package _ocp8.ch04_date_time_locale_resourcebundle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Properties;
import java.util.function.Supplier;


class TimeDateRunner {
    public static void main(String[] args) {
        LocalDateTime parse = LocalDateTime.parse("2017-08-21 10:19", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(parse);

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime);

        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());

        System.out.println("isLeapYear: " + LocalDate.now().isLeapYear());


        ZonedDateTime zonedPacific = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zonedPacific);

        ZonedDateTime zonedEastern = zonedPacific.withZoneSameInstant(ZoneId.of("US/Eastern"));
        System.out.println(zonedEastern);

        lisZoneIDs();

        diffDuration(zonedPacific, zonedEastern);

        diffPeriod(zonedPacific, zonedEastern);

        //Use of TemporalAdjusters: https://www.baeldung.com/java-temporal-adjuster
        adjustViaTemporalAdjuster(zonedEastern);

        OffsetDateTime offsetDateTime = OffsetDateTime.from(zonedEastern);
        System.out.println(offsetDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

//        ParseUnparsedZonedTime(offsetDateTime);


    }

    public static void adjustViaTemporalAdjuster(ZonedDateTime zonedEastern) {
        ZonedDateTime firstDayInMonth = zonedEastern.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayInMonth.getDayOfWeek());

        ZonedDateTime plus2Days = zonedEastern.with(t -> t.plus(Period.ofDays(2))); // similar to this -> zonedEastern.plusDays(2)
        System.out.println(plus2Days.getDayOfWeek());
    }

    public static void lisZoneIDs() {
        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(z -> z.toLowerCase().startsWith("us/"))
                .forEach(System.out::println);
    }

    public static void diffPeriod(ZonedDateTime zonedDateTimePacific, ZonedDateTime zonedDateTimeEastern) {
        Period period = Period.between(zonedDateTimeEastern.toLocalDate().plusDays(5), zonedDateTimePacific.toLocalDate());
        System.out.println("period: " + period);
    }

    public static void diffDuration(ZonedDateTime zonedDateTimePacific, ZonedDateTime zonedDateTimeEastern) {
        Duration duration = Duration.ofMinutes(ChronoUnit.MINUTES.between(zonedDateTimeEastern.plusDays(5), zonedDateTimePacific));
        System.out.println("duration " + duration);
        Duration duration1 = Duration.between(zonedDateTimeEastern.plusDays(5), zonedDateTimePacific);
        System.out.println("duration1: " + duration1);
    }

    public static void ParseUnparsedZonedTime(OffsetDateTime offsetDateTime) {
        try {
            String substring = offsetDateTime.toString().substring(0, offsetDateTime.toString().lastIndexOf('-'));
            System.out.println(substring);
            System.out.println(ZonedDateTime.parse(substring));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

class PropertiesRunner {

    /*
     * ToDo: This method is just to avoid the boilerplate with the checked exception in the lambda expression
     *      - Make it generic
     */
    public static InputStream createInputStreamFromURI(ThrowingSupplier<URI, URISyntaxException> ts) throws IOException {
        try {
            return Files.newInputStream(Paths.get(ts.get()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        final String propFileName = "PropTest.props";
        final InputStream inputStream1 = createInputStreamFromURI(() -> PropertiesRunner.class.getClassLoader().getResource(propFileName).toURI());
        final InputStream inputStream2 = createInputStreamFromURI(() -> ClassLoader.getSystemResource(propFileName).toURI());


        try (inputStream1; inputStream2) {

            Properties properties = new Properties();
            properties.load(inputStream1);
            properties.forEach((k, v) -> System.out.println(k + " " + v));

            properties.load(inputStream2);
            properties.forEach((k, v) -> System.out.println(k + " " + v));

        }
    }


    /**
     * Adopted from:
     * - https://www.baeldung.com/java-lambda-exceptions
     * - https://dzone.com/articles/how-to-handle-checked-exception-in-lambda-expressi
     */
    interface ThrowingSupplier<T, E extends Throwable> {
        static <T, E extends Throwable> Supplier<T> unchecked(ThrowingSupplier<T, E> consumer) {
            return () -> {
                try {
                    return consumer.get();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            };
        }

        T get() throws E;
    }
}
