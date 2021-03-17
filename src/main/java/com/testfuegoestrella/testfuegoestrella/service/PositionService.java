package com.testfuegoestrella.testfuegoestrella.service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.testfuegoestrella.testfuegoestrella.model.PositionDTO;
import com.testfuegoestrella.testfuegoestrella.model.SatelliteDTO;
import com.testfuegoestrella.testfuegoestrella.service.impl.IPositionService;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase para realizar las operaciones asociadas a la posición de de la nave.
 * @author john.pineros
 * @since 17/03/2021
 */
@Service
public class PositionService implements IPositionService {
    private static final double[][] positionSatellites = new double[][]{
            {-500, -200},//Kenobi
            {100, -100},//Skywalker
            {500, 100} //Solo
    };

    /**
     * Método para obtener la posición de la nave con respecto a cada satélite.
     * @param arrayDistancias Arreglo de doubles con los datos de la distancia hacia cada satélite.
     * @return Retorna objeto PositionDTO con los datos de la ubicación en x, y.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public PositionDTO getLocation(double[] arrayDistancias) {
        try {
            return getPosition(positionSatellites, arrayDistancias);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener la posición de la nave.
     * @param arrayPositionSatellites Array bidimensional con la posición de los satélites.
     * @param arrayDistances Array con la distancia de la nave con respecto a cada satélite.
     * @return Retorna objeto PositionDTO con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    private PositionDTO getPosition(double[][] arrayPositionSatellites, double[] arrayDistances) {
        try {
            TrilaterationFunction trilaterationFunction = new TrilaterationFunction(arrayPositionSatellites, arrayDistances);
            NonLinearLeastSquaresSolver nLeastSquareSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
            double[] positionSolver = nLeastSquareSolver.solve().getPoint().toArray();
            if(positionSolver != null && positionSolver.length > 1) {
                return new PositionDTO(positionSolver[0], positionSolver[1]);
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener las distancias de cada satélite.
     * @param listSatellites Lista de satélites.
     * @return Retorna arreglo de dobles con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public double[] getDistancesSatellites(List<SatelliteDTO> listSatellites) {
        try {
            double[] arrayDistances = new double[listSatellites.size()];
            for(int i = 0; i < listSatellites.size(); i++) {
                arrayDistances[i] = listSatellites.get(i).getDistance();
            }
            return arrayDistances;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener la posición de la nave para el servicio nivel 3.
     * @param listSatellites Lista de satélites con datos reportados.
     * @return Retorna objeto PositionDTO con los datos de las coordenadas x, y.
     * @author john.pineros
     * @since 17/02021
     */
    @Override
    public PositionDTO getLocationNivel3(List<SatelliteDTO> listSatellites) {
        try {
            double [][] positions = getPositionSatellitesnivel3(listSatellites);
            if(positions != null) {
                double[] arrayDistances = getDistancesSatellites(listSatellites);
                return getPosition(positions, arrayDistances);
            }
            return null;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener los datos datos de las posiciones de los satélites para el nivel 3.
     * @param listSatellites Lista de satélites que han reportado datos para ubicar la nave.
     * @return Retorna arreglo bidimensional de doubles con los datos obtenidos.
     * @author john.pineros
     * @since 17/03/2021
     */
    private double[][] getPositionSatellitesnivel3(List<SatelliteDTO> listSatellites) {
        try {
            double [][] positions = new double[listSatellites.size()][];
            for(int i = 0; i < listSatellites.size(); i++) {
                switch (listSatellites.get(i).getName()) {
                    case "kenobi":
                        positions[i] = positionSatellites[0];
                        break;
                    case "skywalker":
                        positions[i] = positionSatellites[1];
                        break;
                    case "solo":
                        positions[i] = positionSatellites[2];
                        break;
                }
            }
            return positions;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
