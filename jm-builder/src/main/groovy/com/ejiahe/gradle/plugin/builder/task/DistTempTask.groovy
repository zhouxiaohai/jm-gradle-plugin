package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.file.CopySpec
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.bundling.Compression
import org.gradle.api.tasks.bundling.Tar

class DistTempTask extends Tar {
    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {
        println("------distTempTask------")
        getArchiveFileName().set(applicationTemplateService.get().getAppName() + ".tar.gz")
        getDestinationDirectory().set(project.file(BuilderConstant.OUT_PUT_TEMP_DIR_PATH))
        setCompression(Compression.GZIP)
        from BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH
        String fromDir = BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH + File.separator + project.name + File.separator
        project.copy { CopySpec copySpec ->
            copySpec.from fromDir + BuilderConstant.META
            copySpec.from fromDir + BuilderConstant.DEPENDENCY
            copySpec.from fromDir + BuilderConstant.INSTALL_SHELL
            copySpec.from fromDir + BuilderConstant.CHANGELOG
            copySpec.into BuilderConstant.OUT_PUT_TEMP_DIR_PATH
        }
    }
}
