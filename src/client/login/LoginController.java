package client.login;

import client.base.Controller;
import client.base.IAction;
import client.misc.IMessageView;
import client.misc.MessageView;
import model.ClientException;
import model.ClientFacade;

//import java.net.*;
//import java.io.*;
//import java.util.*;
//import java.lang.reflect.*;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// TODO: log in user
		
		boolean success;
		try {
			success = ClientFacade.getSingleton()
					.userLogin(getLoginView().getLoginUsername(), getLoginView().getLoginPassword());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			success = false;
			e.printStackTrace();
		}

		getLoginView().closeModal();
		loginAction.execute();
		// If log in succeeded
		if (!success) {
			IMessageView error = new MessageView();
			error.setTitle("Error!");
			error.setMessage("Login failed, bad password or username.");
			getLoginView().showModal();
			error.showModal();
		}
	}

	@Override
	public void register() {
		
		boolean success;
		try {
			if (getLoginView().getRegisterPassword().equals(getLoginView().getRegisterPasswordRepeat())) {
				success = ClientFacade.getSingleton()
						.userRegister(getLoginView().getRegisterUsername(), getLoginView().getRegisterPassword());
			} else {
				success = false;
			}
		} catch (ClientException e) {
			success = false;
			e.printStackTrace();
		}
		// If log in succeeded
		if (success) {
			getLoginView().closeModal();
			loginAction.execute();
		}
	}

}

