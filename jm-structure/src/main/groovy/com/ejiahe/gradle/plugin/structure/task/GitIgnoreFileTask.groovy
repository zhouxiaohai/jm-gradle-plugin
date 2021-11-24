package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IGitIgnoreFileService
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class GitIgnoreFileTask extends AbstractFileTask {
    private final IGitIgnoreFileService gitIgnoreService

    @Inject
    GitIgnoreFileTask(IGitIgnoreFileService gitIgnoreService) {
        this.gitIgnoreService = gitIgnoreService
    }

    @TaskAction
    void executor() {
        if (!existsFile(StructureConstant.IGNORE_TEMPLATE)) {
            def ignoreServiceTemplate = gitIgnoreService.getIgnoreServiceTemplate()
            copy(ignoreServiceTemplate, StructureConstant.IGNORE_TEMPLATE)
        }

    }
}
