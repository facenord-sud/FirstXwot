/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwotdomain;

import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author <a href="mailto:andreas.ruppen@gmail.com">Andreas Ruppen</a>
 */
@XmlRootElement(name = "Tasks")
public class Tasks {

    private Collection<Task> tasks;

    private Collection<Link> links;
    private URI uri;

    public Tasks()
    {

    }


    @XmlElementWrapper(name = "Links")
    @XmlElementRef
    public Collection<Link> getLinks() {
        return links;
    }

    public void setLinks(Collection<Link> links) {
        this.links = links;
    }

   
    @XmlElementWrapper(name = "Tasklist")
    @XmlElementRef
    public Collection<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Collection<Task> tasks) {
        this.tasks = tasks;
    }

    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public void setUriInfo(URI _uri, boolean isUriExtendable)
    {
        uri = (isUriExtendable) ? _uri: _uri;
    }

}
