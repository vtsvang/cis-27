package lab1;

import java.util.ArrayList;


public class LinkedList {
    
    private DoubleNode first;
    
    private DoubleNode getFirst() {
        return first;
    }
    
    private DoubleNode getLast() {
        DoubleNode current = getFirst();
        
        while(current.next != null) {
            current = current.next;
        }
        
        return current;
    }
    
    public void insertAtBeginning(DoubleNode node) {
        DoubleNode oldFirst = first;
        
        first = node;
        node.next = oldFirst;
        node.previous = null;
        
        if(oldFirst != null) {
            oldFirst.previous = node;
        }
    }
    
    public void insertAtEnd(DoubleNode node) {
        if(first == null) {
            insertAtBeginning(node);
        } else {
            insertAfter(node, getLast());
        }
    }
    
    public DoubleNode removeFromBeginning() {
        remove(first);
        return first;
    }
    
    public DoubleNode removeFromEnd() {
        DoubleNode last = getLast();
        remove(last);
        return last;
    }
    
    public void insertBefore(DoubleNode node, DoubleNode target) {
        DoubleNode oldPrevious = target.previous;
        
        node.next = target;
        node.previous = oldPrevious;
        target.previous = node;
        
        if(oldPrevious != null) {
            oldPrevious.next = node;
        }
    }
    
    public void insertAfter(DoubleNode node, DoubleNode target) {
        DoubleNode oldNext = target.next;
        
        target.next = node;
        node.previous = target;
        node.next = oldNext;
        
        if(oldNext != null) {
            oldNext.previous = node;
        }
    }
    
    public void remove(DoubleNode node) {
        if(!ensureNodeBelongsToList(node)) {
            return;
        }
        
        if(node == null) {
            return;
        }
        
        DoubleNode next = node.next;
        DoubleNode prev = node.previous;
        
        if(prev != null) {
            prev.next = next;
            
            if(next != null) {
                next.previous = prev;
            }
        } else {
            first = next;
        }
    }
    
    public void moveToFront(DoubleNode node) {
        remove(node);
        insertAtBeginning(node);
    }
    
    public void moveToEnd(DoubleNode node) {
        remove(node);
        insertAtEnd(node);
    }
    
    public static class DoubleNode {
        public DoubleNode previous, next;
        public Object value;

        public DoubleNode(Object value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        DoubleNode current = first;
        ArrayList<String> output = new ArrayList<>();
        
        while(current != null) {
            output.add(current.value.toString());
            current = current.next;
        }
        
        return "{" + String.join(",", output) + "}";
    }
    
    private boolean ensureNodeBelongsToList(DoubleNode node) {
        DoubleNode current = first;

        while(current != null) {
            if(node == current) {
                return true;
            }
            
            current = current.next;
        }
        
        return false;
    }
}

// Output
/*
1. Linked Lists - Dequeue
insertAtBeginning(first): {first}
insertAtEnd(last): {first,last}
insertBefore(new LinkedList.DoubleNode("beforeLast"), last): {first,beforeLast,last}
insertAfter(new LinkedList.DoubleNode("afterFirst"), first): {first,afterFirst,beforeLast,last}
moveToFront(last): {last,first,afterFirst,beforeLast}
moveToEnd(first): {last,afterFirst,beforeLast,first}
remove(first): {last,afterFirst,beforeLast}
removeFromBeginning(): {afterFirst,beforeLast}
removeFromEnd(): {afterFirst}
*/