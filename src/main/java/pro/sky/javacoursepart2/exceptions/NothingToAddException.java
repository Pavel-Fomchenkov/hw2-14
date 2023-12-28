package pro.sky.javacoursepart2.exceptions;

public class NothingToAddException extends RuntimeException{
    public NothingToAddException(String message) {
        super(message);
        System.out.println(message);
    }
}
