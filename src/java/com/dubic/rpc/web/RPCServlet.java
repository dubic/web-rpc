/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.rpc.web;

import com.dubic.rpc.data.Metadata;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DUBIC
 */
@WebServlet(name = "RPCServlet", urlPatterns = {"/rpc"})
public class RPCServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Object value = callMethod(request);
            GsonBuilder gb = new GsonBuilder();
            gb.disableHtmlEscaping();
            Gson g = gb.create();
            out.println(g.toJson(value));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RPCServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RPCServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RPCServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(RPCServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(RPCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Object callMethod(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String rpcString = request.getParameter("rpc");
        
        Gson g = new Gson();
        Metadata rpc = g.fromJson(rpcString, Metadata.class);
        System.out.println("classname - "+rpc.getClassName());
        Class<?> serviceClass = Class.forName(rpc.getClassName());
        Method serviceMethod = getMethod(serviceClass,rpc.getMethod(),rpc.getParameters());
        if(serviceMethod == null){
            
        }
        Object service = serviceClass.newInstance();//check exception
        Object[] args = getServiceParameters(serviceMethod.getParameterTypes(),rpc.getParameters());
        Object value = serviceMethod.invoke(service, args);
        return value;
    }
    
    public Method getMethod(Class cls,String methodName,Object[] params){
        Method[] methods = cls.getMethods();
        for (Method m : methods) {
            if(m.getName().equals(methodName)){
                if(m.getParameterTypes().length == params.length){
                    return m;
                }
            }
        }
        return null;
    }

    private Object[] getServiceParameters(Class<?>[] parameterTypes, Object[] parameters) {
        List plist = new ArrayList();
        Gson g = new Gson();
        for (int i = 0; i < parameterTypes.length; i++) {
            String jsonP = g.toJson(parameters[i]);
            Object o = g.fromJson(jsonP, parameterTypes[i]);
            plist.add(o);
        }
        return plist.toArray();
    }
}
