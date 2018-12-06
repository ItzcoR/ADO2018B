package gestor;

import administrador.Administrador;
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
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ControlUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        //String id=(String) session.getAttribute("id");
        //String tipo=(String) session.getAttribute("tipo");
        String xml=request.getRealPath("WEB-INF\\practica.xml");
        int aux=tamano(xml);
        String[][] usuarios=new String[3][aux];
        usuarios=nombre(xml);
        try (PrintWriter out = response.getWriter()) {
              
            out.println("<!DOCTYPE html>");
            out.println("<html style='height:100%; width:100%; margin:0px;'>");
            out.println("<head>");
            out.println("<title>Servlet Adm</title>"); 
            out.println("<meta charset=\"utf-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\" media=\"screen\"/>\n" +
            "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
            "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body class='colorful'>");
            out.println("<div class=\"container\">\n" +
            //"<h2 class='blanco'>Bienvenido "+tipo+": "+id+"</h2>           \n" +
            "<table class=\"table\">\n" +
            "   <thead>\n" +
            "     <tr class='blanco'>\n" +
            "       <th>Nombre</th>\n" +
            "       <th>Tipo</th>\n" +
            "       <th>Accion</th>\n" +
            "     </tr>\n" +
            "   </thead>\n" +
            "   <tbody>\n" +
            "     <tr>\n" +
            "       <td>");
            for(int i=0;i<aux;i++){
                out.println("<h5 class='blanco'>"+usuarios[0][i]+"</h5>");
                out.println("<hr>");
            }
            out.println("</td>\n" +
            "<td>");        
            for(int i=0;i<aux;i++){
                out.println("<h5 class='blanco'>"+usuarios[2][i]+"</h5>");
                out.println("<hr>");
            }
            out.println("</td>\n" +
            "<td>");
            for(int i=0;i<aux;i++){
                out.println("<button class='btn-info'><a class='blanco' href='modificar1?nombre="+usuarios[1][i]+"&tipo="+usuarios[2][i]+"'>Modificar</a></button>");
                out.println("<button class='btn-info'><a class='blanco' href='eliminar?nombre="+usuarios[1][i]+"&tipo="+usuarios[2][i]+"'>Eliminar</a></button>");
                out.println("<hr>");    
            }
            
            out.println("</td>\n" +
            "      </tr>\n" +
            "    </tbody>\n" +
            "  </table>\n" +
            "</div>");
            /*out.println("<div class=\"row\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-4\"><button class='btn-info'><a class='blanco' href='nuevoUsuario.html'>Nuevo usuario</a></button></div>\n" +
            "  <div class=\"col-sm-4\"><button class='btn-info'><a class='blanco' href='CrearGrupo'>Crear Grupos</a></button></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");*/
            out.println("<div class=\"row\">\n" +
            "  <div class=\"col-sm-8\"></div>\n" +
            "  <div class=\"col-sm-4\"><button class='btn-info'><a class='blanco' href='Administrador'>Regresar</a></button></div>\n" +
            "</div>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }
    
        public int tamano(String direc)
        {
        int aux=0;
        try{
            /*SAXBuilder se encarga de cargar el archivo XML del disco o de un String */
            SAXBuilder builder=new SAXBuilder();
            //Forma de abriri el archivo
            
            File xmlFile = new File(direc);
            /*Almacenamos el xml cargado en builder en un documento*/
            Document bd_xml=builder.build(xmlFile);
            //Elemento raiz
            Element raiz=bd_xml.getRootElement();
            //Se almacenan los hijos en una lista
            List hijos=raiz.getChildren();
            for(int i=0;i<hijos.size();i++)
            {
                Element hijo=(Element)hijos.get(i);
                List nietos=hijo.getChildren();
                for(int j=0;j<nietos.size();j++)
                {
                   aux++;
                }
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    
    public String[][] nombre(String direc)
    {
        int aux=tamano(direc);
        int aux2=0;
        String[][] nombU=new String[3][aux];
        try{
            /*SAXBuilder se encarga de cargar el archivo XML del disco o de un String */
            SAXBuilder builder=new SAXBuilder();
            //Forma de abriri el archivo
            File xmlFile = new File(direc);
            /*Almacenamos el xml cargado en builder en un documento*/
            Document bd_xml=builder.build(xmlFile);
            //Elemento raiz
            Element raiz=bd_xml.getRootElement();
            //Se almacenan los hijos en una lista
            List hijos=raiz.getChildren();
            for(int i=0;i<hijos.size();i++)
            {
                Element hijo=(Element)hijos.get(i);
                List nietos=hijo.getChildren();
                for(int j=0;j<nietos.size();j++)
                {
                    Element nieto=(Element)nietos.get(j);
                    nombU[0][aux2]=nieto.getAttributeValue("nombre");
                    nombU[1][aux2]=nieto.getAttributeValue("id");
                    nombU[2][aux2]=hijo.getName();
                    aux2++;
                }
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(ControlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombU;
    }
}

