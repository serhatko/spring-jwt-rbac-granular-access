package com.example.springboot.jwt;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.springboot.jwt.context.StaticContext;

@Component
public class StartupRunner {

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<RUN ONLY ONCE WHEN CONTEXT IS INITIALIZED>>>>>>>>>>>>>>>>>>>>>>>>>>");
		StaticContext.initializeContext();
	}

}
