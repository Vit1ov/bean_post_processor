package injectRandomString;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        AnnotationCheck check = context.getBean(AnnotationCheck.class);
        check.printValue();
    }
}
