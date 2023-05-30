package com.shoshore.churchback.entity;

import com.shoshore.churchback.enums.Role;
import lombok.*;

import javax.persistence.*;

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
public class ChurchMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
    private String username;
    private String password;

    private String email;

    private String mobileNumber;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_group_id")
    private CellGroup cellGroup;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role;
}
