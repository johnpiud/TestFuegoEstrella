package com.testfuegoestrella.testfuegoestrella.model;

import java.io.Serializable;
import java.util.List;

/**
 * DTO para gestionar request servicio nivel 2.
 * @author john.pineros
 * @since 17/03/2021
 */
public class RequestSatellitesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SatelliteDTO> satellites;

    public List<SatelliteDTO> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteDTO> satellites) {
        this.satellites = satellites;
    }
}
