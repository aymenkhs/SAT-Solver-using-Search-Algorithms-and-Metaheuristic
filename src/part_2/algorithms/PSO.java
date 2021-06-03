package part_2.algorithms;

import utils.Algorithm;
import utils.sat_structure.SAT;

import java.util.ArrayList;
import java.util.Random;

public class PSO implements Algorithm {


    private double w, c1,c2;
    private double g_best;
    private double current_best_evaluation;

    private int num_particles;
    private double initial_velocity;
    private int max_iteration;

    private double max_value;

    private double time;

    private double percent_done;

    private ArrayList<Particle> particles;
    private SAT sat;

    private boolean goal_reached;

    ArrayList<Integer> solution = null;



    public PSO(SAT sat, int num_particles, float w, double c1, double c2, double initial_velocity, int max_iteration) {

        this.sat = sat;
        this.num_particles = num_particles;
        this.max_iteration = max_iteration;


        this.w = w;
        this.c1 = c1;
        this.c2 = c2;

        this.initial_velocity = initial_velocity;

        this.goal_reached = false;

        particles = new ArrayList<>();
    }


    private double evaluate_position(Particle particle){

        // fitness function is the number of satisfied clauses
        ArrayList<Integer> p_solution = particle.get_binary(sat.getNbVariables());
        return sat.get_nb_verified_clauses(p_solution);
    }


    private void init_particles(){

        double number_variables = sat.getNbVariables();
        this.max_value = Math.pow(2, number_variables) - 1;

        // 0 clauses resolved
        current_best_evaluation = 0;

        // initialize particles
        for(int i = 0 ; i <= num_particles; i++){

            // a random position is set in the particle's constructor
            Particle particle = new Particle(max_value, initial_velocity);

            // evaluate position
            double evaluation = evaluate_position(particle);
            particle.setBest_evaluation(evaluation);

            // add particle
            particles.add(particle);

            // updating best position
            if (evaluation > current_best_evaluation){
                current_best_evaluation = evaluation;
                g_best = particle.getPosition();
            }
        }
    }







    @Override
    public void solve(){


        long startTime = System.nanoTime();

        //long startTime = System.nanoTime();

        init_particles();

        int iteration = 0;

        while(  iteration < max_iteration &&
                current_best_evaluation != sat.getNbClauses()) {

            Random random = new Random();
            double new_velocity, new_position, r1, r2;

            for (Particle p: particles) {



                r1 = random.nextDouble();
                r2 = random.nextDouble();


                new_velocity = w * p.getVelocity()
                        + c1 * r1 * (p.getP_best() - p.getPosition())
                        + c2 * r2 * (g_best - p.getPosition());

                p.setVelocity(new_velocity);

                new_position  = Math.floor(p.getPosition() + new_velocity) % max_value;


                // respecting boundaries
                if(new_position > max_value) new_position = max_value;
                if (new_position < 0) new_position = 0;



                p.setPosition(new_position);

                double evaluation = evaluate_position(p);

                if(evaluation >= p.getBest_evaluation()) {

                    p.setP_best(new_position);
                    p.setBest_evaluation(evaluation);

                    if (evaluation >= current_best_evaluation) {
                        g_best = new_position;
                        current_best_evaluation = evaluation;
                    }

                }
            }


            iteration += 1;
        }

        long endTime = System.nanoTime();
        long results =  (endTime - startTime);
        this.time = (double) results/1000000000;

        this.solution = Particle.get_binary(g_best, sat.getNbVariables());

        if(current_best_evaluation == sat.getNbClauses()){
            this.goal_reached = true;
        }

        percent_done = (current_best_evaluation / sat.getNbClauses()) * 100;



    }

    @Override
    public double getPercent_done() {
        return percent_done;
    }

    @Override
    public boolean isSatisfiable() { return isGoal_reached(); }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public ArrayList<Integer> getSolution() {
        return solution;
    }

    @Override
    public boolean isGoal_reached() {
        return goal_reached;
    }
}
