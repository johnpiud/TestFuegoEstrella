package com.testfuegoestrella.testfuegoestrella.service.impl;

import com.testfuegoestrella.testfuegoestrella.model.RequestSatellitesDTO;
import com.testfuegoestrella.testfuegoestrella.model.SatelliteDTO;

import java.util.List;

/**
 * Interface para obtener el mensaje de la nave.
 * @author john.pineros
 * @since 17/03/2021
 */
public interface IMessageService {
    public String getMessage(List<List<String>> listMessages);
    public List<List<String>> getMessagesSatellites(List<SatelliteDTO> listSatellites);
}
