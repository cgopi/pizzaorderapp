package com.app.pizzaorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find entity with input parameters.")
public class EntityNotFoundWithInputParametersException extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 597743289083637798L;

	/**
	 * @param message
	 */
	public EntityNotFoundWithInputParametersException(String message)
    {
        super(message);
    }

}
