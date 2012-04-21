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
package org.stormcat.jvbeans.sample.task;

import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.NonDelayTrigger;
import org.stormcat.jvbeans.config.DataOption;
import org.stormcat.jvbeans.jvlink.JvLinkManager;
import org.stormcat.jvbeans.jvlink.JvReader;
import org.stormcat.jvbeans.jvlink.definitions.dto.JockeyMasterDto;
import org.stormcat.jvbeans.jvlink.definitions.key.rtopen.RtOpenKeyFactory;
import org.stormcat.jvbeans.jvlink.definitions.resolver.RealTimeDataResolver;
import org.stormcat.jvbeans.jvlink.definitions.resolver.StoredDataResolver;

/**
 * @author a.yamada
 *
 */
//@Task
//@NonDelayTrigger
public class SimpleReaderTask {

    public JvLinkManager jvLinkManager;
    
    public void start() {
        jvLinkManager.init();
    }
    
    public void doExecute() {
        JvReader<String> reader = 
            jvLinkManager.simpleOpen(StoredDataResolver._DIFF()._SE(), 
                    "20091201000000", DataOption.STANDARD);
//        JvReader<String> reader =
//                jvLinkManager.rtOpen(RealTimeDataResolver._0B12()._SE(), 
//                        RtOpenKeyFactory.createYYYYMMDD(2009, 12, 19));
         
        for (String s : reader) {
            System.out.println(s);
        }
//        JvReader<JockeyMasterDto> reader =
//                jvLinkManager.open(StoredDataResolver._DIFF()._KS(), "20091201000000", DataOption.STANDARD);
//        reader.setOutputType(FileExtension.CSV);
//        
//        for (JockeyMasterDto dto : reader) {
//            System.out.println(dto.toString());
//        }
    }
    public void end() {
        jvLinkManager.close();
    }
}
