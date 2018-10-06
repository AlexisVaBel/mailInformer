package controllers;

/**
 * Created by alexeybel on 04.10.18.
 */
public interface IConditionController {
    void    processConditions();
    boolean allProcessed();
    String  getInfo();
}
