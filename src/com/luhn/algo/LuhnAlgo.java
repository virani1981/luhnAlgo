package com.luhn.algo;

import java.util.logging.Logger;

import com.luhn.algo.exceptions.LuhnApplicationException;
import com.luhn.algo.impl.LuhnAlgoImpl;
import com.luhn.algo.interfaces.ILuhnAlgo;

/**
 * 
 * @author Iqbal Virani
 *  <p>
 *	The main application to test Luhn Algorithm
 *  </p>
 *  
 *  @see https://www.ibm.com/docs/en/order-management-sw/9.3.0?topic=cpms-handling-credit-cards
 *  @see https://en.wikipedia.org/wiki/Luhn_algorithm
 *  
 *  @see Verification Utility: http://tuxgraphics.org/~guido/javascript/luhn-calculator.html
 *  @see Test C/C numbers: https://www.validcreditcardnumber.com/
 *  
 *  American Express	371449635398431
 *	Diners Club			30569309025904
 *	Discover			6011111111111117
 *	JCB					3530111333300000
 *	MasterCard			5555555555554444
 *	Visa				4111111111111111  
 */
class LuhnAlgo {

	public static void main(String[] args) throws LuhnApplicationException {
		Logger logger = Logger.getLogger(LuhnAlgo.class.getName());
		String validCardNumber = "4624748233249780";
		String invalidCardNumber = "4624748233249080";
		ILuhnAlgo ln = new LuhnAlgoImpl();
		
		//Checking if the card number is valid
		logger.info(String.format("Checking %s isValid? : %s", validCardNumber, ln.isValidLuhn(validCardNumber)));
		logger.info(String.format("Checking %s isValid? : %s", invalidCardNumber, ln.isValidLuhn(invalidCardNumber)));
		
		//Generating Last Check digit
		logger.info(String.format("Generating Checking Digit with %s : %s", validCardNumber, ln.generateCheckDigit(validCardNumber)));
		logger.info(String.format("Generating Checking Digit with %s : %s", invalidCardNumber, ln.generateCheckDigit(invalidCardNumber)));
		 
		//Counting Valid Luhn Numbers in a range 
		logger.info(ln.countRange(invalidCardNumber, validCardNumber) + "");
	}

}
