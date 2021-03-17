package com.testfuegoestrella.testfuegoestrella.model;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para gestionar request servicio POST nivel 3.
 * @author john.pineros
 * @since 17/03/2021
 */
public class RequestSatelliteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private double distance;
    private List<String> message;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
