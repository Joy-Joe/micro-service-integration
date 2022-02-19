package cn.sy.common.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author joy joe
 * @date 2022/2/19 下午7:54
 * @DESC TODO
 */
@Slf4j
@Component
public class ConcurrentDemo {

    public void dealData(){
        List<Map<String,Object>> list = new ArrayList();
        for (int i=0;i<100000;i++){
            Map<String,Object> map = new HashMap();
            map.put("count",i+"");
            map.put("id", UUID.randomUUID().toString().replaceAll("-",""));
            list.add(map);
        }

        int countt = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(countt+1);
        AtomicInteger atomicInteger =new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        try {
            for (Object map1 :list){
                executorService.execute(()->{
                    try {
                        atomicInteger.incrementAndGet();
                        Map<String,Object> mppp = (Map) map1;
                        String count1 = (String)mppp.get("count");
                        String id1 = (String)mppp.get("id");
                    } catch (Exception e) {

                    }finally {
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await();
        } catch (Exception e) {

        }finally {
            executorService.shutdown();
        }
    }

    public void semaphoreDemo(){
        Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        try {
            for (int i=0;i<10;i++){
                final int s = i;
                executorService.execute(()->{
                    try {
                        semaphore.acquire();
                        System.out.println("第"+s +"辆车驶入车场");
                        Thread.sleep(1000);
                        System.out.println("第"+s +"辆车驶出车场===");
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {

        }
    }
}
