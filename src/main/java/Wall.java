public class Wall implements Obstacle{
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public boolean go(Participant participant){
        if(participant.jump(height)){
            System.out.println("Good job");
            return true;
        } else System.out.println("Fail");
        return false;
    }
}
