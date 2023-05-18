package com.shoshore.churchback.util.churchMember;

import com.shoshore.churchback.entity.ChurchMember;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public class ChurchMemberConverter {
    public static CreateChurchMemberResponse convert(ChurchMember churchMember) {
        final CreateChurchMemberResponse createChurchMemberResponse = new CreateChurchMemberResponse();
        createChurchMemberResponse.setId(churchMember.getId());
        createChurchMemberResponse.setFirstName(churchMember.getFirstName());
        createChurchMemberResponse.setLastName(churchMember.getLastName());
        createChurchMemberResponse.setEmail(churchMember.getEmail());
        createChurchMemberResponse.setAddress(churchMember.getAddress());
        createChurchMemberResponse.setRole(churchMember.getRole());
        createChurchMemberResponse.setCellGroup(churchMember.getCellGroup());
        createChurchMemberResponse.setMobileNumber(churchMember.getMobileNumber());

        return createChurchMemberResponse;
    }

}
