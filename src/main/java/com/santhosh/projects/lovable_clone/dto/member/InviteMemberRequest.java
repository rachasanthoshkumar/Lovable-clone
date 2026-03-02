package com.santhosh.projects.lovable_clone.dto.member;

import com.santhosh.projects.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @NotNull Long userId,
        @NotNull ProjectRole role
) {
}
