package com.example.hotelreservationsystem.api.v1.request;

import com.example.hotelreservationsystem.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRq {

    private String username;

    private String password;

    private String email;

    private RoleType roleType;
}
