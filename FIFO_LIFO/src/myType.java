public class myType {
    private int int_value;
    private double double_value;
    private char char_value;
    private String string_value;

    private int control;

    myType(int value){
        this.int_value = value;
        this.control = 1;
    }

    myType(double value){
        this.double_value = value;
        this.control = 2;
    }

    myType(char value){
        this.char_value = value;
        this.control = 3;
    }

    myType(String value){
        this.string_value = value;
        this.control = 4;
    }

    public int get_control(){
        return control;
    }

    public int get_int_value(){
        return int_value;
    }

    public double get_double_value(){
        return double_value;
    }

    public char get_char_value(){
        return char_value;
    }

    public String get_string_value(){
        return string_value;
    }

    public String toString(){
        String tmp = "";
        if(control == 1){
            tmp = String.valueOf(int_value);
        }
        else if(control == 2){
            tmp = String.valueOf(double_value);
        }
        else if(control == 3){
            tmp = String.valueOf(char_value);
        }
        else if(control == 4){
            tmp = string_value;
        }
        return tmp;
    }
}
