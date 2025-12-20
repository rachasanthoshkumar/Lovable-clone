package com.santhosh.projects.lovable_clone.service;

import com.santhosh.projects.lovable_clone.dto.subscription.PlanLimitsResponse;
import com.santhosh.projects.lovable_clone.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {

     UsageTodayResponse getTodayUsageofUser(Long userId);

     PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
