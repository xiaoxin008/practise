package com.xiaoxin008.jvmexecute.loader;

/**
 * 相同字段同时出现在接口与实现类中
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class ParseClassTest {

    public interface ParseClassTestParent0{
        int SUM = 0;
    }
    public interface ParseClassTestParent1 extends ParseClassTestParent0 {
        int SUM = 1;
    }
    public interface ParseClassTestParent2{
        int SUM = 2;
    }
    public static class ParseClassTestSub1 implements ParseClassTestParent1{
        public static int SUM = 3;
    }
    public static class ParseClassTestSub2 extends ParseClassTestSub1 implements ParseClassTestParent2{
        public static int SUM = 4;
    }
    public static void main(String[] args) {
        System.out.println(ParseClassTestSub2.SUM);
    }
}
