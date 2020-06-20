package br.unicamp.ic.mc322.heroquest.util.tree;

class Leaf <T>{
    private T data;
    private Leaf<T> leftChild;
    private Leaf<T> rightChild;

    public Leaf(T value) {
        this.data = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Leaf<T> getLeftChild() {
        return leftChild;
    }

    public Leaf<T> getRightChild() {
        return rightChild;
    }

    public T getData() {
        return data;
    }

    public void setLeftChild(Leaf<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Leaf<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isBaseLeaf() {
        return (this.getLeftChild() == null && this.getRightChild() == null);
    }
}
