package com.edu.ulab.app.validation;

import com.edu.ulab.app.dto.UserDto;

public class IsValidUser {

    public static boolean isValid(UserDto userDto) {
        boolean valid = (userDto.getFullName() != null && userDto.getAge() > 0) ? true : false;
        return valid;
    }
}
