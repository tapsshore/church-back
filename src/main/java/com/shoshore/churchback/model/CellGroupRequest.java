package com.shoshore.churchback.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CellGroupRequest {
    private String cellGroupName;
    private String location;
    private String phoneNumber;
    private String email;
}
