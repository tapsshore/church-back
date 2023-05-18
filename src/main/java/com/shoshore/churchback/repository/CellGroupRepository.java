package com.shoshore.churchback.repository;

import com.shoshore.churchback.entity.CellGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Repository
public interface CellGroupRepository extends JpaRepository<CellGroup, Long> {
    List<CellGroup> findByCellGroupNameContainingIgnoreCase(String keyword);

    List<CellGroup> findByLocationContainingIgnoreCase(String keyword);

    List<CellGroup> findByPhoneNumberContainingIgnoreCase(String keyword);

    List<CellGroup> findByEmailContainingIgnoreCase(String keyword);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByCellGroupName(String cellGroupName);
}
