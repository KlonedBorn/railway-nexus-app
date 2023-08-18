package com.kloneborn.component;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ComponentFactory {
    public static < R extends Parent, C> Component<R,C> attach(URL loc, R root, C controller){
        Component<R,C> component = new Component<R,C>(loc, root, controller);
        component.bind();
        return component;
    }
    public static class Component<Root extends Parent,Controller>{
        private FXMLLoader loader;

        public Component(URL location, Root root, Controller controller) {
            this.loader = new FXMLLoader(location);
            this.loader.setRoot(root);
            this.loader.setController(controller);
        }

        public void bind(){
            try {
                this.loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
