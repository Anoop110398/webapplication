package com.example.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HelloPage extends WebPage{
	public HelloPage(final PageParameters parameters) {
		super(parameters);
        add(new Label("message", "Hello, welcome to the application!"));
    }



}
