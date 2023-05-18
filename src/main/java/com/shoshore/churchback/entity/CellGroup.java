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
    @ToString.Exclude
    private List<ChurchMember> churchMembers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CellGroup cellGroup = (CellGroup) o;
        return getId() != null && Objects.equals(getId(), cellGroup.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
