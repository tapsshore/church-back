package com.shoshore.churchback.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String result;
    private Object data;
    private boolean success;


    public CustomerResponse(String res, Object obj) {
        result = res;
        data = obj;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerResponse{");
        sb.append("result='").append(result).append('\'');
        sb.append(", data=").append(data);
        sb.append(", success=").append(success);
        sb.append('}');
        return sb.toString();
    }
}
