package com.vpontus.gradle


import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import java.util.stream.Collectors

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.junit.jupiter.api.Assertions.*
/**
 * @author Volodymyr Pontus <v.pontus@gmail.com> 30/07/2022
 */
class CustomGradlePluginTest extends AbstractPluginTest {


    @Test
    @DisplayName("simple Build task should pass")
    void testBuild() {
        def result = gradle('build')
        assertEquals(result.task(":build").outcome, SUCCESS)
    }


    @Test
    @DisplayName("DisplayVersion gradle task should pass")
    void testVersionDisplay() {
        def result = gradle('versionDisplay')
        Assertions.assertEquals(result.task(':versionDisplay').outcome, SUCCESS)
    }

    @Test
    void testScmIsDefined() {



//        Arrange
        buildFile.append """
import net.nemerosa.versioning.VersionInfo

def stub = new VersionInfo()
stub.scm = 'git'
stub.display = 'dummy'
stub.commit = '0000'
versioning.metaClass.getInfo = {->stub}

jar {
    manifest {
        attributes(
                'SCM': project.versioning.scm,
                'Display': project.versioning.info.display
        )
    }
}
"""
//        Act
        def result = gradle('jar')
//        Assert
        assertEquals(result.task(':jar').outcome, SUCCESS)
        File manifest = testProjectDir.toPath().resolve('build/tmp/jar/MANIFEST.MF').toFile()
        assertTrue(manifest.exists())

        def lines = manifest.readLines()
        def mapLines = lines
                .stream()
                .filter(l->!l.isEmpty())
                .map(l->l.split(":"))
                .collect(Collectors.toMap(ar->ar[0].trim(),ar->ar[1].trim()))
        assertTrue(mapLines.keySet().size()>0)
        assertNotNull(mapLines.get('Display'))
        assertFalse(mapLines.get('Display').toString().isEmpty())

    }



}
