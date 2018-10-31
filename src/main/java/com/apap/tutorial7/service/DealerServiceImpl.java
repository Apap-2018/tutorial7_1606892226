package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	@Autowired
	private DealerDb dealerDb;
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id){
		return dealerDb.findById(id);
	}
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}
	
	@Override
	public void deleteDealer(long dealerId) {
		// TODO Auto-generated method stub
		dealerDb.deleteById(dealerId);
	}
	
	@Override
	public List<DealerModel> getAllDealer() {
		// TODO Auto-generated method stub
		return dealerDb.findAll();
	}

    @Override
    public void dealerUpdate(DealerModel updateDealer, Long dealerId) {
        DealerModel dataLama = dealerDb.findById(dealerId).get();
        dataLama.setAlamat(updateDealer.getAlamat());
        dataLama.setNoTelp(updateDealer.getNoTelp());
        dealerDb.save(dataLama);
    }


}