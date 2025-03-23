package org.global.dax.shared;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandParserSanitizeTests {

    @Test
    void testSanitizeNull() {
        String result = CommandParser.sanitize(null);
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    void testSanitizeEmpty() {
        String result = CommandParser.sanitize("");
        Assertions.assertThat(result).isEqualTo("");
    }

    @Test
    void testSanitizeSimple() {
        String result = CommandParser.sanitize("GET");
        Assertions.assertThat(result).isEqualTo("GET");
    }

    @Test
    void testSanitizeSimpleWithSpaces() {
        String result = CommandParser.sanitize(" GET  ");
        Assertions.assertThat(result).isEqualTo("GET");
    }

    @Test
    void testSanitizeMoreComplexWithSpaces() {
        String result = CommandParser.sanitize(" GET ALL ");
        Assertions.assertThat(result).isEqualTo("GET ALL");
    }

    @Test
    void testSanitizeSpaceBetweenWords() {
        String result = CommandParser.sanitize(" GET     ALL ");
        Assertions.assertThat(result).isEqualTo("GET ALL");
    }

}
