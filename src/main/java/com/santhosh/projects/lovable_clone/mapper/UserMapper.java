package com.santhosh.projects.lovable_clone.mapper;


import com.santhosh.projects.lovable_clone.dto.auth.AuthResponse;
import com.santhosh.projects.lovable_clone.dto.auth.SignupRequest;
import com.santhosh.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.santhosh.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest request);

    UserProfileResponse toUserProfileResponse(User user);
}
