package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import org.gradle.api.tasks.TaskAction

class StaticFrontendTask extends AbstractFileTask {

    @TaskAction
    void executor() {
        def file = project.file(StructureConstant.STATIC_FRONTEND)
        if (!file.exists()) {
            file.mkdir()
        }
    }
}
