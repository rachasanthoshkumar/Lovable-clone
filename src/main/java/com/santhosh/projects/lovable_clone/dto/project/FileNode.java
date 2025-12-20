package com.santhosh.projects.lovable_clone.dto.project;

import java.time.Instant;

public record FileNode(
        String path,
        Instant modiefiedAt,
        String type,
        Long size
) {
}
