/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.actions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.validator.annotations.Validation;
import diuf.unifr.ch.first.xwot.client.LPLTaskClient;
import diuf.unifr.ch.first.xwot.client.LPLTasksClient;
import diuf.unifr.ch.first.xwotdomain.Task;
/**
 *
 * @author ruppena
 */
@Validation()
@Conversion()
public class TaskAction extends ActionSupport  {
    protected Task task;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }
    

    public String execute() throws Exception {
        LPLTaskClient tc = new LPLTaskClient(id);
        task = tc.getXml(Task.class);
        tc.close();
        return SUCCESS;
    }

}
