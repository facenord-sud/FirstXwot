/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwotdomain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author <a href="mailto:andreas.ruppen@gmail.com">Andreas Ruppen</a>
 */
@XmlRootElement(name="result")
public class Solution {
    private String imageResult;
    private String fileResult;
    private String logResult;

    public Solution()
    {
        imageResult = "";
        fileResult = "";
        logResult = "";
    }

    public Solution(String _fileResult)
    {
        this();
        this.fileResult = _fileResult;
    }

    public Solution(String _fileResult, String _logResult)
    {
        this(_fileResult);
        this.logResult = _logResult;
    }

    public Solution(String _fileResult, String _logResult, String _imageResult)
    {
        this(_fileResult, _logResult);
        this.imageResult = _imageResult;
    }

    @XmlElement
    public String getFileResult() {
        return fileResult;
    }

    public void setFileResult(String fileResult) {
        this.fileResult = fileResult;
    }

    @XmlElement
    public String getImageResult() {
        return imageResult;
    }

    public void setImageResult(String imageResult) {
        this.imageResult = imageResult;
    }

    public String getLogResult() {
        return logResult;
    }

    public void setLogResult(String logResult) {
        this.logResult = logResult;
    }


}
