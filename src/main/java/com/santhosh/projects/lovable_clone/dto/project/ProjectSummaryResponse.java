package com.santhosh.projects.lovable_clone.dto.project;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collector;

public record ProjectSummaryResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {

}
