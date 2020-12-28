package com.pengo.business.controller;

import com.pengo.business.BusinessApplication;
import com.pengo.common.value.ResponseValue;
import com.pengo.common.value.StorageValue;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BusinessController
{

  @Autowired
  private RestTemplate restTemplate;
//  @Autowired
//  private BusinessApplication.StorageService storageService;
//  @Autowired
//  private BusinessApplication.OrderService orderService;

  @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
  @GetMapping(value = "/seata/rest", produces = "application/json")
  public String rest()
  {
    String url = "http://127.0.0.1:18002/storage/";
    String result = restTemplate.getForObject(url + StorageValue.COMMODITY_CODE + "/" + StorageValue.ORDER_COUNT, String.class);
    if(!ResponseValue.SUCCESS.equals(result))
      throw new RuntimeException("storage error===> " + result);

    url = "http://127.0.0.1:18003/order";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("userId", StorageValue.USER_ID);
    map.add("commodityCode", StorageValue.COMMODITY_CODE);
    map.add("orderCount", StorageValue.ORDER_COUNT + "");

    HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

    ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

    result = response.getBody();
    if(!ResponseValue.SUCCESS.equals(result))
    {
      throw new RuntimeException("order error===> " + result);
    }

    return ResponseValue.SUCCESS;

  }

//  @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
//  @GetMapping(value = "/seata/feign", produces = "application/json")
//  public String feign()
//  {
//    String result = storageService.storage(StorageValue.COMMODITY_CODE, StorageValue.ORDER_COUNT);
//    if(!ResponseValue.SUCCESS.equals(result))
//      throw new RuntimeException("storage error===> " + result);
//
//    result = orderService.order(StorageValue.USER_ID, StorageValue.COMMODITY_CODE, StorageValue.ORDER_COUNT);
//
//    if(!ResponseValue.SUCCESS.equals(result))
//      throw new RuntimeException("order error===> " + result);
//
//    return ResponseValue.SUCCESS;
//  }
}
