package com.henrikstabell.gistgradle;

import com.henrikstabell.gistgradle.tasks.CreateGistTask;
import com.henrikstabell.gistgradle.tasks.EditGistTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="https://opensource.org/licenses/EUPL-1.1">License</a> for what you can and can't do with the code.
 * Created by Hennamann(Ole Henrik Stabell) on 19/03/2018.
 */
public class GistGradlePlugin implements Plugin<Project> {

    public static Logger slf4jLogger = LoggerFactory.getLogger("GistGradle");

        @Override
        public void apply(Project project) throws NullPointerException {
            project.getTasks().create("editGist", EditGistTask.class, (task) -> {
                task.setGitUser(null);
                task.setGitPass(null);
                task.setGistID(null);
                task.setGistContent(null);
                task.setGistFileName(null);
            });
            project.getTasks().create("createGist", CreateGistTask.class, (task) -> {
                task.setGitUser(null);
                task.setGitPass(null);
                task.setGistContent(null);
                task.setGistFileName(null);
                task.setGistDescription("Gist file Generated by the GistGradle plugin for Gradle");
                task.setGistStatus(false);
            });
        }
}