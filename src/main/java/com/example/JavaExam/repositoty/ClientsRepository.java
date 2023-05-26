package com.example.JavaExam.repositoty;

import com.example.JavaExam.model.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {

}
