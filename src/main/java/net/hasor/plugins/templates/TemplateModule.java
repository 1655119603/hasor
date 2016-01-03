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
package net.hasor.plugins.templates;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.more.util.StringUtils;
import net.hasor.web.WebApiBinder;
/**
 * 
 * @version : 2015年8月19日
 * @author 赵永春(zyc@hasor.net)
 */
public class TemplateModule {
    public void loadModule(WebApiBinder apiBinder) throws Throwable {
        String interceptNames = apiBinder.getEnvironment().getSettings().getString("hasor.template.urlPatterns", "htm;html;");
        TemplateHttpServlet servlet = new TemplateHttpServlet();
        for (String name : interceptNames.split(";")) {
            if (StringUtils.isBlank(name) == false) {
                apiBinder.serve(name).with(servlet);
            }
        }
    }
}
class TemplateHttpServlet extends HttpServlet {
    private static final long   serialVersionUID = -4405894246041827036L;
    private final AtomicBoolean inited           = new AtomicBoolean(false);
    private TemplateContext     templateContext;
    //
    @Override
    public void init(ServletConfig config) throws ServletException {
        if (this.inited.compareAndSet(false, true)) {
            this.templateContext = new TemplateContext();
            this.templateContext.init(config.getServletContext());
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        ContextMap contextMap = ContextMap.genContextMap(req, resp);
        String requestURI = req.getRequestURI().substring(req.getContextPath().length());
        this.templateContext.processTemplate(requestURI, resp.getWriter(), contextMap);
    }
}