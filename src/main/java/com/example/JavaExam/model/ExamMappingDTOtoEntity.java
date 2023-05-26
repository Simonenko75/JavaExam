package com.example.JavaExam.model;

import com.example.JavaExam.repositoty.ClientsRepository;
import com.example.JavaExam.repositoty.RoomsRepository;
import com.example.JavaExam.service.dto.ClientsEntityDTO;
import com.example.JavaExam.service.dto.RoomsEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExamMappingDTOtoEntity {

    @Autowired
    public ClientsRepository clientsRepository;

    @Autowired
    public RoomsRepository roomsRepository;

    public ClientsEntity clientsDtoToEntity(ClientsEntityDTO clientsEntityDTO) {
        if (clientsEntityDTO == null) {
            return null;
        } else {
            ClientsEntity clientsEntity = new ClientsEntity();
            clientsEntity.setFullName(clientsEntityDTO.getFullName());
            clientsEntity.setRoomNumber(clientsEntityDTO.getRoomNumber());
            clientsEntity.setQuantity(clientsEntityDTO.getQuantity());
            clientsEntity.setSettled(clientsEntityDTO.getSettled());
            return clientsEntity;
        }
    }

    public ClientsEntityDTO clientsResponseEntityToDTO(ClientsEntity clientsEntity) {
        if (clientsEntity == null) {
            return null;
        } else {
            ClientsEntityDTO clientsResponseEntityDTO = new ClientsEntityDTO();
            clientsResponseEntityDTO.setId(clientsEntity.getId());
            clientsResponseEntityDTO.setFullName(clientsEntity.getFullName());
            clientsResponseEntityDTO.setRoomNumber(clientsEntity.getRoomNumber());
            clientsResponseEntityDTO.setQuantity(clientsEntity.getQuantity());
            clientsResponseEntityDTO.setSettled(clientsEntity.getSettled());
            return clientsResponseEntityDTO;
        }
    }

    public RoomsEntity roomsDtoToEntity(RoomsEntityDTO roomsEntityDTO) {
        if (roomsEntityDTO == null) {
            return null;
        } else {
            RoomsEntity roomsEntity = new RoomsEntity();
            roomsEntity.setNumber(roomsEntityDTO.getNumber());
            roomsEntity.setPlaceQuantity(roomsEntityDTO.getPlaceQuantity());
            roomsEntity.setFreeQuantity(roomsEntityDTO.getFreeQuantity());
            roomsEntity.setFreeRoom(roomsEntityDTO.getFreeRoom());
            return roomsEntity;
        }
    }

    public RoomsEntityDTO roomsResponseEntityToDTO(RoomsEntity roomsEntity) {
        if (roomsEntity == null) {
            return null;
        } else {
            RoomsEntityDTO roomsResponseEntityDTO = new RoomsEntityDTO();
            roomsResponseEntityDTO.setId(roomsEntity.getId());
            roomsResponseEntityDTO.setNumber(roomsEntity.getNumber());
            roomsResponseEntityDTO.setPlaceQuantity(roomsEntity.getPlaceQuantity());
            roomsResponseEntityDTO.setFreeQuantity(roomsEntity.getFreeQuantity());
            roomsResponseEntityDTO.setFreeRoom(roomsEntity.getFreeRoom());
            return roomsResponseEntityDTO;
        }
    }

    public ClientsEntity clientUpdate(ClientsEntity clientsEntity, ClientsEntityDTO clientsEntityDTO) {
        if (clientsEntityDTO != null) {
            clientsEntity.setFullName(clientsEntityDTO.getFullName());
            clientsEntity.setRoomNumber(clientsEntityDTO.getRoomNumber());
            clientsEntity.setQuantity(clientsEntityDTO.getQuantity());
            clientsEntity.setSettled(clientsEntityDTO.getSettled());
            return clientsEntity;
        }

        return null;
    }

    public RoomsEntity roomUpdate(RoomsEntity roomsEntity, RoomsEntityDTO roomsEntityDTO) {
        if (roomsEntityDTO != null) {
            roomsEntity.setNumber(roomsEntityDTO.getNumber());
            roomsEntity.setPlaceQuantity(roomsEntityDTO.getPlaceQuantity());
            roomsEntity.setFreeQuantity(roomsEntityDTO.getFreeQuantity());
            roomsEntity.setFreeRoom(roomsEntityDTO.getFreeRoom());
            return roomsEntity;
        }

        return null;
    }

}
