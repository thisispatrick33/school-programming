public class Ucet {
    private int ucet_id;
    private int user_id;
    private double balance;

    Ucet(int ucet_id, int user_id, double balance){
        this.ucet_id = ucet_id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public int getUcet_id() {
        return ucet_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setUcet_id(int ucet_id) {
        this.ucet_id = ucet_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
