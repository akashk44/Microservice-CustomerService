package in.akash.api;

import in.akash.entity.CustomerEntity;
import in.akash.entity.LoginRequest;
import in.akash.feign.FriendFeignClient;
import in.akash.feign.PlanFeignClient;
import in.akash.model.CustomerProfile;
import in.akash.model.PlanData;
import in.akash.repository.CustomerRepository;
import in.akash.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    PlanFeignClient pfc;

    @Autowired
    FriendFeignClient ffc;

    @Autowired
    public CustomerService customerService;

    private static final String PLAN_URL = "http://localhost:8080/api/plans/{id}";
    private static final String FRIEND_URL = "http://localhost:8081/api/friend/{phoneNumber}";

    @PostMapping("/register")
    public boolean addCustomer(@RequestBody in.akash.entity.CustomerEntity customer) {
        return customerService.registerCustomer(customer);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest loginRequest) {
        return customerService.loginCustomer(loginRequest);
    }


    @GetMapping("/showprofile/{phoneNumber}")
    public ResponseEntity<CustomerProfile> Showprofile(@PathVariable Long phoneNumber) {

       /*
        RestTemplate restTemplate = new RestTemplate();
        CustomerEntity customerEntity = customerService.readCustomer(phoneNumber);
        CustomerProfile customerProfile = new CustomerProfile();

        BeanUtils.copyProperties(customerEntity, customerProfile);

        //calling plan microservices
        ResponseEntity<PlanData> planResponse = restTemplate.getForEntity(PLAN_URL, PlanData.class, customerEntity.getPlanId());
        in.akash.model.PlanData planData = planResponse.getBody();
        BeanUtils.copyProperties(planData, customerProfile);

        //calling friend microservices
       // ResponseEntity<Object[]> friendResponse = restTemplate.getForEntity(FRIEND_URL, Object[].class, phoneNumber);
        ParameterizedTypeReference<List<Object[]>> typeRef = new ParameterizedTypeReference<List<Object[]>>() {};
        ResponseEntity<List<Object[]>> friendResponse = restTemplate.exchange(FRIEND_URL, HttpMethod.GET, null, typeRef, phoneNumber);
        List<Object[]> friendContactNumbers = friendResponse.getBody();
        customerProfile.setFriendContactNumbers(friendContactNumbers);
        return ResponseEntity.ok(customerProfile);*/
        CustomerEntity customerEntity = customerService.readCustomer(phoneNumber);
        CustomerProfile customerProfile = new CustomerProfile();
        BeanUtils.copyProperties(customerEntity, customerProfile);

        //calling plan service using feign client
        PlanData planData = pfc.fetchPlan(customerEntity.getPlanId());
        BeanUtils.copyProperties(planData, customerProfile);

        //calling friend service using feign client
        List<Object[]> friendContactNumbers = ffc.getFriendContactNumbers(phoneNumber);
        //BeanUtils.copyProperties(friendContactNumbers, customerProfile);
        customerProfile.setFriendContactNumbers(friendContactNumbers);

        return ResponseEntity.ok(customerProfile);
    }




}

