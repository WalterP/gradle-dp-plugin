package com.vpontus.gradle


import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 30/07/2022
 */
abstract class AbstractPluginTest {

    @TempDir
    protected File testProjectDir
    protected File settingsFile
    protected File buildFile







    @BeforeEach
    void setup(){
        settingsFile = new File(testProjectDir, "settings.gradle")
        settingsFile.createNewFile()
        buildFile = new File(testProjectDir, 'build.gradle')
        buildFile.createNewFile()

        buildFile.write """
plugins {
    id 'com.vpontus.gradle.custom-plugin'
    id 'java'
}
"""

    }


    BuildResult gradle(boolean expectFail, String... arguments = ['tasks']){
         arguments += '--stacktrace'

        def runner = GradleRunner.create()
                .withPluginClasspath()
                .withProjectDir(testProjectDir)
                .withDebug(true)
                .withArguments(arguments)



        return expectFail ? runner.buildAndFail() : runner.build()
     }

    BuildResult gradle(String... arguments = ['tasks']) {
        return  gradle(false, arguments)
    }



    @Test
    @DisplayName("test project settings file should exists")
    void testSettingsFileExists() {
        Assertions.assertTrue(settingsFile.exists())
    }

    @Test
    @DisplayName("test project build file should exists")
    void testBuildFileExists() {
        Assertions.assertTrue(buildFile.exists())
    }


}
