package injectRandomString;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;


public class InjectRandomStringAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();

        for (Field field: fields) {
            InjectRandomString annotation = field.getAnnotation(InjectRandomString.class);
            List<String> list = null;

            if (annotation != null) {
                try {
                    Field fieldWithList = bean.getClass().getDeclaredField(annotation.listName());
                    fieldWithList.setAccessible(true);
                    list = (List<String>) fieldWithList.get(bean);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                Random random = new Random();
                int i = random.nextInt(list.size());
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, list.get(i));
            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
