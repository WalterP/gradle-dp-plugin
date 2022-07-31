package com.vpontus.gradle.action

import net.nemerosa.versioning.VersioningPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 30/07/2022
 */
class VersioningPluginAction implements PluginApplicationAction{

    private static final Logger log = LoggerFactory.getLogger(VersioningPluginAction.class)

    @Override
    Class<? extends Plugin<? extends Project>> getPluginClass() throws ClassNotFoundException, NoClassDefFoundError {
        return VersioningPlugin.class
    }

    @Override
    void execute(Project project) {
        log.info("Versioning Plugin applied")
    }
}
