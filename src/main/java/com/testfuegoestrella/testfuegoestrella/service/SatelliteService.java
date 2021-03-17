package com.testfuegoestrella.testfuegoestrella.service;

import com.testfuegoestrella.testfuegoestrella.model.PositionDTO;
import com.testfuegoestrella.testfuegoestrella.model.RequestSatelliteDTO;
import com.testfuegoestrella.testfuegoestrella.model.ResponsePositionMessageDTO;
import com.testfuegoestrella.testfuegoestrella.model.SatelliteDTO;
import com.testfuegoestrella.testfuegoestrella.service.impl.IMessageService;
import com.testfuegoestrella.testfuegoestrella.service.impl.IPositionService;
import com.testfuegoestrella.testfuegoestrella.service.impl.ISatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase para realizar las operaciones asociadas al mensaje enviado por la nave.
 * @author john.pineros
 * @since 17/03/2021
 */
@Service
public class SatelliteService implements ISatelliteService {

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IMessageService messageService;

    private static List<SatelliteDTO> satellites = null;

    /**
     * Método para obtener los datos de posición y mensaje para el servicio nivel 2.
     * @param listSatellites Lista de satélites con datos.
     * @return Retorna objeto ResponsePositionMessageDTO con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public ResponsePositionMessageDTO getPositionMessage(List<SatelliteDTO> listSatellites) {
        try {
            double[] arrayDistances = positionService.getDistancesSatellites(listSatellites);
            PositionDTO positionDTO = positionService.getLocation(arrayDistances);
            return getResponsePositionMessage(positionDTO, listSatellites);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para guardar los datos que llegan a cada satélite.
     * @param nombreSatelite Nombre del satélite.
     * @param requestSatelliteDTO Objeto RequestSatelliteDTO con los datos recibidos.
     * @return Retorna objeto SatelliteDTO con los datos organizados.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public SatelliteDTO setSatelliteData(String nombreSatelite, RequestSatelliteDTO requestSatelliteDTO) {
        try {
            SatelliteDTO satelliteDTO = new SatelliteDTO();
            if(satellites == null) {
                satellites = new ArrayList<>();
            }
            List<SatelliteDTO> satellite = satellites.stream().filter(sat->sat.getName().equals(nombreSatelite)).collect(Collectors.toList());
            if(satellite.size() > 0) {
                satellite.get(0).setMessage(requestSatelliteDTO.getMessage());
                satellite.get(0).setDistance(requestSatelliteDTO.getDistance());
                satelliteDTO = satellite.get(0);
            } else {
                satelliteDTO.setName(nombreSatelite);
                satelliteDTO.setDistance(requestSatelliteDTO.getDistance());
                satelliteDTO.setMessage(requestSatelliteDTO.getMessage());
                satellites.add(satelliteDTO);
            }
            return satelliteDTO;
        } catch(Exception e) {
            e.printStackTrace();
            return new SatelliteDTO();
        }
    }

    /**
     * Método para obtener los datos de posición y mensaje para el servicio nivel 3.
     * @return Retorna objeto ResponsePositionMessageDTO con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public ResponsePositionMessageDTO getPositionMessagenivel3() {
        try {
            if(satellites != null && satellites.size() > 1) {
                PositionDTO positionDTO = positionService.getLocationNivel3(satellites);
                return getResponsePositionMessage(positionDTO, satellites);
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener los datos de la posición y el mensaje de forma organizada.
     * @param positionDTO Objeto PositionDTO con los datos de la ubicación de la nave.
     * @param listSatellites Lista de datos de los satélites.
     * @return objeto ResponsePositionMessageDTO con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    private ResponsePositionMessageDTO getResponsePositionMessage(PositionDTO positionDTO, List<SatelliteDTO> listSatellites) {
        try {
            ResponsePositionMessageDTO responsePositionMessageDTO = new ResponsePositionMessageDTO();
            if(positionDTO == null) return null;
            responsePositionMessageDTO.setPosition(positionDTO);
            List<List<String>> listMessage = messageService.getMessagesSatellites(listSatellites);
            String message = messageService.getMessage(listMessage);
            if(message == null || message.isEmpty()) return null;
            responsePositionMessageDTO.setMessage(message);
            return responsePositionMessageDTO;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
