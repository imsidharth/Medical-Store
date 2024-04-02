package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Meds;



public interface MedRepository extends JpaRepository<Meds,Integer> {



	Optional<Meds> findByName(String name);

	

	

	
}