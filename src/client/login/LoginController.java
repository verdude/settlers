package client.login;

import client.base.Controller;
import client.base.IAction;
import client.misc.IMessageView;
import client.misc.MessageView;
import model.ClientException;
import model.ClientFacade;
import model.ClientModel;

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
			String password = getLoginView().getRegisterPassword();
			String repeatPassword = getLoginView().getRegisterPasswordRepeat();
			String username = getLoginView().getRegisterUsername();
			if (password.equals(repeatPassword) && !password.isEmpty() && password != null && !username.isEmpty() && username != null) {
				success = ClientFacade.getSingleton()
						.userRegister(username, password);
			} else {
				success = false;
			}
		} catch (ClientException e) {
			success = false;
			e.printStackTrace();
		}
		// If log in succeeded
		if (success) {
			try {
				ClientFacade.getSingleton()
					.userLogin(getLoginView().getRegisterUsername(), getLoginView().getRegisterPassword());
			} catch (ClientException e) {
				System.out.println("Failed to login new user");
				e.printStackTrace();
			}
			getLoginView().closeModal();
			loginAction.execute();
		}
	}

	/* (non-Javadoc)
	 * @see client.base.IObserver#notify(model.ClientModel)
	 */
	@Override
	public void notify(ClientModel model) {
		// TODO Auto-generated method stub
		
	}

}

