package people.spheres.demo2.services.builder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import people.spheres.demo2.util.Messages;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionBuilderTest {
    private ExpressionBuilder builder;

    @BeforeEach
    void setUp() {
        String[] inputs = {"example", "test", "hello"};
        builder = new ExpressionBuilder(inputs);
    }

    @Test
    void testGetFirstLetter() {
        builder.executeExpression("getFirstLetter(input1)");
        assertEquals("e", builder.toString());
    }

    @Test
    void testGetLastLetter() {
        builder.executeExpression("getLastLetter(input2)");
        assertEquals("t", builder.toString());
    }

    @Test
    void testGetWholeWord() {
        builder.executeExpression("getWholeWord(input3)");
        assertEquals("hello", builder.toString());
    }

    @Test
    void testPutConstant() {
        builder.executeExpression("putConstant(@)");
        assertEquals("@", builder.toString());
    }

    @Test
    void testIgnoreInvalidOperation() {
        builder.executeExpression("getFirstLetter(input1).invalidOperation().getWholeWord(input2)");
        assertEquals("etest", builder.toString());
    }

    @Test
    void testCompleteExpression() {
        builder.executeExpression("getFirstLetter(input1).getWholeWord(input2).putConstant(@).getWholeWord(input3)");
        assertEquals("etest@hello", builder.toString());
    }

    @Test
    void testWithEmptyInput() {
        ExpressionBuilder emptyBuilder = new ExpressionBuilder(new String[]{""});
        emptyBuilder.executeExpression("getFirstLetter(input1)");
        assertEquals("", emptyBuilder.toString());
    }

    @Test
    void testWithNullInput() {
        ExpressionBuilder nullBuilder = new ExpressionBuilder(new String[]{null});
        nullBuilder.executeExpression("getFirstLetter(input1)");
        assertEquals("", nullBuilder.toString());
    }

    @Test
    void testGetFirstLetterWithOutOfBoundsInput() {
        builder.executeExpression("getFirstLetter(input4)"); // Assuming there is no input4
        String expected = String.format(Messages.MISSING_INPUT, "input4");
        assertEquals(expected, builder.toString());
    }

    @Test
    void testFilterRemovesProhibitedCharacters() {
        builder.executeExpression("putConstant('do#you#see#that?!').filter()");
        assertEquals("doyouseethat", builder.toString());
    }

}