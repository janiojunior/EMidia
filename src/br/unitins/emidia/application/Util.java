package br.unitins.emidia.application;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import com.sun.faces.component.visit.FullVisitContext;

public class Util {
	
	public static void main(String[] args) {
		System.out.println(Util.hash("janiojunior@gmail.com123"));
		System.out.println(Util.hash("122"));
		System.out.println(Util.hash("135"));
		System.out.println(Util.hash("245"));
		System.out.println(Util.hash("356"));
		System.out.println(Util.hash("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3"));
	}
	
	public static void addErrorMessage(String msg) {
		addMessage(null, FacesMessage.SEVERITY_ERROR, msg);
	}
	
	public static void addErrorMessage(String clientId, String msg) {
		addMessage(clientId, FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void addInfoMessage(String msg) {
		addMessage(null, FacesMessage.SEVERITY_INFO, msg);
	}
	
	public static void addInfoWarnMessage(String msg) {
		addMessage(null, FacesMessage.SEVERITY_WARN, msg);
	}
	
	private static void addMessage(String clientId, Severity severity, String msg) {
		FacesContext.getCurrentInstance().
		addMessage(clientId, new FacesMessage(severity, msg, null));
	}
	
	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance()
				.getExternalContext().redirect(page);
		} catch (IOException e) {
			e.printStackTrace();
			addErrorMessage("Problemas ao redirecionar a página.");
		}
	}
	
	public static UIComponent findComponent(final String id) {
	    FacesContext context = FacesContext.getCurrentInstance(); 
	    UIViewRoot root = context.getViewRoot();
	    final UIComponent[] found = new UIComponent[1];

	    root.visitTree(new FullVisitContext(context), new VisitCallback() {     
	        @Override
	        public VisitResult visit(VisitContext context, UIComponent component) {
	            if (component != null 
	                && component.getId() != null 
	                && component.getId().equals(id)) {
	                found[0] = component;
	                return VisitResult.COMPLETE;
	            }
	            return VisitResult.ACCEPT;              
	        }
	    });

	    return found[0];
	}	
	
	public static String hash(String valor) {
		return DigestUtils.sha256Hex(valor);
	}
}
