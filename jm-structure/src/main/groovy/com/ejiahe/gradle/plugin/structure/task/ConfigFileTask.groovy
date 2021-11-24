package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IConfigFileService
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class ConfigFileTask extends AbstractFileTask {
    private IConfigFileService configFileService

    @Inject
    ConfigFileTask(IConfigFileService configFileService) {
        this.configFileService = configFileService
    }

    @TaskAction
    void executor() {
        writeApplication()
        writeKeystore()
        writeLogBack()
    }

    private void writeApplication() {
        def applicationTemplate = configFileService.getApplicationTemplate()
        writeFile(applicationTemplate, StructureConstant.CONFIG_APPLICATION_TEMPLATE, StructureConstant.CONFIG_APPLICATION_TEMPLATE_BACK)
    }

    private void writeKeystore() {
        def keystoreFile = configFileService.getKeystoreTemplate()
        copyFile(keystoreFile, StructureConstant.CONFIG_KEYSTORE)
    }

    private void writeLogBack() {
        def logBackFile = configFileService.getLogBackTemplate()
        def iterator = logBackFile.entrySet().iterator()
        while (iterator.hasNext()) {
            def next = iterator.next()
            def key = next.getKey()
            def value = next.getValue()
            copyFile(value, key)
        }
    }
}
