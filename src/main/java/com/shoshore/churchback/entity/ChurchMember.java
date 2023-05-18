package com.shoshore.churchback.entity;

import com.shoshore.churchback.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@mail.com
 * @created : 18/5/2023, Thursday
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChurchMember {    private String firstName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    @ManyToOne
    private CellGroup cellGroup;
    @Enumerated(EnumType.STRING)
    private Role role;
}
