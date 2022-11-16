package db.exceptions;

public class DBIntegrityException extends RuntimeException {

    public DBIntegrityException(String msg){
        super(msg);
    }

}