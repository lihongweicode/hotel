package org.dppc.vv.scheduled;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.dppc.vv.common.help.JsonHelp;
import org.dppc.vv.hotel.entity.Client;
import org.dppc.vv.hotel.service.ClientService;
import org.dppc.vv.util.BackupsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description 备份定时
 * @Author lhw
 * @Data 2019/6/14 18:49
 * @Version 1.0
 **/
@Component
public class BackupsScheduled {

    @Value("${backups.dirname}")
    private String dirname;
    private final String preFileName = "kehu";
    @Autowired
    private ClientService clientService;

    @Scheduled(cron = "0/30 0 0/2 * * ? ")
    private void backupsClient(){
        //查询所有客户信息
        List<Client> clients = clientService.findAll();
        String fileName = preFileName + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
        String clientStr = "";
        for (Client client : clients) {
            clientStr += client.toString();
        }
        BackupsUtil.writeContent(clientStr,dirname,fileName + ".txt",true);
        String jsonStr = JsonHelp.toJSon(clients);
        BackupsUtil.writeContent(jsonStr,dirname,fileName + "json.txt",true);
    }
}
