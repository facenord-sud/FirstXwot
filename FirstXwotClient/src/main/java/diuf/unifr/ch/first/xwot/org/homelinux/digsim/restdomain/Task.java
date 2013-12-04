/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwotdomain;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author <a href="mailto:andreas.ruppen@gmail.com">Andreas Ruppen</a>
 */
@XmlRootElement(name="Task")
public class Task {
    private int id;
    private String computation;
    //private String result = "";
    private Solution result;
    private boolean finished;
    private URI uri;
    private static final Random generator = new Random();
    private String username;
    private String problemName;
    private String callbackStrategy = "none";
    private String callbackAddress;
    private Long startdate;
    private boolean aborted;
    private long usedSolverTime;
    private String errorMessage;

    public Task()
    {
        id = generator.nextInt();
    }

    public Task(URI _uri, boolean isUriExtendable)
    {
        
        id = generator.nextInt();
        uri = (isUriExtendable) ? UriBuilder.fromUri(_uri).path(id + "/").build(): _uri;
    }

   
    @XmlElement(required=true)
    public String getComputation() {
        return computation;
    }

    public void setComputation(String computation) {
        this.computation = computation;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    @XmlAttribute
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /*
    @XmlElement
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
*/
    /*
    public void setUriInfo(URI _uri, boolean isUriExtendable)
    {
        uri = (isUriExtendable) ? UriBuilder.fromUri(_uri).path(id + "/").build(): _uri;
    }
     */

    @XmlElement
    public String getCallbackAddress() {
        return callbackAddress;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.callbackAddress = callbackAddress;
    }

    @XmlElement
    public String getCallbackStrategy() {
        return callbackStrategy;
    }

    public void setCallbackStrategy(String callbackStrategy) {
        this.callbackStrategy = callbackStrategy;
    }

    @XmlElement
    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    @XmlElement
    public Long getStartdate() {
        return startdate;
    }

    public void setStartdate(Long startdate) {
        this.startdate = startdate;
    }

    @XmlElement
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlAttribute
    public boolean isAborted() {
        return aborted;
    }

    public void setAborted(boolean aborted) {
        this.aborted = aborted;
    }

    @XmlElement
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @XmlElement
    public long getUsedSolverTime() {
        return usedSolverTime;
    }

    public void setUsedSolverTime(long usedSolverTime) {
        this.usedSolverTime = usedSolverTime;
    }

    @XmlElementRef
    public Solution getResult() {
        return result;
    }

    public void setResult(Solution result) {
        this.result = result;
    }


    
    
}
