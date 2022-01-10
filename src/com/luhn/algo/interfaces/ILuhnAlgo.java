package com.luhn.algo.interfaces;

import com.luhn.algo.exceptions.LuhnApplicationException;

public interface ILuhnAlgo {
	
	/**
	 * Enum provides different operation types to be performed on a given Card Number
	 * 
	 * @author Iqbal Virani
	 *
	 */
	enum OPERATION_TYPE {
		/**
		 * Flag to perform Validation for Card Numbers
		 */
		VALIDATE,
		/**
		 * Flag to Generate Check Code for a Card Number
		 */
		GENERATE_CODE
	}

	/**
	 * Checks if the card number is valid based on Luhn Algorithm
	 * @param cardNumber
	 * 	The card number
	 * @return
	 * 	<p>true if valid <br />
	 * 	false if not valid</p>
	 * @throws LuhnApplicationException
	 * 	Throws {@link LuhnApplicationException}
	 */
	boolean isValidLuhn(String cardNumber) throws LuhnApplicationException;
	
	/**
	 * Generates a Check Digit using Luhn Algorithm
	 * @param cardNumber
	 * 	The Card Number
	 * @return
	 * 	The Check digit
	 * @throws LuhnApplicationException
	 * 	Throws {@link LuhnApplicationException}
	 */
	String generateCheckDigit(String cardNumber) throws LuhnApplicationException;
	
	/**
	 * Provided a range of card numbers, determins how many of the numbers are valid based on Luhn Algorithm
	 * @param startRange
	 * 	The start range (inclusive)
	 * @param endRange
	 * 	The end range (inclusive)
	 * @return
	 * 	The number of valid card numbers
	 * @throws LuhnApplicationException
	 * 	Throws {@link LuhnApplicationException}
	 */
	int countRange(String startRange, String endRange) throws LuhnApplicationException;
	
	/**
	 * Generates a sum value using Luhn Algorithm from the card number provided
	 * @param cardNumber
	 * 	The Card Number
	 * @param operationType
	 * 	The Operation type of {@link OPERATION_TYPE}
	 * @return
	 * @throws LuhnApplicationException
	 * @see ILuhnAlgo.OPERATION_TYPE#VALIDATE
	 * @see ILuhnAlgo.OPERATION_TYPE#GENERATE_CODE
	 */
	default int generateLuhnAlgoSum(String cardNumber, OPERATION_TYPE operationType) throws LuhnApplicationException {
		
		if(!cardNumber.matches("\\d+"))
			throw new LuhnApplicationException("Invalid Card Number Format");
		
		String [] digits = cardNumber.split("");
		int sum = 0;
		/**
		 * Multiply digit by 2 starting the last digit if the operation is to generate the check code
		 * Multiply digit by 2 starting 2nd from last if the operation is to validate the card number
		 */
		boolean multiple = operationType.equals(OPERATION_TYPE.VALIDATE) ? false : true;
		
		for(int i = digits.length; i > 0; i--) {
			
			int digit = Integer.parseInt(digits[i-1]);
			//Alternating between digits to set multiple flag
			if(multiple) {
				digit = digit*2;
				String s [] = Integer.toString(digit).split("");
				if(s.length > 1) {
					digit = Integer.parseInt(s[0])  + Integer.parseInt(s[1]);
				}	
			}
			//Setting multiple flag to alternate
			multiple = !multiple;
			sum += digit;
		}
		return sum;
	}
}
