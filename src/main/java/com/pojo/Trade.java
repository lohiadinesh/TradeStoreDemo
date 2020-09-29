package com.pojo;

import java.util.Date;

/**
 * @author Dinesh
 *
 */
public class Trade {

	/**
	 * @param id
	 * @param i
	 * @param cPartyId
	 * @param bookId
	 * @param mDate
	 * @param cDate
	 * @param expired
	 */
	public Trade(final String id, final Integer version, final String cPartyId, final String bookId, final Date mDate, final Date cDate, final Character expired) {
		super();
		this.id = id;
		this.version = version;
		this.cPartyId = cPartyId;
		this.bookId = bookId;
		this.mDate = mDate;
		this.cDate = cDate;
		this.expired = expired;
	}

	private String id;
	private Integer version;
	private String cPartyId;
	private String bookId;
	private Date mDate;
	private Date cDate;
	private Character expired;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the cPartyId
	 */
	public String getcPartyId() {
		return cPartyId;
	}

	/**
	 * @param cPartyId the cPartyId to set
	 */
	public void setcPartyId(String cPartyId) {
		this.cPartyId = cPartyId;
	}

	/**
	 * @return the bookId
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the mDate
	 */
	public Date getmDate() {
		return mDate;
	}

	/**
	 * @param mDate the mDate to set
	 */
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}

	/**
	 * @return the cDate
	 */
	public Date getcDate() {
		return cDate;
	}

	/**
	 * @param cDate the cDate to set
	 */
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	/**
	 * @return the expired
	 */
	public char getExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(char expired) {
		this.expired = expired;
	}
}
