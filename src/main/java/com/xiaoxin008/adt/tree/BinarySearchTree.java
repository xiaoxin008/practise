package com.xiaoxin008.adt.tree;

/**
 * 二叉查找树
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(BinaryNode<T> root) {
        this.root = root;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * 清空树
     */
    public void makeEmpty() {
        this.root = null;
    }

    /**
     * 查询是否存在
     *
     * @param x 目标元素
     * @return 是否存在
     */
    public boolean contains(T x) {
        return this.contains(x, this.root);
    }

    public boolean contains(T x, BinaryNode<T> c) {
        if (c == null) {
            return false;
        }
        int i = x.compareTo(c.element);
        if (i > 0) {
            return this.contains(x, c.right);
        } else if (i < 0) {
            return this.contains(x, c.left);
        } else {
            return true;
        }
    }

    /**
     * 找到最小节点
     *
     * @return 最小节点
     */
    public T findMin() {
        if (this.isEmpty()) {
            return null;
        }
        BinaryNode<T> min = this.findMin(root);
        return min.element;
    }

    public BinaryNode<T> findMin(BinaryNode<T> c) {
        BinaryNode left = c.left;
        if (left == null) {
            return c;
        } else {
            return findMin(c.left);
        }
    }

    /**
     * 找到最大节点
     *
     * @return 最小节点
     */
    public T findMax() {
        if (this.isEmpty()) {
            return null;
        }
        BinaryNode<T> max = this.findMax(root);
        return max.element;
    }

    public BinaryNode<T> findMax(BinaryNode<T> c) {
        BinaryNode right = c.right;
        if (right == null) {
            return c;
        } else {
            return findMax(c.right);
        }
    }

    /**
     * 插入方法
     */
    public void insert(T x) {
        root = insert(x, root);
    }

    public BinaryNode<T> insert(T x, BinaryNode<T> p) {
        T element = p.element;
        int r = x.compareTo(element);
        if (r > 0) {
            BinaryNode right = p.right;
            if (right == null) {
                p.right = new BinaryNode(x);
            } else {
                insert(x, right);
            }
        } else if (r < 0) {
            BinaryNode left = p.left;
            if (left == null) {
                p.left = new BinaryNode(x);
            } else {
                insert(x, left);
            }
        }
        return p;
    }

    /**
     * 删除方法
     * <p>
     * 1、目标节点是一片树叶 则立即删除
     * 2、如果目标节点有一个儿子 其父节点绕过该节点后删除
     * 3、如果目标节点有两个儿子 用其右子树的最小的数据代替该节点的数据并递归删除那个节点
     */
    public void remove(T x) {
        root = remove(x, root);
    }

    public BinaryNode<T> remove(T x, BinaryNode<T> p) {
        T element = p.element;
        //1.当前节点的值是否等于目标值
        int r = x.compareTo(element);
        if (r > 0) {
            BinaryNode right = p.right;
            if (right != null) {
                p.right = this.remove(x, p.right);
            }
        } else if (r < 0) {
            BinaryNode left = p.left;
            if (left != null) {
                p.left = this.remove(x, p.left);
            }
            //两者相等
        } else {
            BinaryNode<T> right = p.right;
            BinaryNode<T> left = p.left;
            if (right != null && left != null) {
                BinaryNode<T> min = this.findMin(right);
                p.element = min.element;
                p.right = this.remove(min.element,p.right);
            } else {
                p = p.left == null?p.right:p.left;
            }
        }
        return p;
    }

    /**
     * 打印树
     */
    public void printTree() {
        this.printTree(root);
    }

    private void printTree(BinaryNode<T> n) {
        if (n == null) {
            return;
        }
        System.out.println(n);
        BinaryNode<T> left = n.left;
        if (left != null) {
            printTree(left);
        }
        BinaryNode<T> right = n.right;
        if (right != null) {
            printTree(right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tBinarySearchTree = new BinarySearchTree<>
                (new BinaryNode<Integer>(10,
                        new BinaryNode<>(8),
                        new BinaryNode<>(15, new BinaryNode<>(12), new BinaryNode<>(19))));
        tBinarySearchTree.insert(20);
        tBinarySearchTree.insert(16);
        tBinarySearchTree.remove(19);
        Integer min = tBinarySearchTree.findMin();
        Integer max = tBinarySearchTree.findMax();
        tBinarySearchTree.printTree();
        System.out.println(min);
        System.out.println(max);
    }
}
