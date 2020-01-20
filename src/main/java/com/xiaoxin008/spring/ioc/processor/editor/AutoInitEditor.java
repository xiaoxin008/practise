package com.xiaoxin008.spring.ioc.processor.editor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 自动装配转换器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class AutoInitEditor {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        System.out.println(context.getBean("dateFoo"));
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

      //xml配置文件
//    <bean id="dateFoo" class="com.xiaoxin008.spring.ioc.bean.common.DateFoo">
//       <property name="date" value="2020/02/11"></property>
//    </bean>
//
//    <bean class="org.springframework.beans.factory.config.CustomEditorConfigurer">
//       <property name="propertyEditorRegistrars">
//           <list>
//               <ref bean="datePropertyEditorRegistrar"/>
//           </list>
//       </property>
//    </bean>
//
//    <bean id="datePropertyEditorRegistrar" class="com.xiaoxin008.spring.ioc.processor.editor.AutoInitEditor.DatePropertyEditorRegister">
//        <property name="propertyEditor" ref="datePropertyEditor"></property>
//    </bean>
//
//    <bean id="datePropertyEditor" class="com.xiaoxin008.spring.ioc.processor.editor.AutoInitEditor.DatePropertyEditor">
//        <property name="datePattern" value="yyyy/MM/dd"></property>
//    </bean>
}
