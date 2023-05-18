package com.shoshore.churchback.controller;

import com.shoshore.churchback.entity.ChurchMember;
import com.shoshore.churchback.enums.Role;
import com.shoshore.churchback.exceptions.ChurchException;
import com.shoshore.churchback.model.ChurchMemberRequest;
import com.shoshore.churchback.repository.ChurchMemberRepository;
import com.shoshore.churchback.services.churchMember.ChurchMemberService;
import com.shoshore.churchback.util.CustomerResponse;
import com.shoshore.churchback.util.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    @ResponseBody
    public CustomerResponse createChurchMember(@RequestBody ChurchMemberRequest churchMemberRequest) throws ChurchException {
        return churchMemberService.createChurchMember(churchMemberRequest);
    }

    @GetMapping
    public ResponseEntity<List<ChurchMember>> getAllCellGroups() {
        List<ChurchMember> churchMembers = churchMemberRepository.findAll();
        return ResponseEntity.ok(churchMembers);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ChurchMember> getChurchMemberById(@PathVariable Long id) {
        Optional<ChurchMember> churchMember = churchMemberRepository.findById(id);
        return churchMember.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
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
            churchMember.setRole(churchMemberData.getRole());
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
    public ResponseEntity<List<ChurchMember>> findByAnyField(@RequestParam("keyword") String keyword) {
        List<ChurchMember> churchMembers = churchMemberRepository.findByFirstNameContainingIgnoreCase(keyword);
        churchMembers.addAll(churchMemberRepository.findByLastNameContainingIgnoreCase(keyword));
        churchMembers.addAll(churchMemberRepository.findByMobileNumberContainingIgnoreCase(keyword));
        churchMembers.addAll(churchMemberRepository.findByEmailContainingIgnoreCase(keyword));
        churchMembers.addAll(churchMemberRepository.findByRole(Role.valueOf(keyword)));

        return ResponseEntity.ok(churchMembers);
    }
}
