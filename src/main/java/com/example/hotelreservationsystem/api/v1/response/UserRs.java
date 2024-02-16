package com.example.hotelreservationsystem.api.v1.response;

import com.example.hotelreservationsystem.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRs {

    private Long id;

    private String username;

    private String password;

    private String email;

    private List<RoleType> roles;
}
