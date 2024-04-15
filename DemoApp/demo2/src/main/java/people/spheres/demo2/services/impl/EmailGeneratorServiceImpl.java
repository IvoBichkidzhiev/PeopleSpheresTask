package people.spheres.demo2.services.impl;

import org.springframework.stereotype.Service;
import people.spheres.demo2.constants.Constants;
import people.spheres.demo2.dto.EmailDTO;
import people.spheres.demo2.dto.IdValueDTO;
import people.spheres.demo2.services.EmailGeneratorService;
import people.spheres.demo2.services.builder.ExpressionBuilder;
import people.spheres.demo2.util.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmailGeneratorServiceImpl implements EmailGeneratorService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{1,20}\\.[a-zA-Z]{2,6}$");

    @Override
    public EmailDTO generateEmail(String[] allParams, String expression) {
        if (!expressionIsValid(expression)) {
            throw new IllegalArgumentException(Messages.THE_EXPRESSION_MUST_START_WITH_ADD_EMAIL);
        }

        List<IdValueDTO> generatedEmails = new ArrayList<>();

        String[] expressions = expression.substring(Constants.ADD_EMAIL_COMMAND.length())
                .split(String.format("(?i)%s", Constants.ADD_EMAIL_COMMAND));

        for (int i = 0; i < expressions.length; i++) {
            String generatedEmail = new ExpressionBuilder(allParams).executeExpression(expressions[i]).toString();

            if (!emailIsValid(generatedEmail)) {
                generatedEmail = String.format(Messages.INVALID_EXPRESSION, i + 1, generatedEmail);
            }

            generatedEmails.add(new IdValueDTO(generatedEmail, generatedEmail));
        }

        return new EmailDTO(generatedEmails);
    }

    private boolean emailIsValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean expressionIsValid(String expression) {
        return expression.toLowerCase().startsWith(Constants.ADD_EMAIL_COMMAND);
    }

}
