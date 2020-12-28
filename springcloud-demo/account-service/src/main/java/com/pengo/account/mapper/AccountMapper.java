package com.pengo.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengo.common.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account>
{
}
