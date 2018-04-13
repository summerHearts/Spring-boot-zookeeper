package com.wangpu.controller;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * Created by Kenvin on 2018/4/13.
 */
public class CreateCallback implements AsyncCallback.StatCallback {

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
        System.out.println("i = [" + i + "], s = [" + s + "], o = [" + o + "], stat = [" + stat + "]");
    }
}
