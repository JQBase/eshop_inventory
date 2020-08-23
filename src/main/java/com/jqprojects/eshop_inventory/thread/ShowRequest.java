package com.jqprojects.eshop_inventory.thread;

import com.jqprojects.eshop_inventory.request.Request;
import com.jqprojects.eshop_inventory.request.RequestQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;

@Component
@Slf4j
public class ShowRequest {

    @Scheduled(fixedRate = 2000)
    public void showQ(){
        RequestQueue requestQueue = RequestQueue.getInstance();
        for (int i = 0; i < requestQueue.queueSize() ; i++) {
            ArrayBlockingQueue<Request> queue = requestQueue.getQueue(i);
            log.debug(" 内部队列-{}，有[{}]个任务待处理...",i,queue.size());
        }
    }
}
