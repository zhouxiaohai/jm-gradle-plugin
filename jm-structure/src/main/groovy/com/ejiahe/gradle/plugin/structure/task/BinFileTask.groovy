package com.ejiahe.gradle.plugin.structure.task


import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IBinFileService
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class BinFileTask extends AbstractFileTask {
    private final IBinFileService binFileService

    @Inject
    BinFileTask(IBinFileService binFileService) {
        this.binFileService = binFileService

    }

    @TaskAction
    void executor() {
        def binFileReadLines = binFileService.getBinTemplate()
        writeFile(binFileReadLines, replaceAppName(StructureConstant.BIN_FILE), replaceAppName(StructureConstant.BIN_BACK_FILE))

        def binServiceFileReadLines = binFileService.getBinServiceTemplate()
        writeFile(binServiceFileReadLines, replaceAppName(StructureConstant.BIN_SERVICE), replaceAppName(StructureConstant.BIN_BACK_SERVICE))
    }

    private String replaceAppName(String s) {
        def all = s.replaceAll("\\{APP_NAME}", project.name)
        return all
    }
}
