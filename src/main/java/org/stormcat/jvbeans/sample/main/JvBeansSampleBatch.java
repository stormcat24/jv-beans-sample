/*
 * Copyright 2009 the Stormcat Project.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.stormcat.jvbeans.sample.main;

import org.seasar.chronos.core.Scheduler;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author a.yamada
 *
 */
public class JvBeansSampleBatch {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SingletonS2ContainerFactory.init();
        SingletonS2Container.getComponent(Scheduler.class).process();
        SingletonS2ContainerFactory.destroy();
    }

}
