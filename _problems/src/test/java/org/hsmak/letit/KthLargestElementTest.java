package org.hsmak.letit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KthLargestElementTest {

    KthLargestElement kthLargestElement = new KthLargestElement();

    @Test
    public void testKthElement() {
        assertThat(kthLargestElement.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2)).isEqualTo(5);
        assertThat(kthLargestElement.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4)).isEqualTo(4);
    }
}