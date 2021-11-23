package com.ejiahe.gradle.plugin.builder

import com.ejiahe.gradle.plugin.builder.constant.BuilderConstant
import com.ejiahe.gradle.plugin.builder.dsl.IApplicationTemplateService
import com.ejiahe.gradle.plugin.builder.dsl.impl.ApplicationTemplateServiceImpl
import com.ejiahe.gradle.plugin.builder.task.*
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.file.CopySpec
import org.gradle.api.tasks.bundling.Compression
import org.gradle.api.tasks.bundling.Tar

class BuilderPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        IApplicationTemplateService applicationTemplateService = new ApplicationTemplateServiceImpl(project)

        def clean = project.getTasksByName("clean", false).first()
        def installDist = project.getTasksByName("installDist", false).first()

        def gzbDeleteTask = project.tasks.create("gzbDelete", DeleteTask.class)
        bandTaskGroup(gzbDeleteTask)

        //create updateMetaProperties
        Task updatePropertiesTask = project.tasks.create("updateMetaProperties", UpdateMetaPropertiesTask.class) {
            it.setApplicationTemplateService(applicationTemplateService)
        }
        bandTaskGroup(updatePropertiesTask)

        //create gzbArtifact
        def gzbArtifactTask = project.tasks.create("gzbArtifact", ArtifactTask.class) {
            it.setApplicationTemplateService(applicationTemplateService)
        }
        gzbArtifactTask.dependsOn(clean, installDist, updatePropertiesTask, gzbDeleteTask)
        bandTaskGroup(gzbArtifactTask)

        //create gzbDistTemp
        def gzbDistTempTask = project.tasks.create("gzbDistTemp", Tar.class) {
            def appName = applicationTemplateService.get().getAppName()
            it.getArchiveFileName().set(appName + ".tar.gz")
            it.getDestinationDirectory().set(project.file(BuilderConstant.OUT_PUT_TEMP_DIR_PATH))
            it.setCompression(Compression.GZIP)
            it.from BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH
            it.doLast {
                String fromDir = BuilderConstant.OUT_PUT_ARTIFACT_DIR_PATH + File.separator + appName + File.separator
                project.copy { CopySpec copySpec ->
                    copySpec.from fromDir + BuilderConstant.META
                    copySpec.from fromDir + BuilderConstant.DEPENDENCY
                    copySpec.from fromDir + BuilderConstant.INSTALL_SHELL
                    copySpec.from fromDir + BuilderConstant.CHANGELOG
                    copySpec.into BuilderConstant.OUT_PUT_TEMP_DIR_PATH
                }
            }
        }
        gzbDistTempTask.dependsOn(gzbArtifactTask)
        bandTaskGroup(gzbDistTempTask)

        //create gzbDistTar
        def gzbDistTarTask = project.tasks.create("gzbDistTar", Tar.class) {
            def appReleaseName = applicationTemplateService.get().getAppReleaseName()
            println "will package dist: " + appReleaseName

            it.getArchiveFileName().set(appReleaseName)
            it.getDestinationDirectory().set(project.file(BuilderConstant.OUT_PUT_RELEASE_DIR))
            it.setCompression(Compression.GZIP)
            it.from BuilderConstant.OUT_PUT_TEMP_DIR_PATH

            it.doLast {
                println "build distTar to: " + BuilderConstant.OUT_PUT_TEMP_DIR_PATH
            }
        }
        gzbDistTarTask.dependsOn(gzbDistTempTask)
        bandTaskGroup(gzbDistTarTask)


        //create publish2FTP
        def publish2FTPTask = project.tasks.create("publish2FTP", Publish2FTPTask.class) {
            it.setApplicationTemplateService(applicationTemplateService)
        }
        bandTaskGroup(publish2FTPTask)

        //create gzbDockerImage
        def gzbDockerImageTask = project.tasks.create("gzbDockerImage", DockerImage.class) {
            it.setApplicationTemplateService(applicationTemplateService)
        }
        gzbDockerImageTask.dependsOn(gzbDistTempTask, gzbDeleteTask)
        bandTaskGroup(gzbDockerImageTask)

        //create gzbDockerImagePush
        def gzbDockerImagePushTask = project.tasks.create("gzbDockerImagePush", DockerImagePush.class) {
            it.setApplicationTemplateService(applicationTemplateService)
        }
        gzbDockerImagePushTask.dependsOn(gzbDockerImageTask)
        bandTaskGroup(gzbDockerImagePushTask)

    }

    private void bandTaskGroup(Task task) {
        task.setGroup(BuilderConstant.TASK_GROUP)
    }

}
