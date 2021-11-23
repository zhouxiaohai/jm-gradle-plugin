package com.ejiahe.gradle.plugin.structure

import com.ejiahe.gradle.plugin.structure.constant.StructureConstant
import com.ejiahe.gradle.plugin.structure.dsl.*
import com.ejiahe.gradle.plugin.structure.dsl.impl.*
import com.ejiahe.gradle.plugin.structure.task.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class StructurePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        IBinFileService binFileService = new BinFileServiceImpl(project)
        def createBinFileTask = project.tasks.create("structureBinFile", BinFileTask.class, binFileService)
        bandTaskGroup(createBinFileTask)

        IConfigFileService configFileService = new ConfigFileServiceImpl(project)
        def createConfigFileTask = project.tasks.create("structureConfigFile", ConfigFileTask.class, configFileService)
        bandTaskGroup(createConfigFileTask)

        IInstallFileService installFileService = new InstallFileServiceImpl(project)
        def createInstallFileTask = project.tasks.create("structureInstallFile", InstallFileTask.class, installFileService)
        bandTaskGroup(createInstallFileTask)

        def createStaticFrontendTask = project.tasks.create("structureStaticFrontend", StaticFrontendTask.class)
        bandTaskGroup(createStaticFrontendTask)

        IChangeLogFileService changeLogFileService = new ChangeLogFileServiceImpl(project)
        def createChangeLog = project.tasks.create("structureChangeLog", ChangeLogFileTask.class, changeLogFileService)
        bandTaskGroup(createChangeLog)

        IGitIgnoreFileService gitIgnoreFileService = new GitIgnoreFileServiceImpl(project)
        def createGitIgnore = project.tasks.create("structureIgnore", GitIgnoreFileTask.class, gitIgnoreFileService)
        bandTaskGroup(createGitIgnore)

        bandTaskGroup(project.tasks.create("structureGradleProperties", GradlePropertiesFileTask.class))

        IDockerFileService dockerFileService = new DockerFileServiceImpl(project)
        bandTaskGroup(project.tasks.create("structureDocker", DockerFileTask.class, dockerFileService))

        def createAll = project.tasks.create("structureAll").dependsOn(createBinFileTask, createConfigFileTask,
                createInstallFileTask, createStaticFrontendTask, createChangeLog, createGitIgnore)
        bandTaskGroup(createAll)


    }


    private void bandTaskGroup(Task task) {
        task.setGroup(StructureConstant.TASK_GROUP)
    }
}
