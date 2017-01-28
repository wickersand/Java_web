package br.com.dextraining.web.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FaceUtils {

	public static void redirect(String url) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void createSuccessMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
		saveMessage(facesMessage);
	}

	public static void createFailureMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
		saveMessage(facesMessage);
	}

	public static void createWarningMessage(String message) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
		saveMessage(facesMessage);
	}

	private static void saveMessage(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}
