package com.trade.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trade.app.dao.TradeStoreDAO;
import com.trade.app.model.Trade;

/**
 * This is the service class which handles services for trades operations .
 *
 */
@Service
public class TradeStoreService {
	
	@Autowired
	private TradeStoreDAO tradeStoreDAO;
	
	/**
	 * @param trade.
	 * @return void
	 *
	 */
	public void insertTradeData(Trade trade) {
		this.tradeStoreDAO.insertTradeData(trade);
	}
	
	/**
	 * @return list of trades from trade store.
	 *
	 */
	public List<Trade> getTradeStore() {
		
		List<Trade> tradeList = this.tradeStoreDAO.getTradeStore();
	
		return tradeList;
	}
	
}
