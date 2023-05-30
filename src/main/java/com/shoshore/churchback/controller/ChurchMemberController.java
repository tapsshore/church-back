package com.shoshore.churchback.controller;

import com.shoshore.churchback.entity.ChurchMember;
import com.shoshore.churchback.model.CellGroupRequest;
import com.shoshore.churchback.repository.ChurchMemberRepository;
import com.shoshore.churchback.services.cellGroup.CellGroupService;
import com.shoshore.churchback.services.churchMember.ChurchMemberService;
import com.shoshore.churchback.util.CustomerResponse;
import com.shoshore.churchback.util.RequestResponse;
import com.shoshore.churchback.util.churchMember.ChurchMemberConverter;
import com.shoshore.churchback.util.churchMember.CreateChurchMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@RestController
@RequestMapping("/api/churchMembers")
public class ChurchMemberController {
    @Autowired
    ChurchMemberRepository churchMemberRepository;
    @Autowired
    ChurchMemberService churchMemberService;

    @Autowired
    CellGroupService cellGroupService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCellGroup(@RequestBody CellGroupRequest cellGroupRequest) {
        CustomerResponse response = cellGroupService.createCellGroup(cellGroupRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    @ResponseBody
    List<CreateChurchMemberResponse> getAllChurchMembers() {
        List<ChurchMember> churchMembers = churchMemberRepository.findAll();
        return churchMembers.stream()
                .map(ChurchMemberConverter::convert)
                .collect(Collectors.toList());
    }


    @PutMapping("/{id}")
    public ResponseEntity<ChurchMember> updateChurchMember(@PathVariable Long id, @RequestBody ChurchMember churchMemberData) {
        Optional<ChurchMember> existingChurchMember = churchMemberRepository.findById(id);
        if (existingChurchMember.isPresent()) {
            ChurchMember churchMember = existingChurchMember.get();
            churchMember.setLastName(churchMemberData.getFirstName());
            churchMember.setLastName(churchMemberData.getLastName());
            churchMember.setMobileNumber(churchMemberData.getMobileNumber());
            churchMember.setEmail(churchMemberData.getEmail());
            churchMember.setAddress(churchMember.getAddress());
            churchMember.setCellGroup(churchMemberData.getCellGroup());
            ChurchMember updatedChurchMember = churchMemberRepository.save(churchMember);
            return ResponseEntity.ok(updatedChurchMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public CustomerResponse deleteChurchMember(@PathVariable Long id) {
        Optional<ChurchMember> churchMember = churchMemberRepository.findById(id);
        if (churchMember.isPresent()) {
            churchMemberRepository.delete(churchMember.get());
            return RequestResponse.getOKResponse("Church Member with ID " + id + " deleted successfully.");
        } else {
            return RequestResponse.getBADResponse("Church Member  with ID " + id + " not available");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreateChurchMemberResponse>> findByAnyField(@RequestParam("keyword") String keyword) {
        List<ChurchMember> churchMembers = churchMemberRepository.findByFirstNameContainingIgnoreCase(keyword);
        churchMembers.addAll(churchMemberRepository.findByLastNameContainingIgnoreCase(keyword));
        churchMembers.addAll(churchMemberRepository.findByMobileNumberContainingIgnoreCase(keyword));
        churchMembers.addAll(churchMemberRepository.findByEmailContainingIgnoreCase(keyword));

        List<CreateChurchMemberResponse> responseList = new ArrayList<>();
        for (ChurchMember churchMember : churchMembers) {
            CreateChurchMemberResponse response = ChurchMemberConverter.convert(churchMember);
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }
}
