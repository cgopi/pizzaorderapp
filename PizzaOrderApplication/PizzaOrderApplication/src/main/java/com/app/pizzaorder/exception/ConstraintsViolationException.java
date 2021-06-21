package com.app.pizzaorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ""
		+ "Please give a valid pizza order. "
		+ "Size should be small or medium or large. "
		+ "Variety should be cheese or chicken or beef. "
		+ "Order should not be deleted")
public class ConstraintsViolationException extends Exception
{

    static final long serialVersionUID = -3387516993224229948L;


    /**
     * @param message
     */
    public ConstraintsViolationException(String message)
    {
        super(message);
    }

}
