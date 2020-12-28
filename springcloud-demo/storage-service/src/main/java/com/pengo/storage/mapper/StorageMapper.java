package com.pengo.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengo.common.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StorageMapper extends BaseMapper<Storage>
{
}
