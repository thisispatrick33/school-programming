public class Hlavna {
    public static void main(String[] args) {
        Citanie ct1 = new Citanie("C:\\Users\\Mamuss\\IdeaProjects\\school-programming\\Vlakna\\src\\tmp");
        Producent v1Pr1 = new Producent(ct1);
        Konzument v1K1 = new Konzument(ct1);

        v1K1.start();
        v1Pr1.start();
    }
}
