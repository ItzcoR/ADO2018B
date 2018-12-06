
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
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class InfoEventos extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String xml=request.getRealPath("WEB-INF\\practica2.xml");
         String id=request.getParameter("id");
         
         HttpSession session=request.getSession();
         String[] res=atributo(xml,id);
         String cuponuevo=res[6];
         int nuevocupo=0;
         int numEntero = Integer.parseInt(cuponuevo);
         nuevocupo=numEntero-1;
         int aux=tamano(xml);
         String[] ida=new String[aux];
         ida=id(xml);
         //session.setAttribute("idv",res[0]);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html style='height:100%; width:100%; margin:0px;'>");
            out.println("<head>");
            out.println("<title>Servlet modificar1</title>");  
            out.println("<script type =\"text/javascript\">\n" +
            "function letraNumVal(e) {\n" +
            "   tecla = document.all ? e.keyCode : e.which;\n" +
            "   if (tecla === 8 || tecla === 32)\n" +
            "           return true;\n" +
            "       patron = /[a-z]|[A-Z]|á|é|í|ó|ú|Á|É|Í|Ó|Ú|[0-9]/;\n" +
            "       te = String.fromCharCode(tecla);\n" +
            "           return patron.test(te);\n" +
            "   }\n" +
            "function letraVal(e) {\n" +
            "   tecla = document.all ? e.keyCode : e.which;\n" +
            "   if (tecla === 8 || tecla === 32)\n" +
            "           return true;\n" +
            "       patron = /[a-z]|[A-Z]|á|é|í|ó|ú|Á|É|Í|Ó|Ú/;\n" +
            "       te = String.fromCharCode(tecla);\n" +
            "           return patron.test(te);\n" +
            "   }\n" +
            "</script>");
            out.println("<meta charset=\"utf-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"estilos.css\" media=\"screen\"/>\n" +
            "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
            "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("</head>");
            out.println("<body class='colorful'>");
            out.println("<form action='ModiAsistirEvento' method='post'>");
            out.println("<div class=\"container\">\n" +
            "<h2 class='blanco'>Informacion de Evento</h2>\n"+
            //"<p class=\"blanco\">Ingrese los datos que se le piden en el formato indicado</p>\n" +
            "");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"><input type=\"text\" class=\"form-control\" name=\"idgestor\" style='visibility:hidden' value="+res[1]+" ></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\"></p>  <input type=\"text\" class=\"form-control\" name=\"id\" style='visibility:hidden' value="+res[0]+" ></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\" name='nombre'>Nombre: </p>  <input type=\"text\" class=\"form-control\" value="+res[9]+" name=\"nombre\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
                    "  <div class=\"col-sm-1\"></div>\n" +
                    "  <div class=\"col-sm-8\">\n" +
            "<p class=\"blanco\">Fecha: </p><input class='form-control' placeholder='DDMMAAAA' type=\"text\" name=\"fecha\" value="+res[3]+" onkeypress=\"return letraNumVal(event)\" required>\n"+
            "</div>\n" +
                    "  <div class=\"col-sm-1\"></div>\n" +
                    /*"<table class=\"table\">\n" +
            "   <thead>\n" +
            "     <tr class='blanco'>\n" +
            "       <th>Maestros</th>\n" +
            "       <th>Alumnos</th>\n" +
            "     </tr>\n" +
            "   </thead>\n" +
            "   <tbody>\n" +
            "     <tr>\n" +
            "       <td>");
            for(int i=0;i<auxx;i++){
                out.println("<input type=\"radio\" name=\"io\" value=\""+usuarios[i]+"\"><h5 id='txtUsuario' class='blanco'>"+usuarios[i]+"</h5>");
                out.println("<hr>");
            }
            out.println("</td>\n" +
            "<td>");        
            for(int i=0;i<auxr;i++){
                out.println("<h5 id='txtPassword' class='blanco'>"+usuarios2[i]+"</h5>");
                out.println("<hr>");
            }
            out.println("</td>");
            out.println("</tr>\n" +
            "    </tbody>\n" +
            "  </table>\n" +*/
            "</div>");
            /*out.println("<div class=\"row\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Ingrese los nombres de los alumnos de este grupo, separelos con una coma: </p></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");*/
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Hora: </p>  <input type=\"text\" class=\"form-control\" value="+res[4]+" name=\"hora\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Lugar: </p>  <input type=\"text\" class=\"form-control\" value="+res[5]+" name=\"lugar\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Cupo: </p>  <input type=\"text\" class=\"form-control\" value="+res[6]+" name=\"cupo\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Telefono: </p>  <input type=\"text\" class=\"form-control\" value="+res[7]+" name=\"tel\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Correo Electronico: </p>  <input type=\"text\" class=\"form-control\" value="+res[8]+" name=\"correo\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row form-group\">\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "  <div class=\"col-sm-8\"><p class=\"blanco\">Correo Electronico: </p>  <input type=\"text\" class=\"form-control\" value="+res[2]+" name=\"opc\" required></div>\n" +
            "  <div class=\"col-sm-1\"></div>\n" +
            "</div>");
            out.println("<div class=\"row\">\n" );
         
                
                out.println("<button class='btn-info'><a class='blanco' href='AsistirEvento?id="+id+"'>Asistir</a></button>");
                out.println("<hr>");
                
            
            
            //"  <div class=\"col-sm-4\"><input type=\"submit\" class=\"btn-info\" value=\"Registrar\"></div>\n" +
            //out.println("  <div class=\"col-sm-4\"><input type=\"button\" class=\"btn-info\" value=\"Mis Eventos Creados\" onclick=\"document.location='EventosCreados'\"></div>\n" +
            //"  <div class=\"col-sm-1\"><input type=\"button\" class=\"btn-info\" value=\"Ver Grupos\" onclick=\"document.location='VerGrupos'\"></div>\n" +
            //"</div>");
            out.println("</form>");
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
                
                   aux++;
                
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(VerGrupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
    public String[] id(String direc)
    {
        int aux=tamano(direc);
        int aux2=0;
        String[] nombU=new String[aux];
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
                
                    nombU[aux2]=hijo.getAttributeValue("id");
                    aux2++;
                
            }
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(VerGrupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombU;
    }
    public String[] atributo(String ruta,String nombre)
    {
        String[] res=new String[10];
        try{
            /*SAXBuilder se encarga de cargar el archivo XML del disco o de un String */
            SAXBuilder builder=new SAXBuilder();
            //Forma de abriri el archivo
            File xmlFile = new File(ruta);
            /*Almacenamos el xml cargado en builder en un documento*/
            Document bd_xml=builder.build(xmlFile);
            //Elemento raiz
            Element raiz=bd_xml.getRootElement();
            //Se almacenan nodo hijo 
            //Lista de nodos nietos
            List hijos=raiz.getChildren();
            for(int i=0;i<hijos.size();i++)
            {
                Element nieto=(Element) hijos.get(i);
                String usua=nieto.getAttributeValue("id");
                if(usua.equals(nombre))
                {
                    
                    res[0]=nieto.getAttributeValue("id");
                    res[1]=nieto.getAttributeValue("Creador");
                    res[2]=nieto.getAttributeValue("Tipo");
                    res[3]=nieto.getAttributeValue("Fecha");
                    res[4]=nieto.getAttributeValue("Hora");
                    res[5]=nieto.getAttributeValue("Lugar");
                    res[6]=nieto.getAttributeValue("Cupo");
                    res[7]=nieto.getAttributeValue("Telefono");
                    res[8]=nieto.getAttributeValue("Correo");
                    res[9]=nieto.getText();
                    break;
                }
            }
            
        } catch (JDOMException | IOException ex) {
            Logger.getLogger(modificar1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
