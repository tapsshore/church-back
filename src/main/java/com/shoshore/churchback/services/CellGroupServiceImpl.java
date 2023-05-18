package com.shoshore.churchback.services;

import com.shoshore.churchback.entity.CellGroup;
import com.shoshore.churchback.model.CellGroupRequest;
import com.shoshore.churchback.repository.CellGroupRepository;
import com.shoshore.churchback.util.CustomerResponse;
import com.shoshore.churchback.util.RequestResponse;
import com.shoshore.churchback.util.ValidationUtil;
import com.shoshore.churchback.util.cellGroup.CellGroupConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Service
public class CellGroupServiceImpl implements CellGroupService{

    private final CellGroupRepository cellGroupRepository;

    public CellGroupServiceImpl(CellGroupRepository cellGroupRepository) {
        this.cellGroupRepository = cellGroupRepository;
    }

    @Override
    public CustomerResponse createCellGroup(CellGroupRequest cellGroupRequest) {
        ValidationUtil.validateFieldPresent(cellGroupRequest, "cellGroupName");
        ValidationUtil.validateFieldPresent(cellGroupRequest, "location");
        ValidationUtil.validateFieldPresent(cellGroupRequest, "phoneNumber");
        ValidationUtil.validateFieldPresent(cellGroupRequest, "email");
        String phoneNumber = cellGroupRequest.getPhoneNumber();
        String email = cellGroupRequest.getEmail();
        String cellGroupName =cellGroupRequest.getCellGroupName();
        if (cellGroupRepository.existsByPhoneNumber(phoneNumber)) {
            String errorMessage = "A cell group with the same phone number already exists.";
            return RequestResponse.getBADResponse(errorMessage);
        }

        if (cellGroupRepository.existsByEmail(email)) {
            String errorMessage = "A cell group with the same email already exists.";
            return RequestResponse.getBADResponse(errorMessage);
        }

        if (cellGroupRepository.existsByCellGroupName(cellGroupName)) {
            String errorMessage = "A cell group with the same name already exists.";
            return RequestResponse.getBADResponse(errorMessage);
        }
        CellGroup cellGroup = prepareCellGroupRequest(cellGroupRequest);
        cellGroup = cellGroupRepository.save(cellGroup);
        return RequestResponse.getOKResponse(CellGroupConverter.convert(cellGroup));
    }
    private CellGroup prepareCellGroupRequest(CellGroupRequest cellGroupRequest){
        CellGroup cellGroup =  new CellGroup();
        cellGroup.setCellGroupName(cellGroupRequest.getCellGroupName());
        cellGroup.setLocation(cellGroupRequest.getLocation());
        cellGroup.setEmail(cellGroupRequest.getEmail());
        cellGroup.setPhoneNumber(cellGroupRequest.getPhoneNumber());
        return cellGroup;
    }
}
