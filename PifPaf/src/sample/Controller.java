package sample;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;


public class Controller {
    Random rn = new Random();
    int choose = rn.nextInt(2);
    int pointPlayer = 0;
    int pointBot = 0;
    long zostavaCas = 300;
    long startTime = System.currentTimeMillis();
    double obtiaznost = 40;

    String [] najlepsi = new String[10];

    String ipAdd = "";
    boolean isNetworkGame = false;
    boolean prvyStart=true;
    boolean jeServer = false;
    server localServer = new server();


    @FXML
    Pane hraciaPlocha, login, end, scoreboard;

    @FXML
    Circle modry, cerveny;

    @FXML
    Rectangle side;

    @FXML
    Slider slider;

    @FXML
    Label stav, winner, ranks;

    @FXML
    Label cas;

    @FXML
    CheckBox sietova;

    @FXML
    TextField ip, meno;


    AnimationTimer animacie = new AnimationTimer(){
        private long lastUpdate = 0 ;
        private long lastMovementChange = 0;

        private boolean tmp = true;

        private long x = 0;
        private long y = 0;

        @Override
        public void handle(long now){

            if(now - lastMovementChange >= 500_000_000){
                x = rn.nextInt((int)((100-obtiaznost)*0.1))*(rn.nextInt(3)-1);
                y = rn.nextInt((int)((100-obtiaznost)*0.1))*(rn.nextInt(3)-1);
                lastMovementChange = now;
            }
            if(!isNetworkGame){
                botMovement(choose == 0 ? modry : cerveny, x, y);
                botClick();
            }


            if(tmp){
                if (now - lastUpdate >= 70_000_000*(obtiaznost+1)) {
                    visible();
                    lastUpdate = now ;
                    tmp = false;
                }
            }
            else {
                if (now - lastUpdate >= 45_000_000*(obtiaznost+1)) {
                    invisible();
                    lastUpdate = now ;
                    tmp = true;
                }
            }
            zostavaCas = 30 - (int)((System.currentTimeMillis()-startTime)/1000);
            cas.setText((zostavaCas/60)+":"+((zostavaCas-((zostavaCas/60)*60))));

            if(zostavaCas == 0){
                if(pointPlayer == pointBot){
                    winner.setText("Je to remiza! Skoro si to dal!");
                }
                else if (pointPlayer > pointBot){
                    winner.setText("Vyhral si! Tvoje skore je "+(2*(pointPlayer-pointBot)*(100-obtiaznost)));
                    String tmp;
                    for (int i = 0; i<10; i++){
                        if(najlepsi[i].contains("#")){
                            tmp = najlepsi[i];
                            najlepsi[i]=meno.getText()+";"+(2*(pointPlayer-pointBot)*(100-obtiaznost));
                            String tmp2;
                            for (int r = i+1; r <10; r++){
                                tmp2=najlepsi[r];
                                najlepsi[r]=tmp;
                                tmp = tmp2;
                            }
                            break;
                        }
                        else if ((2*(pointPlayer-pointBot)*(100-obtiaznost))>Double.parseDouble(najlepsi[i].substring(najlepsi[i].indexOf(";")+1))){
                            tmp = najlepsi[i];
                            najlepsi[i]=meno.getText()+";"+(2*(pointPlayer-pointBot)*(100-obtiaznost));
                            String tmp2;
                            for (int r = i+1; r <10; r++){
                                tmp2=najlepsi[r];
                                najlepsi[r]=tmp;
                                tmp = tmp2;
                            }
                            break;
                        }
                    }
                    try {
                        BufferedWriter out = new BufferedWriter(new FileWriter("src/sample/scores"));
                        for (int i=0; i<10; i++){
                            out.write(najlepsi[i]);
                            out.newLine();
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    winner.setText("Prehral si! Viac stastia nabuduce.....");
                }
                end.setVisible(true);
                this.stop();
                cerveny.setVisible(true);
                modry.setVisible(true);

            }
        }
    };

    @FXML
    void initialize() {
        scoreboard.setVisible(false);
        end.setVisible(false);
        if (prvyStart && jeServer){
            localServer.start();
            prvyStart = false;
        }
    }

    public void kruhy(){
        modry.setTranslateX(400);
        modry.setTranslateY(350);
        cerveny.setTranslateX(600);
        cerveny.setTranslateY(350);
        if(choose == 0){
            cerveny.setVisible(true);
            side.setFill(Color.rgb(255, 31, 55));
            cerveny.setOnMouseDragged(event -> {
                try {
                    drag(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            modry.setOnMouseDragged(null);
            modry.setOnMouseClicked(event -> chytilSom());
            cerveny.setOnMouseClicked(null);

        }
        else {
            modry.setVisible(true);
            side.setFill(Color.rgb(30, 144, 255));
            modry.setOnMouseDragged(event -> {
                try {
                    drag(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            cerveny.setOnMouseDragged(null);
            cerveny.setOnMouseClicked(event -> chytilSom());
            modry.setOnMouseClicked(null);
        }
    }

    public void visible(){
        if(choose == 0){
            modry.setVisible(true);
        }
        else {
            cerveny.setVisible(true);
        }
    }
    public void invisible(){
        if(choose == 0){
            modry.setVisible(false);
        }
        else {
            cerveny.setVisible(false);
        }
    }


    public void drag(MouseEvent event) throws IOException{
        Node n = (Node)event.getSource();
        if((hraciaPlocha.getWidth() > (n.getTranslateX() + event.getX() + 30) && (n.getTranslateX() + event.getX() - 30)  > 0)){
            n.setTranslateX(n.getTranslateX() + event.getX());
        }
        if(hraciaPlocha.getHeight() > (n.getTranslateY() + event.getY() + 30) && (n.getTranslateY() + event.getY() - 30) > 0) {
            n.setTranslateY(n.getTranslateY() + event.getY());
        }
        if (isNetworkGame){
            odosielanie("BLUE " + modry.getTranslateX() + " " + modry.getTranslateY());
            odosielanie("RED " + cerveny.getTranslateX() + " " + cerveny.getTranslateY());
        }
    }

    public void botMovement(Circle bot, float moveX, float moveY){
        if((hraciaPlocha.getWidth() > (moveX + bot.getTranslateX() + 30) && (moveX + bot.getTranslateX() - 30)  > 0)){
            bot.setTranslateX(moveX + bot.getTranslateX());
        }
        if((hraciaPlocha.getHeight() > (moveY + bot.getTranslateY() + 30) && (moveY + bot.getTranslateY() - 30)  > 0)){
            bot.setTranslateY(moveY + bot.getTranslateY());
        }
    }

    public void chytilSom(){
        pointPlayer ++;
        stav.setText(pointPlayer+" : "+pointBot);
    }

    public void botClick(){
        if(rn.nextInt(500) == 0){
            pointBot ++;
            stav.setText(pointPlayer+" : "+pointBot);
        }
    }

    public void restart(){
        end.setVisible(false);
        pointBot = 0;
        pointPlayer = 0;
        zostavaCas = 0;
        stav.setText(0+" : "+0);
        animacie.stop();
        obtiaznost = slider.getValue();
        startTime = System.currentTimeMillis();
        choose = rn.nextInt(2);

        kruhy();
        animacie.start();
        try{
            odosielanie("R");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class UDPEchoReader extends Thread{
        public boolean active;
        public DatagramSocket ds;

        public UDPEchoReader(DatagramSocket ds){
            this.ds = ds;
            active = true;
        }
        @Override
        public void run(){
            byte bufer[] = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bufer, bufer.length);
            while (active){
                try{
                    ds.receive(dp);
                }catch (IOException e){e.printStackTrace(); active=false;}
                String prijataSprava = new String(dp.getData(),0,dp.getLength());
                System.out.println("Prijate zo servera: " + prijataSprava);
            }
        }
    }

    public void odosielanie(String mojaSprava) throws IOException {
        int port = 55555;
        InetAddress adresa = InetAddress.getByName(ipAdd);
        DatagramSocket ds = new DatagramSocket();
        UDPEchoReader reader = new UDPEchoReader(ds);
        reader.setDaemon(true);
        reader.start();
        DatagramPacket dp = new DatagramPacket(mojaSprava.getBytes(), mojaSprava.length(), adresa, port);
        ds.send(dp);
    }

    protected class server extends Thread {
        @Override
        public void run(){
            int port = 55555;
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(port);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Kanál na porte " + port + " otvorený");
            byte buffer[] = new byte[1024];

            DatagramPacket dp = new DatagramPacket(buffer,buffer.length);
            while (true) {
                try {
                    ds.receive(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(ipAdd);
                String sprava = new String(dp.getData(), 0, dp.getLength());
                System.out.println(sprava);


                if (sprava.startsWith("C")){

                }
                if(sprava.startsWith("BLUE") ){
                    String helparr [] ;
                    helparr = sprava.split(" ");
                    modry.setTranslateX(Double.parseDouble(helparr[1]));
                    modry.setTranslateY(Double.parseDouble(helparr[2]));
                }
                if(sprava.startsWith("RED") ){
                    String helparr [];
                    helparr = sprava.split(" ");
                    cerveny.setTranslateX(Double.parseDouble(helparr[1]));
                    cerveny.setTranslateY(Double.parseDouble(helparr[2]));
                }
                if(sprava.equals("R")){
                    restart();

                }

            }
        }
    }

    public void sietova() throws IOException{
        isNetworkGame = sietova.isSelected();
        if(isNetworkGame){
            ipAdd = ip.getText();

        }
    }

    public void play() throws IOException {
        if(meno.getText().compareTo("")!=0){
            BufferedReader in = new BufferedReader(new FileReader("src/sample/scores"));
            for (int i = 0; i < 10; i++){
                najlepsi[i]=in.readLine();
            }
            login.setVisible(false);
            kruhy();
            animacie.start();
        }

    }

    public  void top(){
        String tmp = "";
        for (int i = 0; i < 10; i++){
            if(!najlepsi[i].contains("#")){
                tmp += "#"+(i+1)+" "+najlepsi[i].substring(0,najlepsi[i].indexOf(";"))+" : "+najlepsi[i].substring(najlepsi[i].indexOf(";")+1)+"\n";
            }
            else {
                tmp += "#"+(i+1)+" Este nie je\n";
            }


        }
        ranks.setText(tmp);
        scoreboard.setVisible(true);
    }

    public  void closeScore(){
        scoreboard.setVisible(false);
    }



}
