package com.shoshore.churchback.util;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public class RequestResponse {
    private RequestResponse() {
    }

    public static CustomerResponse getOKResponse() {
        CustomerResponse customerResponse = new CustomerResponse("OK", "");
        customerResponse.setSuccess(true);
        return customerResponse;
    }

    public static CustomerResponse getOKResponse(Object data) {
        CustomerResponse customerResponse = new CustomerResponse("OK", data);
        customerResponse.setSuccess(true);
        return customerResponse;
    }


    public static CustomerResponse getBADResponse() {

        return new CustomerResponse("BAD", "");
    }

    public static CustomerResponse getBADResponse(Object data) {

        return new CustomerResponse("BAD", data);
    }
}
