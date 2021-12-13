package com.java.hotel_palacius.exceptions;

public class CartEmptyException extends Exception {

	private static final long serialVersionUID = 7520251456314057945L;

	public CartEmptyException() {
		super("ERROR: Cart empty exception.");
	}

}
