package com.trade;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pojo.Trade;
import com.trade.exception.IllegalArgumentException;
import com.trade.store.TradeStore;
import com.utils.DateTimeUtils;

/**
 * @author Dinesh
 *
 */

@Service
public class TradeExecutor implements ITrade {

	@Autowired
	private TradeStore tradeStore;

	/**
	 * Add method will add trade into store. In case version already exist it will
	 * override maturity date.
	 * 
	 * @param trade type of Trade
	 * @return the status [true | false]
	 * @throws IllegalArgumentException for all handle exceptions
	 * 
	 */
	@Override
	public boolean add(final Trade trade) throws IllegalArgumentException {
		if (this.validate(trade)) {
			final Stream<Trade> trades = this.getTradeStore().getTrades().stream()
					.filter(item -> item.getId().equalsIgnoreCase(trade.getId()) && item.getVersion() == trade.getVersion());
			if (trades.count() == 0)
				return this.tradeStore.add(trade);
			return updateTrade(trade);
		}
		return false;
	}

	/**
	 * Add method will validate trade before inserting into store. In case version
	 * already exist it will override maturity date.
	 * 
	 * @param trade type of Trade
	 * @return the status [true | false]
	 * @throws IllegalArgumentException for all handle exceptions
	 */
	@Override
	public boolean validate(final Trade trade) throws IllegalArgumentException {
		this.isValidVersion(trade);
		this.isValidMaturityDate(trade);
		return true;
	}

	private void isValidVersion(final Trade trade) throws IllegalArgumentException {
		final Stream<Trade> invalidVersions = this.getTradeStore().getTrades().stream()
				.filter(item -> item.getId().equalsIgnoreCase(trade.getId())
						&& item.getVersion() > trade.getVersion());
		if (invalidVersions.count() > 0)
			throw new IllegalArgumentException("New version can not be lower than current version.");

	}

	private void isValidMaturityDate(final Trade trade) throws IllegalArgumentException {
		if (trade.getmDate().before(DateTimeUtils.getTodayDate()))
			throw new IllegalArgumentException("Maturity date can not be older than today's date.");
	}

	/**
	 * This method will run on every every day at 00:01 hrs to validate and expire
	 * all matured trades.
	 */
	public void executeMaturityTrades() {
		this.getTradeStore().executeMaturityTrades();
	}

	/**
	 * @return the tradeStore
	 */
	public TradeStore getTradeStore() {
		return tradeStore;
	}

	/**
	 * @param tradeStore the tradeStore to set
	 */
	public void setTradeStore(TradeStore tradeStore) {
		this.tradeStore = tradeStore;
	}

	private boolean updateTrade(final Trade trade) {
		final Stream<Trade> trades = this.getTradeStore().getTrades().stream()
				.filter(item -> item.getId().equalsIgnoreCase(trade.getId()) && item.getVersion() == trade.getVersion());
		trades.forEachOrdered(item -> item.setmDate(trade.getmDate()));
		return true;

	}

	@Override
	public List<Trade> find(String tradeId) {
		return this.getTradeStore().find(tradeId);
	}

}
