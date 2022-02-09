package com.example.demo.trees;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
  private Node root;

  // прочитать Effective java про вложенные статические классы
  // и почему они должны быть в приоритете
  private class Node {
    private Key key;
    private Value value;
    private Node left, right;
    private int N;

    public Node(Key key, Value value, int n) {
      this.key = key;
      this.value = value;
      N = n;
    }
  }

  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null) return 0;
    else return x.N;
  }

  public Value get(Key key) {
    return get(root, key);
  }

  private Value get(Node x, Key key) {
    if (x == null) return null;
    int compare = key.compareTo(x.key);
    if (compare < 0) return get(x.left, key);
    else if (compare > 0) return get(x.right, key);
    else return x.value;
  }

  public void put(Key key, Value value) {
    root = put(root, key, value);
  }

  private Node put(Node x, Key key, Value value) {
    if (x == null) return new Node(key, value, 1);
    int compare = key.compareTo(x.key);
    if (compare < 0) x.left = put(x.left, key, value);
    else if (compare > 0) x.right = put(x.right, key, value);
    else x.value = value;
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  public Key min() {
    if (size() == 0) return null;
    return min(root).key;
  }

  private Node min(Node x) {
    if (x.left == null) return x;
    return min(x.left);
  }

  public Key max() {
    if (size() == 0) return null;
    return max(root).key;
  }

  private Node max(Node x) {
    if (x.right == null) return x;
    return max(x.right);
  }

  public Key floor(Key key) {
    Node x = floor(root, key);
    if (x == null) return null;
    return x.key;
  }

  private Node floor(Node x, Key key) {
    if (x == null) return null;
    int compare = key.compareTo(x.key);
    if (compare == 0) return x;
    if (compare < 0) return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;
  }

  public Key ceiling(Key key) {
    Node x = ceiling(root, key);
    if (x == null) return null;
    return x.key;
  }

  private Node ceiling(Node x, Key key) {
    if (x == null) return null;
    int compare = key.compareTo(x.key);
    if (compare == 0) return x;
    if (compare > 0) return ceiling(x.right, key);
    Node t = ceiling(x.left, key);
    if (t != null) return t;
    else return x;
  }

  public Key select(int k) {
    final Node result = select(root, k);
    if (result == null) return null;
    return result.key;
  }

  private Node select(Node x, int k) {
    if (x == null) return null;
    int t = size(x.left);
    if (t > k) return select(x.left, k);
    else if (t < k) return select(x.right, k - t - 1);
    else return x;
  }

  public int rank(Key key) {
    return rank(key, root);
  }

  private int rank(Key key, Node x) {
    if (x == null) return 0;
    int compare = key.compareTo(x.key);
    if (compare < 0) return rank(key, x.left);
    else if (compare > 0) return 1 + size(x.left) + rank(key, x.left);
    else return size(x.left);
  }

}
