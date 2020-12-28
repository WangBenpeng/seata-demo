package com.pengo.order.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pengo.common.entity.Order;
import com.pengo.common.value.ResponseValue;
import com.pengo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Slf4j
@RestController
public class OrderController
{
  private Random random = new Random();
  private static final String USER_ID = "U100001";

  @Autowired
  private OrderService orderService;
  @Autowired
  private RestTemplate restTemplate;

  @PostMapping(value = "/order", produces = "application/json")
  public String order(String userId, String commodityCode, Integer orderCount)
  {
    int orderMoney = 2 * orderCount;
    invokerAccountService(orderMoney);

    final Order order = new Order();
    order.setUserId(userId);
    order.setCommodityCode(commodityCode);
    order.setCount(orderCount);
    order.setMoney(orderMoney);

    boolean result = orderService.save(order);

    if(random.nextBoolean())
    {
      throw new RuntimeException("===> Order Exception");
    }

    if(result)
      return ResponseValue.SUCCESS;
    else
      return ResponseValue.FAIL;

  }

  private void invokerAccountService(int orderMoney)
  {
    String url = "http://127.0.0.1:18004/account";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

    map.add("userId",USER_ID);
    map.add("money", orderMoney + "");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    log.info("account resp===> {}", response.getBody());
  }
}
