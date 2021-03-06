/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.openmessaging;

import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.observer.Observer;

/**
 * The {@code MessagingAccessPoint} obtained from {@link MessagingAccessPointFactory} is capable of creating {@code
 * Producer}, {@code Consumer}, {@code ServiceEndPoint}, and so on.
 * <p> For example:
 * <pre>
 * MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory.getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");
 * Producer producer = messagingAccessPoint.createProducer();
 * producer.send(producer.createBytesMessageToTopic("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
 * </pre>
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface MessagingAccessPoint extends ServiceLifecycle {
    /**
     * Returns the properties of this {@code MessagingAccessPoint} instance.
     * <p>
     * There are some standard properties defined by OMS for {@code MessagingAccessPoint}:
     * <ul>
     * <li> {@link PropertyKeys#ACCESS_POINTS}, the specified access points.
     * <li> {@link PropertyKeys#DRIVER_IMPL}, the fully qualified class name of the specified MessagingAccessPoint's
     * implementation, the default value is {@literal io.openmessaging.<driver_type>.MessagingAccessPointImpl}.
     * <li> {@link PropertyKeys#NAMESPACE}, the specified namespace.
     * </ul>
     *
     * @return the properties
     */
    KeyValue properties();

    /**
     * Creates a new {@code Producer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code Producer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     */
    Producer createProducer();

    /**
     * Creates a new {@code Producer} for the specified {@code MessagingAccessPoint}
     * with some preset properties.
     *
     * @param properties the preset properties
     * @return the created {@code Producer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     */
    Producer createProducer(KeyValue properties);

    /**
     * Creates a new {@code SequenceProducer} for the specified {@code MessagingAccessPoint}.
     *
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code SequenceProducer}
     */
    SequenceProducer createSequenceProducer();

    /**
     * Creates a new {@code SequenceProducer} for the specified {@code MessagingAccessPoint}
     * with some preset properties.
     *
     * @param properties the preset properties
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code SequenceProducer}
     */
    SequenceProducer createSequenceProducer(KeyValue properties);

    /**
     * Creates a new {@code PushConsumer} for the specified {@code MessagingAccessPoint}.
     * The returned {@code PushConsumer} isn't attached to any queue,
     * uses {@link PushConsumer#attachQueue(String, MessageListener)} to attach queues.
     *
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code PushConsumer}
     */
    PushConsumer createPushConsumer();

    /**
     * Creates a new {@code PushConsumer} for the specified {@code MessagingAccessPoint} with some preset properties.
     *
     * @param properties the preset properties
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code PushConsumer}
     */
    PushConsumer createPushConsumer(KeyValue properties);

    /**
     * Creates a new {@code PullConsumer} for the specified {@code MessagingAccessPoint} with the specified queue.
     *
     * @param queueName the only attached queue for this {@code PullConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code PullConsumer}
     */
    PullConsumer createPullConsumer(String queueName);

    /**
     * Creates a new {@code PullConsumer} for the specified {@code MessagingAccessPoint} with some preset properties.
     *
     * @param queueName the only attached queue for this {@code PullConsumer}
     * @param properties the preset properties
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code PullConsumer}
     */
    PullConsumer createPullConsumer(String queueName, KeyValue properties);

    /**
     * Creates a new {@code IterableConsumer} for the specified {@code MessagingAccessPoint}.
     *
     * @param queueName the only attached queue for this {@code IterableConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code IterableConsumer}
     */
    IterableConsumer createIterableConsumer(String queueName);

    /**
     * Creates a new {@code IterableConsumer} for the specified {@code MessagingAccessPoint} with some preset properties.
     *
     * @param queueName the only attached queue for this {@code IterableConsumer}
     * @param properties the preset properties
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created consumer
     */
    IterableConsumer createIterableConsumer(String queueName, KeyValue properties);

    /**
     * Gets a lightweight {@code ResourceManager} instance from the specified {@code MessagingAccessPoint}.
     *
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code ResourceManager}
     */
    ResourceManager getResourceManager();


    /**
     * Create a new {@code ServiceEndPoint} for the specified {@code MessagingAccessPoint}.
     *
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code ServiceEndPoint}
     */
    ServiceEndPoint createServiceEndPoint();

    /**
     * Create a new {@code ServiceEndPoint} for the specified {@code MessagingAccessPoint} with some preset properties.
     *
     * @param properties the preset properties
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails
     * to handle this request due to some internal error
     * @return the created {@code ServiceEndPoint}
     */
    ServiceEndPoint createServiceEndPoint(KeyValue properties);

    /**
     * Register an observer in an serviceEndPoint object. Whenever serviceEndPoint object publish or bind an service
     * object, it will be notified to the list of observer object registered before
     *
     * @param observer observer event object to an serviceEndPoint object
     */
    void addObserver(Observer observer);

    /**
     * Removes the given observer from the list of observer
     * <p>
     * If the given observer has not been previously registered (i.e. it was
     * never added) then this method call is a no-op. If it had been previously
     * added then it will be removed. If it had been added more than once, then
     * only the first occurrence will be removed.
     *
     * @param observer The observer to remove
     */
    void deleteObserver(Observer observer);
}
