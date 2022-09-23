// Amena Akbary
// CS 284 B

import java.util.Random;
import java.util.Stack;





public class Treap<E extends Comparable<E>> {

	private static class Node<E> {
		E data; // key for the search
		int priority; // random heap priority
		Node<E> left;
		Node<E> right;

		public Node(E data, int priority) {
			if (data == null)
				throw new IllegalArgumentException();
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;

		}

		Node<E> rotateRight() {
			if (this.left == null)
				return this;
			Node<E> b = this.left;
			this.left = b.right;
			b.right = this;
			return b;
		}

		Node<E> rotateLeft() {
			if (this.right == null)
				return this;
			Node<E> b = this.right;
			this.right = b.left;
			b.left = this;
			return b;
		}

		public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}
		
		public boolean find(E elem) {
			if (left == null && right == null){
				return data.equals(elem);
		} else {
			return data.equals(elem) || (left == null? false: left.find(elem)) || (right == null? false: right.find(elem));
		}
	}
}

	private Random priorityGenerator;
	private Node<E> root;
	

	public Treap() {
		this.root = null;
		this.priorityGenerator = new Random();
	}
	
	public Treap(long seed) {
		this.root = null;
		this.priorityGenerator = new Random(seed);
	}
	
// it would return false if it can not be added but I believe there will never be a case where it cant be added
	
	public boolean add(E key) {
		if (root == null){
			root = new Node(key,priorityGenerator.nextInt());
			return true;
		} else{
			Stack<Node<E>> k = new Stack<Node<E>>();
			Node<E> current = root;
			while(true){
				k.push(current);
				if (current.data.compareTo(key)<0){//root.data < key
					if(current.right == null){
						current.right = new Node<E>(key,priorityGenerator.nextInt());
						current = current.right;
						break;
					} else {
						current = current.right;
					}
				}
				else if(current.data.compareTo(key)>0){
					if(current.left == null){
						current.left = new Node<E>(key,priorityGenerator.nextInt());
						current = current.left;
						break;
					} else{
						current = current.left;
					}
				} else{
					return false;
			}
		}
			reheap(k,current);
			return true;
		}
	}

	public boolean add(E key, int priority) {
		if (root == null){
			root = new Node(key,priority);
			return true;
		}
		else{
			Stack<Node<E>> k = new Stack<Node<E>>();
			Node<E> current = root;
			while (true){
				k.push(current);
				if (current.data.compareTo(key)<0){//root.data < key
					if(current.right == null){
						current.right = new Node<E>(key,priority);
						current = current.right;
						break;
					} else {
						current = current.right;
					}
				}
				else if(current.data.compareTo(key)>0){
					if(current.left == null){
						current.left = new Node<E>(key,priority);
						current = current.left;
						break;
					} else{
						current = current.left;
					}
				} else{
					return false;
				}
		}
			reheap(k,current);
			return true;
		}
	}
	
	private void reheap(Stack<Node<E>> s, Node<E> curr) {
		// helper to add, restores invariant
		while(!s.empty()){
			Node<E> parent = s.pop();
			Node<E> grandparent;
			if (s.empty() != true){
				grandparent = s.peek();
			} else{
				grandparent = null;
			}
			if (parent.left != null && parent.left.equals(curr) && parent.priority < curr.priority){
				/*if(grandparent == null){
					Node<E> temmp = parent.rotateRight();
					grandparent = temmp;
					root = grandparent;
				} else if (grandparent.left == parent){
					Node<E> temmp = parent.rotateRight();
					grandparent.left = temmp;
				} else{
					Node<E> temp = parent.rotateRight();
					grandparent.right = temp;
				}*/
				parent.rotateRight();
				if(grandparent == null)
					root = curr;
				else if(grandparent.left != null && grandparent.left.equals(parent))
					grandparent.left = curr;
				else
					grandparent.right = curr;
			} else if (parent.right != null && parent.right.equals(curr) && parent.priority < curr.priority){
				/*if (grandparent == null){
					Node<E> temmp = parent.rotateLeft();
					grandparent = temmp;
					root = grandparent;
				} else if (grandparent.left != null && grandparent.left.equals(parent)){
					Node<E> temmp = parent.rotateLeft();
					grandparent.right = temmp;
				}*/
				parent.rotateLeft();
				if(grandparent == null)
					root = curr;
				else if(grandparent.left != null && grandparent.left.equals(parent))
					grandparent.left = curr;
				else
					grandparent.right = curr;
			}
		}
	}

	public boolean delete(E key) {
		if (root == null){
			return false;
		} else if(key == null){
			return false; 
		} else if(find(key) == false){
			return false;
		} else{
			Node<E> parent = findParent(root,key);
			Node<E> nodeDelete;
			if (parent.left != null && parent.left.data.compareTo(key) == 0){
				nodeDelete = parent.left;
			} else {
				nodeDelete = parent.right;
			}
			if (isLeaf(nodeDelete)){
				if(parent.left != null && parent.left.equals(nodeDelete))
					parent.left = null;
				else
					parent.right = null;
				return true;
			} else {
				makeLeaf(nodeDelete,parent);
				return true;
			}
		}
	}
	
	/*//in order to do recursively delete i will create a helper function
	private void deells(Node<E> parent, Node<E> nodeDelete, E key){
		// will find position of key in treap, then go down the node with the key until it becomes and leaf and then removes it
		while(nodeDelete.left != null || nodeDelete.right!= null){ 
			if(nodeDelete.left == null){ // working with left child
				if (parent.left.equals(nodeDelete) == true){
				parent.left = nodeDelete.rotateLeft();
			} else {
				parent.right = nodeDelete.rotateLeft();
			}
			parent = parent.left;
			nodeDelete = parent.left;
		} else if(nodeDelete.right == null){ // working with right child
			if(parent.left.equals(nodeDelete) == true){
				parent.left = nodeDelete.rotateRight();
			} else{
				parent.right = nodeDelete.rotateRight();
			}
			parent = parent.right;
			nodeDelete = parent.right;
		} else{ // 2 children
			if(nodeDelete.left.priority > nodeDelete.right.priority){
				if(parent.left.equals(nodeDelete)== true){
					parent.left = nodeDelete.rotateRight();
				} else{
					parent.right = nodeDelete.rotateRight();
				}
				parent = parent.right;
				nodeDelete = parent.right;
			} else{
				if (parent.left.equals(nodeDelete) == true){
					parent.left = nodeDelete.rotateLeft();
				} else{
					parent.right = nodeDelete.rotateLeft();
				}
				parent = parent.left;
				nodeDelete = parent.left;
			}
		}
	}
	if(parent.left != null && parent.right == null){
		parent.left = null;
	} else{
		parent.right = null;
	}
} */

