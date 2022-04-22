/*
Ashvin Ganesan
Algorithms
Thursday Jan 14th 2020
Homework 12
*/
import java.util.Iterator;


public class PQue implements PriorityQueue<String>{
    public String[] heap;
    public int size;
    public int k;
    
    public PQue(int k) {
        this.k = k;
        // I know doing 2k is unnecessary, but it seemed simpler 
        heap = new String[2*k];
        size = 0;
        this.k = k;
        
    }
    public void print() {
        for(int i = 0; i <k; i++) {
            System.out.println(heap[i]);
        }
    }
    
    
    
    @Override
    public int size(){
        return size;
    }
    
    @Override
    public boolean isEmpty(){
        return (size() == 0);
    }
    
    public void attemptToAdd(String item) {
        if (size() < k){
            //System.out.println(item);
            enqueue(item);
            return;
        }
        if(head().length() < item.length()) {
            //System.out.println("size is" + size);
            dequeue();
            //System.out.println("size is" + size);

            enqueue(item);
        }
        
    }
    
    @Override
    public void enqueue(String item){
        heap[size()] = item;
        size++;
        floatUp(size()-1);
        
    }
    
    public void floatUp(int i) {
        if(heap[i].length() >= heap[getParInd(i)].length()) {
            sink(i);
            return;
        }
        String temp = heap[getParInd(i)];
        heap[getParInd(i)] = heap[i];
        heap[i] = heap[getParInd(i)];
        floatUp(getParInd(i));
    }
    public int getParInd(int i) {
        return (i-1)/2;
    }

    @Override
    public String dequeue(){
        if(isEmpty()) {
            return "";
        }
        String temp = head();
        removeMin();
        size--;
        return temp;
    }
    public void sink(int i) {
        int parLen = heap[i].length();
        if(size() > (i+1)*2 && heap[(i+1)*2] != null) {
            int rightLen = heap[(i+1)*2].length();
            int leftLen = heap[(i+1)*2-1].length();         
            if(rightLen < leftLen) {
                if(rightLen < parLen) {
                    String temp = heap[i];
                    heap[i] = heap[(i+1)*2];
                    heap[(i+1)*2] = temp;
                    sink((i+1)*2);
                    return;
                }
            }
            if(leftLen < parLen) {
                String temp = heap[i];
                    heap[i] = heap[(i+1)*2-1];
                    heap[(i+1)*2-1] = temp;
                    sink((i+1)*2-1);
                    return;
            }
            return;
        }
        return;
    }
    
    public void removeMin() {
        heap[0] = heap[size()-1];
        heap[size()-1] = null;
        sink(0);
//        int parLen = heap[i].length();
//        if(size() > (i+1)*2) {
//    
//            int leftLen = heap[(i+1)*2-1].length();
//            int rightLen = heap[(i+1)*2].length();
//            if(leftLen < rightLen) {
//                heap[i] = heap[(i+1)*2-1];
//                removeMin((i+1)*2-1);
//            } else {
//                heap[i] = heap[(i+1)*2];
//                removeMin((i+1)*2);
//            }
//        } else if(size() > (i+1)*2-1) {
//            int leftLen = heap[(i+1)*2-1].length();
//            heap[i] = heap[(i+1)*2-1];
//            removeMin((i+1)*2-1);
//            int rightLen = -1;
//        }
//        else {
//            heap[i] = null;
//            return;
//        }
    }
        
    
    @Override
    public String head(){
        if (size() >= 1){
            return heap[0];
        }
        return null;
    }
    
    @Override
    public Iterator<String> iterator() {
        return this.new PriorityQueueIterator();
    }

    private class PriorityQueueIterator implements Iterator<String> {
        private int count;


        public PriorityQueueIterator() {
            count = 0;
        }

        @Override
        public boolean hasNext() {
            if (count-1 < size()) {
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            
            return heap[count++];
        }
    }
}
    

