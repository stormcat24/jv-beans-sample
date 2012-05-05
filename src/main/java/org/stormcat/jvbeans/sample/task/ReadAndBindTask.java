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

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.seasar.chronos.core.annotation.task.Task;
import org.seasar.chronos.core.annotation.trigger.DelayTrigger;
import org.seasar.framework.exception.IORuntimeException;
import org.stormcat.jvbeans.common.constants.Charset;
import org.stormcat.jvbeans.common.io.BufferedReaderUtil;
import org.stormcat.jvbeans.jvlink.analyze.JvBindingDtoFactory;
import org.stormcat.jvbeans.jvlink.definitions.dto.HorseRaceInfoDto;
import org.stormcat.jvbeans.jvlink.definitions.resolver.StoredDataResolver;

/**
 * @author a.yamada
 *
 */
@Task
@DelayTrigger(delay = 300000)
public class ReadAndBindTask {

    public JvBindingDtoFactory jvBindingDtoFactory;
    
    public void doExecute() {
        
        BufferedReader reader = BufferedReaderUtil.getReader("test_SE.txt", Charset.MS932);
        
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                HorseRaceInfoDto dto = jvBindingDtoFactory.create(line, StoredDataResolver._RACE()._SE());
                System.out.println(dto.toString());
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        
        IOUtils.closeQuietly(reader);
    }
    
}