private void makeLeaf(Node<E> curr, Node<E> parent) {
	Node<E> p = parent;
	Node<E> c = curr;
	while(c.right != null || c.left != null) {
		if(c.right == null || (c.left != null && c.left.priority > c.right.priority)) {
			p.right = c.left;
			p = c.left;
			c.rotateRight();
			p.right = c;
		} else if(c.left == null || (c.right != null && c.right.priority > c.left.priority)) {
			p.left = c.right;
			p = c.right;
			c.rotateLeft();
			p.left = c;
		}
	}
	if(c == root) {
		root = null;
		return;
	}
	if(p.left != null && p.left.equals(c))
		p.left = null;
	else
		p.right = null;
}

// another helper function to help me treap and find the parent node of the node containing key
	private Node<E> findParent (Node<E> current, E key){
		if (current == null){
			return null;
		} else if (current.data.compareTo(key) == 0){
			return current;
		} else if (current.left != null && current.left.data.compareTo(key) == 0){
			return current;
		} else if (current.right != null && current.right.data.compareTo(key) == 0){
			return current;
		} else {
			Node<E> left = findParent(current.left, key);
			Node<E> right = findParent(current.right, key);
			if (left == null){
				return right;
			} else {
				return left;
			}
		}
	}
	
	// another helper function for delete that will check if the current node is a leaf by look at its child nodes
	public boolean isLeaf(Node<E> current){
		return current != null && (current.left == null && current.right == null);
	}

	public boolean find(E key) {
		// return if node with key key exists
		return root.find(key);
//		if (root == null){
//			return false;
//		} else if (key == null){
//			return false;
//		} else {
//		// how do you search in a binary tree?
//		return find(root,key);
//	}
}

	public String toString() {
		// string representation of the treap
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(this.root, 1, sb);
		return sb.toString();
	}

	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		// helper to toString, traverses treap and adds to stringbuilder
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	public static void main(String[]args){
		Treap<Character> t = new Treap<Character>();
		t.add('p', 99);
		t.add('g', 80);
		t.add('a', 60);
		t.add('j', 65);
		t.add('u', 75);
		t.add('r', 40);
		t.add('z', 47);
		t.add('w', 32);
		t.add('v', 21);
		t.add('x', 25);
		System.out.println(t);
		t.delete('z');
		System.out.println(t);
	}
}