package com.affaince.subscription.product.web.exception;

/**
 * Created by mandar on 10/21/2017.
 */
public class CategoryAlreadyExistsException extends Exception  {
    private static final String message = "The category with Id %s, already exists ";

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

    public static CategoryAlreadyExistsException build(String categoryId ) {
        return new CategoryAlreadyExistsException(String.format(message, categoryId));
    }

}
