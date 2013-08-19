package org.hasor.test.app.beans;
import java.io.IOException;
import org.hasor.context.anno.Bean;
import org.hasor.context.anno.context.AnnoAppContext;
import org.hasor.test.plugin.log.OutLog;
/**
 * 
 * @version : 2013-7-25
 * @author ������ (zyc@hasor.net)
 */
@Bean("LogBean")
public class LogBean {
    @OutLog
    public void print() {
        System.out.println("�ڴ�֮ǰ�����־!");
    }
    //
    //
    public static void main(String[] args) throws IOException {
        AnnoAppContext aac = new AnnoAppContext();
        aac.start();
        //
        LogBean logBean = (LogBean) aac.getBean("LogBean");
        logBean.print();
        //
        aac.destroy();
    }
}