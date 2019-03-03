package com.tian.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;

public class session {

    public static void main(String args[]){

        //重试策略，最大三次，初始间隔为1s
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        /*CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.1.38:2181", 5000,
                3000, retryPolicy);
        client.start();*/
        //fluent风格
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.1.38:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(2000)
                .retryPolicy(retryPolicy)
                .namespace("test")  //实现业务隔离的命名空间,创建test节点下面的业务
                .build();
        client.start();

        /* PERSISTENT(0, false, false),//持久的，默认值
           PERSISTENT_SEQUENTIAL(2, false, true),//持久的，连续的
           EPHEMERAL(1, true, false), //临时的
           EPHEMERAL_SEQUENTIAL(3, true, true);*/
        //client.create().withMode(CreateMode.PERSISTENT).forPath("tyzs1");
        try {
            //如果指定路径的数据节点的路径上的父节点不存在则进行递归创建。
            //String dataNode = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("tyzs1");
            //client.getData().forPath("");//获取数据
            //client.delete().forPath("");//删除节点
            //client.setData().forPath("");//更新数据
            ExecutorService threaPool = Executors.newFixedThreadPool(2);//创建线程池，提高事件的处理能力。如果不传入线程池则会用默认的EventThread处理
            CountDownLatch semahpore = new CountDownLatch(2);
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                .inBackground(new BackgroundCallback(){
                   @Override //client当前客户端实例，event服务端事件(定义了服务端发往客户端的一系列事件参数，主要有事件类型和响应码)
                   public void processResult(CuratorFramework client, CuratorEvent event) throws Exception{
                       System.out.println("code: "+event.getResultCode()+ ",type: "+event.getType());
                       System.out.println("thread name: "+ Thread.currentThread().getName());
                       semahpore.countDown();//释放所有等待的线程;如果当前计数为0，则什么也不会发生;如果当前计数大于0，则递减。如果新计数为0，则重新启用所有等待的线程
                  }
               }, threaPool)
               .forPath("tyzs2", "tyzs2".getBytes());
            //Stat stat = new Stat();
            client.setData().forPath("tyzs2", "hello qingmang".getBytes());//更新数据

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
