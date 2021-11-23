package com.ejiahe.gradle.plugin.structure.constant

final class StructureConstant {
    static final String JM_STRUCTURE_BASE_PATH = "http://192.168.76.88:8086/static/build/"

    static final String TASK_GROUP = "jm structure";


    /**
     * BIN 文件夹
     */
    private static final String BIN = "bin" + File.separator
    static final String BIN_TEMPLATE = BIN + "service_bin"
    static final String BIN_FILE = BIN + "{APP_NAME}"
    static final String BIN_BACK_FILE = BIN + "{APP_NAME}_back"

    static final String BIN_SERVICE_TEMPLATE = "bin" + File.separator + "service_bin.service"
    static final String BIN_SERVICE = "bin" + File.separator + "{APP_NAME}.service"
    static final String BIN_BACK_SERVICE = "bin" + File.separator + "{APP_NAME}_back.service"


    /**
     * Config 文件夹
     */
    private static final String CONFIG = "config" + File.separator
    static final String CONFIG_APPLICATION_TEMPLATE = CONFIG + "application_template.properties"
    static final String CONFIG_APPLICATION_TEMPLATE_BACK = CONFIG + "application_template_back.properties"

    static final String CONFIG_KEYSTORE_TEMPLATE = CONFIG + "keystore_template"
    static final String CONFIG_KEYSTORE = CONFIG + "keystore"

    static final String CONFIG_LOGBACK_DOCKER = CONFIG + "logback_docker.xml"
    static final String CONFIG_LOGBACK_TEMPLATE = CONFIG + "logback_template.xml"
    static final String CONFIG_LOGBACK_TEMPLATE_JSON = CONFIG + "logback_template_json.xml"


    /**
     * Install 文件夹
     */
    private static final String INSTALL = "install" + File.separator
    static final String INSTALL_DEPENDENCY_TEMPLATE = INSTALL + "dependency.yml"
    static final String INSTALL_DEPENDENCY_TEMPLATE_BACK = INSTALL + "dependency_back.yml"
    static final String INSTALL_SQL_TEMPLATE = INSTALL + "install.sql"
    static final String INSTALL_META_TEMPLATE = INSTALL + "meta.properties"
    static final String INSTALL_UPDATE_TEMPLATE = INSTALL + "update.sh"


    static final String STATIC_FRONTEND = "static-frontend"
    static final String CHANGELOG_TEMPLATE = "changelog.html"
    static final String IGNORE_TEMPLATE = ".gitignore"

    /**
     * docker
     */
    private static final String DOCKER = "docker" + File.separator
    static final String DOCKER_ENTRYPOINT = DOCKER + "docker-entrypoint.sh"
    static final String DOCKER_FILE = DOCKER + "Dockerfile"
    static final String DOCKER_LOGBACK = DOCKER + "logback.xml"

}
