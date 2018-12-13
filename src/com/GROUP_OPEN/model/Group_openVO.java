package com.GROUP_OPEN.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class Group_openVO {

	private String GROUP_NO;
	private String MEMBER_NO;
	private String GOODS_NO;
	private String GROUP_NAME;
	private Integer GROUP_LIMIT;
	private String GROUP_INTRODUCTION;
	private String GROUP_MIND;
	private Timestamp GROUP_START_DATE;
	private Timestamp GROUP_CLOSE_DATE;
	private byte[] GROUP_BANNER_1;
	private byte[] GROUP_BANNER_2;
	private String GROUP_STATUS;
	private String GROUP_ADDRESS;
	private Double LATITUDE;
	private Double LONGITUDE;
	private Timestamp GROUP_TIME;
	private Integer GROUP_PRICE;

	public Group_openVO() {
	};

	public Group_openVO(String gROUP_NO, String mEMBER_NO, String gOODS_NO, String gROUP_NAME, Integer gROUP_LIMIT,
			String gROUP_INTRODUCTION, String gROUP_MIND, Timestamp gROUP_START_DATE, Timestamp gROUP_CLOSE_DATE,
			byte[] gROUP_BANNER_1, byte[] gROUP_BANNER_2, String gROUP_STATUS, String gROUP_ADDRESS, Double lATITUDE,
			Double lONGITUDE, Timestamp gROUP_TIME, Integer gROUP_PRICE) {
		super();
		GROUP_NO = gROUP_NO;
		MEMBER_NO = mEMBER_NO;
		GOODS_NO = gOODS_NO;
		GROUP_NAME = gROUP_NAME;
		GROUP_LIMIT = gROUP_LIMIT;
		GROUP_INTRODUCTION = gROUP_INTRODUCTION;
		GROUP_MIND = gROUP_MIND;
		GROUP_START_DATE = gROUP_START_DATE;
		GROUP_CLOSE_DATE = gROUP_CLOSE_DATE;
		GROUP_BANNER_1 = gROUP_BANNER_1;
		GROUP_BANNER_2 = gROUP_BANNER_2;
		GROUP_STATUS = gROUP_STATUS;
		GROUP_ADDRESS = gROUP_ADDRESS;
		LATITUDE = lATITUDE;
		LONGITUDE = lONGITUDE;
		GROUP_TIME = gROUP_TIME;
		GROUP_PRICE = gROUP_PRICE;
	}

	public String getGROUP_NO() {
		return GROUP_NO;
	}

	public void setGROUP_NO(String gROUP_NO) {
		GROUP_NO = gROUP_NO;
	}

	public String getMEMBER_NO() {
		return MEMBER_NO;
	}

	public void setMEMBER_NO(String mEMBER_NO) {
		MEMBER_NO = mEMBER_NO;
	}

	public String getGOODS_NO() {
		return GOODS_NO;
	}

	public void setGOODS_NO(String gOODS_NO) {
		GOODS_NO = gOODS_NO;
	}

	public String getGROUP_NAME() {
		return GROUP_NAME;
	}

	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}

	public Integer getGROUP_LIMIT() {
		return GROUP_LIMIT;
	}

	public void setGROUP_LIMIT(Integer gROUP_LIMIT) {
		GROUP_LIMIT = gROUP_LIMIT;
	}

	public String getGROUP_INTRODUCTION() {
		return GROUP_INTRODUCTION;
	}

	public void setGROUP_INTRODUCTION(String gROUP_INTRODUCTION) {
		GROUP_INTRODUCTION = gROUP_INTRODUCTION;
	}

	public String getGROUP_MIND() {
		return GROUP_MIND;
	}

	public void setGROUP_MIND(String gROUP_MIND) {
		GROUP_MIND = gROUP_MIND;
	}

	public Timestamp getGROUP_START_DATE() {
		return GROUP_START_DATE;
	}

	public void setGROUP_START_DATE(Timestamp gROUP_START_DATE) {
		GROUP_START_DATE = gROUP_START_DATE;
	}

	public Timestamp getGROUP_CLOSE_DATE() {
		return GROUP_CLOSE_DATE;
	}

	public void setGROUP_CLOSE_DATE(Timestamp gROUP_CLOSE_DATE) {
		GROUP_CLOSE_DATE = gROUP_CLOSE_DATE;
	}

	public byte[] getGROUP_BANNER_1() {
		return GROUP_BANNER_1;
	}

	public void setGROUP_BANNER_1(byte[] gROUP_BANNER_1) {
		GROUP_BANNER_1 = gROUP_BANNER_1;
	}

	public byte[] getGROUP_BANNER_2() {
		return GROUP_BANNER_2;
	}

	public void setGROUP_BANNER_2(byte[] gROUP_BANNER_2) {
		GROUP_BANNER_2 = gROUP_BANNER_2;
	}

	public String getGROUP_STATUS() {
		return GROUP_STATUS;
	}

	public void setGROUP_STATUS(String gROUP_STATUS) {
		GROUP_STATUS = gROUP_STATUS;
	}

	public String getGROUP_ADDRESS() {
		return GROUP_ADDRESS;
	}

	public void setGROUP_ADDRESS(String gROUP_ADDRESS) {
		GROUP_ADDRESS = gROUP_ADDRESS;
	}

	public Double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(Double lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public Double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(Double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public Timestamp getGROUP_TIME() {
		return GROUP_TIME;
	}

	public void setGROUP_TIME(Timestamp gROUP_TIME) {
		GROUP_TIME = gROUP_TIME;
	}

	public Integer getGROUP_PRICE() {
		return GROUP_PRICE;
	}

	public void setGROUP_PRICE(Integer gROUP_PRICE) {
		GROUP_PRICE = gROUP_PRICE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((GOODS_NO == null) ? 0 : GOODS_NO.hashCode());
		result = prime * result + ((GROUP_ADDRESS == null) ? 0 : GROUP_ADDRESS.hashCode());
		result = prime * result + Arrays.hashCode(GROUP_BANNER_1);
		result = prime * result + Arrays.hashCode(GROUP_BANNER_2);
		result = prime * result + ((GROUP_CLOSE_DATE == null) ? 0 : GROUP_CLOSE_DATE.hashCode());
		result = prime * result + ((GROUP_INTRODUCTION == null) ? 0 : GROUP_INTRODUCTION.hashCode());
		result = prime * result + ((GROUP_LIMIT == null) ? 0 : GROUP_LIMIT.hashCode());
		result = prime * result + ((GROUP_MIND == null) ? 0 : GROUP_MIND.hashCode());
		result = prime * result + ((GROUP_NAME == null) ? 0 : GROUP_NAME.hashCode());
		result = prime * result + ((GROUP_NO == null) ? 0 : GROUP_NO.hashCode());
		result = prime * result + ((GROUP_PRICE == null) ? 0 : GROUP_PRICE.hashCode());
		result = prime * result + ((GROUP_START_DATE == null) ? 0 : GROUP_START_DATE.hashCode());
		result = prime * result + ((GROUP_STATUS == null) ? 0 : GROUP_STATUS.hashCode());
		result = prime * result + ((GROUP_TIME == null) ? 0 : GROUP_TIME.hashCode());
		result = prime * result + ((LATITUDE == null) ? 0 : LATITUDE.hashCode());
		result = prime * result + ((LONGITUDE == null) ? 0 : LONGITUDE.hashCode());
		result = prime * result + ((MEMBER_NO == null) ? 0 : MEMBER_NO.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group_openVO other = (Group_openVO) obj;
		if (GOODS_NO == null) {
			if (other.GOODS_NO != null)
				return false;
		} else if (!GOODS_NO.equals(other.GOODS_NO))
			return false;
		if (GROUP_ADDRESS == null) {
			if (other.GROUP_ADDRESS != null)
				return false;
		} else if (!GROUP_ADDRESS.equals(other.GROUP_ADDRESS))
			return false;
		if (!Arrays.equals(GROUP_BANNER_1, other.GROUP_BANNER_1))
			return false;
		if (!Arrays.equals(GROUP_BANNER_2, other.GROUP_BANNER_2))
			return false;
		if (GROUP_CLOSE_DATE == null) {
			if (other.GROUP_CLOSE_DATE != null)
				return false;
		} else if (!GROUP_CLOSE_DATE.equals(other.GROUP_CLOSE_DATE))
			return false;
		if (GROUP_INTRODUCTION == null) {
			if (other.GROUP_INTRODUCTION != null)
				return false;
		} else if (!GROUP_INTRODUCTION.equals(other.GROUP_INTRODUCTION))
			return false;
		if (GROUP_LIMIT == null) {
			if (other.GROUP_LIMIT != null)
				return false;
		} else if (!GROUP_LIMIT.equals(other.GROUP_LIMIT))
			return false;
		if (GROUP_MIND == null) {
			if (other.GROUP_MIND != null)
				return false;
		} else if (!GROUP_MIND.equals(other.GROUP_MIND))
			return false;
		if (GROUP_NAME == null) {
			if (other.GROUP_NAME != null)
				return false;
		} else if (!GROUP_NAME.equals(other.GROUP_NAME))
			return false;
		if (GROUP_NO == null) {
			if (other.GROUP_NO != null)
				return false;
		} else if (!GROUP_NO.equals(other.GROUP_NO))
			return false;
		if (GROUP_PRICE == null) {
			if (other.GROUP_PRICE != null)
				return false;
		} else if (!GROUP_PRICE.equals(other.GROUP_PRICE))
			return false;
		if (GROUP_START_DATE == null) {
			if (other.GROUP_START_DATE != null)
				return false;
		} else if (!GROUP_START_DATE.equals(other.GROUP_START_DATE))
			return false;
		if (GROUP_STATUS == null) {
			if (other.GROUP_STATUS != null)
				return false;
		} else if (!GROUP_STATUS.equals(other.GROUP_STATUS))
			return false;
		if (GROUP_TIME == null) {
			if (other.GROUP_TIME != null)
				return false;
		} else if (!GROUP_TIME.equals(other.GROUP_TIME))
			return false;
		if (LATITUDE == null) {
			if (other.LATITUDE != null)
				return false;
		} else if (!LATITUDE.equals(other.LATITUDE))
			return false;
		if (LONGITUDE == null) {
			if (other.LONGITUDE != null)
				return false;
		} else if (!LONGITUDE.equals(other.LONGITUDE))
			return false;
		if (MEMBER_NO == null) {
			if (other.MEMBER_NO != null)
				return false;
		} else if (!MEMBER_NO.equals(other.MEMBER_NO))
			return false;
		return true;
	}
	
	
	
	
	

}
