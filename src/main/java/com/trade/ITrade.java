package com.trade;

import java.util.List;

import com.pojo.Trade;
import com.trade.exception.IllegalArgumentException;

/**
 * @author Dinesh
 *
 */

public interface ITrade {

	boolean validate(Trade trade) throws IllegalArgumentException;

	boolean add(Trade trade) throws IllegalArgumentException;

	List<Trade> find(String tradeId);

}
