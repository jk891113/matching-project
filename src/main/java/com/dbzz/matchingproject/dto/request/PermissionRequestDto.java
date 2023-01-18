package com.dbzz.matchingproject.dto.request;

import com.dbzz.matchingproject.enums.UserRoleEnum;
import lombok.Getter;

@Getter
public class PermissionRequestDto {

    private UserRoleEnum role;
}
