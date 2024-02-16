package com.example.hotelreservationsystem.mappers;

import com.example.hotelreservationsystem.api.v1.request.UserRq;
import com.example.hotelreservationsystem.api.v1.response.UserRs;
import com.example.hotelreservationsystem.entity.Role;
import com.example.hotelreservationsystem.entity.RoleType;
import com.example.hotelreservationsystem.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserRs> toDTO(List<User> list);
    @Mapping(target = "roles", source = "roles", qualifiedByName = "listRoles")
    UserRs toDTO(User user);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toModel(UserRq userRQ);

    @Named("listRoles")
    static List<RoleType> listRoles(List<Role> roleList) {
        return roleList.stream().map(Role::getAuthority).toList();
    }

}
