package com.trade.app.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.trade.app.exception.LowerVersionException;
import com.trade.app.exception.MaturityDateException;
import com.trade.app.model.Trade;

/**
 * This is the service class which handles services for trades operations .
 *
 */
@Repository
@Transactional
public class TradeStoreDAO {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @param trade.
	 * @return void
	 *
	 */
	public void insertTradeData(Trade trade) {

		Trade tradeResult = null;

		try {
			tradeResult = this.entityManager.createNamedQuery("getTradeDetails", Trade.class)
					.setParameter(1, trade.getTradeId()).getSingleResult();
		} catch (Exception ex) {

		}
		// If trade id is already present then check version

		if (Optional.ofNullable(tradeResult).isPresent()) {
			if (trade.getVersion() < tradeResult.getVersion()) {
				throw new LowerVersionException(1, "Lower verion");
			} else if (trade.getVersion() == tradeResult.getVersion()) {
				trade.setId(tradeResult.getId());
				this.entityManager.merge(trade);
			}
		} else {
			// Check if maturity date is less than current date
			boolean isInValid = checkMaturityDate(trade);
			if (isInValid) {
				throw new MaturityDateException(2, "Maturity date not valid");
			} else {
				this.entityManager.merge(trade);
			}
		}

	}

	/**
	 * @param trade
	 * @return boolean
	 *
	 */
	public boolean checkMaturityDate(Trade trade) {

		String DATE_PATTERN = "yyyy-MM-dd";
		DateTimeFormatter LD_FOMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

		String currentDate = LD_FOMATTER.format(LocalDate.now());
		String maturityDate = LD_FOMATTER.format(LocalDate.parse(trade.getMaturityDate()));
		// Check maturity date from current ate
		if (maturityDate.compareTo(currentDate) < 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Trade automatically check and update expire flag if trade cross the maturity
	 * date
	 *
	 */
	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 0 0 * * *")
	public void run() {

		List<Trade> tradeList = this.entityManager.createQuery("from Trade").getResultList();

		for (Trade trade : tradeList) {
			boolean isValid = checkMaturityDate(trade);
			if (isValid) {
				trade.setExpiry("Y");
				this.entityManager.merge(trade);
			}
		}
	}

	/**
	 * @return list of trades from trade store.
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<Trade> getTradeStore() {

		List<Trade> tradeList = this.entityManager.createQuery("from Trade").getResultList();
		return tradeList;
	}

}
