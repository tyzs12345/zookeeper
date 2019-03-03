package com.tian.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class master1 {

    /**
     *master选举的实现原理：
     * 选择一个节点，例如/master_select,多台机器同时创建一个节点/master_select/lock,
     * 利用zookeeper的特性，最终只有一台机器可以创建成功，成功的那台机器就是master去处理任务，
     * 被选举为master的分布式节点挂了，或者退出，才会选择新的master节点。master的takeLeadership
     * 方法执行完后会马上释放master权利在进入新的一轮master选举。
     * 业务场景：在分布式系统中，对于比较复杂的消耗性能、i/o、cpu的业务，需要集群中的部分去处理，
     * 然后将处理结果共享给集群中的其他节点。
     *
     */
    public static void main(String args[]) {

        //重试策略，最大三次，初始间隔为1s
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        //fluent风格
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.1.38:2181")
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(2000)
                .retryPolicy(retryPolicy)
                .namespace("test")  //实现业务隔离的命名空间,创建test节点下面的业务
                .build();
        client.start();

        try {


          /*  for(int i = 0; i < 10; i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LeaderSelector leaderSelector = new LeaderSelector(client, "/master_select", new LeaderSelectorListenerAdapter() {
                            @Override
                            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                                System.out.println("获得master权利");
                                Thread.sleep(100);
                                System.out.println("当前的线程: "+Thread.currentThread().getName());
                                System.out.println("释放master权利");
                            }
                        });
                        leaderSelector.autoRequeue();
                        leaderSelector.start();
                    }
                }).start();
               //Thread.sleep(600);
            }*/


            LeaderSelector leaderSelector = new LeaderSelector(client, "/master_select", new LeaderSelectorListenerAdapter() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    System.out.println("获得master权利");
                    Thread.sleep(5000);
                    System.out.println("当前的线程: "+Thread.currentThread().getName());
                    System.out.println("释放master权利");
                }
            });
            leaderSelector.autoRequeue();
            leaderSelector.start();


            /**
             * 创建LeaderSelector时需要传入监听器LeaderSelectorListenerAdapter，
             * Curator获取到master权利时会回调改监听器
             */
            Thread.sleep(Integer.MAX_VALUE);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
