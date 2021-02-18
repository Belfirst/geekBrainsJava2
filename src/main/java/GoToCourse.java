public class GoToCourse {
    public void go(Obstacle[] obstacles, Participant[] participants){
        for (Participant p:participants) {
            for  (Obstacle o:obstacles){
                if(!o.go(p)) break;
            }
        }
    }
}
