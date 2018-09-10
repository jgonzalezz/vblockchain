package com.itm.edu.vblockchain.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.itm.edu.vblockchain.models.entity.Voting;


public interface IVotingDao extends PagingAndSortingRepository<Voting, Long> {

}

// PagingAndSortingRepository nos ayuda a 
//paginar los datos y tambien extiende de CRUDRepository
