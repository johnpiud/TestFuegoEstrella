package com.testfuegoestrella.testfuegoestrella.model;

import java.io.Serializable;

/**
 * DTO para dar respuesta de la posición y el mensaje obtenido.
 * @author john.pineros
 * @since 17/03/2021
 */
public class ResponsePositionMessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private PositionDTO position;
    private String message;

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
