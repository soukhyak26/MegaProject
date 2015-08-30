package com.affaince.subscription.command.interceptors;

import org.axonframework.commandhandling.CommandDispatchInterceptor;
import org.axonframework.commandhandling.CommandMessage;

/**
 * Created by mandark on 30-08-2015.
 */
public class CommandLoggingInterceptor implements CommandDispatchInterceptor {
    public CommandLoggingInterceptor(String name){

    }
    public CommandMessage<?> handle(CommandMessage<?> commandMessage){
        return commandMessage;
    }
}
