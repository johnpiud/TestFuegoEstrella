package com.testfuegoestrella.testfuegoestrella.service.impl;

import com.testfuegoestrella.testfuegoestrella.model.RequestSatelliteDTO;
import com.testfuegoestrella.testfuegoestrella.model.ResponsePositionMessageDTO;
import com.testfuegoestrella.testfuegoestrella.model.SatelliteDTO;

import java.util.List;

/**
 * Interface para trabajar los datos de un satelite.
 * @author john.pineros
 * @since 17/03/2021
 */
public interface ISatelliteService {
    public SatelliteDTO setSatelliteData(String nombreSatelite, RequestSatelliteDTO requestSatelliteDTO);
    public ResponsePositionMessageDTO getPositionMessagenivel3();
    public ResponsePositionMessageDTO getPositionMessage(List<SatelliteDTO> listSatellites);
}
