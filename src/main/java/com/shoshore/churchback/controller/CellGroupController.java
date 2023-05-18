package com.shoshore.churchback.controller;

import com.shoshore.churchback.entity.CellGroup;
import com.shoshore.churchback.model.CellGroupRequest;
import com.shoshore.churchback.repository.CellGroupRepository;
import com.shoshore.churchback.services.CellGroupService;
import com.shoshore.churchback.util.CustomerResponse;
import com.shoshore.churchback.util.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cellGroups")
public class CellGroupController {
    @Autowired
    CellGroupRepository cellGroupRepository;

    @Autowired
    CellGroupService cellGroupService;

    @GetMapping
    public ResponseEntity<List<CellGroup>> getAllCellGroups() {
        List<CellGroup> cellGroups = cellGroupRepository.findAll();
        return ResponseEntity.ok(cellGroups);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CellGroup> getCellGroupById(@PathVariable Long id) {
        Optional<CellGroup> cellGroup = cellGroupRepository.findById(id);
        return cellGroup.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseBody
    public CustomerResponse createCellGroup(@RequestBody CellGroupRequest cellGroupRequest) {
        return cellGroupService.createCellGroup(cellGroupRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CellGroup> updateCellGroup(@PathVariable Long id, @RequestBody CellGroup cellGroupData) {
        Optional<CellGroup> existingCellGroup = cellGroupRepository.findById(id);
        if (existingCellGroup.isPresent()) {
            CellGroup cellGroup = existingCellGroup.get();
            cellGroup.setCellGroupName(cellGroupData.getCellGroupName());
            cellGroup.setLocation(cellGroupData.getLocation());
            cellGroup.setPhoneNumber(cellGroupData.getPhoneNumber());
            cellGroup.setEmail(cellGroupData.getEmail());

            CellGroup updatedCellGroup = cellGroupRepository.save(cellGroup);
            return ResponseEntity.ok(updatedCellGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public CustomerResponse deleteCellGroup(@PathVariable Long id) {
        Optional<CellGroup> cellGroup = cellGroupRepository.findById(id);
        if (cellGroup.isPresent()) {
            cellGroupRepository.delete(cellGroup.get());
            return RequestResponse.getOKResponse("Cell Group with ID " + id + " deleted successfully.");
        } else {
            return RequestResponse.getBADResponse("Cell Group with ID " + id + " not available");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CellGroup>> findByAnyField(@RequestParam("keyword") String keyword) {
        List<CellGroup> cellGroups = cellGroupRepository.findByCellGroupNameContainingIgnoreCase(keyword);
        cellGroups.addAll(cellGroupRepository.findByLocationContainingIgnoreCase(keyword));
        cellGroups.addAll(cellGroupRepository.findByPhoneNumberContainingIgnoreCase(keyword));
        cellGroups.addAll(cellGroupRepository.findByEmailContainingIgnoreCase(keyword));

        return ResponseEntity.ok(cellGroups);
    }

}
