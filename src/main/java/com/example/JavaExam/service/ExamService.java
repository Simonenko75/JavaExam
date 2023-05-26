package com.example.JavaExam.service;

import com.example.JavaExam.model.ClientsEntity;
import com.example.JavaExam.model.ExamMappingDTOtoEntity;
import com.example.JavaExam.service.dto.ResponseDTO;
import com.example.JavaExam.model.RoomsEntity;
import com.example.JavaExam.repositoty.ClientsRepository;
import com.example.JavaExam.repositoty.RoomsRepository;
import com.example.JavaExam.service.dto.ClientsEntityDTO;
import com.example.JavaExam.service.dto.RoomsEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    public ClientsRepository clientsRepository;

    @Autowired
    public RoomsRepository roomsRepository;

    @Autowired
    public ExamMappingDTOtoEntity examMappingDTOtoEntity;

    public ClientsEntity findClientById(long id) {
        return clientsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    public RoomsEntity findRoomById(long id) {
        return roomsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public List<RoomsEntityDTO> findRoomByNumber(int number) {
        List<RoomsEntityDTO> resultList = new ArrayList<>();
        roomsRepository.findAllByNumber(number).forEach(
                roomsEntity -> {
                    resultList.add(examMappingDTOtoEntity.roomsResponseEntityToDTO(roomsEntity));
                }
        );

        return resultList;
    }

    public ClientsEntity clientSave(ClientsEntity clientsEntity) {
        return clientsRepository.save(clientsEntity);
    }

    public RoomsEntity roomSave(RoomsEntity roomsEntity) {
        return roomsRepository.save(roomsEntity);
    }

    public ClientsEntityDTO insertClientsEntity(ClientsEntityDTO clientsEntityDTO){
        List<RoomsEntityDTO> resultRoomList = findRoomByNumber(clientsEntityDTO.getRoomNumber());

        if (resultRoomList.get(0).getFreeRoom() && resultRoomList.get(0).getFreeQuantity() >= clientsEntityDTO.getQuantity()) {
            resultRoomList.get(0).setFreeQuantity(resultRoomList.get(0).getFreeQuantity() - clientsEntityDTO.getQuantity());

            if (resultRoomList.get(0).getFreeQuantity() == 0) {
                resultRoomList.get(0).setFreeRoom(false);
            }

            RoomsEntity roomsEntity = findRoomById(resultRoomList.get(0).getId());
            RoomsEntity roomsResponseEntity = roomUpdate(roomsEntity, resultRoomList.get(0));
            roomSave(roomsEntity);

            clientsEntityDTO.setSettled(true);

            return examMappingDTOtoEntity.clientsResponseEntityToDTO(clientSave(examMappingDTOtoEntity.clientsDtoToEntity(clientsEntityDTO)));
        }

        return null;
    }

    public RoomsEntityDTO insertRoomsEntity(RoomsEntityDTO roomsEntityDTO){
        return examMappingDTOtoEntity.roomsResponseEntityToDTO(roomSave(examMappingDTOtoEntity.roomsDtoToEntity(roomsEntityDTO)));
    }

    public ClientsEntity clientUpdate(ClientsEntity clientsEntity, ClientsEntityDTO clientsEntityDTO) {
        List<RoomsEntityDTO> resultRoomList = findRoomByNumber(clientsEntityDTO.getRoomNumber());
        List<RoomsEntityDTO> changeRoomList = findRoomByNumber(clientsEntity.getRoomNumber());

        if (clientsEntity.getSettled() && clientsEntity.getRoomNumber() != clientsEntityDTO.getRoomNumber()) {
            changeRoomList.get(0).setFreeQuantity(changeRoomList.get(0).getFreeQuantity() + clientsEntity.getQuantity());

            if (changeRoomList.get(0).getFreeQuantity() != 0) {
                changeRoomList.get(0).setFreeRoom(true);
            }

            RoomsEntity roomsEntity = findRoomById(changeRoomList.get(0).getId());
            RoomsEntity roomsResponseEntity = roomUpdate(roomsEntity, changeRoomList.get(0));
            roomSave(roomsEntity);
        }

        if (resultRoomList.get(0).getFreeRoom() && resultRoomList.get(0).getFreeQuantity() >= clientsEntityDTO.getQuantity()) {
            resultRoomList.get(0).setFreeQuantity(resultRoomList.get(0).getFreeQuantity() - clientsEntityDTO.getQuantity());

            if (resultRoomList.get(0).getFreeQuantity() == 0) {
                resultRoomList.get(0).setFreeRoom(false);
            }

            RoomsEntity roomsEntity = findRoomById(resultRoomList.get(0).getId());
            RoomsEntity roomsResponseEntity = roomUpdate(roomsEntity, resultRoomList.get(0));
            roomSave(roomsEntity);

            clientsEntityDTO.setSettled(true);

            return examMappingDTOtoEntity.clientUpdate(clientsEntity, clientsEntityDTO);
        }

        return null;
    }

    public RoomsEntity roomUpdate(RoomsEntity roomsEntity, RoomsEntityDTO roomsEntityDTO) {
        return examMappingDTOtoEntity.roomUpdate(roomsEntity, roomsEntityDTO);
    }

    public ResponseDTO deleteClientById(long id) {
        ClientsEntity clientsEntity = findClientById(id);
        List<RoomsEntityDTO> changeRoomList = findRoomByNumber(clientsEntity.getRoomNumber());

        changeRoomList.get(0).setFreeQuantity(changeRoomList.get(0).getFreeQuantity() + clientsEntity.getQuantity());

        if (changeRoomList.get(0).getFreeQuantity() != 0) {
            changeRoomList.get(0).setFreeRoom(true);
        }

        RoomsEntity roomsEntity = findRoomById(changeRoomList.get(0).getId());
        RoomsEntity roomsResponseEntity = roomUpdate(roomsEntity, changeRoomList.get(0));
        roomSave(roomsEntity);

        ResponseDTO responseDTO = new ResponseDTO();
        clientsRepository.deleteById(id);
        responseDTO.setMessage("Delete is completed.");
        return responseDTO;
    }

    public ResponseDTO deleteRoomById(long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        roomsRepository.deleteById(id);
        responseDTO.setMessage("Delete is completed.");
        return responseDTO;
    }

}
