package com.shoshore.churchback.services.churchMember;

import com.shoshore.churchback.exceptions.ChurchException;
import com.shoshore.churchback.model.ChurchMemberRequest;
import com.shoshore.churchback.util.CustomerResponse;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public interface ChurchMemberService {
    CustomerResponse createChurchMember(ChurchMemberRequest churchMemberRequest) throws ChurchException;
}
