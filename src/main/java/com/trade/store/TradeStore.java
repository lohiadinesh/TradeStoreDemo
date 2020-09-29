package com.trade.store;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.pojo.Trade;
import com.utils.DateTimeUtils;

/**
 * @author Dinesh
 *
 */
@Repository
public class TradeStore {

	private List<Trade> trades = new ArrayList<Trade>();

	private Comparator<Trade> compareByName = Comparator.comparing(Trade::getId).thenComparing(Trade::getVersion)
			.reversed();

	/**
	 * This method will run at 00:00:00am every day to validate and expire
	 * all matured trades.
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void executeMaturityTrades() {
		final Stream<Trade> maturedTrades = this.getTrades().stream()
				.filter(item -> item.getmDate().before(DateTimeUtils.getTodayDate()));
		maturedTrades.forEachOrdered(item -> item.setExpired('Y'));
		System.out.println("All matured trades set to exprired.");
	}

	/**
	 * @param trades the trades to set
	 */
	public boolean add(final Trade trade) {
		trades.add(trade);
		setTrades(trades.stream().sorted(compareByName).collect(Collectors.toList()));
		return true;
	}

	/**
	 * @return the trades
	 */
	public List<Trade> getTrades() {
		return trades;
	}

	/**
	 * @param trades the trades to set
	 */
	private void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

}
