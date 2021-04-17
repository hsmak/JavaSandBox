package org.hsmak.letit;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(Parameterized.class)
public class UniqueOccurrencesTest {

    UniqueOccurrences uniqueOccurrences;
//    @Parameterized.Parameters(name = "Strategy -> {0}")
//    public static EnumSet<TwoSum.StrategyE> getStrategies(){
//        return EnumSet.allOf(TwoSum.StrategyE.class);
//    }


    public UniqueOccurrencesTest() {
        this.uniqueOccurrences = new UniqueOccurrences();
    }

    @Test
    public void testUniqueOccurrences() {

        assertThat(uniqueOccurrences.uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3})).isTrue();
        assertThat(uniqueOccurrences.uniqueOccurrences(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0})).isTrue();

    }
}

