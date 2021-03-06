package com.ysc.device.service.rocketMQ.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.stereotype.Component;

/**
 * Create by yusicong
 * Date 2019/8/8 16:40
 */
@Component
public class PayProducer {

    /**
     * 生产组,生产者必须在生产组内
     */
    private String producerGroup = "pay_group";

    /**
     * 端口
     */
    private String nameServer = "39.106.206.72:9876;47.101.56.137:9876";

    private DefaultMQProducer producer;

    public PayProducer() {
        producer = new DefaultMQProducer(producerGroup);
        //关闭默认vip 否则自动走10911端口
        producer.setVipChannelEnabled(false);
        // 指定nameServer地址,多个地址之间以 ; 隔开
        producer.setNamesrvAddr(nameServer);
        producer.setSendMsgTimeout(9999);
        start();
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    /**
     * 对象在使用之前必须调用一次,并且只能初始化一次
     */
    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般在应用上下文,使用上下文监听器,进行关闭
     */
    public void shutdown() {
        producer.shutdown();
    }

}