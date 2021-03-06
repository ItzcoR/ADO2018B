package administrador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ver extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String nomC=request.getParameter("id");
        String xml=request.getRealPath("WEB-INF\\canvas.xml");
        String res=ver(xml,nomC);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ver gestor</title>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src='fabric.js'></script>");
            out.println("<script src='http://code.responsivevoice.org/responsivevoice.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div style='width:1200px;height:80px;float:center;border:1px solid #A6C9E2'>");
            out.println("<button class='btn-info'><a href='Maestro'>Regresar</a></button>");
            //out.println("<a href='cerrar'>Cerrar Session</a>");
            out.println("</div>");
            
            out.println("<canvas id='c'width='850' height='550' style='border:1px solid #000000'></canvas>");
            out.println("<script>");
            out.println("var canvas = new fabric.Canvas('c');");
            out.println("canvas.loadFromJSON("+res+");");
            out.println("$(\"canvas\").click(function(e){\n" +
                        "var txt = canvas.getActiveObject().text;\n" +
                        "Decir(txt);\n" +
                        "});\n" +  
                        "function Decir(say){\n" +
                        "var voicelist = responsiveVoice.getVoices();\n" +
                        "responsiveVoice.speak(say,\"Spanish Latin American Female\");\n" +
                        "};");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
           
        }   
    }
    
    public String ver(String xml,String c)
    {
        String res="";
        int aux=0;
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile= new File(xml);
            Document bd_xml=builder.build(xmlFile);
            Element raiz=bd_xml.getRootElement();
            List hijos=raiz.getChildren();
            for(int i=0;i<hijos.size();i++)
            {
                Element hijo=(Element)hijos.get(i);
                String canv=hijo.getAttributeValue("id");
                if(c.equals(canv))
                {
                  aux=i;  
                }
            }
            Element ver=(Element)hijos.get(aux);
            res=ver.getText();
            return res;
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ver.class.getName()).log(Level.SEVERE, null, ex);
        }
      return res;
    }
}
