package com.pengo.order.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengo.common.entity.Order;
import com.pengo.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService
{
}
