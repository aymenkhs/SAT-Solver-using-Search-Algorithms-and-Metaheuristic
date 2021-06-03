package part_2.algorithms;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Particle {

    private double p_best;
    private double best_evaluation;
    private double position;
    private double velocity;




    public Particle(double max_value, double initial_velocity) {

        Random random = new Random();

        position = Math.floor(random.nextDouble() * max_value);
        p_best = position;
        this.velocity = initial_velocity;

    }


    public static ArrayList<Integer> get_binary(double position, int num_variables){


        BigInteger big_num = BigDecimal.valueOf(position).toBigInteger();
        StringBuilder binary_string = new StringBuilder(big_num.toString(2));

        while (binary_string.length() != num_variables){

            binary_string.insert(0, "0");
        }

        ArrayList<Integer> binary = new ArrayList<>();

        for(int i = 0 ; i < binary_string.length(); i++) {

            int value = Character.getNumericValue(binary_string.charAt(i));
            binary.add(value);
        }


        return binary;
    }



    public ArrayList<Integer> get_binary(int num_variables){

        return get_binary(this.position, num_variables);
    }

    public double getBest_evaluation() {
        return best_evaluation;
    }

    public void setBest_evaluation(double best_evaluation) {
        this.best_evaluation = best_evaluation;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getP_best() {
        return p_best;
    }

    public void setP_best(double p_best) {
        this.p_best = p_best;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
