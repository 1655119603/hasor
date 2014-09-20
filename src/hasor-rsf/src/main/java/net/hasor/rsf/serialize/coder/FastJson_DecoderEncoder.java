/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.rsf.serialize.coder;
import java.io.IOException;
import net.hasor.rsf.serialize.Decoder;
import net.hasor.rsf.serialize.Encoder;
/**
 * 
 * @version : 2014年9月19日
 * @author 赵永春(zyc@hasor.net)
 */
public class FastJson_DecoderEncoder implements Decoder, Encoder {
    public byte[] encode(Object object) throws IOException {
        String text = JSON.json(object);
        return text.getBytes(RemotingConstants.DEFAULT_CHARSET);
    }
    //
    public Object decode(byte[] bytes) throws IOException {
        return JSON.parse(new String(bytes));
    }
}