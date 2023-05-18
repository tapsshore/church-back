package com.shoshore.churchback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@mail.com
 * @created : 18/5/2023, Thursday
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cellGroupName;
    private String location;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "cellGroup")
    private List<ChurchMember> churchMembers = new ArrayList<>();
}
