package in.akash.feign;

import in.akash.model.PlanData;
import org.springframework.stereotype.Component;

@Component
public class PlanFeignClientFallback implements PlanFeignClient {
    @Override
    public PlanData fetchPlan(String planId) {
        return new PlanData("0", "Fallback Plan", "0 days", "Fallback Plan");
    }
}
