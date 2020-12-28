package com.pengo.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengo.account.mapper.AccountMapper;
import com.pengo.common.entity.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService
{

}
