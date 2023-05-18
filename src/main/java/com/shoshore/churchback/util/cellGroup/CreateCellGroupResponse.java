package com.shoshore.churchback.util.cellGroup;

import com.shoshore.churchback.entity.ChurchMember;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Data
public class CreateCellGroupResponse {
    private Long id;
    private String cellGroupName;
    private String location;
    private String phoneNumber;
    private String email;
    private List<ChurchMember> churchMembers = new ArrayList<>();
}
