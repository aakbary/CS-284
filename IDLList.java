import java.util.ArrayList;
import java.awt.image.*;

//import javax.xml.soap.*;

//import javax.xml.soap.*;


public class IDLList<E> {
	//Properties of IDLList<E>:
	private ArrayList<Node<E>> indices;
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	//Inner class Node<E>
	private class Node<E> {
		//Properties of Node<E>:
		private E data;
		private Node<E> prev;
		private Node<E> next;
		
		//Constructor of Node<E>, given 'E' elem
		public Node(E elem) {
			this.data = elem;
			this.prev = null;
			this.next = null;
		}
		
		//Constructor of Node<E>, given 'E' elem, a link to the previous node, and
		//a link to the next node
		public Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
		public String toString(){
			return data.toString();
		}
	}
	
	//Constructor of IDLList
	public IDLList() {
		this.indices = new ArrayList<Node<E>>();
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	
	//Constructor of IDLList
	public IDLList(E[] newIndices) {

		this.indices = new ArrayList<Node<E>>();
        if(newIndices.length>0){
            for(int i = 0; i<newIndices.length; i++){
                this.indices.add(new Node<E>(newIndices[i]));  
            }
            this.size = this.indices.size();
		    this.head = this.indices.get(0);
		    this.tail = this.indices.get(this.size - 1);

            for(int i = 0; i<newIndices.length;i++){
                if(i == 0){
                    this.indices.get(i).prev = null;
                    this.indices.get(i).next = this.indices.get(i+1);
                }
                else if(i == newIndices.length-1){
                    this.indices.get(i).prev = this.indices.get(i-1);
                    this.indices.get(i).next = null;
                }
                else{
                    this.indices.get(i).prev = this.indices.get(i-1);
                    this.indices.get(i).next = this.indices.get(i+1);
                }
            }
        }
        else{
            this.size = 0;
		    this.head = null;
		    this.tail = null;
        }
	}
	
	
	//Given an index, the .add() method adds a new node at the given index 
	public boolean add(int index, E elem) {
		if(index==size)
			return append(elem);
		if(index==0)
			return add(elem);
		if (index > size || index < 0){
			throw new IllegalStateException("Index out of bounds.");
		}else{
			int i = 0;
			Node<E> temp = new Node<E>(elem),current = head;
			while (i<index){
				i += 1;
				current=current.next;
		}
		// red <- blue
		temp.next = current.next;
		// red -> green
		temp.prev = current;
		current.next = temp;
		temp.next.prev = temp;
		indices.add(index,temp);
		size = size + 1;
	}
		return true;
	}
	
	//The .add() method adds a new node at the head
	public boolean add(E elem) {
		Node<E> temp = new Node<E>(elem);
		indices.add(0, temp);
		if (size == 0){
			this.head = temp;
			this.tail = temp;
			head.next = null;
			head.prev = null;
			size = size + 1;
			return true;
		}
			temp.next = head;
			head.prev = temp;
			head = temp;
		//Node<E> temp = new Node<E>(elem);
		//temp.next =  head;
		//head.prev = temp;
		//head.prev = temp;
		//head=temp;
		size = size+1;
		return true;
	}
	
	//The .append() method adds a new node at the tail
	public boolean append(E elem) {
		Node<E> temp = new Node<E>(elem);
//		if (size == 0){
//			this.head = temp;
//			this.tail = temp;
//			head.next = null;
//			head.prev = null;
//			size += 1;
//			return true;
//		}
		size += 1;
		temp.prev = tail;
		 if (tail != null){
			tail.next = temp;
		} else {
			head = temp;
		}
			tail = temp;
			indices.add(temp);
		return true;
	}

	//The .get() method returns the data of a node at the given index
	public E get(int index) {
		Node<E> current = head;
		if (index > size || index < 0){
			throw new IllegalStateException("Index is out of bounds.");
		} else{
			int i = 0;
			while (i<index){
				i += 1;
				current=current.next;
		}
		}
		return current.data;
	}
	
	//The .getHead() method returns the data of the node at the head
	public E getHead() {
		if (this.size == 0){
			return null;
		}else{
		return head.data;
		}
	}
		
	//The .getLast() method returns the data of the node at the tail
	public E getLast() {
		if (this.size == 0){
			return null;
		}else{
		return tail.data;
	}
	}

	//The .remove() method removes the node at the head and returns the node's data
	public E remove() {
		if (this.size == 0){
			throw new IllegalStateException("Head node does not exist.");
		} else{
			size = size - 1;
			return this.indices.remove(0).data;
			}
		}
		
	//The .removeLast() method removes the node at the tail and returns the node's data
	public E removeLast() {
		if (this.size == 0){
			throw new IllegalStateException("Tail does not exist");
		} else{
			size = size - 1;
			if (size == 1){
				E t = head.data;
				tail = null;
				head = null;
				return t;
			}
				tail = tail.prev;
				E t = tail.next.data;
				tail.next = null;
				return t;
		}
	}
		
	//Given an index, the .removeAt() method removes the node at the index and returns its data
	public E removeAt(int index) {
		if (index >= this.size || index < 0){
			throw new IllegalStateException("Unable to remove");
		} else{
			if(index == size - 1) {
				return removeLast();
			}
			else if (index==0) {
				return remove();
			}
			size = size - 1;
			return this.indices.remove(index).data;	
			
		}
	}	
	
	
	//Given an element to remove, the .remove() method removes the node that is the first instance
	//in which the data is found to be the same
	public boolean remove(E elem) {
		if (head == null){
			return false;
		}
//		else if(head.data.equals(elem)){
//			remove();
//			return true;
//		} else if(tail.data.equals(elem)){
//			removeLast();
//			return true;
		else{
			Node<E> current = head;
			int index = 0;
			while (current != null && !current.data.equals(elem)){
				current = current.next;
				index ++;
				System.out.println(current);
			}
			if (current == null){
				return false;
			} else {
				removeAt(index);
				return true;
			}
		}
	}
//			}
//				current.next.prev=current.prev;
//				//System.out.println(current.prev);
//				current.prev.next=current.next;
//				//System.out.println(current.next);
//			
//			size-=1;
//			this.indices.remove(elem);
//			//System.out.print(tail);
//		} return true;
//		
//		}


	//The .size() method returns the size of the ArrayList
	public int size() {
		return this.size;
	}

	//Converts the ArrayList into a readible string of each Node's data
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		if (this.size > 0) {
			for (int i = 0; i < this.size; i++) {
				result.append(this.indices.get(i).data + ", ");
			}
			result.delete(result.length() - 2, result.length());
		}
		result.append("]");
		return result.toString();
	}

