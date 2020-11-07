package _ocp8;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class UtilsTest {

    @Test
    public void getSlidingStream() {
        System.out.println(Utils.getSlidingStream("0123456789".toCharArray(), 3, 2).collect(Collectors.toList()));
        Assertions.assertThat(Utils.getSlidingStream("0123456789".toCharArray(), 3, 2)
                .collect(Collectors.toList()))
                .containsExactlyElementsOf(List.of("012", "234", "456", "678", "89"));
    }
}