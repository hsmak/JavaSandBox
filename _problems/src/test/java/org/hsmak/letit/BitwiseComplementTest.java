package org.hsmak.letit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BitwiseComplementTest {

    BitwiseComplement bitwiseComplement = new BitwiseComplement();
    @Test
    public void complementN() {
//        assertThat(bitwiseComplement.complementN(5)).isEqualTo(2);
//        assertThat(bitwiseComplement.complementN(7)).isEqualTo(0);
        assertThat(bitwiseComplement.complementN(10)).isEqualTo(5);
    }
}