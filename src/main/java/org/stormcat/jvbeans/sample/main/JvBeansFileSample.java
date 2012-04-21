/*
 * Copyright 2009-2010 the Stormcat Project.
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

import java.io.BufferedReader;
import java.io.BufferedWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.stormcat.commons.constants.Charset;
import org.stormcat.commons.constants.FileExtension;
import org.stormcat.commons.io.BufferedReaderUtil;
import org.stormcat.commons.io.BufferedWriterUtil;
import org.stormcat.jvbeans.config.DataOption;
import org.stormcat.jvbeans.jvlink.JvComponentInjector;
import org.stormcat.jvbeans.jvlink.JvLinkManager;
import org.stormcat.jvbeans.jvlink.JvReader;
import org.stormcat.jvbeans.jvlink.analyze.JvBindingDtoFactory;
import org.stormcat.jvbeans.jvlink.definitions.dto.TrainerMasterDto;
import org.stormcat.jvbeans.jvlink.definitions.resolver.StoredDataResolver;

/**
 * @author a.yamada
 *
 */
public class JvBeansFileSample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        JvComponentInjector.init("org.stormcat.jvbeans.jvlink.definitions.dto");
        JvLinkManager manager = JvComponentInjector.createJvLinkManager();
        manager.init();
        
        try {
            JvReader<String> reader = 
                manager.simpleOpen(StoredDataResolver._DIFF()._CH(), "20100601000000", DataOption.STANDARD);
            
            reader.setOutputType(FileExtension.CSV);

            BufferedWriter writer = BufferedWriterUtil.getWriter("test_CH.tsv", Charset.MS932);
            
            for (String data : reader) {
                if (StringUtils.isNotBlank(data)) {
                    System.out.println(data);
                    writer.write(data);
                    writer.write("\r\n");                  
                }
            }
            
            IOUtils.closeQuietly(writer);
            
            System.out.println("ファイル出力完了。");
            System.out.println("生成したファイルを逆にDTOにバインドします。");
            
            JvBindingDtoFactory factory = JvComponentInjector.getSingletonFactory();
            factory.setInputType(FileExtension.CSV);
            
            BufferedReader br = BufferedReaderUtil.getReader("test_CH.tsv", Charset.MS932);
            String line = null;
            while ((line = br.readLine()) != null) {
                TrainerMasterDto dto = factory.create(line, StoredDataResolver._DIFF()._CH());
                System.out.println(dto);
            }
            
            IOUtils.closeQuietly(br);
            
            
        } catch (Exception e) {
            throw e;
        } finally {
            manager.close();
        }

    }

}
