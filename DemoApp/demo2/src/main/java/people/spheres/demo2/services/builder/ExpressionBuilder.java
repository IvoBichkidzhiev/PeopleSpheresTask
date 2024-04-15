package people.spheres.demo2.services.builder;

import people.spheres.demo2.constants.Operations;
import people.spheres.demo2.util.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionBuilder {
    private static final Pattern EXPRESSION_PATTERN =
            Pattern.compile(Operations.constructRegexBasedOnValues(), Pattern.CASE_INSENSITIVE);

    private static final String PROHIBITED_CHARS_REGEX = "[^a-zA-Z0-9@._+-]";

    private final StringBuilder result = new StringBuilder();
    private final String[] inputs;

    public ExpressionBuilder(String[] inputs) {
        this.inputs = inputs;
    }

    public ExpressionBuilder executeExpression(String expression) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(expression);

        while (matcher.find()) {
            String operation = matcher.group(1);
            String inputWord = matcher.group(2);

            try {
                Operations operationEnum = Operations.fromString(operation);
                switch (operationEnum) {
                    case GET_FIRST_LETTER -> getFirstLetter(inputWord);
                    case GET_LAST_LETTER -> getLastLetter(inputWord);
                    case GET_WHOLE_WORD -> getWholeWord(inputWord);
                    case PUT_CONSTANT -> putConstant(inputWord.replaceAll("\"", ""));
                    case FILTER -> filter();
                    default -> throw new IllegalArgumentException("Unsupported operation: " + operation);
                }
            } catch (IllegalArgumentException e) { // That way we can continue with the creation of other emails.
                clearSb();
                result.append(e.getMessage());
                break;
            }
        }

        return this;
    }

    private void clearSb() {
        result.setLength(0);
    }

    private void getFirstLetter(String input) {
        if (input.contains("\"") || input.contains("'")) {
            throw new IllegalArgumentException(String.format(Messages.FOUND_FORBIDDEN_SYMBOLS, input));
        }
        int index = getIndex(input);
        if (!isInBounds(index)) {
            throw new IllegalArgumentException(String.format(Messages.MISSING_INPUT, input));
        }

        if (isNotNullOrEmpty(inputs[index])) {
            result.append(inputs[index].charAt(0));
        }
    }

    private void getLastLetter(String input) {
        if (input.contains("\"") || input.contains("'")) {
            throw new IllegalArgumentException(String.format(Messages.FOUND_FORBIDDEN_SYMBOLS, input));
        }
        int index = getIndex(input);
        if (!isInBounds(index)) {
            throw new IllegalArgumentException(String.format(Messages.MISSING_INPUT, input));
        }

        if (isNotNullOrEmpty(inputs[index])) {
            result.append(inputs[index].charAt(inputs[index].length() - 1));
        }
    }

    private void getWholeWord(String input) {
        if (input.contains("\"") || input.contains("'")) {
            throw new IllegalArgumentException(String.format(Messages.FOUND_FORBIDDEN_SYMBOLS, input));
        }
        int index = getIndex(input);
        if (!isInBounds(index)) {
            throw new IllegalArgumentException(String.format(Messages.MISSING_INPUT, input));
        }

        if (isNotNullOrEmpty(inputs[index])) {
            result.append(inputs[index]);
        }
    }

    private void putConstant(String constant) {
        result.append(constant);
    }

    private void filter() {
        String filteredResult = result.toString().replaceAll(PROHIBITED_CHARS_REGEX, "");
        clearSb();
        result.append(filteredResult);
    }

    private int getIndex(String input) {
        try {
           return Integer.parseInt(input.substring("input".length())) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isNotNullOrEmpty(String input) {
        return input != null && !input.isEmpty();
    }

    private boolean isInBounds(int index) {
        return index >= 0 && index < inputs.length;
    }

    @Override
    public String toString() {
        return result.toString();
    }

}