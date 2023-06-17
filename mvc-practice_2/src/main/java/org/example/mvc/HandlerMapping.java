package org.example.mvc;

import org.example.mvc.controller.Controller;

public interface HandlerMapping {
    Object findHendler(HandlerKey handlerKey);
}
