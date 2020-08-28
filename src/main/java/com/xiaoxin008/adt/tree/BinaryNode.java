package com.xiaoxin008.adt.tree;

/**
 * 二叉查找树节点
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class BinaryNode<T> {

    T element;
    BinaryNode<T> left;
    BinaryNode<T> right;

    public BinaryNode(T element) {
        this(element, null, null);
    }

    public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "element=" + element +
                '}';
    }
}
