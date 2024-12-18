package com.example.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.User;
import com.example.services.UserService;

@Component
public class LoginPage extends WebPage{
	@SpringBean
    private UserService userService; // Field injection

    private String userid = "";
    private String password = "";

    // Default constructor for Wicket
    public LoginPage(final PageParameters parameters) {
        super(parameters); // Call the superclass constructor

        Form<Void> form = new Form<Void>("loginForm") {
            @Override
            protected void onSubmit() {
                String userid = ((TextField<String>) get("userid")).getModelObject();
                String password = ((PasswordTextField) get("password")).getModelObject();

                User user = userService.findUserByUserid(userid);
                if (user == null) {
                    error("User  not found");
                } else if (!password.equals(user.getPassword())) {
                    error("Invalid password");
                } else {
                    setResponsePage(HelloPage.class);
                }
            }
        };

        form.add(new TextField<>("userid", Model.of(userid)).setRequired(true));
        form.add(new PasswordTextField("password", Model.of(password)).setRequired(true));
        add(form);
    }

}
