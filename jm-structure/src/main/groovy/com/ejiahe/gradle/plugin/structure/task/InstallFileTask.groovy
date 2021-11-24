package com.ejiahe.gradle.plugin.structure.task

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.IInstallFileService
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class InstallFileTask extends AbstractFileTask {
    private final IInstallFileService installFileService

    @Inject
    InstallFileTask(IInstallFileService installFileService) {
        this.installFileService = installFileService
    }

    @TaskAction
    void executor() {
        writeDependency()
        writeInstallSql()
        writeMeta()
        writeUpdate()
    }

    void writeDependency() {
        def dependencyTemplate = installFileService.getDependencyTemplate()
        writeFile(dependencyTemplate, StructureConstant.INSTALL_DEPENDENCY_TEMPLATE, StructureConstant.INSTALL_DEPENDENCY_TEMPLATE_BACK)
    }

    void writeInstallSql() {
        def sqlTemplate = installFileService.getInstallSqlTemplate()
        writeFile(sqlTemplate, StructureConstant.INSTALL_SQL_TEMPLATE)
    }

    void writeMeta() {
        def metaPropertiesTemplate = installFileService.getMetaPropertiesTemplate()
        writeFile(metaPropertiesTemplate, StructureConstant.INSTALL_META_TEMPLATE)
    }

    void writeUpdate() {
        def updateTemplate = installFileService.getUpdateTemplate()
        writeFile(updateTemplate, StructureConstant.INSTALL_UPDATE_TEMPLATE)
    }
}
