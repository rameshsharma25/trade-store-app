package com.trade.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trade.app.exception.ExceptionResponse;
import com.trade.app.exception.LowerVersionException;
import com.trade.app.exception.MaturityDateException;
import com.trade.app.model.Trade;
import com.trade.app.service.TradeStoreService;



/**
 * This is actual end point for trade store services.
 *
 */

@RestController
public class TradeStoreController {

	
	/**
	 * This service class handle for trade store service.
	 */
	@Autowired
	private TradeStoreService tradeStoreService;
	
	/**
	 * @param trade
	 * @return void
	 *
	 */
	@RequestMapping(value="/tradeStore/insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertTradeData(@RequestBody Trade trade) {
		this.tradeStoreService.insertTradeData(trade);
	}
	
	/**
	 * @return List<Trade>
	 *
	 */
	@RequestMapping(value="tradeStore/getTradeList", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Trade> getTradeStore(){
		
		List<Trade> tradeList= this.tradeStoreService.getTradeStore();
		return tradeList;
	}
	
	/**
	 * @exception LowerVersionException
	 *
	 */
	@ExceptionHandler(LowerVersionException.class)
	public ResponseEntity<ExceptionResponse> lowerVersionException(LowerVersionException ex){
		
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("001");
		response.setErrorMessage(ex.getMessage());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * @exception MaturityDateException
	 *
	 */
	@ExceptionHandler(MaturityDateException.class)
	public ResponseEntity<ExceptionResponse> maturityDateException(MaturityDateException mex){
		
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("002");
		response.setErrorMessage(mex.getMessage());
		
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}

