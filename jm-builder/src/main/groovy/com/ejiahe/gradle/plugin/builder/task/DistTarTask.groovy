package com.ejiahe.gradle.plugin.builder.task

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.bundling.Compression
import org.gradle.api.tasks.bundling.Tar

class DistTarTask extends Tar {
    IApplicationTemplateService applicationTemplateService

    @TaskAction
    void executor() {

        def appReleaseName = applicationTemplateService.get().getAppReleaseName()
        println "will package dist: " + appReleaseName

        getArchiveFileName().set(appReleaseName)
        getDestinationDirectory().set(project.file(BuilderConstant.OUT_PUT_RELEASE_DIR))
        setCompression(Compression.GZIP)

        from BuilderConstant.OUT_PUT_TEMP_DIR_PATH

        println "build distTar to: " + BuilderConstant.OUT_PUT_TEMP_DIR_PATH

    }


}
