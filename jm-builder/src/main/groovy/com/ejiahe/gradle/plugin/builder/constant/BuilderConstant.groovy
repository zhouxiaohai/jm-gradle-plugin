package com.ejiahe.gradle.plugin.builder.constant

final class BuilderConstant {
    static final String TASK_GROUP = "jm dist"

    static final String META = "install" + File.separator + "meta.properties"

    static final String DEPENDENCY = "install" + File.separator + "dependency.yml"

    static final String CHANGELOG = "changelog.html"

    static final String README = "README.md"

    static final String INSTALL_SHELL = "install" + File.separator + "update.sh"

    static final String OUT_PUT_ARTIFACT_DIR_PATH = "out" + File.separator + "artifacts"

    static final String OUT_PUT_RELEASE_DIR = "out" + File.separator + "dist"

    static final String OUT_PUT_TEMP_DIR_PATH = "out" + File.separator + "tmp"

    static final String FTP_DIR = File.separator + "home" + File.separator + "ftp" + File.separator

    static final String CONFIG_APPLICATION_TEMPLATE = "config" + File.separator + "application_template.properties"

    static final String DOCKER_IMAGE_TAG = "registry.mygzb.com" + File.separator + "gzb" + File.separator

    static final String DOCKER_OUTPUT_DIR = "out" + File.separator + "docker"

    static final String DOCKER_ENTRYPOINT = "docker" + File.separator + "docker-entrypoint.sh"

    static final String DOCKER_File = "docker" + File.separator + "Dockerfile"

    static final String DOCKER_LOGBACK = "docker" + File.separator + "logback.xml"
}
