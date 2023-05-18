package com.shoshore.churchback.services;

import com.shoshore.churchback.model.CellGroupRequest;
import com.shoshore.churchback.util.CustomerResponse;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public interface CellGroupService {
    CustomerResponse createCellGroup(CellGroupRequest cellGroupRequest);
}
