package people.spheres.demo2.constants;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Operations {
    GET_FIRST_LETTER("getFirstLetter"),
    GET_LAST_LETTER("getLastLetter"),
    GET_WHOLE_WORD("getWholeWord"),
    PUT_CONSTANT("putConstant"),
    FILTER("filter");

    private final String name;

    Operations(String operationName) {
        this.name = operationName;
    }

    public static String constructRegexBasedOnValues() {
        String joinedNames = Arrays.stream(Operations.values())
                .map(Operations::getName)
                .collect(Collectors.joining("|"));

        return String.format("(%s)\\(([^)]*)\\)", joinedNames);
    }

    public static Operations fromString(String text) {
        for (Operations op : Operations.values()) {
            if (op.name.equalsIgnoreCase(text)) {
                return op;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

    public String getName() {
        return name;
    }

}