package com.santhosh.projects.lovable_clone.dto.subscription;

public record PlanResponse(
        Long id,
        String name,

        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreviews,
        Boolean unlimitedAi,
        String price

) {
}
