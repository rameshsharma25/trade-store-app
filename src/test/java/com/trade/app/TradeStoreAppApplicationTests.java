 package com.trade.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.trade.app.dao.TradeStoreDAO;
import com.trade.app.model.Trade;

@ComponentScan("com.trade.app.dao")
@DataJpaTest
public class TradeStoreAppApplicationTests {

		
	@Autowired
	private TradeStoreDAO tradeStoreDAO;

	@Test
	public void testInsertTradeStore() {
		
		Trade trade = new Trade();
		
		trade.setId(1L);
		trade.setTradeId("T1");
		trade.setVersion(1);
		trade.setCounterPartyId("CP-1");
		trade.setBookId("B1");
		trade.setMaturityDate("2020-07-20");
		trade.setCreatedDate("2020-06-22");
		trade.setExpiry("N");
		
		tradeStoreDAO.insertTradeData(trade);
		
		Assertions.assertNotNull(trade.getId());
	}
	
	@Test
	public void testAllTradeStore() {
		
		Assertions.assertNotNull(tradeStoreDAO.getTradeStore());
	}
}
