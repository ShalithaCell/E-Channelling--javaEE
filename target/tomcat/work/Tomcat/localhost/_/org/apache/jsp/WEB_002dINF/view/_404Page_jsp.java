/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-09-15 07:38:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _404Page_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <script src=\"../../resources/libs/jquery/jquery-3.4.1.min.js\"></script>\n");
      out.write("\n");
      out.write("        <link href=\"../../resources/libs/font-awesome-4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <script src=\"../../resources/libs/MDB/js/popper.min.js\"></script>\n");
      out.write("        <link href=\"../../resources/libs/MDB/css/mdb.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        <script src=\"../../resources/libs/bootstrap-4.3.1/js/bootstrap.min.js\"></script>\n");
      out.write("        <link href=\"../../resources/libs/bootstrap-4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n");
      out.write("\n");
      out.write("        <link href=\"../../resources/styles/style-404.css\" rel=\"stylesheet\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"error-content\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <div class=\"row\">\n");
      out.write("                    <div class=\"col-md-12 \">\n");
      out.write("                        <div class=\"error-text\">\n");
      out.write("                            <h1 class=\"error\">404 Error</h1>\n");
      out.write("                            <div class=\"im-sheep\">\n");
      out.write("                                <div class=\"top\">\n");
      out.write("                                    <div class=\"body\"></div>\n");
      out.write("                                    <div class=\"head\">\n");
      out.write("                                        <div class=\"im-eye one\"></div>\n");
      out.write("                                        <div class=\"im-eye two\"></div>\n");
      out.write("                                        <div class=\"im-ear one\"></div>\n");
      out.write("                                        <div class=\"im-ear two\"></div>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"im-legs\">\n");
      out.write("                                    <div class=\"im-leg\"></div>\n");
      out.write("                                    <div class=\"im-leg\"></div>\n");
      out.write("                                    <div class=\"im-leg\"></div>\n");
      out.write("                                    <div class=\"im-leg\"></div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <h4>Oops! This page Could Not Be Found!</h4>\n");
      out.write("                            <p>Sorry bit the page you are looking for does not exist, have been removed or name changed.</p>\n");
      out.write("                            <a href=\"home\" class=\"btn btn-primary btn-round\">Go to homepage</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
