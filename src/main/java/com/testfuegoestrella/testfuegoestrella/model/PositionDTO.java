package com.testfuegoestrella.testfuegoestrella.model;

import java.io.Serializable;

/**
 * DTO para gestionar los datos de posición.
 * @author john.pineros
 * @since 17/03/2021
 */
public class PositionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private double x;
    private double y;

    public PositionDTO() {
        //default
    }
    public PositionDTO(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
