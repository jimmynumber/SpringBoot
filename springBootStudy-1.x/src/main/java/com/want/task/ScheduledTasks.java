package com.want.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //[秒] [分] [小时] [日] [月] [周] [年]
    //@Scheduled(cron="${time.cron}")
    //@Scheduled(cron="*/${time.interval} * * * * *")
    //@Scheduled(fixedDelayString = "5000") //上一次执行完毕时间点之后5秒再执行
    //@Scheduled(fixedDelayString = "${time.fixedDelay}")
    //@Scheduled(initialDelay=1000, fixedRate=5000) //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    @Scheduled(fixedDelay = 1000*60) //上一次执行完毕时间点之后5秒再执行
    public void reportCurrentTime() {
        System.out.println("当前时间：" + dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedDelay = 1000*50) //上一次执行完毕时间点之后5秒再执行
    public void reportCurrentTime1() {
        System.out.println("当前时间111：" + dateFormat.format(new Date()));
    }

}
