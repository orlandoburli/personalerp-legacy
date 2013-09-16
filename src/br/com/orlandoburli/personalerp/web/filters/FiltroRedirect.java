package br.com.orlandoburli.personalerp.web.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltroRedirect implements Filter {

    private static final boolean debug = false;

    private FilterConfig filterConfig = null;

    public FiltroRedirect() {
    } 

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (debug) log("FiltroRedirect:DoBeforeProcessing");
    } 

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (debug) log("FiltroRedirect:DoAfterProcessing");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (debug) log("FiltroRedirect:doFilter()");

        doBeforeProcessing(request, response);

        String url = ((HttpServletRequest)request).getServletPath();
        
        if (url.endsWith(".jsp")) { // Redireciona todos os jsp's para mtw's
            ((HttpServletResponse)response).sendRedirect(url.substring(1).replaceAll(".jsp", ".mtw"));
        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        }
        catch(Throwable t) {
            problem = t;
            //t.printStackTrace();
        }

        doAfterProcessing(request, response);
        
        if (problem != null) {
            if (problem instanceof ServletException) throw (ServletException)problem;
            if (problem instanceof IOException) throw (IOException)problem;
            sendProcessingError(problem, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() { 
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
            log("FiltroRedirect:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null)
            return ("FiltroRedirect()");
        StringBuffer sb = new StringBuffer("FiltroRedirect(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if(stackTrace != null && !stackTrace.equals("")) {
            try {
            response.setContentType("text/html");
            PrintStream ps = new PrintStream(response.getOutputStream());
            PrintWriter pw = new PrintWriter(ps);
            pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

            // PENDING! Localize this for next official release
            pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
            pw.print(stackTrace);
            pw.print("</pre></body>\n</html>"); //NOI18N
            pw.close();
            ps.close();
            response.getOutputStream().close();
            }
            catch(Exception ex) {}
        }
        else {
            try {
            PrintStream ps = new PrintStream(response.getOutputStream());
            t.printStackTrace(ps);
            ps.close();
            response.getOutputStream().close();
            }
            catch(Exception ex) {}
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        }
        catch(Exception ex) {}
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
