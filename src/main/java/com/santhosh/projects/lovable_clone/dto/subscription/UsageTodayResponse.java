package com.santhosh.projects.lovable_clone.dto.subscription;

public record UsageTodayResponse(
        Integer tokesnUsed,
        Integer tokensLimit,
        Integer previewsRunning,
        Integer previewsLimit
) {
}
