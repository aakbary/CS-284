public class SingleLinkedList<E> {
		//head reference
		private Node<E> headNode = null;
		
		//get headNode
		public Node<E> getHeadNode() {
			Node current = head;
			count = 0;
			
			while (current != null) {
				if (count == 0) {
				return current.data;
			}
			//Node.get(0);
			//return headNode;
		}
		}
		
		//set headNode
		public void setHeadNode(Node<E> node) {
			Node.set(0,node);
			headNode = node;
		}
		
		/**
		 * Determines whether the recipient list has cycles.
		 * @return boolean(true | false) -> If cycle return true else return false
		 */
		public Boolean hasCycle() {
			if (this.head != null) {
				Node<E> previous = new Node<E>(head.data);
				Node<E> = node.head;
				while (node.next != null) {
					node = node.next;
				if(node.head() == previous.head()){
					return true;
					}
				}
			return false;
			}
		}
		
		public static void main(String[] args) {
			//TestCase 1
			//list contains three nodes 1, 2, 3 and they are linked as below
			//1 -> 2 -> 3 -> 1
			//Answer: List has a cycle, because 3 is pointing back to 1 
			SingleLinkedList<Integer> list = new SingleLinkedList<>();
			Node<Integer> node1 = new Node<Integer>(1);
			Node<Integer> node2 = new Node<Integer>(2);
			Node<Integer> node3 = new Node<Integer>(3);
			list.headNode = node1;
			node1.setNext(node2); 
			node2.setNext(node3);
			node3.setNext(node1);
			
			//Validate if list contains cycle
			if(list.hasCycle()) {
				System.out.println("List contains cycle");
			} else {
				System.out.println("List doesn't contain cycle");
			}
			
			//TestCase 2
			//list2 contains two nodes 10, 20 and they are linked as below
			//10 -> 20
			//Answer: list2 doesn't contain a cycle
			SingleLinkedList<Integer> list2 = new SingleLinkedList<>();
			Node<Integer> node4 = new Node<Integer>(10);
			Node<Integer> node5 = new Node<Integer>(20);
			list2.headNode = node4;
			node4.setNext(node5);
			
			//Validate if list contains cycle
			if(list2.hasCycle()) {
				System.out.println("List2 contains cycle");
			} else {
				System.out.println("List2 doesn't contain cycle");
			}
			
		}
	

	}