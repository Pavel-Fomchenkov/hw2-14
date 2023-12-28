package pro.sky.javacoursepart2.exceptions;

public class IllegalCapacityException extends RuntimeException {
    public IllegalCapacityException(String message){
        super(message);
        System.out.println(message);
    }


}
