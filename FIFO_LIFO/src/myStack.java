public class myStack {
    private int top;
    private int bottom;
    private int capacity;

    private myType [] LIFO;

    myStack(int capacity){
        if(capacity > 0) {
            this.top = 0;
            this.bottom = 0;
            this.capacity = capacity;
            this.LIFO = new myType[capacity];
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public int getTop() {
        return top;
    }

    public int getCapacity() {
        return capacity;
    }

    public myType[] getLIFO() {
        return LIFO;
    }

    public void push(myType data){
        if(freeCap() == 0){
            throw new IndexOutOfBoundsException();
        }
        else{
            LIFO[top] = data;
            top++;
        }
    }

    public myType pop(){
        if (top == bottom){
            throw new IndexOutOfBoundsException();
        }
        else {
            top--;
            return LIFO[top];
        }
    }

    public int freeCap(){
        return capacity-top;
    }

    public void clear(){
        top = 0;
    }

    public String toString(){
        String output = "";
        for (myType m: LIFO) {
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
        for(int i = top-1; i >= 0; i--){
            output += LIFO[i].toString()+" ";
        }
        if(output.length() > 0){
            return output;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

}
