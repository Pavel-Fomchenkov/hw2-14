package pro.sky.javacoursepart2.exceptions;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
