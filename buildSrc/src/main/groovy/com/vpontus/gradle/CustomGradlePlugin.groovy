package com.vpontus.gradle

import com.vpontus.gradle.action.DependencyManagementPluginAction
import com.vpontus.gradle.action.PluginApplicationAction
import com.vpontus.gradle.dsl.TestPluginExtension
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project

import java.util.function.Consumer

/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 22/07/2022
 */
class CustomGradlePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        createExtension(project)
        registerPluginActions(project)

    }

    private void createExtension(Project project) {
        project.extensions.create('testPlugin', TestPluginExtension)

    }

    private void registerPluginActions(Project project){
        def actions = configureActions()




        for (PluginApplicationAction action : actions) {
            withPluginClassOfAction(action, (pluginClass)->{
                project.getPlugins().apply(pluginClass)
                action.execute(project)
            })
        }
    }

    private List<PluginApplicationAction> configureActions() {
        return List.of(new DependencyManagementPluginAction())
    }




    private void withPluginClassOfAction(PluginApplicationAction action,
                                         Consumer<Class<? extends Plugin<? extends Project>>> consumer) {
        try {
            consumer.accept(action.getPluginClass());
        }
        catch (Throwable ex) {
            // Plugin class unavailable. Continue.
        }
    }
}
