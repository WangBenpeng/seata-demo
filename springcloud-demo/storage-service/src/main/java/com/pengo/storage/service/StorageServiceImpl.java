package com.pengo.storage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengo.common.entity.Storage;
import com.pengo.storage.mapper.StorageMapper;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService
{
}
