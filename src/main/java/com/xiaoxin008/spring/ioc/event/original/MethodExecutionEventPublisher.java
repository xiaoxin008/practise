package com.xiaoxin008.spring.ioc.event.original;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 自定义事件发布
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class MethodExecutionEventPublisher {

    private List<MethodExecutionEventListener> listeners = new ArrayList<>();

    public void methodToMonitor() throws InterruptedException {
        MethodExecutionEvent event = new MethodExecutionEvent(this,"methodToMonitor");
        this.publishEvent(MethodExecutionEvent.EVENT_BEGIN,event);
        TimeUnit.SECONDS.sleep(3);
        this.publishEvent(MethodExecutionEvent.EVENT_END,event);
    }

    private void publishEvent(String type,MethodExecutionEvent event){
        List<MethodExecutionEventListener> listenerList = new ArrayList<>(listeners);
        listenerList.stream().forEach(l -> {
            if(MethodExecutionEvent.EVENT_BEGIN.equals(type)){
                l.onMethodBegin(event);
            }else{
                l.onMethodEnd(event);
            }
        });
    }

    private void addListener(MethodExecutionEventListener listener){
        listeners.add(listener);
    }

    private void removeListener(MethodExecutionEventListener listener){
        if(listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    private void removeAllListeners(){
        listeners.clear();
    }

    public static void main(String[] args) throws InterruptedException{
        MethodExecutionEventPublisher publisher = new MethodExecutionEventPublisher();
        publisher.addListener(new MethodExecutionEventListener() {
        });
        publisher.addListener(new MethodExecutionEventListener() {
            @Override
            public void onMethodBegin(MethodExecutionEvent event) {
                System.out.println("start");
            }

            @Override
            public void onMethodEnd(MethodExecutionEvent event) {
                System.out.println("finish");
            }
        });
        publisher.methodToMonitor();
    }
}
