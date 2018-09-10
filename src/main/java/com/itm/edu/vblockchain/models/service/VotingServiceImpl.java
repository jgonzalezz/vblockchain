package com.itm.edu.vblockchain.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itm.edu.vblockchain.models.dao.IVotingDao;
import com.itm.edu.vblockchain.models.entity.Voting;

@Service
public class VotingServiceImpl implements IVotingService{

	@Autowired
	private IVotingDao votingDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Voting> findAll() {
		return (List<Voting>) votingDao.findAll();
	}

	@Override
	public Page<Voting> findAll(Pageable pageable) {
		return votingDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Voting voting) {
		votingDao.save(voting);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Voting> findById(Long id) {
		return votingDao.findById(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		votingDao.deleteById(id);		
	}

}
