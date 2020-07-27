package br.unicamp.ic.mc322.heroquest.util.tree;

public class Node<T> {
    private final T data;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T value) {
        this.data = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public boolean isLeaf() {
        return (this.getLeftChild() == null && this.getRightChild() == null);
    }
}
