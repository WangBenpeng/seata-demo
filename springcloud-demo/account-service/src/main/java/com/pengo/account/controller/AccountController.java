package com.pengo.account.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pengo.account.service.AccountService;
import com.pengo.common.entity.Account;
import com.pengo.common.value.ResponseValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController
{
  @Autowired
  private AccountService accountService;

  @PostMapping(value = "/account", produces = "application/json")
  public String account(String userId, int money)
  {
    UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("user_id", userId);
    updateWrapper.setSql("money = money - " + money);
    boolean result = accountService.update(updateWrapper);
    if(result)
      return ResponseValue.SUCCESS;
    else
      return ResponseValue.FAIL;
  }
}
