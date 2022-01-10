package com.luhn.algo.impl;

import java.util.logging.Logger;

import com.luhn.algo.exceptions.LuhnApplicationException;
import com.luhn.algo.interfaces.ILuhnAlgo;

public class LuhnAlgoImpl implements ILuhnAlgo{

	Logger logger = Logger.getLogger(getClass().getName());
	
	public boolean isValidLuhn(String cardNumber) throws LuhnApplicationException {
		int sum = generateLuhnAlgoSum(cardNumber, OPERATION_TYPE.VALIDATE);
		return sum  % 10 == 0;
	}
	
	public String generateCheckDigit(String cardNumber) throws LuhnApplicationException {
		int sum = generateLuhnAlgoSum(cardNumber, OPERATION_TYPE.GENERATE_CODE);
		return Integer.toString(10 - sum % 10);
	}
	
	public int countRange(String startRange, String endRange) throws LuhnApplicationException {
		Long curVal = 0L;
		Long end = 0L;
		int validNumbers = 0;
		try {
			curVal = Long.valueOf(startRange);
		}catch(NumberFormatException ne) {
			throw new LuhnApplicationException("Unable to parse StartRange", ne);
		}
		
		try {
			end = Long.valueOf(endRange);
		}catch(NumberFormatException ne) {
			throw new LuhnApplicationException("Unable to parse EndRange", ne);
		}
		
		if(end <= curVal) {
			logger.warning("Invalid number range");
			return 0;
		}
		
		while(curVal <= end) {
//			logger.info(String.format("Working on %s", curVal)); 
			if(isValidLuhn(curVal.toString()))
				validNumbers++;
			curVal++;
		}
		return validNumbers;
	}
}
