package com.testfuegoestrella.testfuegoestrella.service;

import com.testfuegoestrella.testfuegoestrella.model.SatelliteDTO;
import com.testfuegoestrella.testfuegoestrella.service.impl.IMessageService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clase para realizar las operaciones asociadas al mensaje enviado por la nave.
 * @author john.pineros
 * @since 17/03/2021
 */
@Service
public class MessageService implements IMessageService {

    /**
     * Método para obtener el mensaje de acuerdo a lo recibido en cada satélite.
     * @param listMessages Lista de los mensajes que llegan a cada satélite.
     * @return Retorna String con el mensaje obtenido.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public String getMessage(List<List<String>> listMessages) {
        try {
            List<String> listUniqueWords = getUniqueWords(listMessages);
            if(listUniqueWords != null && !listUniqueWords.isEmpty()) {
                if(!getValidateSizeMessage(listUniqueWords.size(), listMessages)) return "";
                List<List<String>> listMessagesClean = getMessagesRemoveGap(listUniqueWords.size(), listMessages);
                return getMessageClean(listMessagesClean);
            }
            return "";
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener los mensajes que recibe cada satélite.
     * @param listSatellites Lista de satélites.
     * @return Retorna lista de arreglos de String organizado.
     * @author john.pineros
     * @since 17/03/2021
     */
    @Override
    public List<List<String>> getMessagesSatellites(List<SatelliteDTO> listSatellites) {
        try {
            List<List<String>> listMessages = new ArrayList<>();
            for(SatelliteDTO satelliteDTO : listSatellites) {
                listMessages.add(satelliteDTO.getMessage());
            }
            return listMessages;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener las palabra de los mensajes sin repetir.
     * @param listMessages Lista de los mensajes que llegan a cada satélite.
     * @return Retorna lista de Strings.
     * @author john.pineros
     * @since 17/03/2021
     */
    private List<String> getUniqueWords(List<List<String>> listMessages) {
        try {
            List<String> listUniqueWords = new ArrayList<>();
            for(List<String> message : listMessages) {
                    listUniqueWords = Stream.concat(listUniqueWords.stream(), message.stream()).distinct().collect(Collectors.toList());}
            listUniqueWords.remove("");
            return listUniqueWords;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para validar que la cantidad de datos de cada mensaje obtenido por los satélites tenga una cantidad mínima.
     * @param lenghtWords Tamaño del mensaje, cantidad de palabras.
     * @param listMessages Lista de los mensajes que llegan a cada satélite.
     * @return Retorna boolean con el resultado de la validación. True cantidad de datos del mensaje correcto, False en caso contrario
     * @author john.pineros
     * @since 17/03/2021
     */
    private boolean getValidateSizeMessage(int lenghtWords, List<List<String>> listMessages) {
        try {
            for(List<String> message : listMessages) {
                if(message.size() < lenghtWords) return false;
            }
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para remover el desfase que se presenta en el mensaje asumiendo un desfase de izquierda a derecha.
     * @param lenghtWords Tamaño del mensaje, cantidad de palabras.
     * @param listMessagesGap Lista de los mensajes que llegan a cada satélite.
     * @return Retorna lista de mensajes (String[]) sin desfase.
     * @author john.pineros
     * @since 17/03/2021
     */
    private List<List<String>> getMessagesRemoveGap(int lenghtWords, List<List<String>> listMessagesGap) {
        try {
            List<List<String>> listMessages = new ArrayList<>();
            for(List<String> messageGap : listMessagesGap) {
                List<String> message = messageGap.subList(messageGap.size() - lenghtWords, messageGap.size());
                listMessages.add(message);
            }
            return listMessages;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para obtener el mensaje de acuerdo al mensaje que recibe cada satélite procesado.
     * @param listMessages Lista de los mensajes que llegan a cada satélite procesado.
     * @return Retorna String con el mensaje obtenido.
     * @author john.pineros
     * @since 17/03/2021
     */
    private String getMessageClean(List<List<String>> listMessages) {
        try {
            String messageFinal = "";
            for(List<String> message : listMessages) {
                if(message.size() > 0 && !message.get(0).isEmpty()) {
                    messageFinal = messageFinal.concat(message.get(0)).concat(" ");
                    listMessages.forEach(str-> str.remove(0));
                    return messageFinal + getMessageClean(listMessages);
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
