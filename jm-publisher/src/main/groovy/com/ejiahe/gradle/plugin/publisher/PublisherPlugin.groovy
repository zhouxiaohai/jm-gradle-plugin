package com.ejiahe.gradle.plugin.publisher

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenArtifact
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.tasks.bundling.Jar

/**
 * https://www.tabnine.com/code/java/methods/org.gradle.api.publish.maven.MavenPublication/artifact
 * @auther Robin
 * @description Publisher Maven plugin
 */
class PublisherPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def url = System.getenv("maven.publish.url")
        if ("" == url || null == url) {
            url = "http://maven.ejiahe.com:8081/repository/gzb3rd_Part/"
        }
        def username = System.getenv("maven.publish.username")
        if ("" == username || null == username) {
            username = "publisher"
        }
        def password = System.getenv("maven.publish.password")
        if ("" == password || null == password) {
            password = "Jh123456"
        }

        project.getPluginManager().apply(MavenPublishPlugin.class)
        JavaPluginConvention java = project.getConvention().getPlugin(JavaPluginConvention.class)

        project.getExtensions().configure(PublishingExtension.class, new Action<PublishingExtension>() {
            @Override
            void execute(PublishingExtension publishingExtension) {
                Task sourceJar = project.getTasks().create("sourceJar", Jar.class, new Action<Jar>() {
                    @Override
                    void execute(Jar jar) {
                        jar.from(java.getSourceSets().getByName("main").getAllSource())
                    }
                })

                publishingExtension.publications(new Action<PublicationContainer>() {
                    @Override
                    void execute(PublicationContainer publications) {
                        publications.create("maven", MavenPublication.class, new Action<MavenPublication>() {
                            @Override
                            void execute(MavenPublication mavenPublication) {
                                mavenPublication.setGroupId(project.group)
                                mavenPublication.setArtifactId(project.name)
                                mavenPublication.setVersion(project.version)
                                println("groupId="+project.group + "name=" + project.name + "version=" + project.version)
                                mavenPublication.artifact(sourceJar, new Action<MavenArtifact>() {
                                    @Override
                                    void execute(MavenArtifact mavenArtifact) {
                                        mavenArtifact.setClassifier("sources")
                                    }
                                })
                            }
                        })
                    }
                })

                publishingExtension.repositories(new Action<RepositoryHandler>() {
                    @Override
                    void execute(RepositoryHandler artifactRepositories) {
                        artifactRepositories.maven(new Action<MavenArtifactRepository>() {
                            @Override
                            void execute(MavenArtifactRepository mavenArtifactRepository) {
                                mavenArtifactRepository.setUrl(URI.create(url))
                                mavenArtifactRepository.credentials(PasswordCredentials.class, new Action<PasswordCredentials>() {
                                    @Override
                                    void execute(PasswordCredentials t) {
                                        t.setUsername(username)
                                        t.setPassword(password)
                                    }
                                })
                            }
                        })
                    }
                })
            }
        })
    }
}
