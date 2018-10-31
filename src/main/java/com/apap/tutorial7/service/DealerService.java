package com.apap.tutorial7.service;
import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.DealerModel;
	
	public interface DealerService {
		Optional <DealerModel> getDealerDetailById(Long id);
		
		void addDealer (DealerModel dealer);
		void deleteDealer(long dealerId);
		List<DealerModel> getAllDealer();
		void dealerUpdate(DealerModel dealer, Long dealerId);
	}


