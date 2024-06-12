package com.vd.restaurant.restaurant_commission_calculator.exceptions;

public class MenuItemNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuItemNotFoundException(String message) {
        super(message);
    }
}