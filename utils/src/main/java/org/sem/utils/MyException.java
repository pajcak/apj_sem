package org.sem.utils;

/**
 *
 * @author Skarab
 */
public class MyException extends Exception {

    public MyException(Throwable t) {
        super(t);
    }

    public MyException(String mesage) {
        super(mesage);
    }
}
