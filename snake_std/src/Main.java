import java.awt.*;
import java.util.ArrayList;





public class Main {

    static void draw(ArrayList<Double> x,ArrayList<Double> y){
        if (x.size() == 1) {
          StdDraw.point(x.get(0),y.get(0));
            return;
        }


        for (int i = 0; i < x.size()-1; i++) {
            StdDraw.setPenColor(Color.cyan );
            StdDraw.line(x.get(i),y.get(i),x.get(i+1),y.get(i+1));
        }

    }


    static void food(double fx,double fy,double fr){
        StdDraw.setPenColor(Color.red);
        StdDraw.setPenRadius(fr);
        StdDraw.filledCircle(fx,fy,fr);


    }
    static void rock(double rfx,double rfy,double rfr){
        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.04);
        StdDraw.filledCircle(rfx,rfy,rfr);


    }
    static void timefood(double tfx,double tfy,double tfr){


        StdDraw.setPenColor(Color.yellow);

        StdDraw.setPenRadius(0.03);
        StdDraw.filledCircle(tfx,tfy,tfr);

    }
static boolean crash(ArrayList<Double> snakex,ArrayList<Double> snakey){
       double x= (double) snakex.get(0);
       double y= (double) snakey.get(0);
       if (x <= 0 || x >= 1 || y <= 0 || y >= 1)
    return true ;
    for (int i = 0; i < snakex.size(); i++) {
        if (x==snakex.get(i) && y==snakey.get(i)){
           return true;
        }
    }


       return false;
}
static void move(ArrayList<Double> snakex,ArrayList<Double> snakey,int f){

    for (int i = snakex.size()-1; i >0 ; i--) {
         snakex.set(i,snakex.get(i-1));
        snakey.set(i,snakey.get(i-1));
    }
switch (f){
    case 1:
     snakex.set(0,snakex.get(0)+0.03);
     break;
    case 2:
        snakey.set(0,snakey.get(0)-0.03);
        break;
    case 3:
        snakex.set(0,snakex.get(0)-0.03);
        break;
    case 0:
        snakey.set(0,snakey.get(0)+0.03);
        break;
}




}
static boolean eat(double x,double y,double fx,double fy,double fr){

    return  Math.sqrt(Math.pow(x-fx,2)+Math.pow(y-fy,2))< fr+0.02;

}


    public static void main(String[] args) {
          double x=0;
          double y=0;
        ArrayList<Double> snakex=new ArrayList<>();
        ArrayList<Double> snakey=new ArrayList<>();
        snakex.add(0.5);
        snakey.add(0.5);
        double fx=0;
        double fr=0;
        double tfx=0;
        double tfy=0;
        double tfr=0;
        double rfx=0;
        double rfy=0;
        double rfr=0;
        int d=0;
        double fy=0;
        boolean ok;
        boolean okey;

        while (true){
            StdDraw.clear();
            draw(snakex,snakey);


           move(snakex,snakey,d);

               if (d!=0 && StdDraw.isKeyPressed(40)){
                   d=2;

               }
            if (d!=2 && StdDraw.isKeyPressed(38)){
                d=0;

            } if (d!=1 && StdDraw.isKeyPressed(37)){
                d=3;

            } if (d!=3 && StdDraw.isKeyPressed(39)){
                d=1;

            }
            if (fr==0) {
                fx = Math.random();
                fy = Math.random();
                fr = Math.random() * 0.03f+0.02f;

            } if (tfr==0) {
                tfx = Math.random();
                tfy = Math.random();
                tfr = Math.random() * 0.03f+0.02f;

            }
            if (rfr==0) {
                rfx = Math.random();
                rfy = Math.random();
                rfr = Math.random() * 0.03f+0.02f;

            }
            food(fx, fy, fr);
            StdDraw.setPenRadius(0.05);
           StdDraw.setPenColor(Color.cyan);
           StdDraw.point(x,y);

              boolean cok=crash(snakex,snakey);
            if(cok){
                  if(snakex.get(0) > 1) {
                      snakex.replaceAll(aDouble -> 1 - aDouble);
                  }
                  else if(snakex.get(0) < 0) {
                      snakex.replaceAll(aDouble -> 1 + aDouble);
                  }
                  else if(snakey.get(0) >= 1) {
                      snakey.replaceAll(aDouble -> 1 - aDouble);
                  }
                  else if(snakey.get(0) <= 0) {
                      snakey.replaceAll(aDouble -> 1 + aDouble);
                  }

                 /*

 for(int i = 0; i < snakex.size(); i++) {
    snakex.set(i,1 +- snakex.get(i));
    }
    for(int i = 0; i < snakey.size(); i++) {
    snakey.set(i,1 +- snakey.get(i));
    }
*/
            }


            if (eat(snakex.get(0),snakey.get(0),fx,fy,fr)) {
                snakey.add(snakey.get(snakey.size()-1));
                snakex.add(snakex.get(snakex.size()-1));
                fr=0;
            } if (eat(snakex.get(0),snakey.get(0),rfx,rfy,rfr)){
                rfr=0;
                break;

            }if (snakex.size()%3==0){
               rock(rfx,rfy,rfr);
               rfr-=0.00000001;
            }

            if (snakex.size()%5==0){

                timefood(tfx,tfy,tfr);

                tfr-=0.0000001;
               if (eat(snakex.get(0),snakey.get(0),tfx,tfy,tfr)){
                   snakey.add(snakey.get(snakey.size()-1));
                   snakex.add(snakex.get(snakex.size()-1));
                   tfr=0;
               }

            }
          StdDraw.pause(150);


        }
       StdDraw.setPenColor(Color.black);
        StdDraw.text(0.5,0.5,"you lose");
         StdDraw.text(0.5,0.95,"scoore :"+snakex.size());

    }
}