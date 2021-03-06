/*
 * Copyright 2008-2009 the original author or authors.
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
package net.hasor.boot;
import net.hasor.core.AppContext;
import net.hasor.core.BindInfo;
import net.hasor.utils.StringUtils;
/**
 * RSF命令
 * @version : 2016年4月3日
 * @author 赵永春 (zyc@hasor.net)
 */
class CommandLauncherDef implements CommandLauncher {
    private int                                 checkArgsIndex;
    private String                              commandName;
    private BindInfo<? extends CommandLauncher> bindInfo;
    //
    public CommandLauncherDef(int checkArgsIndex, String commandName, BindInfo<? extends CommandLauncher> bindInfo) {
        this.checkArgsIndex = checkArgsIndex;
        this.commandName = commandName;
        this.bindInfo = bindInfo;
    }
    public int getArgsIndex() {
        return checkArgsIndex;
    }
    @Override
    public void run(String[] args, AppContext appContext) {
        if (args.length <= this.checkArgsIndex) {
            return;
        }
        if (!StringUtils.equalsIgnoreCase(this.commandName, args[this.checkArgsIndex].trim())) {
            return;
        }
        //
        appContext.getInstance(this.bindInfo).run(args, appContext);
    }
}