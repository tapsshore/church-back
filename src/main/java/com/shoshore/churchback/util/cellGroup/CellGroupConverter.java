package com.shoshore.churchback.util.cellGroup;

import com.shoshore.churchback.entity.CellGroup;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public class CellGroupConverter {
    public static CreateCellGroupResponse convert(CellGroup cellGroup) {
        final CreateCellGroupResponse createCellGroupResponse = new CreateCellGroupResponse();
        createCellGroupResponse.setId(cellGroup.getId());
        createCellGroupResponse.setCellGroupName(cellGroup.getCellGroupName());
        createCellGroupResponse.setEmail(cellGroup.getEmail());
        createCellGroupResponse.setPhoneNumber(cellGroup.getPhoneNumber());
        createCellGroupResponse.setLocation(cellGroup.getLocation());
        return createCellGroupResponse;
    }
}
