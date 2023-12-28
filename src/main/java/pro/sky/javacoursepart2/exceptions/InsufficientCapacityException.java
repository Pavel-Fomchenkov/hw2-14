package pro.sky.javacoursepart2.exceptions;

public class InsufficientCapacityException extends RuntimeException{

    public InsufficientCapacityException(String message) {
        super(message);
        System.out.println(message);
    }
}
