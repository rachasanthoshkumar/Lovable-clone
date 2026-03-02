package com.santhosh.projects.lovable_clone.dto.member;

import com.santhosh.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId, String name, ProjectRole projectRole, Instant invitedAt
) {
}
