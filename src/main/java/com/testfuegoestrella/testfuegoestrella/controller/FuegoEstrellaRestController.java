package com.testfuegoestrella.testfuegoestrella.controller;

import com.testfuegoestrella.testfuegoestrella.model.*;
import com.testfuegoestrella.testfuegoestrella.service.impl.IMessageService;
import com.testfuegoestrella.testfuegoestrella.service.impl.IPositionService;
import com.testfuegoestrella.testfuegoestrella.service.impl.ISatelliteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sun.security.provider.certpath.OCSPResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/fuegoEstrella")
public class FuegoEstrellaRestController {

    @Autowired
    private ISatelliteService satelliteService;

    /**
     * Servicio para obtener la posición y el mensaje de la nave.
     * @param satellites Objeto RequestSatellitesDTO con los datos que recibe cada satélite.
     * @param response Objeto HttpServletResponse response del servicio.
     * @return Retorna objeto ResponsePositionMessageDTO con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    @PostMapping(path = "/topsecret")
    public ResponsePositionMessageDTO getPositionMessage(@RequestBody RequestSatellitesDTO satellites, HttpServletResponse response) {
        ResponsePositionMessageDTO responsePositionMessageDTO = new ResponsePositionMessageDTO();
        try {
            responsePositionMessageDTO = satelliteService.getPositionMessage(satellites.getSatellites());;
            if(responsePositionMessageDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return responsePositionMessageDTO;
    }

    /**
     * Servicio para recibir los datos reportados a un satélite determinado.
     * @param nameSatellite Nombre del satélite.
     * @param satellite Objeto RequestSatelliteDTO con los datos recibidos.
     * @return Retorna objeto SatelliteDTO con los datos organizados.
     * @author john.pineros
     * @since 17/03/2021
     */
    @PostMapping(path = "/topsecret_split/{satellite_name}")
    public SatelliteDTO setSatelliteData(@PathVariable("satellite_name") String nameSatellite, @RequestBody RequestSatelliteDTO satellite) {
        try {
            return satelliteService.setSatelliteData(nameSatellite, satellite);
        } catch(Exception e) {
            e.printStackTrace();
            return new SatelliteDTO();
        }
    }

    /**
     * Servicio para consultar la posición y mensaje de acuerdo a los datos reportados.
     * @param response Objeto HttpServletResponse response del servicio.
     * @return Retorna objeto ResponsePositionMessageDTO con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    @GetMapping(path = "/topsecret_split")
    public ResponsePositionMessageDTO getPositionMessagenivel3(HttpServletResponse response) {
        ResponsePositionMessageDTO responsePositionMessageDTO = new ResponsePositionMessageDTO();
        try {
            responsePositionMessageDTO = satelliteService.getPositionMessagenivel3();
            if(responsePositionMessageDTO == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return responsePositionMessageDTO;
    }
}
