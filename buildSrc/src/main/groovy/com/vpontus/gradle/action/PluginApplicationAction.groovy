package com.vpontus.gradle.action

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 22/07/2022
 */
interface PluginApplicationAction extends Action<Project> {

    Class<? extends Plugin<? extends Project>> getPluginClass() throws ClassNotFoundException, NoClassDefFoundError;

}
