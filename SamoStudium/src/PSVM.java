import java.util.Random;

public class PSVM {
    public static void main(String[] args) {
        Random rn = new Random();
        BankovyUcet [] ucty = new BankovyUcet[3];

        System.out.println("Stav pred vykonaním transakcií: ");
        for (int i = 0; i < ucty.length; i++) {
            ucty[i] = new BankovyUcet(rn.nextInt((101)+50));
            System.out.println("Na "+(i+1)+". účte je hotovosť "+ucty[i].stavNaUcte());
        }

        System.out.println();

        for (int i = 0; i < 7 ; i++) {
            int index = rn.nextInt(3);
            if (rn.nextBoolean()){
                ucty[index].prijem(Math.round((rn.nextDouble()*160+40)*100.0)/100.0);
                System.out.println("Transakcia prebehla v poriadku.");
                System.out.println("Na "+(index+1)+". účte je hotovosť "+ucty[index].stavNaUcte());
            }
            else{
                ucty[index].vydaj(Math.round((rn.nextDouble()*160+40)*100.0)/100.0);
                System.out.println("Na "+(index+1)+". účte je hotovosť "+ucty[index].stavNaUcte());
            }
        }

        System.out.println("\nStav po vykonaní transakcií: ");
        for (int i = 0; i < ucty.length ; i++) {
            System.out.println("Na "+(i+1)+". účte je hotovosť "+ucty[i].stavNaUcte());
        }
    }
}
