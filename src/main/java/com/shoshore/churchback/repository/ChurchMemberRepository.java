package com.shoshore.churchback.repository;

import com.shoshore.churchback.entity.ChurchMember;
import com.shoshore.churchback.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@gmail.com
 * @created : 18/5/2023, Thursday
 **/
@Repository
public interface ChurchMemberRepository extends JpaRepository<ChurchMember, Long> {
    boolean existsByMobileNumber(String phoneNumber);

    boolean existsByEmail(String email);

    List<ChurchMember> findByFirstNameContainingIgnoreCase(String keyword);

    Collection<? extends ChurchMember> findByLastNameContainingIgnoreCase(String keyword);

    Collection<? extends ChurchMember> findByMobileNumberContainingIgnoreCase(String keyword);

    Collection<? extends ChurchMember> findByEmailContainingIgnoreCase(String keyword);

    Collection<? extends ChurchMember> findByRole(Role role);
}
