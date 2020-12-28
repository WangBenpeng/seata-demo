package com.pengo.storage.controlelr;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pengo.common.entity.Storage;
import com.pengo.common.value.ResponseValue;
import com.pengo.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController
{
  @Autowired
  private StorageService storageService;

  @GetMapping(value = "/storage/{commodityCode}/{count}")
  public String reduce(@PathVariable String commodityCode,@PathVariable int count)
  {
    UpdateWrapper<Storage> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("commodity_code", commodityCode);
    updateWrapper.setSql("count = count - " + count);
    boolean result = storageService.update(updateWrapper);
    if(result)
      return ResponseValue.SUCCESS;
    else
      return ResponseValue.FAIL;
  }
}
