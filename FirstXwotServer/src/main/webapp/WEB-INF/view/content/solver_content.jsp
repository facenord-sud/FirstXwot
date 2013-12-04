<%--
    Document   : tasks_content
    Created on : 7 d�c. 2010, 12:01:32
    Author     : ruppena
--%>
<%@page import="java.io.*"%>
<script type="text/javascript" src="jslpl.js"></script>
<%!
    //function that searches within a directory "dir" for a file "name"
    //returns the absolute path of the file
    //if there is no file with "name" it returns an empty string
//TODO not used anymore
    public static String findFile(String name, File dir) {
        String ret = "";
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile() && files[i].getName().equalsIgnoreCase(name)) {
                    ret = files[i].getAbsolutePath();
                } else if (files[i].isDirectory()) {
                    String rec = findFile(name, files[i]); // ruft sich selbst rekursiv auf
                    if (!rec.isEmpty()) {
                       ret = rec;
                    }
                    }
            }
        }
        return ret;
    }

%>


<%
        //initializes
        String problemDescLink="";
        String modelDescLink="";
        String modelDir = "";
        session.setAttribute("modelDir", modelDir);
        String filePath = "";
        String name = request.getParameter("name"); // the name of the Model
        ServletContext context = getServletContext();
        String MODELS_PATH = context.getInitParameter("demoModels");
        MODELS_PATH = MODELS_PATH.replace("\\", "/");




        if (name!=null) {
            if (name.contains("/")) {
            //remove the directory if there is given one in the parameter
                int l = name.lastIndexOf("/");
                name = name.substring(l+1);
            }
            //search for the file in the "lplmodels" and subdirectorys
            File dir = new File(getServletContext().getRealPath(MODELS_PATH));//new File(MODELS_PATH);
            filePath = findFile(name+".lpl", dir);//MODELS_PATH+"/"+name+".lpl";//

            //check if the pdf-files exist and implement them
            if (filePath.length() != 0) {
                String pdfPath = filePath.substring(0, filePath.length() -4);
                boolean problemDescFile = new java.io.File(pdfPath+".1.pdf").exists();
                boolean modelDescFile = new java.io.File(pdfPath+".pdf").exists();
                String[] pathSplit = pdfPath.split("lplmodels");
                String pdfName = pathSplit[1].replace("\\", "/");

            if (problemDescFile) {
                problemDescLink = "<a class=\"menu\" href=\"lplmodels"+pdfName+".1.pdf\">Problem Description</a>";
                //problemDescLink = "<input type=\"button\" value=\"Problem Description\" onClick=\"location.href='lplmodels"+pdfName+".1.pdf\">";
            }

            if (modelDescFile) {
                modelDescLink = "<a class=\"menu\" href=\"lplmodels"+pdfName+".pdf\">Model Description</a>";
                //modelDescLink = "<input type=\"button\" value=\"Model Description\" onClick=\"location.href='lplmodels"+pdfName+".pdf\">";
            }
        int lastSlash = pdfName.lastIndexOf("/");
        if (lastSlash == -1 || lastSlash == 0 ) {
            //do nothing
        } else {
            //set the model-dir to the session
            modelDir = pdfName.substring(1, lastSlash);
        }
                session.setAttribute("modelDir", modelDir);
        } }

%>

<div class="post">
    <h2 class="title"><a href="#">LPL Web Solver  </a></h2>
    <p class="meta"><span class="date"><%= new java.util.Date()%></span><span class="posted">LPL</span></p>
    <div class="entry">
        <h1>Solver</h1>
        <div id="input" class="input" style="display:block" >
            <form >
                        <div id="solvermenu">
                            <input TYPE="button" VALUE="Run and Solve" onclick="submitModel();">
                            <%= problemDescLink %>
                            <%= modelDescLink %>
                            <%= MODELS_PATH %>
                           
                        </div>
                        <input type="hidden" name="modelDir" value=<%= modelDir %>>
                        <br />
                        <label for="callback">Callback: </label>
                        <select name="callback" id="callback">
                            <option value="-1">Select Call back method</option>
                            <option value="email">E-mail</option>
                        </select>
                        <input type="text" name="callbackaddress" id="callbackaddress" />
                        <br/>
                        <label for="modellname">Name of Model: </label><input type="text" name="modellname" id="modellname" />
                        <br/>
                        <textarea NAME="lplinput" id="model" class="model" rows="35" cols="60" ><%
                                        String DEMO_MODELS = context.getInitParameter("demoModels");
                                        String MODEL_NAME = request.getParameter("name"); // the name of the Model
                                        //String FILE_PATH = DEMO_MODELS + MODEL_NAME + ".lpl"; //the path of the demo-model lpl-file
                                        // scriptlet for insert the code of the model "name" into the box
                                        String line;
                                        File modFile = new File(filePath);
                                        //String fileName=getServletContext().getRealPath(filePath);
                                        //File modFile=new File(fileName);
                                        if (modFile.exists()) {
                                          //if(true){
                                           //java.net.URL url =config.getServletContext().getResource("/lplmodels/cutstock1.lpl");
                                            BufferedReader buffreader = new BufferedReader(new FileReader(modFile));
                                           //BufferedReader buffreader =new BufferedReader(new InputStreamReader(url.openStream()));

                                            while (buffreader.ready()) {
                                                line = buffreader.readLine();
                                                if (line != null) {
                                                    out.println(line);
                                                }
                                            }
                                        } else {
                                            //if the model does not exists
                                            out.println("No such model exists!");
                                        }
                            %>
                        </textarea>
                        </form>
                </div>

                <div id="ajaxLoader" align="center">
                </div>

                <div id="output" class="output" style="display:none" align="center">

                    <div id="description">
                        <%= problemDescLink %>
                        <%= modelDescLink %>
                        <a class="menu" href="javascript:location.reload()">Back to model</a>
                    </div>
                    <div id="drawing" class="outputSection">
                        <!-- if available insert drawing here-->
                    </div>
                    <div id="solutionTable" class="outputSection">
                        <b>The solution is as follows:</b>
                        <div id="solution" class="fileOutput">
                            <!-- insert solution here -->
                        </div>
                    </div>

                    <div id="logTable" class="outputSection">
                        <b>The LOG-File output of LPL is as follows:</b>
                        <div id="log" class="fileOutput">
                            <!-- insert log file -->
                        </div>
                    </div>
                </div>
    </div>
    <p class="links"><a href="#">Read More</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="b0x w">Comments</a></p>
</div>