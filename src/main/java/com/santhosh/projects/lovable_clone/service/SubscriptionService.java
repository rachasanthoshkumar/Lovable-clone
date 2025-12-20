package com.santhosh.projects.lovable_clone.service;

import com.santhosh.projects.lovable_clone.dto.subscription.CheckoutRequest;
import com.santhosh.projects.lovable_clone.dto.subscription.CheckoutResponse;
import com.santhosh.projects.lovable_clone.dto.subscription.PortalResponse;
import com.santhosh.projects.lovable_clone.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {

     SubscriptionResponse getCurrentSubscription(Long userId);

     CheckoutResponse createCheckoutSession(CheckoutRequest request, Long userId);


     PortalResponse openCustomerPortal(Long userId);
}
