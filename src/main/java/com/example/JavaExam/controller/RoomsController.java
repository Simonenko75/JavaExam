package com.example.JavaExam.controller;

import com.example.JavaExam.model.ClientsEntity;
import com.example.JavaExam.service.dto.ResponseDTO;
import com.example.JavaExam.model.RoomsEntity;
import com.example.JavaExam.service.ExamService;
import com.example.JavaExam.service.dto.RoomsEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private ExamService examService;

    @PostMapping("/create")
    public @ResponseBody RoomsEntityDTO insertRoomEntity(@RequestBody RoomsEntityDTO roomsEntityDTO){
        return examService.insertRoomsEntity(roomsEntityDTO);
    }

    @PutMapping("update")
    public @ResponseBody RoomsEntity roomsUpdate(@RequestParam Long id, @RequestBody RoomsEntityDTO roomsEntityDTO) {
        RoomsEntity roomsEntity = examService.findRoomById(id);
        RoomsEntity roomsResponseEntity = examService.roomUpdate(roomsEntity, roomsEntityDTO);
        examService.roomSave(roomsEntity);
        return roomsResponseEntity;
    }

    @DeleteMapping("/delete")
    public @ResponseBody ResponseDTO deleteRoom(@RequestParam("id") Long roomId) {
        return examService.deleteRoomById(roomId);
    }

}
