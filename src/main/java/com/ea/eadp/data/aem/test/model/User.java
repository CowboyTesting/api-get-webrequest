/**
 * EA Private Ltd. All rights reserved 2012
 */
package com.ea.eadp.data.aem.test.model;

/**
 * @author cstuehrenberg
 *
 */

import java.io.Serializable;

public class User implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private static String name;
	private static String password;
	public static String getName()
	{
		return name;
	}
	public static void setName(String name)
	{
		User.name = name;
	}
	public static String getPassword()
	{
		return password;
	}
	public static void setPassword(String password)
	{
		User.password = password;
	}
}
