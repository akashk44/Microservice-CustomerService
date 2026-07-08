package in.akash.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

@FeignClient(name = "FRIENDSERVICE")
public interface FriendFeignClient {
    @GetMapping(value = "/api/friend/{phoneNumber}")
    List<Object[]> getFriendContactNumbers(@PathVariable Long phoneNumber);
}
