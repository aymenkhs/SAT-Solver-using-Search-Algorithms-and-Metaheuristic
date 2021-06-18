import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class Test {

    public static void main(String[] args) {

        //int dec = Integer.parseInt(bin, 2);
        //System.out.println(dec);

        double max_value = Math.pow(2, 20) - 1;

        double pmax_value = 1129170;
        BigInteger bigNum = BigDecimal.valueOf(max_value).toBigInteger();

        String bin = bigNum.toString(2);

        System.out.println(bin);

        bigNum = BigDecimal.valueOf(pmax_value).toBigInteger();

        bin = bigNum.toString(2);

        System.out.println(bin);

        System.out.println(Math.pow(2, 20) - 1);

        Random random = new Random();
        System.out.println("");

        for(int i = 0 ; i < 20 ;i ++){

            double r = random.nextDouble();

            System.out.println(r);
            System.out.println(Math.floor(r * max_value));
        }



        //System.out.println(Double.toString(max_value));



        //System.out.println("Particle : " + Particle.get_binary(1129170));




        System.out.println(bin);


    }
}
