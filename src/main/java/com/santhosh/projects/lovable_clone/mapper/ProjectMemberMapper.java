package com.santhosh.projects.lovable_clone.mapper;

import com.santhosh.projects.lovable_clone.dto.member.MemberResponse;
import com.santhosh.projects.lovable_clone.entity.ProjectMember;
import com.santhosh.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User user);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponse toProjectMemberResponseFromProjectMember(ProjectMember projectMember);
}
