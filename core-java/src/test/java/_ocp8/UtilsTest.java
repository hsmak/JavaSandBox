package _ocp8;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {

    @Test
    public void getSlidingStream() {
        System.out.println(Utils.getSlidingStream("0123456789".toCharArray(), 3, 2).collect(Collectors.toList()));

        assertThat(Utils.getSlidingStream("0123456789".toCharArray(), 3, 2)
                .collect(Collectors.toList()))
                .containsExactlyElementsOf(List.of("012", "234", "456", "678", "89"));

        assertThat(Utils.getSlidingStream("0123456789".toCharArray(), 1, 1)
                .collect(Collectors.toList()))
                .containsExactlyElementsOf(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

        assertThat(Utils.getSlidingStream("0123456789".toCharArray(), 2, 2)
                .collect(Collectors.toList()))
                .containsExactlyElementsOf(List.of("01", "23", "45", "67", "89"));

        assertThat(Utils.getSlidingStream("0123456789".toCharArray(), 4, 2)
                .collect(Collectors.toList()))
                .containsExactlyElementsOf(List.of("0123", "2345", "4567", "6789", "89"));
    }
}