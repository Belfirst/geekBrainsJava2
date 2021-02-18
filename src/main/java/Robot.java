public class Robot implements Participant{

    private int maxHeight;
    private int maxLength;

    public Robot(int maxHeight, int maxLength) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    public boolean jump(int height){
        System.out.print("Robot jump: ");
        if(maxHeight >= height) return true;
        return  false;
    }

    public boolean run(int distance){
        System.out.print("Robot run: ");
        if(maxLength >= distance) return true;
        return  false;
    }
}
