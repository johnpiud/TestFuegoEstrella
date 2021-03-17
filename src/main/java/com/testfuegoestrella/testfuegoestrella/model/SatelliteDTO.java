package com.testfuegoestrella.testfuegoestrella.model;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para gestionar datos de un satélite.
 * @author john.pineros
 * @since 17/03/2021
 */
public class SatelliteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double distance;
    private List<String> message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
