package org.global.dax.shared;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandParser {

    // 'GET' followed by a whitespace, not followed by 'ALL'
    private static final Pattern GET_SINGLE_PATTERN = Pattern.compile("^GET\\s(?!ALL)(\\w+)$");

    // 'GET ALL' and nothing else
    private static final Pattern GET_ALL_PATTERN = Pattern.compile("^GET\\sALL$");

    // 'ADD' not followed by 'ALL', followed by two words
    private static final String ADD_PATTERN = "^ADD\\s(?!ALL)(\\w+)\\s(\\w+)$";

    // 'DELETE' not followed by 'ALL', followed by a word
    private static final String DELETE_PATTERN = "^DELETE\\s(?!ALL)(\\w+)$";

    private CommandParser() {

    }

    /**
     * some basic clean-up and move everything to upper-case.
     * our cache is going to be case-insensitive.
     */
    public static String sanitize(String command) {
        if (command == null) {
            return "";
        }
        command = command.trim();
        // 'GET   ALL' becomes 'GET ALL'
        command = command.replaceAll("\\s+", " ");
        command = command.toUpperCase();

        return command;
    }

    // must be invoked after sanitize
    public static CacheCommandWithArguments parse(String command) {

        Matcher getSingleMatcher = GET_SINGLE_PATTERN.matcher(command);
        if (getSingleMatcher.matches()) {
            return new CacheCommandWithArguments(CacheCommand.GET_SINGLE, List.of(getSingleMatcher.group(1)));
        }

        Matcher getAllMatcher = GET_ALL_PATTERN.matcher(command);
        if (getAllMatcher.matches()) {
            return new CacheCommandWithArguments(CacheCommand.GET_ALL, List.of());
        }

        Matcher addMatcher = Pattern.compile(ADD_PATTERN).matcher(command);
        if (addMatcher.matches()) {
            return new CacheCommandWithArguments(CacheCommand.ADD_SINGLE, List.of(addMatcher.group(1), addMatcher.group(2)));
        }

        Matcher deleteMatcher = Pattern.compile(DELETE_PATTERN).matcher(command);
        if (deleteMatcher.matches()) {
            return new CacheCommandWithArguments(CacheCommand.DELETE_SINGLE, List.of(deleteMatcher.group(1)));
        }

        return null;
    }

}
