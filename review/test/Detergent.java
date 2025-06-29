package review.test;


import review.test.Cleanser;

public class Detergent extends Cleanser {
    private String specialIngredient;

    @Override
    public void scrub(Brush b) {
        System.out.println("Gently scrubbing with detergent...");
        super.scrub(b); // call the base class method
    }

    public void foam() {
        System.out.println("Making foam.");
    }
}