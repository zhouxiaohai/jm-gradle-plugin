package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IChangeLogFileService
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class ChangeLogFileTask extends AbstractFileTask {
    private final IChangeLogFileService changeLogFileService

    @Inject
    ChangeLogFileTask(IChangeLogFileService changeLogFileService) {
        this.changeLogFileService = changeLogFileService
    }

    @TaskAction
    void executor() {
        if (!existsFile(StructureConstant.CHANGELOG_TEMPLATE)) {
            def changeLogTemplate = changeLogFileService.getChangeLogTemplate()
            copy(changeLogTemplate, StructureConstant.CHANGELOG_TEMPLATE)
        }
    }
}
