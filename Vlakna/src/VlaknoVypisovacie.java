public class VlaknoVypisovacie  extends Thread{
    public void run(){
        Thread.currentThread().setPriority(MIN_PRIORITY);

        long a = 0;

        while (VlaknoCitacie.idem){
            System.out.print(a+" ");
            a++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
