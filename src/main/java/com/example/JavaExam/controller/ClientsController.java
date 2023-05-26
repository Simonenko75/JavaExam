package com.example.JavaExam.controller;

import com.example.JavaExam.model.ClientsEntity;
import com.example.JavaExam.model.ExamMappingDTOtoEntity;
import com.example.JavaExam.model.RoomsEntity;
import com.example.JavaExam.service.ExamService;
import com.example.JavaExam.service.dto.ClientsEntityDTO;
import com.example.JavaExam.service.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamMappingDTOtoEntity examMappingDTOtoEntity;

    @PostMapping("/create")
    public @ResponseBody ClientsEntityDTO insertClientEntity(@RequestBody ClientsEntityDTO clientsEntityDTO){
        return examService.insertClientsEntity(clientsEntityDTO);
    }

    @PutMapping("/update")
    public @ResponseBody ClientsEntity clientsUpdate(@RequestParam Long id, @RequestBody ClientsEntityDTO clientsEntityDTO) {
        ClientsEntity clientsEntity = examService.findClientById(id);
        ClientsEntity clientsResponseEntity = examService.clientUpdate(clientsEntity, clientsEntityDTO);
        examService.clientSave(clientsEntity);
        return clientsResponseEntity;
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseDTO deleteClient(@RequestParam("id") Long clientId) {
        return examService.deleteClientById(clientId);
    }

}
