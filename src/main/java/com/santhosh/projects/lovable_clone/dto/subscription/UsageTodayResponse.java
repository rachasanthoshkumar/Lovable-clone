package com.santhosh.projects.lovable_clone.dto.subscription;

public record UsageTodayResponse(
        int tokesnUsed,
        int tokensLimit,
        int previewsRunning,
        int previewsLimit
) {
}
