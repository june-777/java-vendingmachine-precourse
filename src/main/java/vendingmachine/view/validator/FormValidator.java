package vendingmachine.view.validator;

import java.util.Arrays;
import java.util.List;
import vendingmachine.view.exception.InputException;

public class FormValidator {

    private static final String FORM_REGEX = ";";
    private static final String ELEMENT_FORM_REGEX = ",";

    public static void validate(String input) {
        validateForm(input);

        List<String> elements = Arrays.asList(input.split(FORM_REGEX));
        for (String element : elements) {
            validateElementForm(element);
        }

        for (String element : elements) {
            String productInfo = removeSquareBrackets(element);
            List<String> product = Arrays.asList(productInfo.split(ELEMENT_FORM_REGEX));
            String price = product.get(1);
            String count = product.get(2);
            System.out.println("price = " + price);
            System.out.println("count = " + count);
            NumberValidator.validate(price);
            NumberValidator.validate(count);
        }
    }

    private static String removeSquareBrackets(String element) {
        return element.substring(1, element.length() - 1);
    }

    // [상품명,가격,수량];[상품명,가격,수량];... 폼을 검증한다.
    private static void validateForm(String target) {
        validateBlank(target);
        validateStartWithSeparator(target, FORM_REGEX);
        validateEndWithSeparator(target, FORM_REGEX);
        validateContinuousSeparator(target, FORM_REGEX);
    }

    private static void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            InputException.BLANK.make();
        }
    }

    private static void validateMaxLength(String target, int limitLength) {
        if (target.length() > limitLength) {
            InputException.EXCEED_LENGTH.make();
        }
    }

    private static void validateStartWithSeparator(String target, String separator) {
        if (target.startsWith(separator)) {
            InputException.START_WITH_SEPARATOR.make();
        }
    }

    private static void validateEndWithSeparator(String target, String separator) {
        if (target.endsWith(separator)) {
            InputException.END_WITH_SEPARATOR.make();
        }
    }

    private static void validateContinuousSeparator(String target, String separator) {
        boolean blankExist = Arrays.stream(target.split(separator))
                .anyMatch(String::isBlank);

        if (blankExist) {
            InputException.CONTINUOUS_SEPARATOR.make();
        }
    }

    // [상품명,가격,수량] 폼을 검증한다.
    private static void validateElementForm(String target) {
        System.out.println("target = " + target);
        if(!target.startsWith("[")) {
            throw new IllegalArgumentException("상품 정보는 [ 로 시작해야 합니다.");
        }
        if(!target.endsWith("]")) {
            throw new IllegalArgumentException("상품 정보는 ] 로 끝나야 합니다.");
        }
        String product = removeSquareBrackets(target);
        validateStartWithSeparator(product, ELEMENT_FORM_REGEX);
        validateEndWithSeparator(product, ELEMENT_FORM_REGEX);
        validateContinuousSeparator(product, ELEMENT_FORM_REGEX);
        validateSeparatorCount(product);
    }

    private static void validateSeparatorCount(String target) {
        int count = calculateSeparatorCount(target);

        if(count != 2) {
            InputException.WRONG_COUNT_SEPARATOR.make();
        }
    }

    private static int calculateSeparatorCount(String target) {
        return (int) target.chars()
                .filter(ch -> ch == ',')
                .count();
    }
}