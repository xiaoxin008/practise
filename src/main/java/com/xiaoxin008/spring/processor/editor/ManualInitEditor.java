package com.xiaoxin008.spring.processor.editor;

import com.xiaoxin008.spring.bean.common.DateFoo;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 手动装配转换器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ManualInitEditor {

    public static void main(String[] args) {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("application-context.xml"));
        DatePropertyEditor datePropertyEditor = new DatePropertyEditor();
        datePropertyEditor.setDatePattern("yyyy/MM/dd");
        DatePropertyEditorRegister register = new DatePropertyEditorRegister();
        register.setPropertyEditor(datePropertyEditor);
        beanFactory.addPropertyEditorRegistrar(register);
        DateFoo dateFoo = (DateFoo)beanFactory.getBean("dateFoo");
        System.out.println(dateFoo);
    }

    static class DatePropertyEditor extends PropertyEditorSupport {

        private String datePattern;

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern(getDatePattern());
            Date result = null;
            try {
                result = simpleDateFormat.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            super.setValue(result);
        }

        public String getDatePattern() {
            return datePattern;
        }

        public void setDatePattern(String datePattern) {
            this.datePattern = datePattern;
        }
    }

    static class DatePropertyEditorRegister implements PropertyEditorRegistrar{

        private PropertyEditor propertyEditor;

        @Override
        public void registerCustomEditors(PropertyEditorRegistry pRegistry) {
            pRegistry.registerCustomEditor(Date.class,getPropertyEditor());
        }

        public PropertyEditor getPropertyEditor() {
            return propertyEditor;
        }

        public void setPropertyEditor(PropertyEditor propertyEditor) {
            this.propertyEditor = propertyEditor;
        }
    }
}
