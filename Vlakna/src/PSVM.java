public class PSVM {
    public static void main(String[] args) {
        System.out.println("main");
        VlaknoCitacie vc = new VlaknoCitacie();
        VlaknoVypisovacie vv = new VlaknoVypisovacie();
        vc.start();
        vv.start();
        System.out.println("main konci");
    }
}
