package com.example.demo.trees;

public class BSTClient {
  public static void main(String[] args) {
    BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
    bst.put(20, 20);
    bst.put(21, 21);
    bst.put(7, 7);
    bst.put(6, 6);
    bst.put(15, 15);
    bst.put(11, 11);
    bst.put(13, 13);
    System.out.println(bst.floor(8));
    System.out.println(bst.ceiling(8));
    System.out.println(bst.min());
    System.out.println(bst.max());
    System.out.println(bst.select(3));
    System.out.println(bst.rank(16));
  }
}
