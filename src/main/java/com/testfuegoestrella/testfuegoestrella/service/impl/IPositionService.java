package com.testfuegoestrella.testfuegoestrella.service.impl;

import com.testfuegoestrella.testfuegoestrella.model.PositionDTO;
import com.testfuegoestrella.testfuegoestrella.model.SatelliteDTO;

import java.util.List;


/**
 * Interface para obtener la posición de la nave.
 * @author john.pineros
 * @since 17/03/2021
 */
public interface IPositionService {
    public PositionDTO getLocation(double[] arrayDistancias);
    public double[] getDistancesSatellites(List<SatelliteDTO> listSatellites);
    public PositionDTO getLocationNivel3(List<SatelliteDTO> listSatellites);
}
