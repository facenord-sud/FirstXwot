/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.validator.annotations.Validation;
import diuf.unifr.ch.first.xwot.client.LPLTasksClient;
import diuf.unifr.ch.first.xwotdomain.Tasks;

/**
 *
 * @author ruppena
 */
@Validation()
@Conversion()
public class TasksAction  extends ActionSupport {
    protected Tasks tasks;
    protected int start = 0;
    protected int size = 10;

    public Tasks getTasks() {
        return tasks;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String execute() throws Exception {
        LPLTasksClient tc = new LPLTasksClient();
        tasks = tc.getTasksXML_XML(Tasks.class, start, size);
        tc.close();
        return SUCCESS;
    }
}
