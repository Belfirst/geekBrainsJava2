public class Treadmill implements Obstacle {
    private int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public boolean go(Participant participant){
        if(participant.run(distance) ){
            System.out.println("Good job");
            return true;
        } else System.out.println("Fail");
        return  false;
    }
}
