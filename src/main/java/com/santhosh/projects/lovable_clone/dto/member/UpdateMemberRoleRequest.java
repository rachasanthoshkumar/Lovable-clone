package com.santhosh.projects.lovable_clone.dto.member;

import com.santhosh.projects.lovable_clone.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(@NotNull ProjectRole role) {
}
