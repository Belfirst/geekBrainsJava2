public class Basic {
    public static void main(String[] args) {

        Participant[] participants = new Participant[]{
                new Cat(100,1000),
                new Human(500, 2000),
                new Robot(200,500)
        };
        Obstacle[] obstacles = new Obstacle[]{
                new Treadmill(1500),
                new Wall(50)
        };
        GoToCourse goToCourse = new GoToCourse();

        goToCourse.go(obstacles,participants);

    }
}
