import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        NAMES.generate();
        final int START_AGE = 15;
        final int END_AGE   = 28;


        Stream<People> gen = Stream.generate(People::new);

        gen.limit(10000)
                .filter((p) -> p.getAge() >= START_AGE)
                .filter((p) -> p.getAge() <= END_AGE)
                .forEach(System.out::println);

    }

}
