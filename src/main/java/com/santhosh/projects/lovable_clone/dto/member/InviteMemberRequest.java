package com.santhosh.projects.lovable_clone.dto.member;

import com.santhosh.projects.lovable_clone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
