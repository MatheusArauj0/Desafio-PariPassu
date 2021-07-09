package com.example.gerenciadorsenhasbancarias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gerenciadorsenhasbancarias.entities.Password;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long>{
	
	String deleteByPass(String pass);
}
