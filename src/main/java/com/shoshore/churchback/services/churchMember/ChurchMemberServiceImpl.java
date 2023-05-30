package com.shoshore.churchback.services.churchMember;

import com.shoshore.churchback.entity.CellGroup;
import com.shoshore.churchback.entity.ChurchMember;
import com.shoshore.churchback.entity.User;
import com.shoshore.churchback.enums.Role;
import com.shoshore.churchback.exceptions.ChurchException;
import com.shoshore.churchback.model.ChurchMemberRequest;
import com.shoshore.churchback.repository.CellGroupRepository;
import com.shoshore.churchback.repository.ChurchMemberRepository;
import com.shoshore.churchback.repository.UserRepository;
import com.shoshore.churchback.util.CustomerResponse;
import com.shoshore.churchback.util.RequestResponse;
import com.shoshore.churchback.util.ValidationUtil;
import com.shoshore.churchback.util.churchMember.ChurchMemberConverter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
public class ChurchMemberServiceImpl implements ChurchMemberService {
    private final ChurchMemberRepository churchMemberRepository;
    private final CellGroupRepository cellGroupRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ChurchMemberServiceImpl(ChurchMemberRepository churchMemberRepository,
                                   CellGroupRepository cellGroupRepository,
                                   PasswordEncoder passwordEncoder,
                                   UserRepository userRepository) {
        this.churchMemberRepository = churchMemberRepository;
        this.cellGroupRepository = cellGroupRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public CustomerResponse createChurchMember(ChurchMemberRequest churchMemberRequest) throws ChurchException {
        ValidationUtil.validateFieldPresent(churchMemberRequest, "firstName");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "lastName");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "username");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "password");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "email");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "mobileNumber");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "address");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "cellGroupId");
        ValidationUtil.validateFieldPresent(churchMemberRequest, "role");

        String phoneNumber = churchMemberRequest.getMobileNumber();
        String email = churchMemberRequest.getEmail();
        if (churchMemberRepository.existsByMobileNumber(phoneNumber)) {
            String errorMessage = "A church member with the same phone number already exists.";
            return RequestResponse.getBADResponse(errorMessage);
        }

        if (churchMemberRepository.existsByEmail(email)) {
            String errorMessage = "A church member with the same email already exists.";
            return RequestResponse.getBADResponse(errorMessage);
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(churchMemberRequest.getPassword());

        ChurchMember churchMember = prepareChurchMemberRequest(churchMemberRequest);
        churchMemberRepository.save(churchMember);

        User user = new User();
        user.setUsername(churchMemberRequest.getUsername());
        user.setPassword(hashedPassword);
        user.setRoles(Collections.singleton(churchMemberRequest.getRole()));
        user.setChurchMember(churchMember);
        userRepository.save(user);

        return RequestResponse.getOKResponse(ChurchMemberConverter.convert(churchMember));
    }

    private ChurchMember prepareChurchMemberRequest(ChurchMemberRequest churchMemberRequest) throws ChurchException {
        CellGroup cellGroup = cellGroupRepository.findById(churchMemberRequest.getCellGroupId())
                .orElseThrow(() -> new ChurchException("Invalid Cell Group"));

        ChurchMember churchMember = new ChurchMember();
        churchMember.setFirstName(churchMemberRequest.getFirstName());
        churchMember.setLastName(churchMemberRequest.getLastName());
        churchMember.setEmail(churchMemberRequest.getEmail());
        churchMember.setAddress(churchMemberRequest.getAddress());
        churchMember.setMobileNumber(churchMemberRequest.getMobileNumber());
        churchMember.setCellGroup(cellGroup);

        // Set the username and password
        churchMember.setUsername(churchMemberRequest.getUsername());
        churchMember.setPassword(churchMemberRequest.getPassword());

        // Set the role
        Role role = churchMemberRequest.getRole();
        churchMember.setRole(role != null ? role : Role.GENERAL_MEMBER);

        return churchMember;
    }

}
