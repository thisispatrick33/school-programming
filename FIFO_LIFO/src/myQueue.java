public class myQueue {
    private int head;
    private int tail;
    private int capacity;

    private myType [] FIFO;

    private boolean full = false;

    myQueue(int capacity) {
        if(capacity > 0){
            this.head = 0;
            this.tail = 0;
            this.capacity = capacity;
            this.FIFO = new myType[capacity];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public int getCapacity() {
        return capacity;
    }

    public myType[] getFIFO() {
        return FIFO;
    }

    public void append(myType data){
        if(head != tail || !full){
            FIFO[head] = data;
            head++;
            if(head == capacity){
                head = 0;
            }
            if(head == tail){
                full = true;
            }
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public myType get(){
        if(full || head != tail){
            int tmp = tail;
            tail++;
            if(tail == capacity){
                tail = 0;
            }
            full = false;
            return FIFO[tmp];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int freeCap(){
        return Math.abs(head-tail);
    }

    public void clear(){
        head = 0;
        tail = 0;
    }

    public String toString(){
        String output = "";
        for (myType m: FIFO) {
            if(m == null){
                output += "[prazdne] ";
            }
            else{
                output += m.toString()+" ";
            }
        }
        return output;
    }

    public String see(){
        String output = "";
        int start = tail;
        boolean control = true;
        while ((full && control) || start != head){
            control = false;
            output += FIFO[start].toString()+" ";
            start++;
            if(start == capacity){
                start = 0;
            }
        }
        if(output.length() > 0){
            return output;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }
}
