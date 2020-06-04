public class RegistrationNumberFormatException extends RuntimeException{
    public RegistrationNumberFormatException(){

    }

    public RegistrationNumberFormatException(String message){
        super(message);
    }

    public RegistrationNumberFormatException(String message, Throwable origin){
        super(message,origin);
    }
}
