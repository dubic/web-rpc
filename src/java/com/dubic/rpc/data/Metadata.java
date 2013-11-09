/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.rpc.data;

/**
 *
 * @author DUBIC
 */
public class Metadata {
    private String className;
    private String method;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
    
    
}
