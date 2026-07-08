package in.akash.feign;

import in.akash.model.PlanData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PLANSERVICE")
public interface PlanFeignClient {
    @GetMapping(value = "/api/plans/{planId}", produces = "application/json")
    PlanData fetchPlan(@PathVariable String planId);
}