	public static void main(String[] args) {
	//	String al9[] = new String[7];
	//	al9[0] = "A";
	//	al9[1] = "B";
	//	al9[2] = "C";
	//	al9[3] = "D";
	//	al9[4] = "E";
	//	al9[5] = "F";
	///	al9[6] = "G";
//IDLList<String> L92 = new IDLList<String>(al9);
		
		//L92.removeAt(-1);
		
		//L92.removeAt(10);
//System.out.print(L92.toString());

		//String l92_remove = L92.removeAt(2);
		//l92_remove = L92.removeAt(5);
		//System.out.print(L92.toString());
		String al10[] = new String[7];
		al10[0] = "A";
        al10[1] = "B";
        al10[2] = "C";
        al10[3] = "D";
        al10[4] = "E";
        al10[5] = "F";
        al10[6] = "G";
		IDLList<String> L102 = new IDLList<String>(al10);

		//assertEquals(L102.remove("D"));
		
		//System.out.print(L102.remove("A"));
		//System.out.print(L102.remove("D"));
		//System.out.print(L102.toString());
		
		//IDLList<String> L101 = new IDLList<String>();
		//System.out.print(L101.add("X"));
		//System.out.print(L101.toString());
		
			//IDLList<String> L4 = new IDLList<String>();
        
            //System.out.print(L4.add("A"));
            //System.out.print(L4.toString());
			
			System.out.print(L102.remove("A"));
			System.out.print(L102.toString());
		
		  IDLList<String> L6 = new IDLList<String>();
			
	        //System.out.print(L6.append("X"));
	        //System.out.print(L6.toString());


	}

}
	