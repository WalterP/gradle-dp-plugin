package com.vpontus.gradle.action

import com.vpontus.gradle.action.config.DependencyConfig
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory
/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 22/07/2022
 */
class DependencyManagementPluginAction implements PluginApplicationAction {

    private static final Logger log = LoggerFactory.getLogger(DependencyManagementPluginAction.class)

    @Override
    Class<? extends Plugin<? extends Project>> getPluginClass() throws ClassNotFoundException, NoClassDefFoundError {
        return DependencyManagementPlugin.class
    }

    @Override
    void execute(Project project) {

        log.info("Dependency Management Plugin Action. Executing... ")
        def config = new DependencyConfig()

        def ext = getExt(project)

        ext.dependencies(config.dependencyConfig)

    }

    private DependencyManagementExtension getExt(Project project) {
        return project.getExtensions().findByType(DependencyManagementExtension.class)
    }
}
