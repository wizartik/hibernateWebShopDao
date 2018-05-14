package dao.impl.responses.products;

import static dao.impl.responses.products.Messages.*;

public enum ProductDaoResponse {

    OK("OK"),
    PRODUCT_EXISTS(ALREADY_EXISTS_MESSAGE);

    private String message;

    ProductDaoResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProductDaoResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
