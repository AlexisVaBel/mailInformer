package controllers;

/**
 * Created by alexeybel on 04.10.18.
 */
public interface IBasicController {
    boolean connect();
    boolean disconnect();
    void processConditions();
    void sendInfo();
}
