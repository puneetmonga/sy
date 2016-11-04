package com.sy.util;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import static java.lang.Math.max;

/**
 * Binary Search Tree that keeps the keys sorted by the comparator provide and also keeps itself balanced.
 * Created by pmonga on 11/2/16.
 */
public class BinaryMap<K, V> {
    private Node ROOT = null;
    private int size;
    private boolean keepBalanced;
    private Comparator<K> keyComparator;

    public BinaryMap(Comparator<K> keyComparator) {
        this(true);
        this.keyComparator = keyComparator;
    }

    public BinaryMap(boolean keepBalanced) {
        this.keepBalanced = keepBalanced;
        size = 0;
    }

    public void put(K key, V value) {
        Node node = find(ROOT, key);
        if(node != null) {
            node.value = value;
            return;
        }
        int previousHeight = height(ROOT);
        if (ROOT == null) {
            ROOT = new Node(key, value);
        } else {
            insert(ROOT, key, value);
        }
        int newHeight = height(ROOT);
        size++;

        balance(previousHeight, newHeight);
    }

    public int height() {
        return height(ROOT);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        if (key == null) return false;
        return find(ROOT, key) != null;
    }

    public V get(K key) {
        if (key == null) return null;
        Node node = find(ROOT, key);
        return node == null ? null : node.value;
    }

    public void clear() {
        ROOT = null;
    }

    public Set<K> keySet() {
        Set<K> keys = new LinkedHashSet<K>();
        inOrderTraversal(ROOT, (node) -> {
            keys.add(node.key);
        });
        return keys;
    }

    public void remove(K key) {
        int previousHeight = height();
        delete(key);
        int newHeight = height();
        balance(previousHeight, newHeight);
        size--;
    }

    private void delete(K key) {
        Node node = find(ROOT, key);
        if (node.isLeaf()) {
            if (node == ROOT) {
                ROOT = null;
            } else {
                removeLeafNode(node);
            }
        } else if (node.hasOnlyLeftChild()) {
            node.replaceWithLeft();
        } else if (node.hasOnlyRightChild()) {
            node.replaceWithRight();
        } else {
            Node biggestNodeInLeftSubtree = findBiggestNodeInSubtree(node.left);
            node.replaceWith(biggestNodeInLeftSubtree);
            remove(biggestNodeInLeftSubtree.key);
        }
    }

    private void removeLeafNode(Node node) {
        if (node.childType == ChildType.LEFT) {
            node.parent.left = null;
        } else {
            node.parent.right = null;
        }
    }

    private void inOrderTraversal(Node node, Consumer<Node> consumer) {
        if (node == null) return;
        inOrderTraversal(node.left, consumer);
        consumer.accept(node);
        inOrderTraversal(node.right, consumer);
    }

    private Node find(Node node, K key) {
        if (node == null) return null;

        if (node.equalTo(key)) {
            return node;
        } else if (node.smallerThan(key)) {
            return find(node.right, key);
        } else {
            return find(node.left, key);
        }
    }

    private void insert(Node node, K key, V value) {
        if (node.smallerThan(key)) {
            insertRight(node, key, value);
        } else {
            insertLeft(node, key, value);
        }
    }

    private void insertLeft(Node node, K key, V value) {
        if (node.left != null) {
            insert(node.left, key, value);
        } else {
            node.left = node.newLeftChild(key, value);
        }
    }

    private void insertRight(Node node, K key, V value) {
        if (node.right != null) {
            insert(node.right, key, value);
        } else {
            node.right = node.newRightChild(key, value);
        }
    }

    private boolean needsBalancing(int previousHeight, int newHeight) {
        return previousHeight < newHeight && (int) Math.pow(2, newHeight - 1) - 1 >= size();
    }

    private void balance(int previousHeight, int newHeight) {
        if (keepBalanced && needsBalancing(previousHeight, newHeight)) {
            if (height(ROOT.left) > height(ROOT.right)) {
                balanceLeft(ROOT.left);
            } else {
                balanceRight(ROOT.right);
            }
        }
    }

    private void balanceRight(Node rightSubtree) {
        Node smallestNode = findSmallestNodeInSubtree(rightSubtree);

        smallestNode.left = ROOT;
        /* If the root of right substree is not itself the smallest node */
        if (smallestNode != rightSubtree) {
            smallestNode.parent.left = smallestNode.right;
            smallestNode.right = rightSubtree;
        }
        ROOT.right = null;
        ROOT = smallestNode;
    }

    private void balanceLeft(Node leftSubtree) {
        Node biggestNode = findBiggestNodeInSubtree(leftSubtree);

        biggestNode.right = ROOT;
            /* If the root of left subtree is not itself the biggest node */
        if (biggestNode != leftSubtree) {
            biggestNode.parent.right = biggestNode.left;
            biggestNode.left = leftSubtree;
        }
        ROOT.left = null;
        ROOT = biggestNode;
    }

    private Node findSmallestNodeInSubtree(Node subtree) {
        Node smallestNode = subtree;
            /* Locate the smallest node in right subtree and its immediate parent */
        while (smallestNode.left != null) {
            smallestNode = smallestNode.left;
        }
        return smallestNode;
    }

    private Node findBiggestNodeInSubtree(Node subtree) {
        Node biggestNode = subtree;
            /*Locate the biggest node in left subtree and its immediate parent*/
        while (biggestNode.right != null) {
            biggestNode = biggestNode.right;
        }
        return biggestNode;
    }

    private int height(Node node) {
        if (node == null) return 0;
        return 1 + max(height(node.left), height(node.right));
    }

    private class Node {
        private K key;
        private V value;

        private Node left;
        private Node right;
        private Node parent;
        private ChildType childType;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private boolean smallerThan(K key) {
            return compare(key) < 0;
        }

        private int compare(K key) {
            return keyComparator.compare(this.key, key);
        }

        private boolean greaterThan(K key) {
            return compare(key) > 0;
        }

        private boolean equalTo(K key) {
            return compare(key) == 0;
        }

        public Node newLeftChild(K key, V value) {
            Node node = new Node(key, value);
            node.parent = this;
            node.childType = ChildType.LEFT;
            return node;
        }

        public Node newRightChild(K key, V value) {
            Node node = new Node(key, value);
            node.parent = this;
            node.childType = ChildType.RIGHT;
            return node;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        private boolean hasOnlyRightChild() {
            return right != null && left == null;
        }

        private boolean hasOnlyLeftChild() {
            return left != null && right == null;
        }

        private void replaceWith(Node node) {
            this.value = node.value;
            this.key = node.key;
        }

        public void replaceWithLeft() {
            this.replaceWith(this.left);
            this.left = null;
        }

        public void replaceWithRight() {
            this.replaceWith(this.right);
            this.right = null;
        }
    }

    public enum ChildType {
        LEFT, RIGHT
    }
}
