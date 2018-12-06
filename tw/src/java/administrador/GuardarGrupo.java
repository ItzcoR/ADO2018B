package administrador;

import java.io.File;
import java.io.FileWriter;
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
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class GuardarGrupo extends HttpServlet {

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String xml=request.getRealPath("WEB-INF\\practica2.xml");
        String check=request.getParameter("opc");
        String id=request.getParameter("id");
        String idgestor=request.getParameter("idgestor");
        String nombre=request.getParameter("nombre");
        String fecha=request.getParameter("fecha");
        String hora=request.getParameter("hora");
        String lugar=request.getParameter("lugar");
        String tel=request.getParameter("tel");
        String cupo=request.getParameter("cupo");
        String correo=request.getParameter("correo");
        String res=agregar(xml,id,idgestor,nombre,fecha,hora,lugar,tel,correo,cupo,check);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html style='height:100%; width:100%; margin:0px;'>");
            out.println("<head>");
            out.println("<title>Servlet add</title>"); 
            out.println("<meta charset=\"utf-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\" media=\"screen\"/>\n" +
            "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
            "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body class='colorful'>");
            //out.println("<h1 class='blanco'>"+res+","+evento+","+nombre+","+nom+","+tipo+","+xml+" </h1>");
            out.println("<div class=\"row\">\n" +
            "  <div class=\"col-sm-8\"></div>\n" +
            "  <div class=\"col-sm-4\"><button class='btn-info'><a class='blanco' href='CrearGrupo'>Regresar</a></button></div>\n" +
            "</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   public String agregar(String ruta,String id,String idgestor, String nom,String fecha,String hora,String lugar,String tel,String correo,String cupo,String check)
    {
        String res="";
        int aux=0;
        try{
            /*SAXBuilder se encarga de cargar el archivo XML del disco o de un String */
            SAXBuilder builder=new SAXBuilder();
            //Forma de abriri el archivo
            File xmlFile = new File(ruta);
            /*Almacenamos el xml cargado en builder en un documento*/
            Document bd_xml=builder.build(xmlFile);
            //Elemento raiz
            Element raiz=bd_xml.getRootElement();
            //metodo que escribe en el archivo xml
            List hijos=raiz.getChildren();
            //metodo que escribe en el archivo xml
            XMLOutputter xmlOutput = new XMLOutputter();
            //Formato en el que se va a escribir
            xmlOutput.setFormat(Format.getPrettyFormat());
            for(int i=0;i<hijos.size();i++)
            {
                Element hijo=(Element)hijos.get(i);
                String nombre=hijo.getAttributeValue("id");
                
                    if(nombre.equals(id))
                    {
                        aux++;   
                        break;
                    }
                
            }
            if(aux==1)
            {
                res="El evento ya existe";
            }
            else
            {   
            Element a=new Element("Evento");
            a.setAttribute("id", id);
            a.setAttribute("Creador", idgestor);
            a.setAttribute("Tipo", check);
            a.setAttribute("Fecha", fecha);
            a.setAttribute("Hora", hora);
            a.setAttribute("Lugar", lugar);
            a.setAttribute("Cupo", cupo);
            a.setAttribute("Telefono", tel);
            a.setAttribute("Correo", correo);
            a.setText(nom);
            raiz.addContent(a);
            
            
            xmlOutput.output(bd_xml,new FileWriter(ruta));
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(GuardarGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
