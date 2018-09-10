package com.itm.edu.vblockchain.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itm.edu.vblockchain.models.entity.Voting;


public interface IVotingService {
	
	
public List<Voting> findAll();
	
	public Page<Voting> findAll(Pageable pageable);

	public void save(Voting voting);
	
	public Optional<Voting> findById(Long id);
	
	public void delete(Long id);

}
