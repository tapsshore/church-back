package com.shoshore.churchback.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@mail.com
 * @created : 18/5/2023, Thursday
 **/
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CellGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cellGroupName;
    private String location;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "cellGroup")
    @ToString.Exclude
    private List<ChurchMember> churchMembers = new ArrayList<>();
}
