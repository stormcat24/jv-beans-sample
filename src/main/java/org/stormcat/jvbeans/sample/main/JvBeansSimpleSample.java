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

import org.stormcat.jvbeans.config.DataOption;
import org.stormcat.jvbeans.jvlink.JvComponentInjector;
import org.stormcat.jvbeans.jvlink.JvLinkManager;
import org.stormcat.jvbeans.jvlink.JvReader;
import org.stormcat.jvbeans.jvlink.definitions.dto.JockeyMasterDto;
import org.stormcat.jvbeans.jvlink.definitions.resolver.StoredDataResolver;

/**
 * @author a.yamada
 *
 */
public class JvBeansSimpleSample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        JvComponentInjector.init("org.stormcat.jvbeans.jvlink.definitions.dto");
        JvLinkManager manager = JvComponentInjector.createJvLinkManager();
        manager.init();
        
        try {
            JvReader<JockeyMasterDto> reader = 
                manager.open(StoredDataResolver._DIFF()._KS(), "20100601000000", DataOption.STANDARD);
            
            for (JockeyMasterDto dto : reader) {
                System.out.println(dto.toString());
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            manager.close();
        }
    }

}
