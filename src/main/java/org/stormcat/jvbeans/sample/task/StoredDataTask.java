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

import java.io.BufferedWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.NonDelayTrigger;
import org.seasar.framework.exception.IORuntimeException;
import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.io.BufferedWriterUtil;
import org.stormcat.jvbeans.common.lang.StringUtil;
import org.stormcat.jvbeans.config.DataOption;
import org.stormcat.jvbeans.jvlink.JvLinkManager;
import org.stormcat.jvbeans.jvlink.JvReader;
import org.stormcat.jvbeans.jvlink.definitions.dto.HorseRaceInfoDto;
import org.stormcat.jvbeans.jvlink.definitions.resolver.StoredDataResolver;

/**
 * @author a.yamada
 *
 */
@Task
@NonDelayTrigger
public class StoredDataTask {

    public JvLinkManager jvLinkManager;
    
    public void start() {
        jvLinkManager.init();
    }
    
    public void doExecute() {
        JvReader<HorseRaceInfoDto> reader = 
            jvLinkManager.open(StoredDataResolver._RACE()._SE() ,"20100601000000", DataOption.STANDARD);
        
        BufferedWriter writer = BufferedWriterUtil.getWriter("test_SE.txt", Charset.MS932);
        for (HorseRaceInfoDto dto : reader) {
            String data = dto.toString();
            if (StringUtil.isNotBlank(data)) {
                System.out.println(data);
                try {
                    writer.append(data);
                    writer.append("\r\n");
                } catch (IOException e) {
                    throw new IORuntimeException(e);
                }
            }
        }
        
        IOUtils.closeQuietly(writer);
    }
    
    public void end() {
        jvLinkManager.close();
    }
    
}
