package com.santhosh.projects.lovable_clone.service;

import com.santhosh.projects.lovable_clone.dto.subscription.PlanResponse;
import org.jspecify.annotations.Nullable;

public interface PlanService {
     PlanResponse getAllActivePlans();
}
