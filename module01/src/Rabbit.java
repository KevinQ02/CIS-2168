public class Rabbit extends Animal {

    private boolean canSeeFoxNow = false;
    private boolean haveSeenFox = false;
    private int distanceToFox;
    private int directionToFox;
    private int directionToEdge;
    private int directionToBush;
    private int currentDirection;


    public Rabbit(Model model, int row, int column) {
        super(model, row, column);
    }


    int decideMove() {
        canSeeFoxNow = false;
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
            if (look(i) == Model.RABBIT) {
                canSeeFoxNow = haveSeenFox = true;
                directionToFox = i;
                distanceToFox = distance(i);
            }
        }
        if (haveSeenFox) {
            if (distanceToFox > 0) {
                distanceToFox--;
                return directionToFox;
            }
            else { // rabbit was here--where did it go?
                haveSeenFox = false;
                int currentDirection = Model.random(Model.MIN_DIRECTION,
                        Model.MAX_DIRECTION);
            }
        }

        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++) {
            if (look(i) == Model.FOX) {
                if (canMove(Model.turn(i, 5))) {
                    return Model.turn(i, 5);
                }
                else if (canMove(Model.turn(i, 2))) {
                    return Model.turn(i, 2);
                }
               else if (canMove(Model.turn(i, 4))) {
                    return Model.turn(i, 4);
                }
               else if (canMove(Model.turn(i, -5))) {
                    return Model.turn(i, -5);
                }
               else {
                    currentDirection = Model.random(Model.MIN_DIRECTION, Model.MAX_DIRECTION);
                    for (int l = 0; l < 8; l++) {
                        if (canMove(currentDirection))
                            return currentDirection;
                        else
                            currentDirection = Model.turn(currentDirection, 1);
                    }
                }
            }
        }
        return Model.STAY;
    }
}
