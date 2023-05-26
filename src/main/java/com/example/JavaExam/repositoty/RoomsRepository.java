package com.example.JavaExam.repositoty;

import com.example.JavaExam.model.RoomsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<RoomsEntity, Long> {

    List<RoomsEntity> findAllByNumber(int number);

}
