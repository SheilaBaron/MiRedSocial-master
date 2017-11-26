package ar.edu.davinci.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import ar.edu.davinci.auth.AuthMb;
import ar.edu.davinci.controller.ImageController;
import ar.edu.davinci.controller.UserController;

import ar.edu.davinci.model.Image;

@Named
public class ProfileMb {
	@Inject
	private AuthMb authMb;

	@Inject
	private UserController userCntr;

	@Inject
	private ImageController imgCntl;

	private Part file;

	public void changePorfilePicture() {
		try {
			Image img = null;
			if (file != null && file.getSize() > 0 && file.getContentType().startsWith("image/")) {
				img = imgCntl.upload(file);
				userCntr.changeProfilePicture(authMb.getUser().getId(),img);
				
			}
			//userCntr.changeProfilePicture(authMb.getUser().getId(),img);

		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error interno", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);

		}

	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
