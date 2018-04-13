package com.wangpu.controller;

import org.apache.zookeeper.AsyncCallback;

/**
 * Created by Kenvin on 2018/4/13.
 */
public class DeleteCallBack implements AsyncCallback.VoidCallback {
    @Override
    public void processResult(int rc, String path, Object ctx) {
        System.out.println("删除节点" + path);
        System.out.println((String)ctx);
    }

}
