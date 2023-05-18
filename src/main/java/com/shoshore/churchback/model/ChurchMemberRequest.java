package com.shoshore.churchback.model;

import com.shoshore.churchback.enums.Role;
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
public class ChurchMemberRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private Long cellGroupId;
    private Role role;
}
