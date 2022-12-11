package com.example.demoMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoMVC.model.Programmer;


@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Integer> 
{
	}
