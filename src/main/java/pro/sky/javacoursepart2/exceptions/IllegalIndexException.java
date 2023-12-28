package pro.sky.javacoursepart2.exceptions;

public class IllegalIndexException extends RuntimeException{

    public IllegalIndexException(String message) {
        super(message);
        System.out.println(message);
    }
}
