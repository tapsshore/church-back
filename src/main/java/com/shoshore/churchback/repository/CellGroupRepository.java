package com.shoshore.churchback.repository;

import com.shoshore.churchback.entity.CellGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Repository
public interface CellGroupRepository extends JpaRepository<CellGroup,Long> {
}
