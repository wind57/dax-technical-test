package org.global.dax.shared;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandParserParseTests {

    @Test
    @DisplayName("'GET one' will pass")
    void testParse1() {
        CacheCommandWithArguments result = CommandParser.parse("GET one");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.command()).isSameAs(CacheCommand.GET_SINGLE);
        Assertions.assertThat(result.arguments()).containsExactly("one");
    }

    @Test
    @DisplayName("'GET one two' will fail")
    void testParse2() {
        CacheCommandWithArguments result = CommandParser.parse("GET one two");
        Assertions.assertThat(result).isNull();
    }

    @Test
    @DisplayName("'GET' will fail")
    void testParse3() {
        CacheCommandWithArguments result = CommandParser.parse("GET");
        Assertions.assertThat(result).isNull();
    }


    @Test
    @DisplayName("'GET ALL' will pass")
    void testParse4() {
        CacheCommandWithArguments result = CommandParser.parse("GET ALL");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.command()).isSameAs(CacheCommand.GET_ALL);
        Assertions.assertThat(result.arguments()).isEmpty();
    }

    @Test
    @DisplayName("'ADD ALL one' will fail, since we treat 'ALL' as a reserved keyword")
    void testParse5() {
        CacheCommandWithArguments result = CommandParser.parse("ADD ALL one");
        Assertions.assertThat(result).isNull();
    }

    @Test
    @DisplayName("'ADD key value' will pass")
    void testParse6() {
        CacheCommandWithArguments result = CommandParser.parse("ADD key value");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.command()).isSameAs(CacheCommand.ADD_SINGLE);
        Assertions.assertThat(result.arguments()).containsExactly("key", "value");
    }

    @Test
    @DisplayName("'ADD key' will fail")
    void testParse7() {
        CacheCommandWithArguments result = CommandParser.parse("ADD key");
        Assertions.assertThat(result).isNull();
    }

    @Test
    @DisplayName("'DELETE' will fail")
    void testParse8() {
        CacheCommandWithArguments result = CommandParser.parse("DELETE");
        Assertions.assertThat(result).isNull();
    }

    @Test
    @DisplayName("'DELETE ALL' will fail")
    void testParse9() {
        CacheCommandWithArguments result = CommandParser.parse("DELETE ALL");
        Assertions.assertThat(result).isNull();
    }

    @Test
    @DisplayName("'DELETE ONE' will pass")
    void testParse10() {
        CacheCommandWithArguments result = CommandParser.parse("DELETE ONE");
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.command()).isSameAs(CacheCommand.DELETE_SINGLE);
        Assertions.assertThat(result.arguments()).containsExactly("ONE");
    }

}
