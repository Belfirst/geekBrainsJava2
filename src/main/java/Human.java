public class Human implements Participant {

    private int maxHeight;
    private int maxLength;

    public Human(int maxHeight, int maxLength) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    public boolean jump(int height){
        System.out.print("Human jump: ");
        if(maxHeight >= height) return true;
        return  false;
    }

    public boolean run(int distance){
        System.out.print("Human run: ");
        if(maxLength >= distance) return true;
        return  false;
    }
}
