package com.shoshore.churchback.enums;

/**
 * @author : tapiwanasheshoshore
 * @mailto : tapsshore@mail.com
 * @created : 18/5/2023, Thursday
 **/
public enum Role {
    ADMIN("ADMIN"),
    USHER("USHER"),
    CLEANER("CLEANER"),
    GENERAL_MEMBER("GENERAL_MEMBER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
