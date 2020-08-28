package org.hsmak.letit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hsmak.letit.ReverseWordsInString.StrategyE;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ReverseWordsInStringTest {

    ReverseWordsInString reverseWordsInString;

    public ReverseWordsInStringTest(StrategyE e) {
        this.reverseWordsInString = new ReverseWordsInString(e);
    }

    @Parameters(name = "Strategy -> {0}")
    public static EnumSet<StrategyE> getStrategyEnum() {
        return EnumSet.allOf(StrategyE.class);
    }

    @Test
    public void reverseWords() {
        assertThat(reverseWordsInString.reverseWords("Let's take LetItCode contest")).isEqualTo("s'teL ekat edoCtIteL tsetnoc");
    }

    @Test
    public void reverseWordsWithMultipleWhiteSpaces() {
        assertThat(reverseWordsInString.reverseWords("Let's take   LetItCode       contest")).isEqualTo("s'teL ekat   edoCtIteL       tsetnoc");
    }

    @Test
    public void reverseWordsWithMultipleWhiteSpacesAndTrailingSpaces() {
        assertThat(reverseWordsInString.reverseWords(" \t\t Let's take   LetItCode       contest ")).isEqualTo(" \t\t s'teL ekat   edoCtIteL       tsetnoc ");
    }
}