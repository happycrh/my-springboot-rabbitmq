消息发送确认机制

消费确认机制

消息的重新投递

消费幂等性, 等等

Prefetch count：如果有多个消费者同时订阅同一个Queue中的消息，Queue中的消息会被平摊给多个消费者。这时如果每个消息的处理时间不同，就有可能会导致某些消费者一直在忙，而另外一些消费者很快就处理完手头工作并一直空闲的情况。我们可以通过设置prefetchCount来限制Queue每次发送给每个消费者的消息数，比如我们设置prefetchCount=1，则Queue每次给每个消费者发送一条消息；消费者处理完这条消息后Queue会再给该消费者发送一条消息

1、worker模式，需要指定队列的名称（@config Queue queue = new Queue(queueName) 
rabbitTemplate.convertAndSend(queueName, time);生产者中的routingKey要为queueName,routingKey找不到时会执行returnedMessage方法），否则消费端会报找不到队列的问题(reply-code=404, reply-text=NOT_FOUND - no queue 'order-queue-worker2' in vhost '/', class-id=50, method-id=10)

2、广播的方式（fanout）每个消费端都需要指定自己的@Queue 如果不指定value的值，默认队列会自动删除，消费端重启后队列删除，可能造成数据未消费问题
每个消费端的queue的value不能相同，否则可能导致某消费端未消费的情况（消费确认之后，会删除队列中的消息）
@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue-fanout",durable = "true"),
            exchange = @Exchange(name="order-exchange-fanout",durable = "true",type = "fanout"),
            key = "order.*"
    )
    )
    
    
 @RabbitHandler//如果有消息过来，在消费的时候调用这个方法
     需要注意的 basicAck 方法需要传递两个参数
     deliveryTag（唯一标识 ID）：当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag， 它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，delivery tag 的范围仅限于 Channel
     multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
