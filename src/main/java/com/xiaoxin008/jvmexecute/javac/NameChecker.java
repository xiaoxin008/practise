package com.xiaoxin008.jvmexecute.javac;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic;
import java.util.EnumSet;

import static javax.lang.model.element.ElementKind.FIELD;
import static javax.lang.model.element.Modifier.*;

/**
 * 名称校验器
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class NameChecker {

    private final Messager messager;

    NameCheckerScanner nameCheckerScanner = new NameCheckerScanner();

    public NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    public void checkNames(Element rootElement) {
        nameCheckerScanner.scan(rootElement);
    }

    private class NameCheckerScanner extends ElementScanner8<Void,Void>{

        /**
         * 检查Java类
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void aVoid) {
            scan(e.getTypeParameters(),aVoid);
            checkCamelCase(e,true);
            super.visitType(e, aVoid);
            return null;
        }

        /**
         * 检查方法命名是否合法
         * @param e
         * @param aVoid
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void aVoid) {
            if(e.getKind() == ElementKind.METHOD){
                Name simpleName = e.getSimpleName();
                if(simpleName.contentEquals(e.getEnclosingElement().getSimpleName())){
                    messager.printMessage(Diagnostic.Kind.WARNING,
                            "一个普通方法"+simpleName+"不应当与类名重复，避免与构造函数产生混淆",e);
                    checkCamelCase(e,false);
                }
            }
            super.visitExecutable(e, aVoid);
            return null;
        }

        @Override
        public Void visitVariable(VariableElement e, Void aVoid) {
            if(e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() !=null || heuristicallyConstant(e)){
                checkAllCaps(e);
            }else{
                checkCamelCase(e,false);
            }
            return null;
        }

        private void checkAllCaps(VariableElement e) {
            String name = e.getSimpleName().toString();
            boolean convertional = true;
            int firstCodePoint = name.codePointAt(0);
            if(!Character.isUpperCase(firstCodePoint)){
                convertional = false;
            }else{
                boolean previousUnderscore = false;
                int cp = firstCodePoint;
                for(int i = Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
                    cp = name.codePointAt(i);
                    if(cp == (int)'_'){
                        if(previousUnderscore){
                            convertional = false;
                            break;
                        }
                        previousUnderscore = true;
                    }else{
                        previousUnderscore = false;
                        if(!Character.isUpperCase(cp) && !Character.isDigit(cp)){
                            convertional = false;
                            break;
                        }
                    }
                }
            }
            if(!convertional){
                messager.printMessage(Diagnostic.Kind.WARNING,"常量"+name+"应当全部以大写字母下划线命名，并且以字母开头",e);
            }
        }

        private boolean heuristicallyConstant(VariableElement e) {
            if(e.getEnclosingElement().getKind() == ElementKind.INTERFACE){
                return true;
            }else if(e.getKind() == FIELD && e.getModifiers().containsAll(EnumSet.of(PUBLIC,STATIC,FINAL))){
                return true;
            }else{
                return false;
            }
        }

        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean convertional = false;
            int firstCodePoint = name.codePointAt(0);
            if(Character.isUpperCase(firstCodePoint)){
                previousUpper = true;
                if(!initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当以小写字母开头",e);
                    return;
                }
            }else if(Character.isLowerCase(firstCodePoint)){
                if(!initialCaps){
                    messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当以大写字母开头",e);
                    return;
                }
            }else{
                convertional = false;
            }

            if(convertional){
                int cp = firstCodePoint;
                for(int i = Character.charCount(cp);i<name.length();i+=Character.charCount(cp)){
                    cp = name.codePointAt(1);
                    if(Character.isUpperCase(cp)){
                        if(previousUpper){
                            convertional = false;
                            break;
                        }
                        previousUpper = true;
                    }else{
                        previousUpper = false;
                    }
                }
            }

            if(!convertional){
                messager.printMessage(Diagnostic.Kind.WARNING,"名称"+name+"应当符合驼峰式命名法",e);
            }
        }
    }
}
