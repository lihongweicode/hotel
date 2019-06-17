package org.dppc.vv.common.rest;

import org.dppc.vv.common.annotation.Log;
import org.dppc.vv.common.enums.*;
import org.dppc.vv.common.model.ResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName DealerRest
 * @Description 获取枚举
 * @Author GAOJ
 * @Data 2019/04/16 14:02
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/enums")
public class CommonEnumRest {

    /**
     * @return org.dppc.vv.common.model.ResultDTO
     * @Author GAOJ
     * @Description 获取枚举
     * @Date 2019/04/16 14:02
     * @Param []
     **/
    @Log("获取枚举")
    @GetMapping
    public ResultDTO findEnums() {
        Map<String, List<EnumTypeVO>> map = new HashMap();
        map.put("DealerLevelEnum",  DealerLevelEnum.getEnumList());
        map.put("DealerAuditStatusEnum",  DealerAuditStatusEnum.getEnumList());
        map.put("OrderStatusEnum",  OrderStatusEnum.getEnumList());
        map.put("ProductUnitEnum",  ProductUnitEnum.getEnumList());
        map.put("driverTypeEnum", DriverTypeEnum.getEnumList());
        map.put("sexEnum", SexEnum.getEnumList());
        return ResultDTO.success().putDataValue("enums", map);
    }

}
