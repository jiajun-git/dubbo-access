package com.access.base.mapper;

import com.access.api.entity.DrDevDoor;
import com.access.api.model.AccessDevInitDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrDevDoorMapper extends Mapper<DrDevDoor> {
    List<AccessDevInitDto> getDevInitDate(String devCode);

    DrDevDoor getDevInfoByDevCodeAndMac(String devCode, Integer mac);
}