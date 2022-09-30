package injectRandomString;

import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class AnnotationCheck {

    private final List<String> values = Arrays.asList("qwe", "ert", "rty", "tyu", "yui", "Hello, world!");

    @InjectRandomString(listName = "values")
    private String currentValue;

    public void printValue() {
        System.out.println(currentValue);
    }
}
