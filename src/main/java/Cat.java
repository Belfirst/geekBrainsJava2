public class Cat implements Participant {
    private int maxHeight;
    private int maxLength;

    public Cat(int maxHeight, int maxLength) {
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean jump(int height){
        System.out.print("Cat jumped: ");
        if(maxHeight >= height) return true;
        return  false;
    }

    public boolean run(int distance){
        System.out.print("Cat run: ");
        if(maxLength >= distance) return true;
        return  false;
    }
}
