/**
 * EA Private Ltd. All rights reserved 2012
 */

package com.ea.eadp.data.aem.test.model;

/**
 * @author cstuehrenberg
 *
 */

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class AEMTestTools
{

	/**
	 * 
	 */
	public AEMTestTools()
	{
		// TODO Auto-generated constructor stub
	}

	public int getRandomNumber(int i)
	{
		Random rand = new Random();
		return rand.nextInt(i);
	}
	
	public String getRandomString(int i)
	{
		String ret = RandomStringUtils.randomAlphabetic(i);
		return ret;
	}
}
