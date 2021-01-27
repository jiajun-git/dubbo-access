package com.access.base.mapper;

import com.access.api.entity.DrDevDoor;
import com.access.api.model.AccessDevInitDto;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DrDevDoorMapper extends Mapper<DrDevDoor> {
    List<AccessDevInitDto> getDevInitDate(@Param("devCode") String devCode);

    DrDevDoor getDevInfoByDevCodeAndMac(@Param("devCode")String devCode, @Param("mac")Integer mac);
}