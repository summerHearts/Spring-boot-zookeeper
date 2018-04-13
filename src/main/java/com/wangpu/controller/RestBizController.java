package com.wangpu.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * rest响应的controller
 * @author wangpu
 * @email zq2599@gmail.com
 * @date 2017/04/09 12:43
 */
@RestController
public class RestBizController {

    @RequestMapping(value = "/zkget" ,method = RequestMethod.GET)
    public String zkget() {
        Watcher watcher= new Watcher(){
            public void process(WatchedEvent event) {
                System.out.println("receive event +++++："+event);
            }
        };

        String value = null;
        List<String>  list = new ArrayList<String>();
        try {
            final ZooKeeper zookeeper = new ZooKeeper(
                    "101.132.146.115:2181",
                    999999,
                    watcher
            );

            long sessionId = zookeeper.getSessionId();
            byte[] sessionPassword = zookeeper.getSessionPasswd();

            String  sessionIdString =  "0x"+Long.toHexString(sessionId);

            System.out.println("连接状态："+zookeeper.getState()+ "  " +sessionIdString);
            new Thread().sleep(1000);

            final byte[] data = zookeeper.getData("/tapps/names", watcher, null);
            list =  zookeeper.getChildren("/tapps/names",watcher);
            value = new String(data);
            System.out.println("连接状态："+zookeeper.getState());


            final ZooKeeper zookeeperSession = new ZooKeeper(
                    "101.132.146.115:2181",
                    999999,
                    watcher,sessionId,
                    sessionPassword
            );

            new Thread().sleep(200);
            long sessionIds = zookeeperSession.getSessionId();
            String  sessionIdStrings =  "0x"+Long.toHexString(sessionIds);

            System.out.println("重新连接状态zkSession:"+zookeeperSession.getState());
            System.out.println("连接状态："+zookeeper.getState()+ "  "+sessionIdStrings);

            zookeeper.close();
            zookeeperSession.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return "get value from zookeeper [" + value + "]" + list.toString();
    }
}