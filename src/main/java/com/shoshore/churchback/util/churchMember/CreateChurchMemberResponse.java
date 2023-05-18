package com.shoshore.churchback.util.churchMember;

import com.shoshore.churchback.entity.CellGroup;
import com.shoshore.churchback.enums.Role;
import lombok.Data;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Data
public class CreateChurchMemberResponse {
    private String firstName;
    private Long id;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private Long cellGroupId;
    private Role role;
}
