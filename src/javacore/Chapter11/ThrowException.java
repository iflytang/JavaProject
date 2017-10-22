package javacore.Chapter11;

import java.io.EOFException;
import java.io.IOException;

/**
 * Created by tsf on 17-9-23.
 *
 * Throw an exception.
 */


public class ThrowException {

    // "throws" is used after a method statement, then you can throw corresponding exception in method.
    public void exceptionTest() throws FileFormatException{


        FileFormatException e = new FileFormatException("Self defined FileFormatException.");

        throw e;
    }

    public static void main(String[] args) {

        ThrowException throwException = new ThrowException();

        try{
        throwException.exceptionTest();
        } catch (FileFormatException e) {
            e.getStackTrace();
        }

    }
}

class FileFormatException extends IOException {

    FileFormatException() {}

    FileFormatException(String gripe) {
        super(gripe);
    }
}
